package org.example.api.api.request;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipeRequest {
    private Long id;
    private String nom;
    private String motDePasse;
}