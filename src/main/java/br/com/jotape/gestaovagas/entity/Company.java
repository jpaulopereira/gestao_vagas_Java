package br.com.jotape.gestaovagas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo name não pode estar em branco")
    private String name;

    @Pattern(regexp = "^\\S*$", message = "O campo (Username) não deve conter espaço")
    private String username;

    @Email(message = "Digite um e-mail válido")
    private String email;

    @Length(min = 5, max = 250, message = "A senha deve conter entre (5) e (250) caracteres")
    private String password;

    @NotBlank
    private String description;

    @NotBlank
    private String website;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
