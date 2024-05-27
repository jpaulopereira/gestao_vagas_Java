package br.com.jotape.gestaovagas.controller;

import br.com.jotape.gestaovagas.dto.CompanyDTO;
import br.com.jotape.gestaovagas.service.AuthCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyService authCompany;

    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody CompanyDTO companyDTO) {
        try {
            var result =  this.authCompany.execute(companyDTO);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
