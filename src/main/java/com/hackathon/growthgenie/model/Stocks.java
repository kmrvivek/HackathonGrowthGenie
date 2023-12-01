package com.hackathon.growthgenie.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "stocks")
public class Stocks implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STOCKID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;

    @Column(name = "STOCKNAME")
    private String stockName;

    @Column(name = "STOCKSYMBOL")
    private String stockSymbol;

    @Column(name = "STOCKEXCHANGE")
    private String stockExchange;

    @Column(name = "PURCHASEPRICE")
    private double purchasePrice;

    @Column(name = "PURCHASEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @Column(name = "QUANTITY")
    private long quantity;

    @Column(name = "INVESTMENTACCOUNTID")
    private String investmentAccountID;
}
