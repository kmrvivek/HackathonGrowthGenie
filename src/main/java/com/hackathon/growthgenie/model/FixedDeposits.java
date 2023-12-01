package com.hackathon.growthgenie.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "fixed_deposits")
public class FixedDeposits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "FIXEDDEPOSITID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String fixedDepositId;

    @Column(name = "INVESTMENTACCOUNTID")
    private String investmentAccountId;

    @Column(name = "PRINCIPALAMOUNT")
    private double principalAmount;

    @Column(name = "INTERESTRATE")
    private double interestRate;

    @Column(name = "MATURITYDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date maturityRate;

    @Column(name = "INTERESTPAYMENTFREQUENCY")
    private String interestPaymentFrequency;

    @Column(name = "MATURITYAMOUNT")
    private double maturityAmount;
}
