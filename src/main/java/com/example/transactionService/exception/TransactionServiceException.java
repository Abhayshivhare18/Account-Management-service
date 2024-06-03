package com.example.transactionService.exception;

public class TransactionServiceException extends RuntimeException{

    public TransactionServiceException(String message){
        super(message);
    }

    public TransactionServiceException(String message, Throwable throwable){
        super(message);
    }
}
