package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {
//    Page<Stocks> findByInvestmentAccountIDIn(List<String> ids, Pageable pageable);
   List<Stocks> findByInvestmentAccountID(String investmentAccountID);


}
