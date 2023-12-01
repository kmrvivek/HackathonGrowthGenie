package com.hackathon.growthgenie.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopCustomerDTO {
  
  private Integer customerId;
  private String customerName;
  private Date currentDate;
  private double returnValue;

}
