package br.com.joaopaulo.gestao_vagas.modules.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;

import br.com.joaopaulo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.joaopaulo.gestao_vagas.modules.company.entities.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

     @Autowired
     private CompanyRepository companyRepository;

     @Autowired
     private PasswordEncoder passwordEncoder;

     public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
          var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
          .orElseThrow(() -> {
               throw new UsernameNotFoundException("Company not found");
          });

         var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
     
         if(!passwordMatches) {
               throw new AuthenticationException();
         }

         JWT.create().withIssuer("javagas")
         .withSubject(company.getId().toString());
     }
}
