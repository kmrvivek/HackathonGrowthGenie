package com.hackathon.growthgenie.controller;

import com.hackathon.growthgenie.service.InvestmentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/investmentAccount")
public class InvestmentAccountController {
    @Autowired
    InvestmentAccountService investmentAccountService;

    @GetMapping("/roi/{customerId}")
    public ResponseEntity<Map<String,Double>> getReturns(@PathVariable int customerId){
       return new ResponseEntity<>(investmentAccountService.getReturns(customerId),new HttpHeaders(),HttpStatus.OK);
    }

}
