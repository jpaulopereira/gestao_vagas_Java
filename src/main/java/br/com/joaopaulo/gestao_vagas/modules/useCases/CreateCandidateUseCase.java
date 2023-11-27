package br.com.joaopaulo.gestao_vagas.modules.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.joaopaulo.gestao_vagas.exceptions.UserFoundException;
import br.com.joaopaulo.gestao_vagas.modules.Candidate.CandidateEntity;
import br.com.joaopaulo.gestao_vagas.modules.Candidate.Controllers.CandidateRepoisitory;

@Service
public class CreateCandidateUseCase {

     @Autowired
     private CandidateRepoisitory candidateRepoisitory;

     @Autowired
     private PasswordEncoder passwordEncoder;

     public CandidateEntity execute(CandidateEntity candidateEntity) {

          this.candidateRepoisitory.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
               .ifPresent((user) -> {
                         throw new UserFoundException();
               });

               var password = passwordEncoder.encode(candidateEntity.getPassword());
               candidateEntity.setPassword(password);

          return this.candidateRepoisitory.save(candidateEntity);
     }
}
