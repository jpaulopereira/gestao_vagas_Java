package br.com.jotape.gestaovagas.repository;

import br.com.jotape.gestaovagas.entity.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, UUID> {
}
