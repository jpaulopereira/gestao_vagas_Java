package br.com.joaopaulo.gestao_vagas.modules.company.entities.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaopaulo.gestao_vagas.modules.company.entities.JobEntity;
import br.com.joaopaulo.gestao_vagas.modules.company.entities.repositories.JobRepository;

@Service
public class CreateJobs {
     
     @Autowired
     private JobRepository jobRepository;

     public JobEntity execute(JobEntity jobEntity) {
          return this.jobRepository.save(jobEntity);
     }
}
