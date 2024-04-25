package org.example.api;

import org.example.api.api.user.User;
import org.example.api.api.equipe.Equipe;
import org.example.api.api.equipe.EquipeRepository;
import org.example.api.api.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class JoinTeamIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Test
    public void testJoinTeamSuccess() throws Exception {
        // Préparation des données de test
        User user = new User();
        user.setUsername("user1");
        userRepository.save(user);

        Equipe equipe = new Equipe();
        equipe.setNom("EquipeTest");
        equipeRepository.save(equipe);

        // Envoi de la requête HTTP pour rejoindre l'équipe
        ResultActions result = mockMvc.perform(post("/api/v1/equipe/{id}/join", equipe.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": " + user.getId() + "}"));

        // Vérification de la réponse HTTP
        result.andExpect(status().isOk());

        // Vérification que l'utilisateur a bien rejoint l'équipe dans la base de données
        Equipe updatedEquipe = equipeRepository.findById(equipe.getId()).get();
        assertThat(updatedEquipe.getListeMembres()).contains(user);
    }

    @Test
    public void testJoinTeamInvalidUser() throws Exception {
        // Préparation des données de test
        Equipe equipe = new Equipe();
        equipe.setNom("EquipeTest");
        equipeRepository.save(equipe);

        // Envoi de la requête HTTP pour rejoindre l'équipe avec un utilisateur inexistant
        ResultActions result = mockMvc.perform(post("/api/v1/equipe/{id}/join", equipe.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": 999}"));

        // Vérification de la réponse HTTP
        result.andExpect(status().isNotFound());
    }
}
