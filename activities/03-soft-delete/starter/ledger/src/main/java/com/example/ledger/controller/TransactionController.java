package com.example.ledger.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.math.BigDecimal;

import com.example.ledger.dao.Transaction;
import com.example.ledger.dao.TransactionRepository;

@RestController
public class TransactionController {

	private final TransactionRepository repository;

	  TransactionController(TransactionRepository repository) {
		    this.repository = repository;
		  }

	  // get all transactions

	  @GetMapping("/transactions")
	  List<Transaction> all() {
	    return repository.findAll();
	  }

	  // get the sum of all transactions

	  @GetMapping("/transactions/sum")
	  BigDecimal sum() {
	    return repository.getSumTransactionValue();
	  }

	  // get a specific transaction by id

	  @GetMapping("/transactions/{id}")
	  Transaction one(@PathVariable Long id) {
	    return repository.findById(id)
	    		.orElseThrow(() -> new TransactionNotFoundException(id));
	  }

	  // create a new transaction

	  @PostMapping("/transactions")
	  Transaction newTransaction(@RequestBody Transaction newTransaction) {
	    return repository.save(newTransaction);
	  }

	  // update a transaction

	  @PutMapping("/transactions/{id}")
	  Transaction updateTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id) {

	    return repository.findById(id)
	      .map(transaction -> {
	        transaction.setSender(newTransaction.getSender());
	        transaction.setRecipient(newTransaction.getRecipient());
	        transaction.setTransactionValue(newTransaction.getTransactionValue());
	        return repository.save(newTransaction);
	      })
  		.orElseThrow(() -> new TransactionNotFoundException(id));
	  }

	  // delete a transaction

	  @DeleteMapping("/transactions/{id}")
	  void deleteTransaction(@PathVariable Long id) {
		  if (repository.existsById(id)) {
		      repository.deleteById(id);
		  }
	  }
}
