package br.com.joaopaulo.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private UUID id;

     private String name;

     @NotBlank
     @Pattern(regexp = "\\S+", message = "O campo contem espaço") // verifica se tem espaço
     private String username;

     @Email(message = "O campo email é inválido")
     private String email;

     @Length(min = 10, max = 100)
     private String password;

     private String website;

     private String description;

     private LocalDateTime createdAt;
}
