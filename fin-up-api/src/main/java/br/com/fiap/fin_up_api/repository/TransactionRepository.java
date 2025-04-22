package br.com.fiap.fin_up_api.repository;

import br.com.fiap.fin_up_api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    // List<Transaction> findByDescriptionContainingIgnoringCase(String
    // description); // Query Methods

    // List<Transaction> findByDescriptionContainingIgnoringCaseAndDate(String
    // description, LocalDate date);

    // List<Transaction> findByDate(LocalDate date);

}
