package br.com.fiap.fin_up_api.controller;

import br.com.fiap.fin_up_api.model.Transaction;
import br.com.fiap.fin_up_api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    public record TransactionFilter(String description, LocalDate startDate, LocalDate endDate) {
    }

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public Page<Transaction> index(TransactionFilter filter,
                                   @PageableDefault(size = 10, sort = "date", direction = Sort.Direction.DESC) Pageable pageable) {
        var specification = TransactionSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }
    }

}
