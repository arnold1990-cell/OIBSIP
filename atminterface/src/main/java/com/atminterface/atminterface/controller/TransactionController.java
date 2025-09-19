package com.atminterface.atminterface.controller;

import com.atminterface.atminterface.exceptions.NoTransactionException;
import com.atminterface.atminterface.model.Transaction;
import com.atminterface.atminterface.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TransactionController {
    public  final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping
    public ResponseEntity<?> findById(@RequestBody Long id) throws NoTransactionException {
        Optional<Transaction>transaction=transactionService.findById(id);
        if (transaction.isPresent()) { return  new ResponseEntity<>(transaction.get(),HttpStatus.FOUND);

        }else
        {return  new ResponseEntity<>("Transaction not found",HttpStatus.NOT_FOUND);

        }
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Transaction transaction) {
        Transaction savedUser = transactionService.save(transaction);
        try {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<>("Transaction related to this user cant be found", HttpStatus.CONFLICT);
        }
    }}