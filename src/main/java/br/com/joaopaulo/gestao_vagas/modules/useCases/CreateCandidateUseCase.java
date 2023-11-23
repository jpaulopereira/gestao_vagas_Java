package br.com.joaopaulo.gestao_vagas.modules.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaopaulo.gestao_vagas.exceptions.UserFoundException;
import br.com.joaopaulo.gestao_vagas.modules.Candidate.CandidateEntity;
import br.com.joaopaulo.gestao_vagas.modules.Controllers.CandidateRepoisitory;

@Service
public class CreateCandidateUseCase {

     @Autowired
     private CandidateRepoisitory candidateRepoisitory;

     public CandidateEntity execute(CandidateEntity candidateEntity) {

          this.candidateRepoisitory.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
               .ifPresent((user) -> {
                         throw new UserFoundException();
               });

          return this.candidateRepoisitory.save(candidateEntity);
     }
}
