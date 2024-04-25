package org.example.api.api.equipe;

import lombok.RequiredArgsConstructor;
import org.example.api.api.equipe.EquipeRequest;
import org.example.api.api.equipe.EquipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/equipe")
@RequiredArgsConstructor
public class EquipeController {
    private final EquipeService service;

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody EquipeRequest request
    ) {
        if (service.EquipeExist(request.getNom())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        service.save(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }
}
