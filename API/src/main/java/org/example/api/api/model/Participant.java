package org.example.api.api.model;

import jakarta.persistence.*;
import org.example.api.api.user.User;


@Entity
@Table(name = "participant")
public class Participant extends User {
    private String categorie;
    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "equipe_id")
    //private Equipe equipe;
    private String choixPaiement;
    private boolean paye;
    
}
