package com.hackathon.growthgenie.model;

public enum TransactionStatus {
    FAILED("FAILED"),
    COMPLETED("COMPLETED"),
    PENDING("PENDING");

    private String name;
    TransactionStatus(String name){
        this.name=name;
    }

}
