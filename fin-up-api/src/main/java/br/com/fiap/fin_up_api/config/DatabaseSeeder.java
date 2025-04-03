package br.com.fiap.fin_up_api.config;

import br.com.fiap.fin_up_api.model.Investment;
import br.com.fiap.fin_up_api.repository.InvestmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder {

    @Autowired
    private InvestmentRepository investmentRepository;

    @PostConstruct
    public  void investmentsSeed(){
        investmentRepository.saveAll(
                List.of(
                        Investment.builder().name("Renda Fixa").icon("Book").build(),
                        Investment.builder().name("Dollar").icon("Dices").build(),
                        Investment.builder().name("BitCoin").icon("Heart").build()
                )
        );
    }

}
