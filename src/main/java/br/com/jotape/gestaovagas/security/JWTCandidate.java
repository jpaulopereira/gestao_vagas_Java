package br.com.jotape.gestaovagas.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCandidate {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public DecodedJWT validadeToken(String token) {

        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var tokenDecoded = JWT.require(algorithm)
                    .build()
                    .verify(token);
            return tokenDecoded;
        }catch (JWTVerificationException e) {
            return null;
        }
    }
}
