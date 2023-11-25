package com.hackathon.growthgenie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private String accountID;
    private String transactionType;
    private int amount;
    private String transactionStatus;
    private String remarks;

}
