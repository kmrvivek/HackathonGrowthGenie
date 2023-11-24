package com.hackathon.growthgenie.service;

import com.hackathon.growthgenie.exception.RecordNotFoundException;
import com.hackathon.growthgenie.model.Transaction;
import com.hackathon.growthgenie.model.TransactionStatus;
import com.hackathon.growthgenie.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

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
    	logger.info("Inside getTransactionByAccountId ",accountId);
    	List<Transaction> transactions=transactionRepository.findByAccountID(accountId);
    	logger.info("returning transactions {}",transactions);
    	return transactions;
    }
}
