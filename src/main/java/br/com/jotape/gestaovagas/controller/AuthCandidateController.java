package br.com.jotape.gestaovagas.controller;

import br.com.jotape.gestaovagas.dto.CandidateRequestDTO;
import br.com.jotape.gestaovagas.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody CandidateRequestDTO candidateRequestDTO) {
        try {
            var token = this.candidateService.execute(candidateRequestDTO);
            return ResponseEntity.ok().body(token);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
