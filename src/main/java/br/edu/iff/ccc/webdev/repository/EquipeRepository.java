package br.edu.iff.ccc.webdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.iff.ccc.webdev.entities.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}