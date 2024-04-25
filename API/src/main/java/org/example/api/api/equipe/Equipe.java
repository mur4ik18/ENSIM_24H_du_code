package org.example.api.api.equipe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.servlet.http.Part;
import lombok.*;
import org.example.api.api.user.User;
import org.hibernate.collection.spi.PersistentBag;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "equipe_id", referencedColumnName = "id")
    private Set<User> listeMembres = new HashSet<>();
    private int paiement;
    private int annee;
    private String sujet;
    private String lienWeb;
    private String domaine;
    private String type;
}
