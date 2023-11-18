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
@Table(name = "accounts")
public class Account implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ACCOUNTID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String accountId;

  @Column(name = "CUSTOMERID")
  private Integer customerId;

  @Column(name = "ACCOUNTTYPE")
  private AccountType accountType;

  @Column(name = "ACCOUNTBALANCE")
  private Long accountBalance;

  @Column(name = "ACCOUNTSTATUS")
  private String accountStatus;

  @Column(name = "INTERESTRATE")
  private Double interestRate;

  @Column(name = "OPENINGDATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date openingDate;

  @Column(name = "LASTTRANSACTIONDATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastTransactionDate;

}
