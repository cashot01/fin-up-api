package br.com.fiap.fin_up_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
		info = @Info(
				title = "FinUp API",
				version = "v1",
				description = "API do projeto FinUp",
				contact = @Contact(
						name = "Cauan Passos",
						email = "cauan@finup.com"
				)
		)
)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
