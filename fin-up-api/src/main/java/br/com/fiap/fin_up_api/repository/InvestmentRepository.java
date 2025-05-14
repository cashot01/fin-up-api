package br.com.fiap.fin_up_api.repository;

import br.com.fiap.fin_up_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fin_up_api.model.Investment;
import java.lang.Long;
import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByUser(User user);
}
