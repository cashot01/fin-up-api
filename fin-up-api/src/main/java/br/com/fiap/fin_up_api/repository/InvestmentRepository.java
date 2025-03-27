package br.com.fiap.fin_up_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fin_up_api.model.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, long> {
}
