package com.hackathon.growthgenie.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "loans")
public class Loan implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name="LOANID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String loanId;
      
  @Column(name="CUSTOMERID")
  private Integer customerId;
  
  @Column(name="LOANTYPE")
  private LoanType loanType;
  
  @Column(name="LOANAMOUNT")
  private long loanAmount;
  
  @Column(name="INTERESTRATE")
  private double interestRate;
  
  @Column(name="LOANSTATUS")
  private LoanStatus loanStatus;
    
  @Column(name="EMI")
  private int emi;
  
  @Column(name="LOANTERM")
  private int loanTerm;
  
  @Column(name="DISBURSEMENTDATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date disbursmentDate;
  
  @Column(name="REPAYMENTSCHEDULE")
  private String repaymentSchedule;
   

}
