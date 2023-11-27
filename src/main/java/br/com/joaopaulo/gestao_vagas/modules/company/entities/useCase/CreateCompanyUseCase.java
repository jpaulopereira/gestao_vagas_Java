package br.com.joaopaulo.gestao_vagas.modules.company.entities.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.joaopaulo.gestao_vagas.exceptions.UserFoundException;
import br.com.joaopaulo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.joaopaulo.gestao_vagas.modules.company.entities.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

     @Autowired
     private CompanyRepository companyRepository;

     @Autowired
     private PasswordEncoder passwordEncoder;

     public CompanyEntity execute(CompanyEntity companyEntity) {

          this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                    .ifPresent((user) -> {
                         throw new UserFoundException();
                    });

          var password = passwordEncoder.encode(companyEntity.getPassword());
          companyEntity.setPassword(password);

          return this.companyRepository.save(companyEntity);

     }

}
