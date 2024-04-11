package org.example.api.api.equipe;

import lombok.RequiredArgsConstructor;
import org.example.api.api.equipe.Equipe;
import org.example.api.api.equipe.EquipeRequest;
import org.example.api.api.user.User;
import org.example.api.api.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.example.api.api.equipe.EquipeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EquipeService {
    private final EquipeRepository repository;
    private final UserRepository userRepository;

    public boolean EquipeExist(String nom) {
        return repository.findByNom(nom).isPresent();
    }
    public void save(EquipeRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();
        Equipe equipe = Equipe
                .builder()
                .nom(request.getNom())
                .motDePasse(request.getMotDePasse())
                .build();
        Set<User> listeMembres = equipe.getListeMembres();
        if (listeMembres == null) {
            listeMembres = new HashSet<>();
        }
        listeMembres.add(user);
        equipe.setListeMembres(listeMembres);
        repository.save(equipe);
        user.setSonEquipe(equipe);
    }

    public List<EquipeInfoResponse> findAll() {
        List<Equipe> equipes = repository.findAll();
        ArrayList<EquipeInfoResponse> equipeInfoResponses = new ArrayList<EquipeInfoResponse>();
        for (Equipe equipe : equipes) {
            equipeInfoResponses.add(EquipeInfoResponse.fromEquipe(equipe));
        }
        return equipeInfoResponses;
    }
}
