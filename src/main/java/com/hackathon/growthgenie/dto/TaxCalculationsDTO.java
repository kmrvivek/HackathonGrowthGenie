package com.hackathon.growthgenie.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxCalculationsDTO {
  
 private Integer customerId;
 private String name;
 private Map<String, Double> taxTypeReturn;
 private double totalTax;
 
}
