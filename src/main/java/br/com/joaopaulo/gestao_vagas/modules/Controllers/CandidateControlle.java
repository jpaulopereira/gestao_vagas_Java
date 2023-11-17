package br.com.joaopaulo.gestao_vagas.modules.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaopaulo.gestao_vagas.modules.Candidate.CandidateEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateControlle {
     
     @Autowired
     private CandidateRepoisitory candidateRepoisitory;

     @PostMapping("/")
     public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        return this.candidateRepoisitory.save(candidateEntity);
     }
}
