package org.example.api.api.service;

import lombok.RequiredArgsConstructor;
import org.example.api.api.model.Equipe;
import org.example.api.api.request.EquipeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.api.api.repository.EquipeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService {
    @Autowired
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
