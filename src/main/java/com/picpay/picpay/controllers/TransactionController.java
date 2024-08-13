package com.picpay.picpay.controllers;

import com.picpay.picpay.domain.transaction.Transaction;
import com.picpay.picpay.dtos.TransactionDTO;
import com.picpay.picpay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
