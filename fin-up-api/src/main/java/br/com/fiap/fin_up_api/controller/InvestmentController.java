package br.com.fiap.fin_up_api.controller;

import br.com.fiap.fin_up_api.model.Investment;
import br.com.fiap.fin_up_api.repository.InvestmentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private InvestmentRepository repository;

    // listando os investimentos
    // metodo get :8080/investments -> json
    @GetMapping
    @Cacheable("investments")
    @Operation(
            description = "Listar todos os investimentos",
            tags = "investments",
            summary = "Lista dos Investimentos",
            hidden = true
    )
    public List<Investment> index(){
        log.info("Buscando todos investimentos");
        return repository.findAll();
    }

    // cadastrar investimento
    @PostMapping
    @CacheEvict(value = "investments", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na Validação")
    })
    public Investment create(@RequestBody @Valid Investment investment){
        log.info("Cadastrando Investimento " + investment.getName());
        return repository.save(investment);

    }

    // retornar um investimento
    @GetMapping("{id}")
    public Investment get(@PathVariable Long id){
        log.info("Buscando Investimento " + id);
        return getInvestment(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando investimento " + id);
        repository.delete(getInvestment(id));
    }

    @PutMapping("{id}")
    public Investment update(@PathVariable Long id, @RequestBody @Valid Investment investment) {
        log.info("Atualizando investimento " + id + " " + investment);

        getInvestment(id);
        investment.setId(id);
        return repository.save(investment);
    }

    private Investment getInvestment(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Investimento não encontrado"));
    }

}
