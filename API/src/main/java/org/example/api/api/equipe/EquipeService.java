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

import java.util.*;

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
        System.out.println("User found - " + user);

        // Build a new Equipe object using the request data
        Equipe equipe = Equipe
                .builder()
                .nom(request.getNom())
                .motDePasse(request.getMotDePasse())
                .build();

        System.out.println("Equipe created - " + user);
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


    public String join(EquipeRequest request) {
        System.out.println("Equipe join request - " + request);
        Optional<Equipe> equipe = repository.findByNom(request.getNom());
        System.out.println("Equipe found - " + equipe);
        if (UserInEquipe()) {
            return new String("User already in an equipe");
        }
        if (equipe.isPresent() && equipe.get().getMotDePasse().equals(request.getMotDePasse())) {
            // Get the authentication object from the security context
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            // Find the user from the database using the username from the authentication object
            User user = userRepository.findByUsername(auth.getName()).get();
            Set<User> listeMembres = equipe.get().getListeMembres();
            if (listeMembres == null) {
                listeMembres = new HashSet<>();
            }
            listeMembres.add(user);
            equipe.get().setListeMembres(listeMembres);
            repository.save(equipe.get());
            user.setSonEquipe(equipe.get());
            return "Equipe joined successfully";
        }
        return "Equipe not found or password incorrect";
    }


    public EquipeInfoResponse leave() {
        // Get the authentication object from the security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Find the user from the database using the username from the authentication object
        User user = userRepository.findByUsername(auth.getName()).get();
        System.out.println("User found - " + user);
        System.out.println("User Equipe - " + user.getSonEquipe());
        System.out.println("User Equipe Users - " + user.getSonEquipe().getListeMembres());
        Equipe equipe = user.getSonEquipe();
        Set<User> listeMembres = equipe.getListeMembres();
        listeMembres.remove(user);
        equipe.setListeMembres(listeMembres);
        repository.save(equipe);
        user.setSonEquipe(null);
        return EquipeInfoResponse.fromEquipe(equipe);
    }


    public boolean UserInEquipe() {
        // Get the authentication object from the security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Find the user from the database using the username from the authentication object
        User user = userRepository.findByUsername(auth.getName()).get();
        Equipe equipe = user.getSonEquipe();
        return equipe != null;
    }
}
