package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.MutualFunds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MutualFundsRepository extends JpaRepository<MutualFunds, Long> {
    Page<MutualFunds> findByOrderByInvestmentAmountDesc(Pageable pageable);

    List<MutualFunds> findByInvestmentAccountId(String investmentAccountID);
}
