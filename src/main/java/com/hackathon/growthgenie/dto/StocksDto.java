package com.hackathon.growthgenie.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StocksDto {
    private String stockName;
    private String stockSymbol;
    private String stockExchange;
    private Double returns;
}
