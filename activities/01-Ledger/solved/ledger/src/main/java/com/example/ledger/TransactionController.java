package com.example.ledger;

import java.math.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TransactionController {

  private final TransactionRepository repository;

  TransactionController(TransactionRepository repository) {
    this.repository = repository;
  }

  /*
   * get all transactions
   * 	- does not include transactions that have been soft deleted
   */

  @GetMapping("/transactions")
  List<TransactionView> all() {

    return repository.findAllBySoftDeleteFalse()
      .stream()
      .map(this::convertToTransactionView)
      .collect(Collectors.toList());
  }

  /*
   * get all transactions (for demo purposes only)
   * 	- includes all entity fields
   * 	- includes transactions that have been soft deleted
   */

  @GetMapping("/transactions/all")
  List<Transaction> all2() {
    return repository.findAll();
  }

  /*
   * get the sum of all transactions
   * 	- does not include transactions that have been soft deleted
   */

  @GetMapping("/transactions/sum")
  BigDecimal sum() {
    return repository.getSumTransactionValue();
  }

  // get a specific transaction by id

  @GetMapping("/transactions/{id}")
  TransactionView one(@PathVariable Long id) {

    return repository.findByIdAndSoftDeleteFalse(id)
      .map(transaction -> convertToTransactionView(transaction))
      .orElseThrow(() -> new TransactionNotFoundException(id));
  }

  // create a new transaction

  @PostMapping("/transactions")
  TransactionView newTransaction(@RequestBody Transaction newTransaction) {

    Transaction transaction = repository.save(newTransaction);
    return convertToTransactionView(transaction);
  }

  // update a transaction

  @PutMapping("/transactions/{id}")
  TransactionView replaceTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id) {

    return repository.findByIdAndSoftDeleteFalse(id)
      .map(transaction -> {
        transaction.setSender(newTransaction.getSender());
        transaction.setRecipient(newTransaction.getRecipient());
        transaction.setTransactionValue(newTransaction.getTransactionValue());
        Transaction t = repository.save(newTransaction);
        return convertToTransactionView(t);
      })
      .orElseGet(() -> {
        newTransaction.setId(id);
        Transaction t = repository.save(newTransaction);
        return convertToTransactionView(t);
      });
  }

  // delete a transaction

  @DeleteMapping("/transactions/{id}")
  void deleteTransaction(@PathVariable Long id) {

    Optional<Transaction> transaction = repository.findByIdAndSoftDeleteFalse(id);
    transaction.ifPresent(t -> {
      t.setSoftDelete(true);
      repository.save(t);
    });
  }

  // NOTE: real world we'd use a model mapper but for now we'll map it manually!

  private TransactionView convertToTransactionView(Transaction transaction) {

    TransactionView view = new TransactionView(
    transaction.getId(),
    transaction.getSender(),
    transaction.getRecipient(),
    transaction.getTransactionValue());
    return view;
  }
}
