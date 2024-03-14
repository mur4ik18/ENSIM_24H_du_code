package org.example.api.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "equipe")
public class Equipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Partnaire Sponsor;
    private String image;
    private String motDePasse;
    private List<Participant> listeMembres;
    private int paiement;
    private int annee;
    private String sujet;
    private String lienWeb;
    private String domaine;
    private String type;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
