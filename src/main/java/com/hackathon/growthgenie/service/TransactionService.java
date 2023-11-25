package com.hackathon.growthgenie.service;

import com.hackathon.growthgenie.dto.TransactionDTO;
import com.hackathon.growthgenie.exception.RecordNotFoundException;
import com.hackathon.growthgenie.model.Transaction;
import com.hackathon.growthgenie.model.TransactionStatus;
import com.hackathon.growthgenie.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(int transactionId) {
        return transactionRepository.findById(transactionId).get();
    }

    public List<Transaction> getTransactionByStatus(String transactionStatus) throws RecordNotFoundException{
        try {
            TransactionStatus transactionByStatus = TransactionStatus.valueOf(transactionStatus.toUpperCase());
            List<Transaction> transactions=transactionRepository.findByTransactionStatus(transactionByStatus.name());
            return  transactions;
        } catch (Exception e) {
           logger.error("error while getTransactionByStatsu ", e.getStackTrace());
            throw new RecordNotFoundException("Invalid status ..!!");
        }
    }
    
    public List<Transaction> getTransactionByAccountId(String accountId){
    	logger.info("Inside getTransactionByAccountId {}",accountId);
    	List<Transaction> transactions=transactionRepository.findByAccountID(accountId);
    	logger.info("returning transactions {}",transactions);
    	return transactions;
    }

    @Transactional
    public int newTransaction(TransactionDTO transactionDTO){
        logger.info("Inside new Transaction");
        Transaction transaction=new Transaction();
        transaction.setTransactionDate(new Date());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setAccountID(transactionDTO.getAccountID());
        transaction.setRemarks(transaction.getRemarks());
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setTransactionStatus(transactionDTO.getTransactionStatus());
        Transaction transactionObj= transactionRepository.save(transaction);
        logger.info("Transaction completed {}",transactionObj);
        return transactionObj.getTransactionID();
    }

    @Transactional
    public Transaction updateTransaction(int transactionId,TransactionDTO transactionDTO){
        logger.info("Inside updateTransaction with transaction Id {}",transactionId);
        Transaction transaction= transactionRepository.findById(transactionId).get();
        logger.debug("Transaction found : {} ",transaction);
        transaction.setTransactionDate(new Date());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setAccountID(transactionDTO.getAccountID());
        transaction.setRemarks(transaction.getRemarks());
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setTransactionStatus(transactionDTO.getTransactionStatus());
        logger.info("Transaction Updated {}",transaction);
        return transactionRepository.save(transaction);

    }

    public void deleteTransaction(int transactionId){
        Transaction transaction= transactionRepository.findById(transactionId).get();
        transactionRepository.delete(transaction);
    }
}
