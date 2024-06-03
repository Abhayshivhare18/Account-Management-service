package com.example.transactionService.service;

import com.example.transactionService.model.Transaction;

public interface TransactionService {


    public Transaction createTransaction(Transaction transaction);

    public Transaction updateTransaction(Transaction transaction);

    public Transaction getTransaction(Long transactionId);
}
