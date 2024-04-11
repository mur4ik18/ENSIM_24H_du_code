package org.example.api.api.equipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.api.user.User;

import java.util.List;
import java.util.Set;

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
    private Set<User> listeMembres;

    public static EquipeInfoResponse fromEquipe(Equipe equipe) {
        return EquipeInfoResponse.builder()
                .id(equipe.getId())
                .nom(equipe.getNom())
                .annee(equipe.getAnnee())
                .sujet(equipe.getSujet())
                .lienWeb(equipe.getLienWeb())
                .domaine(equipe.getDomaine())
                .type(equipe.getType())
                .listeMembres(equipe.getListeMembres())
                .build();
    }

}
