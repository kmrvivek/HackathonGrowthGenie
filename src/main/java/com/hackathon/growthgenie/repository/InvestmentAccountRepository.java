package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.InvestmentAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccounts, String> {
    List<InvestmentAccounts> findByCustomerID(int customerId);
}
