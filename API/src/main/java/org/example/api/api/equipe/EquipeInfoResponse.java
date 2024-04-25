package org.example.api.api.equipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.api.user.User;
import org.example.api.api.user.UserInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipeInfoResponse {
    private Long id;
    private String nom;
    private int annee;
    private String sujet;
    private String lienWeb;
    private String domaine;
    private String type;
    private List<UserInfoResponse> listeMembres;

    public static EquipeInfoResponse fromEquipe(Equipe equipe) {
        return EquipeInfoResponse.builder()
                .id(equipe.getId())
                .nom(equipe.getNom())
                .annee(equipe.getAnnee())
                .sujet(equipe.getSujet())
                .lienWeb(equipe.getLienWeb())
                .domaine(equipe.getDomaine())
                .type(equipe.getType())
                .listeMembres(equipe.getListeMembres().stream()
                        .map(UserInfoResponse::fromUser)
                        .collect(Collectors.toList()))
                .build();
    }
    
}
