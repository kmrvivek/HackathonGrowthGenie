package com.hackathon.growthgenie.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackathon.growthgenie.model.Loan;
import com.hackathon.growthgenie.model.LoanStatus;
import com.hackathon.growthgenie.model.LoanType;
import com.hackathon.growthgenie.repository.LoanRepository;

@Service
public class LoanService {

  @Autowired
  private LoanRepository loanRepository;
  
  public List<Loan> getAllLoans(){
    return loanRepository.findAll();
  }
  
  public Loan getLoanById(String loanId){
    return loanRepository.findById(loanId).get();
  }
  
  public List<Loan> getLoanByCustomerId(Integer id){
    return loanRepository.findByCustomerId(id);
  }
  
  public List<Loan> getLoanByType(LoanType loanType){
    return loanRepository.findByLoanType(loanType);
  }
  
  public List<Loan> getLoanByStatus(LoanStatus loanStatus){
    return loanRepository.findByLoanStatus(loanStatus);
  }
  
}
