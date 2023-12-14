package com.hackathon.growthgenie.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.growthgenie.dto.*;
import com.hackathon.growthgenie.model.*;
import com.hackathon.growthgenie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.RouterFunctions;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PopularInvestmentService {
    @Autowired
    private InvestmentAccountRepository investmentAccountRepository;
    @Autowired
    private StocksRepository stocksRepository;
    @Autowired
    private MutualFundsRepository mutualFundsRepository;
    @Autowired
    private FixedDepositsRepository fixedDepositsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LoanRepository loanRepository;


    public Map<String, List<Map<String, Object>>> getTopInvestments(int n) {
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        map.put("Stocks", getTopStocks(n));
        map.put("Mutual Funds", getTopMutualFunds(n));
        map.put("Fixed Deposits", getTopFixedDeposits(n));
        return map;
    }

    public StocksDto getStockDto(List<Stocks> stocks) {
        Collections.sort(stocks, (a, b) -> a.getPurchaseDate().compareTo(b.getPurchaseDate()));
        double a = stocks.get(0).getPurchasePrice();
        double b = stocks.get(stocks.size() - 1).getPurchasePrice();
        double r = ((b - a) / a) * 100;
        Stocks s = stocks.get(0);
        return StocksDto.builder().stockName(s.getStockName()).stockExchange(s.getStockExchange()).stockSymbol(s.getStockSymbol()).returns(r).build();
    }

    public List<Map<String, Object>> getTopMutualFunds(int n) {
        Pageable pageable = Pageable.ofSize(n);
        Page<MutualFunds> fundsPage = mutualFundsRepository.findByOrderByInvestmentAmountDesc(pageable);
        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (MutualFunds dto : fundsPage.getContent()) {
            result.add(mapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
            }));
        }
        return result;
    }

    public List<Map<String, Object>> getTopFixedDeposits(int n) {
        Pageable pageable = Pageable.ofSize(n);
        Page<FixedDeposits> fundsPage = fixedDepositsRepository.findByOrderByInterestRateDesc(pageable);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> result = new ArrayList<>();
        for (FixedDeposits dto : fundsPage.getContent()) {
            result.add(mapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
            }));
        }
        return result;
    }

    public List<Map<String, Object>> getTopStocks(int n) {
        Pageable pageable = Pageable.ofSize(n);
        Map<String, List<TopPerformingInvestmentsDTO>> map = new HashMap<>();
        List<Stocks> stocks = stocksRepository.findAll();
        Map<String, Map<String, Map<String, List<Stocks>>>> collect = stocks.stream().collect(Collectors.groupingBy(Stocks::getInvestmentAccountID, Collectors.groupingBy(Stocks::getStockExchange, Collectors.groupingBy(Stocks::getStockSymbol))));

        Map<String, StocksDto> topStocks = new HashMap<>();
        for (Map.Entry<String, Map<String, Map<String, List<Stocks>>>> entry : collect.entrySet()) {
            Map<String, Map<String, List<Stocks>>> stockExchange = entry.getValue();
            Map<String, Map<String, List<Stocks>>> newStockExchange = new HashMap<>();
            newStockExchange.put("BSE", new HashMap<String, List<Stocks>>());
            newStockExchange.put("NSE", new HashMap<String, List<Stocks>>());

            Map<String, List<Stocks>> bseStocks = stockExchange.getOrDefault("BSE", new HashMap<>());
            Map<String, List<Stocks>> nseStocks = stockExchange.getOrDefault("NSE", new HashMap<>());
            Map<String, StocksDto> effBseStocks = new HashMap<>();
            Map<String, StocksDto> effNseStocks = new HashMap<>();
            bseStocks.forEach((k, v) -> {
                if (v.size() > 1) effBseStocks.put(k, getStockDto(v));
            });
            nseStocks.forEach((k, v) -> {
                if (v.size() > 1) effNseStocks.put(k, getStockDto(v));
            });
            for (Map.Entry<String, StocksDto> en : effNseStocks.entrySet()) {
                String key = en.getKey();
                if (topStocks.containsKey(key)) {
                    if (topStocks.get(key).getReturns() < en.getValue().getReturns()) topStocks.put(key, en.getValue());
                } else topStocks.put(key, en.getValue());
            }
            for (Map.Entry<String, StocksDto> en : effBseStocks.entrySet()) {
                String key = en.getKey();
                if (topStocks.containsKey(key)) {
                    if (topStocks.get(key).getReturns() < en.getValue().getReturns()) topStocks.put(key, en.getValue());
                } else topStocks.put(key, en.getValue());
            }
        }
        Queue<StocksDto> dtos = new PriorityQueue<>((a, b) -> Double.compare(a.getReturns(), b.getReturns()));
        List<StocksDto> stocksDtos = new ArrayList<>(topStocks.values());
        int i = 0;
        for (i = 0; i < stocksDtos.size(); i++) {
            if (dtos.size() < n) dtos.add(stocksDtos.get(i));
        }
        for (; i < stocksDtos.size(); i++) {
            if (dtos.peek().getReturns() < stocksDtos.get(i).getReturns()) {
                dtos.poll();
                dtos.add(stocksDtos.get(i));
            }
        }
        ArrayList<StocksDto> list = new ArrayList<>(dtos);
        Collections.reverse(list);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> result = new ArrayList<>();
        for (StocksDto dto : list) {
            result.add(mapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
            }));
        }
        return result;
    }

    public List<TopInvestorsDto> topInvestors(int n) {
        Pageable pageable = Pageable.ofSize(n);
        List<TopInvestorsDto> dtos = new LinkedList<>();
        List<InvestmentAccounts> content = investmentAccountRepository.findAllByOrderByInvestmentPortfolioDesc(pageable).getContent();
        Set<Integer> ids = content.stream().map(InvestmentAccounts::getCustomerID).map(Integer::valueOf).collect(Collectors.toSet());
        Map<Integer, Customer> customerMap = customerRepository.findAllByCustomerIdIn(ids).stream().collect(Collectors.toMap(Customer::getCustomerId, Function.identity()));
        content.forEach(el -> dtos.add(TopInvestorsDto.builder().name(customerMap.get(el.getCustomerID()).getFirstName() + " " + customerMap.get(el.getCustomerID()).getLastName()).investmentType(el.getAccountType()).portfolio(el.getInvestmentPortfolio()).returns(el.getReturns()).build()));
        return dtos;
    }

    public List<TopCustomerDTO> getHistoricalReturns(int customerId) {
      List<InvestmentAccounts> investmentDetails = investmentAccountRepository.findByCustomerID(customerId);
      Instant now = Instant.now(); //current date
      Customer customer = customerRepository.findById(customerId).get();
      List<TopCustomerDTO> sixMonthReturnData = new ArrayList<>();
      for(int i=1; i<=6; i++) {
        Instant before = now.minus(Duration.ofDays((i*30)+180));
        Date dateBefore = Date.from(before);
        double returns = 0.0d;
        for(InvestmentAccounts investments : investmentDetails) {
          if(dateBefore.after(investments.getInvestmentStartDate()) && dateBefore.before(investments.getInvestmentEndDate())) {
            returns += investments.getReturns();
          }
        }
        sixMonthReturnData.add(new TopCustomerDTO(customerId, customer.getFirstName() + " "+customer.getLastName(), dateBefore, returns));
        
      }
      
      return sixMonthReturnData;
    }

    public TaxCalculationsDTO calculateInvestmentTax(int customerId) {
      List<InvestmentAccounts> investmentDetails = investmentAccountRepository.findByCustomerID(customerId);
      Instant now = Instant.now(); //current date
      Instant before = now.minus(Duration.ofDays(365));
      Date dateBefore = Date.from(before);
      Customer customer = customerRepository.findById(customerId).get();
      Map<String, Double> taxTypeReturn = new HashMap<>();
      double totalTax = 0.0d;
      for(InvestmentAccounts investments : investmentDetails) {
        if(investments.getInvestmentEndDate().after(dateBefore)) {
          String accountType = investments.getAccountType();
          double taxSlab = getTaxSlab(investments);
          double taxValue = investments.getInvestmentPortfolio()* (investments.getReturns()/100);
          if((accountType.equalsIgnoreCase("Stocks") || accountType.equalsIgnoreCase("Mutual Funds")) && taxSlab <= 10.1) {
            if(taxValue > 100000) {
              taxValue = taxValue - 100000;
              taxValue = taxValue * taxSlab/100;
            } else {
              taxValue = 0.0d;
            }
          } else {
            taxValue = taxValue * taxSlab/100;
          }
          totalTax += taxValue;
          if(taxTypeReturn.containsKey(accountType)) {
            taxTypeReturn.put(accountType, taxTypeReturn.get(accountType) + taxValue);
          } else {
            taxTypeReturn.put(accountType, taxValue);
          }
        }
      }  
      TaxCalculationsDTO taxCalculationsDTO = new TaxCalculationsDTO(customerId, customer.getFirstName() + " "+customer.getLastName(), taxTypeReturn, totalTax);
      return taxCalculationsDTO;
    }

    private double getTaxSlab(InvestmentAccounts investments) {
      if(investments.getAccountType().equalsIgnoreCase("Stocks") || investments.getAccountType().equalsIgnoreCase("Mutual Funds")) {
        long diff = investments.getInvestmentEndDate().getTime() - investments.getInvestmentStartDate().getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(days > 365) {
          return 10;
        } else {
          return 15;
        }
      }
      return 10;
    }

    public  Map<String,Map<String,Long>>  popularInvestments(){
        Map<String,Map<String,Long>> popularInvestments=new HashMap<>();

        Map<String, Long> popularMutualFunds = mutualFundsRepository.findAll().stream()
                .collect(Collectors.groupingBy(MutualFunds::getFundName, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)  // Limit to the top 10 entries
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String,Long> popularStocks= stocksRepository.findAll().stream()
                .collect(Collectors.groupingBy(Stocks::getStockName,Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)  // Limit to the top 10 entries
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, Long> popularFixedDeposits = fixedDepositsRepository.findAll().stream()
                .collect(Collectors.groupingBy(FixedDeposits::getInterestPaymentFrequency, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)  // Limit to the top 10 entries
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));



        popularInvestments.put("Mutual Funds",popularMutualFunds);
        popularInvestments.put("Stocks",popularStocks);
        popularInvestments.put("Fixed Deposits",popularFixedDeposits);

        return popularInvestments;
    }

    public Map<String,Double> investmentByCustomerId(int customerId) {
        List<InvestmentAccounts> investmentAccounts=investmentAccountRepository.findByCustomerID(customerId);

        Map<String,Double> investmentBycustomer=new HashMap<>();
        for(InvestmentAccounts investmentAccount:investmentAccounts){
            if(investmentAccount.getAccountType().equalsIgnoreCase("Mutual Funds")){
                investmentBycustomer.put("Mutual Funds",getSumOfMutualFundsByInvestmentAccountID(investmentAccount.getInvestmentAccountID()));
            }else if(investmentAccount.getAccountType().equalsIgnoreCase("Stocks")){
                investmentBycustomer.put("Stocks",getSumOfStocksByInvestmentId(investmentAccount.getInvestmentAccountID()));
            }else if(investmentAccount.getAccountType().equalsIgnoreCase("Fixed Deposits")){
                investmentBycustomer.put("Fixed Deposits",getSumOfFixedDepositsByInvestmentAccountID(investmentAccount.getInvestmentAccountID()));
            }
        }
        investmentBycustomer.put("Loans", getLoanAmountByCustomerId(customerId));
        return investmentBycustomer;
    }

    private Double getSumOfStocksByInvestmentId(String investmentAccountID) {
        List<Stocks> stocks=stocksRepository.findByInvestmentAccountID(investmentAccountID);
        double stockAmount=0;
        for(Stocks stock:stocks){
            stockAmount+= stock.getQuantity()*stock.getPurchasePrice();
        }
        return  stockAmount;
    }

    private Double getSumOfFixedDepositsByInvestmentAccountID(String investmentAccountID) {
        List<FixedDeposits> fixedDeposits=fixedDepositsRepository.findByInvestmentAccountId(investmentAccountID);
        double fixedDepositAmount=0;
        for (FixedDeposits fixedDeposit:fixedDeposits){
            fixedDepositAmount+=fixedDeposit.getPrincipalAmount();
        }
        return fixedDepositAmount;
    }

    private Double getSumOfMutualFundsByInvestmentAccountID(String investmentAccountID) {
        List<MutualFunds> mutualFunds=mutualFundsRepository.findByInvestmentAccountId(investmentAccountID);
        double mutualFundsAmount=0;
        for(MutualFunds mutualFund:mutualFunds){
            mutualFundsAmount+=mutualFund.getInvestmentAmount();
        }
        return mutualFundsAmount;
    }

    private double getLoanAmountByCustomerId(int customerId) {
        List<Loan> loans = getLoansByCustomerId(customerId);
        double loanAmount = 0;
        for (Loan loan : loans) {
            if (loan.getLoanStatus().equalsIgnoreCase("Approved")) {
                loanAmount += (double) loan.getLoanAmount();
            }
        }
        return loanAmount;
    }
        public InvestmentDetailsDTO getDetailedInvestmentByCustomerId ( int customerId){
            InvestmentDetailsDTO investmentDetailsDTO = new InvestmentDetailsDTO();
            Customer customer = customerRepository.findById(customerId).get();
            investmentDetailsDTO.setCustomerId(customerId);
            investmentDetailsDTO.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
            Map<String, List<Stocks>> stocksDetails = new HashMap<>();
            Map<String, List<MutualFunds>> mutualFundDetails = new HashMap<>();
            Map<String, List<FixedDeposits>> fixedDepositsDetails = new HashMap<>();
            List<InvestmentAccounts> investmentAccounts = investmentAccountRepository.findByCustomerID(customerId);
            for (InvestmentAccounts investmentAccount : investmentAccounts) {
                String investmentAccountId = investmentAccount.getInvestmentAccountID();
                List<Stocks> stocksByAccountId = getStockInvestmentByAccountId(investmentAccountId);
                stocksDetails.put(investmentAccountId, stocksByAccountId);
                List<MutualFunds> mutualFundByAccountId = getMutualFundInvestmentByAccountId(investmentAccountId);
                mutualFundDetails.put(investmentAccountId, mutualFundByAccountId);
                List<FixedDeposits> fixedDepositsByAccountId = getFixedDepositsByAccountId(investmentAccountId);
                fixedDepositsDetails.put(investmentAccountId, fixedDepositsByAccountId);
            }
            List<Loan> loans = getLoansByCustomerId(customerId);
            Map<String, List<Loan>> loansDetails = loans.stream().collect(Collectors.groupingBy(loan -> loan.getLoanStatus()));
            investmentDetailsDTO.setMutualFundDetails(mutualFundDetails);
            investmentDetailsDTO.setFixedDepositsDetails(fixedDepositsDetails);
            investmentDetailsDTO.setStocksDetails(stocksDetails);
            investmentDetailsDTO.setLoansDetails(loansDetails);
            return investmentDetailsDTO;
        }

        private List<Loan> getLoansByCustomerId ( int customerId){
            return loanRepository.findByCustomerId(customerId);
        }

        private List<FixedDeposits> getFixedDepositsByAccountId (String investmentAccountId){
            return fixedDepositsRepository.findByInvestmentAccountId(investmentAccountId);
        }

        private List<MutualFunds> getMutualFundInvestmentByAccountId (String investmentAccountId){
            return mutualFundsRepository.findByInvestmentAccountId(investmentAccountId);
        }

        private List<Stocks> getStockInvestmentByAccountId (String investmentAccountID){
            return stocksRepository.findByInvestmentAccountID(investmentAccountID);
        }
    }

