package com.hackathon.growthgenie.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.growthgenie.dto.StocksDto;
import com.hackathon.growthgenie.dto.TopInvestorsDto;
import com.hackathon.growthgenie.dto.TopPerformingInvestmentsDTO;
import com.hackathon.growthgenie.model.*;
import com.hackathon.growthgenie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
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

    public Map<String, List<Map<String, Object>>> getTopInvestments(int n) {
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        map.put("Stocks", getTopStocks(n));
        map.put("Mutual Funds", getTopMutualFunds(n));
        map.put("Fixed Deposits", getTopMutualFunds(n));
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
}