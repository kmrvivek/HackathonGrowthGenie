package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.FixedDeposits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedDepositsRepository extends JpaRepository<FixedDeposits, String> {
    Page<FixedDeposits> findByOrderByInterestRateDesc(Pageable pageable);
}
