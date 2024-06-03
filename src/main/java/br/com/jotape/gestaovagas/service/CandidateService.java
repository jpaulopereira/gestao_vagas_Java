package br.com.jotape.gestaovagas.service;

import br.com.jotape.gestaovagas.dto.AuthCandidateResponseDTO;
import br.com.jotape.gestaovagas.dto.CandidateRequestDTO;
import br.com.jotape.gestaovagas.dto.ProfileCandidateResponseDTO;
import br.com.jotape.gestaovagas.entity.Candidate;
import br.com.jotape.gestaovagas.exception.UserFoundException;
import br.com.jotape.gestaovagas.repository.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@Service
public class CandidateService {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Candidate create(Candidate candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);
        return this.candidateRepository.save(candidate);
    }

    public AuthCandidateResponseDTO execute(CandidateRequestDTO candidateRequestDTO) throws AuthenticationException {
        var candidate = candidateRepository.findByUsername(candidateRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = this.passwordEncoder
                .matches(candidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIN = Instant.now().plus(Duration.ofHours(5));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expiresIN)
                .sign(algorithm);

        long expiresInMillis = Duration.between(Instant.now(), expiresIN).toMillis();
        Duration duration = Duration.ofMillis(expiresInMillis);
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();

        String formattedTime = String.format("%d hours and %d minutes", hours, minutes);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .acess_token(token)
                .expires_in(formattedTime)
                .build();
        return authCandidateResponse;
    }

    public ProfileCandidateResponseDTO dados(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UserFoundException();
                });
        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
        return candidateDTO;
    }
}
