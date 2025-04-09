package br.com.fiap.fin_up_api.controller;

import br.com.fiap.fin_up_api.model.Transaction;
import br.com.fiap.fin_up_api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {

    record TransactionFilter(String description, LocalDate date, BigDecimal amount){}

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(TransactionFilter filter){
        log.info("Buscando transações com descrição {} e data {}", filter.description(), filter.date());

        var probe = Transaction.builder()
                .description(filter.description())
                .date(filter.date())
                .amount(filter.amount())
                .build();

        var matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(probe, matcher);
        return repository.findAll(example);
    }

}
