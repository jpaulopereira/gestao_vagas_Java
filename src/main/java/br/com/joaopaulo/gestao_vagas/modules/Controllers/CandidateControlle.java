package br.com.joaopaulo.gestao_vagas.modules.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaopaulo.gestao_vagas.modules.Candidate.CandidateEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateControlle {
     

     @PostMapping("/")
     public void create(@Valid @RequestBody CandidateEntity candidateEntity) {
          System.out.println("Candidato: " + candidateEntity.getName());
          System.out.println("UserName: " + candidateEntity.getUsername());
          System.out.println("Email: " + candidateEntity.getEmail());
     }
}
