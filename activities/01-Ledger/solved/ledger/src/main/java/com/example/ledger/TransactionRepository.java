package com.example.ledger;

import java.math.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllBySoftDeleteFalse();
	Optional<Transaction> findByIdAndSoftDeleteFalse(Long id);

	@Query("SELECT SUM(t.transactionValue) from Transaction t")
	BigDecimal getSumTransactionValue();
}
