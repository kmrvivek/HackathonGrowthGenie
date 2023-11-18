package com.hackathon.growthgenie.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.growthgenie.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, String>{ 
  
  List<Account> findByCustomerId(Integer customerId);
}
