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
import org.springframework.transaction.annotation.Transactional;

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
        // Get the authentication object from the security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Find the user from the database using the username from the authentication object
        User user = userRepository.findByUsername(auth.getName()).get();
        // Build a new Equipe object using the request data
        Equipe equipe = Equipe
                .builder()
                .nom(request.getNom())
                .motDePasse(request.getMotDePasse())
                .build();
        // Get the list of members from the equipe object
        Set<User> listeMembres = equipe.getListeMembres();
        // If the list of members is null, initialize it as a new HashSet
        if (listeMembres == null) {
            listeMembres = new HashSet<>();
        }
        // Add the current user to the list of members
        listeMembres.add(user);
        // Set the updated list of members to the equipe object
        equipe.setListeMembres(listeMembres);
        // Save the equipe object to the database
        repository.save(equipe);
        System.out.println("Equipe saved - " + equipe);
        // Set the equipe to the user object
        user.setSonEquipe(equipe);
    }


    public List<EquipeInfoResponse> findAll() {
        List<Equipe> equipes = repository.findAll();
        System.out.println("Equipes found - " + equipes);
        ArrayList<EquipeInfoResponse> equipeInfoResponses = new ArrayList<EquipeInfoResponse>();
        for (Equipe equipe : equipes) {
            System.out.println("Equipe found - " + equipe);
            System.out.println("Equipe Users - " + equipe.getListeMembres());
            equipeInfoResponses.add(EquipeInfoResponse.fromEquipe(equipe));
        }
        return equipeInfoResponses;
    }

    public EquipeInfoResponse findById(Integer id) {
        Equipe equipe = repository.findById(id).get();
        System.out.println("Equipe found - " + equipe);
        System.out.println("Equipe Users - " + equipe.getListeMembres());
        return EquipeInfoResponse.fromEquipe(equipe);
    }
}
