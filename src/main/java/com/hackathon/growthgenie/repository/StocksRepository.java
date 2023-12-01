package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepository extends JpaRepository<Stocks, Long> {
//    Page<Stocks> findByInvestmentAccountIDIn(List<String> ids, Pageable pageable);
}
