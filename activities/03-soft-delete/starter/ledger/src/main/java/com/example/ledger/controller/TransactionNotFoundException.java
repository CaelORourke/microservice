package com.example.ledger.controller;

public class TransactionNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2561734181276856423L;

	TransactionNotFoundException(Long id) {
		super("Could not find transaction " + id);
	}
}
