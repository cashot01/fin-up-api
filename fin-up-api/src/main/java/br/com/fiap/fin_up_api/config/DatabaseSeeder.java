package br.com.fiap.fin_up_api.config;

import br.com.fiap.fin_up_api.model.*;
import br.com.fiap.fin_up_api.repository.InvestmentRepository;
import br.com.fiap.fin_up_api.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        var cauan = User.builder()
                .email("cauan@fiap.com.br")
                .password(passwordEncoder.encode("12345"))
                .role(UserRole.ADMIN)
                .build();

        var mateus = User.builder()
                .email("mateus@fiap.com.br")
                .password(passwordEncoder.encode("12345"))
                .role(UserRole.USER)
                .build();

        userRepository.saveAll(List.of(cauan, mateus));


        var investments = List.of(
                Investment.builder().name("Renda Fixa").icon("Book").build(),
                Investment.builder().name("Dollar Americano").icon("Dices").build(),
                Investment.builder().name("Bitcoin").icon("Heart").build(),
                Investment.builder().name("Euro").icon("Apple").build(),
                Investment.builder().name("Fundos Imobiliario").icon("Bus").build());

        investmentRepository.saveAll(investments);

        var descriptions = List.of(
                "Aplicação em CDB",
                "Investimento em Tesouro Direto",
                "Compra de dólar para reserva",
                "Diversificação em moeda estrangeira",
                "Aquisição de criptomoedas",
                "Investimento em blockchain",
                "Compra de euros para viagem",
                "Proteção cambial",
                "Aplicação em FIIs",
                "Diversificação imobiliária",
                "Reinvestimento de dividendos",
                "Compra de cotas de fundo",
                "Proteção contra inflação",
                "Renda passiva mensal",
                "Longo prazo com juros compostos",
                "Alocação conservadora",
                "Alocação moderada",
                "Alocação arrojada",
                "Diversificação de carteira",
                "Projeção de aposentadoria");

        var transactions = new ArrayList<Transaction>();
        for (int i = 0; i < 50; i++) {
            transactions.add(Transaction.builder()
                    .description(descriptions.get(new Random().nextInt(descriptions.size())))
                    .amount(BigDecimal.valueOf(new Random().nextDouble() * 500))
                    .date(LocalDate.now().minusDays(new Random().nextInt(30)))
                    .type(TransactionType.EXPENSE)
                    .investment(investments.get(new Random().nextInt(investments.size())))
                    .build());
        }
        transactionRepository.saveAll(transactions);
    }

}
