package com.hackathon.growthgenie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TopPerformingInvestmentsDTO {
    String investmentType;
    Double returns;
}
