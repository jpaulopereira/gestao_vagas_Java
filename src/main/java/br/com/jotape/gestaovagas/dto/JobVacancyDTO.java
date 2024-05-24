package br.com.jotape.gestaovagas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobVacancyDTO {

    private String description;
    private String level;
    private String benefits;
}
