package br.com.fiap.fin_up_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fin_up_api.model.Investment;
import java.lang.Long;
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
