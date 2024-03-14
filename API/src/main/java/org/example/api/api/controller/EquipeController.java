package org.example.api.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.api.api.repository.EquipeRepository;
import org.example.api.api.request.EquipeRequest;
import org.example.api.api.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/equipe")
@RequiredArgsConstructor
public class EquipeController {
    @Autowired
    private final EquipeService service;

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody EquipeRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }
}
