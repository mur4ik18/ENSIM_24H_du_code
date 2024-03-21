package org.example.api.api.equipe;

import lombok.RequiredArgsConstructor;
import org.example.api.api.equipe.Equipe;
import org.example.api.api.equipe.EquipeRequest;
import org.springframework.stereotype.Service;
import org.example.api.api.equipe.EquipeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService {
    private final EquipeRepository repository;

    public void save(EquipeRequest request) {
        Equipe equipe = Equipe
                .builder()
                .nom(request.getNom())
                .motDePasse(request.getMotDePasse())
                .build();
        repository.save(equipe);
    }

    public List<Equipe> findAll() {
        return null;
    }
}
