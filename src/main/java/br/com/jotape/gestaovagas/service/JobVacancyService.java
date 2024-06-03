package br.com.jotape.gestaovagas.service;

import br.com.jotape.gestaovagas.entity.ApplyJob;
import br.com.jotape.gestaovagas.entity.JobVacancy;
import br.com.jotape.gestaovagas.exception.JobNotFoundException;
import br.com.jotape.gestaovagas.exception.UserNotFoundException;
import br.com.jotape.gestaovagas.repository.ApplyJobRepository;
import br.com.jotape.gestaovagas.repository.CandidateRepository;
import br.com.jotape.gestaovagas.repository.JobVacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobVacancyService {

    @Autowired
    private JobVacancyRepository jobVacancyRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ApplyJobRepository applyJobRepository;

    public JobVacancy create(JobVacancy jobVacancy) {
        return this.jobVacancyRepository.save(jobVacancy);
    }

    public List<JobVacancy> listar(String filter) {
        return this.jobVacancyRepository.findByDescriptionContainingIgnoreCase(filter);
    }

    public ApplyJob execute(UUID idCandidate, UUID idJob) {
        //valida se o candidato existe
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        //valida se a vaga existe
        this.jobVacancyRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);

        var applyJob = ApplyJob.builder()
                .candidateId(idCandidate)
                .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}
