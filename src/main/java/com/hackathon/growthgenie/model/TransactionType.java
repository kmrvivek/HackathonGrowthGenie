package com.hackathon.growthgenie.model;

public enum TransactionType {
    Cash_Withdrawals("Cash Withdrawals"),
    Checks("Checks"),
    Deposit("Deposit"),
    Online_Payments("Online Payments"),
    Wire_Transfers("Wire Transfers"),
    Withdrawal("Withdrawal");

    private String type;


    TransactionType(String type){
        this.type=type;
    }
}
