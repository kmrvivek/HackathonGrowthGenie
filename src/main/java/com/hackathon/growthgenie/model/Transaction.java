package com.hackathon.growthgenie.model;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTIONID")
    private int transactionID;

    @Column(name = "ACCOUNTID")
    private int accountID;

    @Column(name = "TRANSACTIONTYPE")
    private String transactionType;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "TRANSACTIONDATE")
    private Date transactionDate;

    @Column(name = "TRANSACTIONSTATUS")
    private String transactionStatus;

    @Column(name = "REMARKS")
    private String remarks;
}
