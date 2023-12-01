package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.InvestmentAccounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccounts, String> {
    List<InvestmentAccounts> findByCustomerID(int customerId);

    @Query("select p from InvestmentAccounts p where p.accountType='Stocks' and p.investmentStatus='Active' order by returns desc")
    Page<InvestmentAccounts> getTopPerformingStocks(Pageable pageable);
    @Query("select p from InvestmentAccounts p where p.accountType='Mutual Funds' and p.investmentStatus='Active' order by returns desc")
    Page<InvestmentAccounts> getTopPerformingMutualFunds(Pageable pageable);
    @Query("select p from InvestmentAccounts p where p.accountType='Fixed Deposits' and p.investmentStatus='Active' order by returns desc")
    Page<InvestmentAccounts> getTopPerformingFixedDeposits(Pageable pageable);
    Page<InvestmentAccounts> findAllByOrderByInvestmentPortfolioDesc(Pageable pageable);
}
