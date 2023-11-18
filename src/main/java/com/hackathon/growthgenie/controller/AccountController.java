package com.hackathon.growthgenie.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.growthgenie.model.Account;
import com.hackathon.growthgenie.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;
  
  @GetMapping
  public ResponseEntity<List<Account>> getAllAccounts(){
    List<Account> accounts = accountService.getAllAccounts();
    return new ResponseEntity<>(accounts, new HttpHeaders(), HttpStatus.OK);
  }
  
  
  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<Account>> getAccountByCustomerId(@PathVariable("customerId") Integer customerId){
    List<Account> accounts = accountService.getAccountByCustomerId(customerId);
    return new ResponseEntity<>(accounts, new HttpHeaders(), HttpStatus.OK);
  }
  
  @GetMapping("/account/{accountId}")
  public ResponseEntity<Account> getAccountByAccountId(@PathVariable("accountId") String accountId){
    Account account = accountService.getAccountsById(accountId);
    return new ResponseEntity<>(account, new HttpHeaders(), HttpStatus.OK);
  }
  
  @PostMapping
  public ResponseEntity<Account> addAccount(@RequestBody Account account){
    Account savedAccount = accountService.addNewAccount(account);
    return new ResponseEntity<>(savedAccount, new HttpHeaders(), HttpStatus.CREATED);
  }
  
  @PutMapping("{accountId}")
  public ResponseEntity<Account> updateAccount(@PathVariable("accountId") String accountId, @RequestBody Account account) {
    Account updatedAccount = accountService.updateAccount(accountId, account);
    return new ResponseEntity<>(updatedAccount, new HttpHeaders(), HttpStatus.ACCEPTED);
  }
  
  @DeleteMapping("{accountId}")
  public ResponseEntity<String> deleteAccount(@PathVariable("accountId") String accountId){
    accountService.deleteAccount(accountId);
    return new ResponseEntity<>("Account successfully deleted!", new HttpHeaders(), HttpStatus.OK);
  }
}
