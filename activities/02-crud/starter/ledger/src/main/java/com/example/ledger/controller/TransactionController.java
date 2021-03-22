package com.example.ledger.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import com.example.ledger.dao.Transaction;
import com.example.ledger.dao.TransactionRepository;

@RestController
public class TransactionController {

	private final TransactionRepository repository;

	  TransactionController(TransactionRepository repository) {
		    this.repository = repository;
		  }
	
	  /*@GetMapping("/transactions")
	  String all() {
	    return "Response from controller.";
	  }*/

	  @GetMapping("/transactions")
	  List<Transaction> all() {
	    return repository.findAll();
	  }
}
