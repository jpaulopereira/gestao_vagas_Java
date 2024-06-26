package br.com.jotape.gestaovagas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @Pattern(regexp = "^\\S*$", message = "O campo não deve conter espaço")
    private String username;

    @Email(message = "O campo deve conter um e-mail válido")
    private String email;

    @Length(min = 5, max = 200, message = "A senha deve conter entre (5) e (200) caracteres")
    private String password;

    @NotBlank
    private String description;

    @NotBlank
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
