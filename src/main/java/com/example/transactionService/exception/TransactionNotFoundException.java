package com.example.transactionService.exception;

public class TransactionNotFoundException extends  RuntimeException{

    public TransactionNotFoundException(String message){
        super(message);
    }

    public TransactionNotFoundException(String message, Throwable throwable){
        super(message,throwable);
    }


}