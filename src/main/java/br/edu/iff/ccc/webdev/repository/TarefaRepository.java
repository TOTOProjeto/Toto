package br.edu.iff.ccc.webdev.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.webdev.entities.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM Tarefa t WHERE t.prazo = CURRENT_DATE")
    List<Tarefa> buscarTarefasParaHoje();

    @Query("SELECT t FROM Tarefa t WHERE t.prioridade = :prio AND t.prazo IS NOT NULL")
    List<Tarefa> buscarUrgentesPorPrioridade(@Param("prio") Integer prio);
}
