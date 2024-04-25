package org.example.api.api.equipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
    @Query("SELECT e FROM Equipe e LEFT JOIN FETCH e.listeMembres WHERE e.id = :id")
    Optional<Equipe> findById(@Param("id") Long id);
    Optional<Equipe> findByNom(String nom);
}
