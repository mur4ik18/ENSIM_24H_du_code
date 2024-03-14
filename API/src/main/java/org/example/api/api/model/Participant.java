package org.example.api.api.model;

import javax.persistence.OneToMany;

public class Participant extends User {
    private String categorie;
    @OneToMany(mappedBy = "equipe")
    private Equipe equipe;
    private String choixPaiement;
    private boolean paye;
    
}
