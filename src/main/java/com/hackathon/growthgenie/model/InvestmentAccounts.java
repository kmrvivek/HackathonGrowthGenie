package com.hackathon.growthgenie.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "investment_accounts")
public class InvestmentAccounts {

    @Id
    @Column(name = "INVESTMENTACCOUNTID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String investmentAccountID;

    @Column(name="CUSTOMERID")
    private int customerID ;

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "INVESTMENTSTATUS")
    private String  investmentStatus;

    @Column(name = "INVESTMENTSTARTDATE")
    private Date investmentStartDate;

    @Column(name = "INVESTMENTENDDATE")
    private Date investmentEndDate;

    @Column(name = "RETURNS")
    private double returns;

    @Column(name = "INVESTMENTPORTFOLIO")
    private int investmentPortfolio ;//(for tracking the composition of the investment)
}
