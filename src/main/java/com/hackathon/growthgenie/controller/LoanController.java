package com.hackathon.growthgenie.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.growthgenie.model.Loan;
import com.hackathon.growthgenie.model.LoanStatus;
import com.hackathon.growthgenie.model.LoanType;
import com.hackathon.growthgenie.service.LoanService;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

  @Autowired
  private LoanService loanService;
  
  @GetMapping
  public ResponseEntity<List<Loan>> getAllLoans(){
    List<Loan> loans = loanService.getAllLoans();
    return new ResponseEntity<>(loans, new HttpHeaders(), HttpStatus.OK);
  }
  
  @GetMapping("{customerId}")
  public ResponseEntity<List<Loan>> getLoanByCustomerId(@PathVariable("customerId") Integer customerId){
    List<Loan> loans = loanService.getLoanByCustomerId(customerId);
    return new ResponseEntity<>(loans, new HttpHeaders(), HttpStatus.OK);
  }
  
  @GetMapping("{loanId}")
  public ResponseEntity<Loan> getLoanByLoanId(@PathVariable("loanId") String loanId){
    Loan loan = loanService.getLoanById(loanId);
    return new ResponseEntity<>(loan, new HttpHeaders(), HttpStatus.OK);
  }
  
  @GetMapping("{loanType}")
  public ResponseEntity<List<Loan>> getLoanByLoanType(@PathVariable("loanType") LoanType loanType){
    List<Loan> loans = loanService.getLoanByType(loanType);
    return new ResponseEntity<>(loans, new HttpHeaders(), HttpStatus.OK);
  }
  
  @GetMapping("{loanStatus}")
  public ResponseEntity<List<Loan>> getLoanByLoanStatus(@PathVariable("loanStatus") LoanStatus loanStatus){
    List<Loan> loans = loanService.getLoanByStatus(loanStatus);
    return new ResponseEntity<>(loans, new HttpHeaders(), HttpStatus.OK);
  }
  
}
