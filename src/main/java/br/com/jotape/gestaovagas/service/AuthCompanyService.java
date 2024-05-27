package br.com.jotape.gestaovagas.service;

import br.com.jotape.gestaovagas.dto.AuthCompanyResponseDTO;
import br.com.jotape.gestaovagas.dto.CompanyDTO;
import br.com.jotape.gestaovagas.repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(CompanyDTO companyDTO) {
        var company = this.companyRepository.findByUsername(companyDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = this.passwordEncoder.matches(companyDTO.getPassword(), company.getPassword());
        if (!passwordMatches) {
            throw new ArithmeticException();
        }

        var expiresIn = Instant.now().plus(Duration.ofHours(6));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(5)))
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

      var authCompanyResponse = AuthCompanyResponseDTO.builder()
                .acess_token(token)
                .expires_in(String.valueOf(expiresIn.toEpochMilli()))
              .build();
        return authCompanyResponse;
    }
}
