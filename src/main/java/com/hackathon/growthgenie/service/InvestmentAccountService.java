package com.hackathon.growthgenie.service;

import com.hackathon.growthgenie.model.AccountType;
import com.hackathon.growthgenie.model.InvestmentAccounts;
import com.hackathon.growthgenie.repository.InvestmentAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvestmentAccountService {

    private static final Logger logger = LoggerFactory.getLogger(InvestmentAccountService.class);
    @Autowired
    InvestmentAccountRepository investmentAccountRepository;

    public Map<String, Double> getReturns(int customerId) {
        logger.info("Inside returns with customerId " + customerId);
        List<InvestmentAccounts> investmentAccounts = investmentAccountRepository.findByCustomerID(customerId);
        logger.info("investmentsAccounts " + investmentAccounts);
        Map<String, Double> investMentReturns = new HashMap<>();
        double stockReturns=0,fdReturns=0,mfReturns=0;
        int stocksCounter=0,mfCounter=0,fdCounter=0;
        for (InvestmentAccounts investmentAccount : investmentAccounts) {
            logger.info("investmentAccount " + investmentAccount);
            if (investmentAccount.getAccountType().equalsIgnoreCase("Mutual Funds")) {
                mfCounter+=1;
                mfReturns+=investmentAccount.getReturns();
                investMentReturns.put("Mutual Funds", mfReturns/mfCounter );
            } else if (investmentAccount.getAccountType().equalsIgnoreCase("Fixed Deposits")) {
                fdCounter+=1;
                fdReturns+=investmentAccount.getReturns();
                investMentReturns.put("Fixed Deposits", fdReturns/fdCounter );
            } else if (investmentAccount.getAccountType().equalsIgnoreCase("Stocks")) {
                stocksCounter+=1;
                stockReturns+= investmentAccount.getReturns();
                investMentReturns.put("Stocks", stockReturns/stocksCounter);
            }

            logger.info("returning " + investMentReturns);

        }
        return investMentReturns;
    }
}
