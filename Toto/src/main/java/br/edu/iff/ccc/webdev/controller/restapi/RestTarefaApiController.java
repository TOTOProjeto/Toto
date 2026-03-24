package br.edu.iff.ccc.webdev.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.webdev.dto.TarefaDTO;
import br.edu.iff.ccc.webdev.entities.Tarefa;
import br.edu.iff.ccc.webdev.service.TarefaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tarefas")
public class RestTarefaApiController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@Valid @RequestBody TarefaDTO tarefaDTO) {
        Tarefa tarefaSalva = tarefaService.salvar(tarefaDTO);
        
        TarefaDTO response = converterParaDTO(tarefaSalva);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<TarefaDTO> listaDTO = tarefaService.listarTodas().stream()
            .map(this::converterParaDTO)
            .toList();
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.buscarPorIdExcessaoTarefa(id);
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build(); 
    }

    private TarefaDTO converterParaDTO(Tarefa t) {
        TarefaDTO dto = new TarefaDTO(
            t.getId(), 
            t.getTitulo(), 
            t.getDescricao(), 
            t.getDataCriacao(), 
            t.getPrazo(), 
            t.getPrioridade(),
            t.getResponsavel() != null ? t.getResponsavel().getId() : null,
            t.getEquipe() != null ? t.getEquipe().getId() : null
        );
        
        dto.setParticaoId(t.getStatus() != null ? t.getStatus().getId() : null);
        
        if (t.getMarcas() != null) {
            dto.setLabelsIds(t.getMarcas().stream().map(br.edu.iff.ccc.webdev.entities.Labels::getId).toList());
        }
        
        return dto;
    }

@GetMapping("/urgentes")
    public ResponseEntity<List<TarefaDTO>> listarUrgentes(@RequestParam Integer prioridade) {
        List<Tarefa> urgentes = tarefaService.buscarUrgentes(prioridade);
        
        List<TarefaDTO> urgentesDTO = urgentes.stream()
            .map(this::converterParaDTO)
            .toList();
            
        return ResponseEntity.ok(urgentesDTO);
    }
@PutMapping("/{id}")
public ResponseEntity<TarefaDTO> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TarefaDTO tarefaDTO) {
    Tarefa tarefaAtualizada = tarefaService.atualizar(id, tarefaDTO);
    if (tarefaAtualizada == null) {
         return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(converterParaDTO(tarefaAtualizada));
}
}