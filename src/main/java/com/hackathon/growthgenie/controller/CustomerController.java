package com.hackathon.growthgenie.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hackathon.growthgenie.model.Customer;
import com.hackathon.growthgenie.service.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
 
  @Autowired
  private CustomerService customerService;
  
  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomers() {
    List<Customer> list = customerService.getCustomers();
    for(Customer cust :  list) {
      System.out.println(cust);
    }
    return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable int id){
    Customer customer=customerService.getCustomerById(id);
    return new ResponseEntity<>(customer,new HttpHeaders(),HttpStatus.OK);
  }

  @GetMapping("/byEmailId")
  public int getIdByCustomerEmailId(@RequestParam (name = "emailId" , required = true) String emailId){
    return customerService.getCustomerByEmailId(emailId);
  }
  
}
