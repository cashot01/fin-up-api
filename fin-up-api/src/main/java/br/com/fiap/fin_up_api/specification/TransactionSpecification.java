package br.com.fiap.fin_up_api.specification;

import br.com.fiap.fin_up_api.controller.TransactionController;
import br.com.fiap.fin_up_api.model.Transaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {

    public static Specification<Transaction> withFilters(TransactionController.TransactionFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.description() != null && !filter.description().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("description")), "%" + filter.description().toLowerCase() + "%"));
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                        cb.between(root.get("date"), filter.startDate(), filter.endDate()));
            }

            if (filter.startDate() != null && filter.endDate() == null) {
                predicates.add(cb.equal(root.get("date"), filter.startDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
