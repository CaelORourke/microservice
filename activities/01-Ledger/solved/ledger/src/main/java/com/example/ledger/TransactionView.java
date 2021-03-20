package com.example.ledger;

import java.math.BigDecimal;

public class TransactionView {

	private Long id;
	private String sender;
	private String recipient;
	private BigDecimal transactionValue;

	TransactionView(Long id, String sender, String recipient, BigDecimal transactionValue) {
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.transactionValue = transactionValue;
	}

	public Long getId() {
		return this.id;
	}

	public String getSender() {
		return this.sender;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public BigDecimal getTransactionValue() {
		return this.transactionValue;
	}
}
