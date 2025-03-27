package br.com.fiap.fin_up_api.controller;

import br.com.fiap.fin_up_api.model.Investment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InvestmentController {

    private List<Investment> repository = new ArrayList<>();

    // listando os investimentos
    // metodo get :8080/investments -> json
    @GetMapping("/investments")
    public List<Investment> index(){
        return repository;
    }

    // cadastrar investimento
    @PostMapping("/investments")
    public ResponseEntity<Investment> create(@RequestBody Investment investment){
        System.out.println("Cadastrando Investimento " + investment.getName());
        repository.add(investment);
        return ResponseEntity.status(201).body(investment);
    }

    // retornar um investimento
    @GetMapping("/investments/{id}")
    public ResponseEntity<Investment> get(@PathVariable Long id){
        System.out.println("Buscando Investimento " + id);
        var investment = repository.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

        if (investment.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(investment.get());
    }

}
