package com.hackathon.growthgenie.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackathon.growthgenie.model.Account;
import com.hackathon.growthgenie.repository.AccountRepository;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  public List<Account> getAllAccounts() {
    return accountRepository.findAll();
  }


  public Account getAccountsById(String accountId) {
    return accountRepository.findById(accountId).get();
  }

  public List<Account> getAccountByCustomerId(Integer customerId) {
    List<Account> accounts = accountRepository.findByCustomerId(customerId);
    return accounts;
  }

  public Account addNewAccount(Account account) {
    return accountRepository.save(account);
  }


  public Account updateAccount(String accountId, Account account) {
   Account existingAccount = accountRepository.findById(accountId).get();
  //  existingAccount.setAccountBalance(account.());
//    existingAccount.setAccountStatus(account.getAccountStatus());
//    existingAccount.setAccountType(account.getAccountType());
//    existingAccount.setInterestRate(account.getInterestRate());
//    existingAccount.setLastTransactionDate(new Date());
    Account updatedAccount = accountRepository.save(account);
    return updatedAccount;
  }


  public void deleteAccount(String accountId) {
    accountRepository.deleteById(accountId);
  }

}
