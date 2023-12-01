package br.com.joaopaulo.gestao_vagas.modules.company.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaopaulo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.joaopaulo.gestao_vagas.modules.company.entities.useCase.AuthCompanyUsecase;


@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

     @Autowired
     private AuthCompanyUsecase authCompanyUsecase;
     
     @PostMapping("/company")
     public String create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
          return this.authCompanyUsecase.execute(authCompanyDTO);

     }
}
