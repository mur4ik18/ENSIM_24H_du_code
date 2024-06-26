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

    @PostMapping("/join")
    public ResponseEntity<?> join(
            @RequestBody EquipeRequest request
    ) {
        if (service.UsersInEquipe(request.getNom()) >= 5) {
            return new ResponseEntity("Equipe is full", HttpStatus.BAD_REQUEST);
        }
        String response = service.join(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leave(
            @RequestBody EquipeRequest request
    ) {
        return ResponseEntity.ok(service.leave());
    }
}
