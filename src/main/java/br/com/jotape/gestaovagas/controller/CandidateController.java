package br.com.jotape.gestaovagas.controller;

import br.com.jotape.gestaovagas.dto.ProfileCandidateResponseDTO;
import br.com.jotape.gestaovagas.entity.Candidate;
import br.com.jotape.gestaovagas.entity.JobVacancy;
import br.com.jotape.gestaovagas.service.CandidateService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JobVacancyService jobVacancyService;


    @PostMapping("/")
    @Operation(summary = "Cadastro de candidato", description = "Cadastra um candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Candidate.class))
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> createCandidate(@RequestBody @Valid Candidate candidate) {
        try {
            Candidate result = this.candidateService.create(candidate);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(result.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do candidato", description = "Lista o perfil do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.candidateService
                    .dados(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem de vagas disponíveis para candidato", description = "Lista de vagas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
                @Content(
                        array = @ArraySchema(schema = @Schema(implementation = JobVacancy.class))
                )
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobVacancy> findJobVancancyByFilter(@RequestParam String filter) {
        return this.jobVacancyService.listar(filter);
    }

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Inscrição do candidato para um vaga", description = "Realiza inscrição do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = JobVacancy.class))
                    )
            })
    })
    public ResponseEntity<Object> applyJob(@RequestBody HttpServletRequest request, UUID idJob) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var result = this.jobVacancyService.execute(UUID.fromString(idCandidate.toString()), idJob);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
