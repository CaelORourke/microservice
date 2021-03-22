package com.example.ledger.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("SELECT SUM(t.transactionValue) from Transaction t")
	BigDecimal getSumTransactionValue();
}
