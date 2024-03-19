package org.example.api.api.equipe;

import jakarta.persistence.*;
import jakarta.servlet.http.Part;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipe")
@Entity
public class Equipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    //private Partnaire Sponsor;
    private String image;
    private String motDePasse;

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "participants_id", referencedColumnName = "id")
    //private List<User> listeMembres;
    private int paiement;
    private int annee;
    private String sujet;
    private String lienWeb;
    private String domaine;
    private String type;
}
