package com.example.ledger.dao;

import java.math.*;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Transaction {

  private @Id @GeneratedValue Long id;
  private String sender;
  private String recipient;

  @Column(name = "soft_delete")
  private boolean softDelete;

  @Column(name = "transaction_value")
  private BigDecimal transactionValue;

  public Transaction() {}

  public Transaction(String sender, String recipient, BigDecimal transactionValue) {
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

  public boolean getSoftDelete() {
    return this.softDelete;
  }

  public BigDecimal getTransactionValue() {
    return this.transactionValue;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public void setSoftDelete(boolean softDelete) {
    this.softDelete = softDelete;
  }

  public void setTransactionValue(BigDecimal transactionValue) {
    this.transactionValue = transactionValue;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
    return true;
    if (!(o instanceof Transaction))
    return false;

    Transaction transaction = (Transaction) o;
    return Objects.equals(this.id, transaction.id)
      && Objects.equals(this.sender, transaction.sender)
      && Objects.equals(this.recipient, transaction.recipient)
      && Objects.equals(this.softDelete, transaction.softDelete)
      && Objects.equals(this.transactionValue, transaction.transactionValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.sender, this.recipient, this.softDelete, this.transactionValue);
  }

  @Override
  public String toString() {
    return "Transaction{" + "id=" + this.id + ", sender='" + this.sender + '\'' + ", recipient='" + this.recipient + '\'' + ", softDelete='" + this.softDelete + '\'' + ", transactionValue='" + this.transactionValue + '\'' + '}';
  }
}