package br.com.jotape.gestaovagas.controller;

import br.com.jotape.gestaovagas.dto.JobVacancyDTO;
import br.com.jotape.gestaovagas.entity.JobVacancy;
import br.com.jotape.gestaovagas.service.JobVacancyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobVacancyController {

    @Autowired
    private JobVacancyService jobVacancyService;

    @PostMapping("/")
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
