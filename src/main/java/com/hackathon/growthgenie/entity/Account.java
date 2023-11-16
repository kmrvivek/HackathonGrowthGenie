package com.hackathon.growthgenie.entity;

import com.hackathon.growthgenie.constants.AccountStatus;
import com.hackathon.growthgenie.constants.AccountType;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTID")
    private String accountId;

    @Column(name = "CUSTOMERID")
    private String customerId;

    @Column(name = "ACCOUNTTYPE")
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;

    @Column(name = "ACCOUNTBALANCE")
    private Double accountBalance;

    @Column(name = "ACCOUNTSTATUS")
    private AccountStatus accountStatus;

    @Column(name = "INTERESTRATE")
    private Double interestRate;

    @Column(name = "OPENINGDATE")
    private LocalDateTime OpeningDate;

    @Column(name = "LASTTRANSACTIONDATE")
    private LocalDateTime lastTransactionDate;

    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;

    @Column(name = "UPDATEDON")
    private LocalDateTime updatedOn;
}