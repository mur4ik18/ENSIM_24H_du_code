package org.example.api.api.equipe;

import lombok.RequiredArgsConstructor;
import org.example.api.api.equipe.EquipeRequest;
import org.example.api.api.equipe.EquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/equipe")
@RequiredArgsConstructor
public class EquipeController {
    private final EquipeService service;

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody EquipeRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }
}
