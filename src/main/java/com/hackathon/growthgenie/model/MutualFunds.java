package com.hackathon.growthgenie.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "mutual_funds")
public class MutualFunds implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MUTUALFUNDID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mututalFundId;

    @Column(name = "INVESTMENTACCOUNTID")
    private String investmentAccountId;

    @Column(name = "FUNDNAME")
    private String fundName;

    @Column(name = "FUNDMANAGER")
    private String fundManger;

    @Column(name = "FUNDTYPE")
    private String fundType;

    @Column(name = "NAV")
    private double nav;

    @Column(name = "INVESTMENTAMOUNT")
    private double investmentAmount;

    @Column(name = "INVESTMENTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date investmentDate;
}
