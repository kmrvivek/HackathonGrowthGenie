package com.hackathon.growthgenie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackathon.growthgenie.model.Customer;
import com.hackathon.growthgenie.repository.CustomerRepository;

@Service
public class CustomerService {
  
  @Autowired
  private CustomerRepository customerRepository;

  public List<Customer> getCustomers(Integer id) throws RuntimeException {
    if(id == null) {
      List<Customer> customers =  customerRepository.findAll();
      return customers;
    }
    Optional<Customer> customer = customerRepository.findById(id);
    
    if(customer.isPresent()) {
      List<Customer> customers = new ArrayList<>();
      customers.add(customer.get());
        return customers;
    } else {
        throw new RuntimeException("No employee record exist for given id");
    }
  }



}
