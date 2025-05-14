package br.com.fiap.fin_up_api.service;

import br.com.fiap.fin_up_api.model.Token;
import br.com.fiap.fin_up_api.model.User;
import br.com.fiap.fin_up_api.model.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final Long DURATION = 120L; // 10 minutes
    private Algorithm ALG;

    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init(){
        ALG = Algorithm.HMAC256(secret);
    }

    public Token createToken(User user){
        var token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().toString())
                .withExpiresAt(LocalDateTime.now().plusMinutes(DURATION).toInstant(ZoneOffset.ofHours(-3)))
                .sign(ALG);
        return new Token(token, 21315656L, "Bearer", user.getRole().toString());

    }

    public User getUserFromToken(String token) {
        var verifiedToken = JWT.require(ALG)
                .build()
                .verify(token);

        return User
                .builder()
                .id(Long.parseLong( verifiedToken.getSubject() ))
                .email(verifiedToken.getClaim("email").toString())
                .role(UserRole.valueOf(verifiedToken.getClaim("role").asString()))
                .build();

    }

}
