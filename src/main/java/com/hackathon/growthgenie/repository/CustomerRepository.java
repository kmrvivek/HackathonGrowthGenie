package com.hackathon.growthgenie.repository;

import com.hackathon.growthgenie.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByCustomerIdIn(Iterable<Integer> ids);
}
