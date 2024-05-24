package br.com.jotape.gestaovagas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroMessageDTO {

    private String message;
    private String fild;
}
