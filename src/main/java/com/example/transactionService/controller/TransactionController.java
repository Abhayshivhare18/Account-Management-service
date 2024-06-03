package com.example.transactionService.controller;

import com.example.transactionService.model.Transaction;
import com.example.transactionService.response.ResponseHandler;
import com.example.transactionService.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping(value="/createTransaction")
     public ResponseEntity<Object> createTransaction(@RequestBody Transaction  transaction){
        return ResponseHandler.reponseBuilder("save Transaction details",
                HttpStatus.OK, transactionService.createTransaction(transaction));
     }

     @PutMapping(value="/updateTransaction")
      public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction){
        return ResponseHandler.reponseBuilder("update transaction details",
                HttpStatus.OK,transactionService.updateTransaction(transaction));
     }


      @GetMapping(value="/getTransaction/{id}")
     public ResponseEntity<Object> getTransaction(@PathVariable long transactionId){
        return ResponseHandler.reponseBuilder("Get transaction details",
                HttpStatus.OK,transactionService.getTransaction(transactionId));
     }




}
