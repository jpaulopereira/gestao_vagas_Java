package br.com.jotape.gestaovagas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobVacancyDTO {

    @Schema(example = "Vaga para pessoa desenvolvedora júnior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Júnior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
    @Schema(example = "Plano de saúde", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
}
