package com.example.transactionService.service.impl;


import com.example.transactionService.config.TopicName;
import com.example.transactionService.exception.TransactionNotFoundException;
import com.example.transactionService.exception.TransactionServiceException;
import com.example.transactionService.model.Transaction;
import com.example.transactionService.repository.TransactionRepository;
import com.example.transactionService.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;

      public Transaction createTransaction(Transaction transaction){
          Long transactionId=transaction.getId();
          log.info("create Transaction for transaction id : {}",transactionId);
          Transaction createdTransaction = null;
           try {
               createdTransaction = transactionRepository.save(transaction);
               log.info("Transaction created successfully for transaction id: {}",
                       createdTransaction.getId());

                kafkaTemplate.send(TopicName.TRANSACTION_TOPIC,createdTransaction);

           } catch (DataAccessException dae) {
                   log.error("Database access error while creating transaction: {}", dae.getMessage());
                   throw new TransactionServiceException("Database access error", dae);
           } catch (Exception e){
               log.error("Unexpected error while creating transaction : {}", e.getMessage());
               throw new TransactionServiceException("Unexpected error while creating transaction",e);
           }
          return createdTransaction;
      }

      public Transaction updateTransaction(Transaction transaction){
          Long transactionId =transaction.getId();
          log.info("Updating transaction for transaction id: {}", transactionId);
          Transaction updatedTransaction = new Transaction();
          try {
              Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);
              if (existingTransaction.isPresent()) {
                  updatedTransaction = existingTransaction.get();
                  updatedTransaction.setAccountId(transaction.getAccountId());
                  updatedTransaction.setType(transaction.getType());
                  transactionRepository.save(updatedTransaction);
                  log.info("Transaction updated successfully for transaction id: {}", updatedTransaction.getId());

                  kafkaTemplate.send(TopicName.TRANSACTION_TOPIC,updatedTransaction);

              } else {
                  log.error("No transaction found for id: {}", transactionId);
                  throw new TransactionNotFoundException("No transaction found for id: " + transactionId);
              }
          }catch (DataAccessException dae) {
              log.error("Database access error while updating transaction: {}", dae.getMessage());
              throw new TransactionServiceException("Database access error", dae);
          }catch (Exception e){
              log.error("Unexpected error while updating transaction : {}", e.getMessage());
              throw new TransactionServiceException("Unexpected error " +
                      "while updating transaction",e);
          }
          return updatedTransaction;
      }

      public Transaction getTransaction(Long transactionId){
          log.info("get transaction for id :{}",transactionId);
          Optional<Transaction> Optransaction=null;
          Transaction transaction = new Transaction();
          try {
              Optransaction = transactionRepository.findById(transactionId);
              if (Optransaction.isPresent()) {
                  transaction = Optransaction.get();
              } else {
                  log.error("No transaction found for id: {}", transactionId);
                  throw new TransactionNotFoundException("No transaction found for id: " + transactionId);
              }

          }catch (DataAccessException dae) {
              log.error("Database access error while getting transaction: {}", dae.getMessage());
              throw new TransactionServiceException("Database access error", dae);
          }catch (Exception e){
              log.error("No transaction found :{}",e.getMessage());
              throw new TransactionServiceException("No transaction found",e);
          }
          return transaction;
      }
}
