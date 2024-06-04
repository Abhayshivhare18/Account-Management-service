package com.example.transactionService.service.impl;

import com.example.transactionService.model.Transaction;
import com.example.transactionService.repository.TransactionRepository;
import com.example.transactionService.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class TransactionServiceImplTest {
    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void testCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setType("Credit");

        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction createdTransaction = transactionService.createTransaction(transaction);

        assertNotNull(createdTransaction);
        assertEquals(BigDecimal.valueOf(100), createdTransaction.getAmount());
    }

    @Test
    public void testUpdateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(BigDecimal.valueOf(200));
        transaction.setType("Debit");

        Mockito.when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction updatedTransaction = transactionService.updateTransaction(transaction);

        assertNotNull(updatedTransaction);
        assertEquals(BigDecimal.valueOf(200), updatedTransaction.getAmount());
    }

    @Test
    public void testGetTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(BigDecimal.valueOf(100));

        Mockito.when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Transaction foundTransaction = transactionService.getTransaction(1L);

        assertNotNull(foundTransaction);
        assertEquals(1L, foundTransaction.getId());
    }
}
