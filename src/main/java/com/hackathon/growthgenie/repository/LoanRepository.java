package com.hackathon.growthgenie.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.growthgenie.model.Loan;
import com.hackathon.growthgenie.model.LoanStatus;
import com.hackathon.growthgenie.model.LoanType;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
  
  List<Loan> findByCustomerId(Integer customerId);

  List<Loan> findByLoanType(LoanType loanType);

  List<Loan> findByLoanStatus(LoanStatus loanStatus);

}
