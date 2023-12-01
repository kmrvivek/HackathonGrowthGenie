package com.hackathon.growthgenie.controller;

import com.hackathon.growthgenie.dto.CommonDTO;
import com.hackathon.growthgenie.dto.TaxCalculationsDTO;
import com.hackathon.growthgenie.dto.TopCustomerDTO;
import com.hackathon.growthgenie.model.Account;
import com.hackathon.growthgenie.model.Transaction;
import com.hackathon.growthgenie.service.AccountService;
import com.hackathon.growthgenie.service.PopularInvestmentService;
import com.hackathon.growthgenie.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Autowired
    PopularInvestmentService investmentService;

    @GetMapping("{id}")
    public CommonDTO allResponse(@PathVariable int id){
        List<Account> accounts=accountService.getAccountByCustomerId(id);
        List<List<Transaction>> transactions= transactionService.getTransactionByCustomerById(id);
        Map<String,Double> investmentByCustomerId=investmentService.investmentByCustomerId(id);
        TaxCalculationsDTO taxCalculationsDTO=investmentService.calculateInvestmentTax(id);
        List<TopCustomerDTO> topCustomerDTOList=investmentService.getHistoricalReturns(id);
        return CommonDTO.builder()
                .accounts(accounts)
                .transactions(transactions)
                .investmentByCustomerId(investmentByCustomerId)
                .taxCalculationsDTO(taxCalculationsDTO)
                .topCustomerDTOList(topCustomerDTOList)
                .build();
    }


}
