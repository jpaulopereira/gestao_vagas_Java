package br.com.jotape.gestaovagas.service;

import br.com.jotape.gestaovagas.entity.JobVacancy;
import br.com.jotape.gestaovagas.repository.JobVacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobVacancyService {

    @Autowired
    private JobVacancyRepository jobVacancyRepository;

    public JobVacancy create(JobVacancy jobVacancy) {
        return this.jobVacancyRepository.save(jobVacancy);
    }

    public List<JobVacancy> listar(String filter) {
        return this.jobVacancyRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
