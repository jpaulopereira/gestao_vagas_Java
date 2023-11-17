package br.com.joaopaulo.gestao_vagas.modules.Controllers;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.joaopaulo.gestao_vagas.modules.Candidate.CandidateEntity;

public interface CandidateRepoisitory extends JpaRepository<CandidateEntity, UUID> {

}
