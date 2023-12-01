package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.MutualFunds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutualFundsRepository extends JpaRepository<MutualFunds, Long> {
    Page<MutualFunds> findByOrderByInvestmentAmountDesc(Pageable pageable);
}
