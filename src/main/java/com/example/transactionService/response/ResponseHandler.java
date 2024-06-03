package com.example.transactionService.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {


     public static ResponseEntity<Object> reponseBuilder(String message, HttpStatus httpStatus,
                                                         Object responeObject){
           Map<String,Object> map =new HashMap<>();
           map.put("message",message);
           map.put("httpStatus",httpStatus);
           map.put("data",responeObject);
           return new ResponseEntity<>(map,httpStatus);

     }
}
