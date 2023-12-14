package com.hackathon.growthgenie.controller;

import com.hackathon.growthgenie.dto.TaxCalculationsDTO;
import com.hackathon.growthgenie.dto.TopCustomerDTO;
import com.hackathon.growthgenie.service.InvestmentAccountService;
import com.hackathon.growthgenie.service.PopularInvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/investmentAccount")
public class InvestmentAccountController {
    @Autowired
    InvestmentAccountService investmentAccountService;
    @Autowired
    private PopularInvestmentService investmentService;

    @GetMapping("/roi/{customerId}")
    public ResponseEntity<Map<String, Double>> getReturns(@PathVariable int customerId) {
        return new ResponseEntity<>(investmentAccountService.getReturns(customerId), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/roi/topinvestments/{type}/{n}")
    public ResponseEntity<?> getTopInvestments(@PathVariable String type, @PathVariable int n) {
        if (type.equals("Stocks"))
            return new ResponseEntity<>(investmentService.getTopStocks(n), new HttpHeaders(), HttpStatus.OK);
        if (type.equals("MutualFunds"))
            return new ResponseEntity<>(investmentService.getTopMutualFunds(n), new HttpHeaders(), HttpStatus.OK);
        if (type.equals("FixedDeposits"))
            return new ResponseEntity<>(investmentService.getTopFixedDeposits(n), new HttpHeaders(), HttpStatus.OK);
        return new ResponseEntity<>(investmentService.getTopInvestments(n), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/roi/topinvestors/{n}")
    public ResponseEntity<?> getTopInvestors(@PathVariable int n) {
        return new ResponseEntity<>(investmentService.topInvestors(n), new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/historical/{customerId}")
    public ResponseEntity<List<TopCustomerDTO>> getHistoricalReturns(@PathVariable int customerId){
      return new ResponseEntity<>(investmentService.getHistoricalReturns(customerId), new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/taxCalculation/{customerId}")
    public ResponseEntity<TaxCalculationsDTO> calculateInvestmentTax(@PathVariable int customerId){
      return new ResponseEntity<>(investmentService.calculateInvestmentTax(customerId), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/popularInvestments")
    public ResponseEntity<?> popularInvestments(){
        return  new ResponseEntity<>(investmentService.popularInvestments(),new HttpHeaders(),HttpStatus.OK);
    }

    @GetMapping("/getInvestmentByCustomerId/{customerId}")
    public ResponseEntity<?> investmentByCustomerId(@PathVariable int customerId){
        return  new ResponseEntity<>(investmentService.investmentByCustomerId(customerId),new HttpHeaders(),HttpStatus.OK);
    }
    
    @GetMapping("/detailedInvestments/{customerId}")
    public ResponseEntity<?> getDetailedInvestmentDetailsByCustomerId(@PathVariable int customerId){
      return new ResponseEntity<>(investmentService.getDetailedInvestmentByCustomerId(customerId), new HttpHeaders(), HttpStatus.OK);
    }
}
