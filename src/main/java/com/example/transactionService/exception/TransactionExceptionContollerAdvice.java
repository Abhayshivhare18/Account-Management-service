package com.example.transactionService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class TransactionExceptionContollerAdvice {


         @ExceptionHandler(value={TransactionNotFoundException.class})
          public ResponseEntity<Object>handleTransactionNotFoundException(TransactionNotFoundException transactionNotFound){
             TransactionException transactionException = new TransactionException(transactionNotFound.getMessage(),
                     transactionNotFound.getCause(), HttpStatus.NOT_FOUND);
             return new ResponseEntity<>(transactionException,HttpStatus.NOT_FOUND);
         }

         @ExceptionHandler(value = {TransactionServiceException.class})
         public ResponseEntity<Object> handleTransactionServiceException
                 (TransactionServiceException transactionServiceException){
             TransactionException transactionException = new TransactionException(
                     transactionServiceException.getMessage(),
                     transactionServiceException.getCause(),
                     HttpStatus.INTERNAL_SERVER_ERROR);

             return new ResponseEntity<>(transactionException,HttpStatus.INTERNAL_SERVER_ERROR);
         }



}
