package com.example.ledger;

import java.math.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ledger.dao.Transaction;
import com.example.ledger.dao.TransactionRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(TransactionRepository repository) {

    return args -> {
      log.info("Seeding transaction data.");
      log.info("data =>" + repository.save(new Transaction("Iron Man", "Captain America", new BigDecimal("50000.25"))));
      log.info("data =>" + repository.save(new Transaction("Captain America", "Iron Man", new BigDecimal("500.25"))));
      log.info("data =>" + repository.save(new Transaction("Captain America", "Hulk", new BigDecimal("50.25"))));
    };
  }
}