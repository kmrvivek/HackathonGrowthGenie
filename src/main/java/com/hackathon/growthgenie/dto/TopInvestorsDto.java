package com.hackathon.growthgenie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class TopInvestorsDto implements Serializable {
    private String name;
    private String investmentType;
    private double returns;
    private int portfolio;
}
