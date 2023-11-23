package com.hackathon.growthgenie.controller;

import com.hackathon.growthgenie.model.Transaction;
import com.hackathon.growthgenie.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private static final Logger logger= LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    TransactionService transactionService;


    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        logger.info("Inside getAllTransactions");
        List<Transaction> transactions=transactionService.getAllTransaction();
        logger.info("returning all transactions ",transactions);
        return new ResponseEntity<>(transactions,new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/transactionById/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionId") int transactionId){
        logger.info("Inside getTransactionById ",transactionId);
        Transaction transaction=transactionService.getTransactionById(transactionId);
        logger.info("returning transaction ",transaction);
        return new ResponseEntity<>(transaction,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/transactionByStatus")
    public ResponseEntity<List<Transaction>> getTransactionByStatus(@RequestParam(name = "TransactionStatus", required = true )String transactionStatus){
        logger.info("Inside getTransactionByStatus");
        List<Transaction> transactions=transactionService.getTransactionByStatus(transactionStatus);
        logger.info("returning all transactions ",transactions);
        return new ResponseEntity<>(transactions,new HttpHeaders(), HttpStatus.OK);
    }

//    1. all transaction
//            2. transaction by id
//    3. transaction by status
//    4. trasnaction by amount
//    5. transaction by account id
//            6. transaction by date
}
