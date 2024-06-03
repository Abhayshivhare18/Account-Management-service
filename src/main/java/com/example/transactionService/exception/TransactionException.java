package com.example.transactionService.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class TransactionException {

    private  final String message;

    private final Throwable throwable;

    private final HttpStatus httpStatus;
}
