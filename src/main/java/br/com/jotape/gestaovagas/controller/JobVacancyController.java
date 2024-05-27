package br.com.jotape.gestaovagas.controller;

import br.com.jotape.gestaovagas.dto.JobVacancyDTO;
import br.com.jotape.gestaovagas.entity.JobVacancy;
import br.com.jotape.gestaovagas.service.JobVacancyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobVacancyController {

    @Autowired
    private JobVacancyService jobVacancyService;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas ", description = "Cadastro de vagas dentro da empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = JobVacancy.class)
                    )
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public JobVacancy create(@RequestBody @Valid JobVacancyDTO jobVacancyDTO, HttpServletRequest request) {

        var companyId = request.getAttribute("company_id");

        JobVacancy jobEntity = JobVacancy.builder()
                .benefits(jobVacancyDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(jobVacancyDTO.getDescription())
                .level(jobVacancyDTO.getLevel())
                .build();
        return this.jobVacancyService.create(jobEntity);
    }
}
