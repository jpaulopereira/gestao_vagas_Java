package br.com.jotape.gestaovagas.repository;

import br.com.jotape.gestaovagas.entity.JobVacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobVacancyRepository extends JpaRepository<JobVacancy, UUID> {

}
