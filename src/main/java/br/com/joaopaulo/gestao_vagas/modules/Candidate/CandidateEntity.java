package br.com.joaopaulo.gestao_vagas.modules.Candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

     private UUID id;

     private String name;

     @NotBlank
     @Pattern(regexp = "\\S+", message = "O campo contem espaço") // verifica se tem espaço
     private String username;

     @Email(message = "O campo email é inválido")
     private String email;

     @Length(min = 10, max = 100)
     private String password;

     private String description;
     private String curriculum;
}
