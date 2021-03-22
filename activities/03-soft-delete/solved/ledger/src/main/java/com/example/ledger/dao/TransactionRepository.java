package com.example.ledger.dao;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Optional<Transaction> findByIdAndSoftDeleteFalse(Long id);

	List<Transaction> findAllBySoftDeleteFalse();

	@Query("SELECT SUM(t.transactionValue) FROM Transaction t WHERE t.softDelete = 0")
	BigDecimal getSumTransactionValue();
}
