package com.hackathon.growthgenie.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.growthgenie.model.Customer;
import com.hackathon.growthgenie.service.CustomerService;


@RestController
@RequestMapping("/api/cutomers")
public class CustomerController {
 
  @Autowired
  private CustomerService customerService;
  
  @GetMapping
  public ResponseEntity<List<Customer>> getCustomer(@RequestParam(name = "id", required = false) Integer id) {
    List<Customer> list = customerService.getCustomers(id);
    for(Customer cust :  list) {
      System.out.println(cust);
    }
    return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
  }
  
}
