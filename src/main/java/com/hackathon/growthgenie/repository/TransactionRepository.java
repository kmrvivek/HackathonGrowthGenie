package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

           public List<Transaction> findByTransactionStatus(String transactionByStatus);

}
