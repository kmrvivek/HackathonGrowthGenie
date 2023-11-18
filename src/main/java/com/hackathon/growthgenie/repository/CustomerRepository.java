package com.hackathon.growthgenie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.growthgenie.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
