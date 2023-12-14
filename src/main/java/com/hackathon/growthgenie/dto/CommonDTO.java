package com.hackathon.growthgenie.dto;

import java.util.List;
import java.util.Map;
import com.hackathon.growthgenie.model.Account;
import com.hackathon.growthgenie.model.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonDTO {
   private List<Account> accounts;
   private List<List<Transaction>> transactions;
   private Map<String,Double> investmentByCustomerId;
   private TaxCalculationsDTO taxCalculationsDTO;
   private List<TopCustomerDTO> topCustomerDTOList;
}
