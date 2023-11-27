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
    private String accountID;

    @Column(name = "TRANSACTIONTYPE")
    private String transactionType;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "TRANSACTIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name = "TRANSACTIONSTATUS")
    private String transactionStatus;

    @Column(name = "REMARKS")
    private String remarks;
}
