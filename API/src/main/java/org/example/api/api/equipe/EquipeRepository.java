package org.example.api.api.equipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
    Optional<Equipe> findById(Long id);
    Optional<Equipe> findByNom(String nom);
}
