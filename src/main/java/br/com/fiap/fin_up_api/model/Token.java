package br.com.fiap.fin_up_api.model;

public record Token(
        String token,
        Long expiration,
        String type,
        String role
) {

}
