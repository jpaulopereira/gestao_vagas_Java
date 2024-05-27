package br.com.jotape.gestaovagas.service;

import br.com.jotape.gestaovagas.entity.Company;
import br.com.jotape.gestaovagas.exception.UserFoundException;
import br.com.jotape.gestaovagas.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Company create(Company company) {
        this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        String passwordEnc = passwordEncoder.encode(company.getPassword());
        company.setPassword(passwordEnc);
        return this.companyRepository.save(company);
    }
}
