package com.hackathon.growthgenie.controller;

import com.hackathon.growthgenie.dto.TransactionDTO;
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
        logger.info("returning all transactions {}",transactions);
        return new ResponseEntity<>(transactions,new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/transactionById/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionId") int transactionId){
        logger.info("Inside getTransactionById {}",transactionId);
        Transaction transaction=transactionService.getTransactionById(transactionId);
        logger.info("returning transaction {}",transaction);
        return new ResponseEntity<>(transaction,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/transactionByStatus")
    public ResponseEntity<List<Transaction>> getTransactionByStatus(@RequestParam(name = "TransactionStatus", required = true )String transactionStatus){
        logger.info("Inside getTransactionByStatus {}",transactionStatus);
        List<Transaction> transactions=transactionService.getTransactionByStatus(transactionStatus);
        logger.info("returning all transactions {}",transactions);
        return new ResponseEntity<>(transactions,new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @GetMapping("/transactionByAccountId/{accountID}")
    public ResponseEntity<List<Transaction>> getTransactionByAccountId(@PathVariable("accountID") String accountID){
       logger.info("Inside getTransactionByAccountId : {}",accountID);
    	List<Transaction> transactions=transactionService.getTransactionByAccountId(accountID);
    	logger.info("returning transactions : {} ",transactions);
    	return new ResponseEntity<>(transactions,new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Integer> newTransaction(@RequestBody TransactionDTO transactionDTO){
        logger.info("Inside new Transaction {}",transactionDTO);

        int transactionID=transactionService.newTransaction(transactionDTO);
        logger.info("Transaction Completed with transaction id {}",transactionID);
        return new ResponseEntity<>(transactionID,HttpStatus.CREATED);
    }

    @PutMapping("{transactionId}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int transactionId,@RequestBody TransactionDTO transactionDTO){
        logger.info("Inside new Transaction {}",transactionDTO);
        Transaction transaction=transactionService.updateTransaction(transactionId,transactionDTO);
        logger.info("Transaction Updated {}",transaction );
        return new ResponseEntity<>(transaction,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int transactionId){
        logger.info("delete transaction with id {}",transactionId);
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>("Transaction Deleted..!!",new HttpHeaders(),HttpStatus.OK);
    }

    @GetMapping("/transactionsByCustomerId/{customerId}")
    public ResponseEntity<List<List<Transaction>>> getTransactionByCustomerId(@PathVariable int customerId){
        logger.info("getTransactionByCustomerId with cust id "+customerId);
        return new ResponseEntity<>(transactionService.getTransactionByCustomerById(customerId),new HttpHeaders(),HttpStatus.OK);
    }
}
