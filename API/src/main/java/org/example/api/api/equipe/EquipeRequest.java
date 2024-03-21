package org.example.api.api.equipe;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipeRequest {
    private String nom;
    private String motDePasse;
}