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
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.webdev.dto.DiagramaDTO;
import br.edu.iff.ccc.webdev.entities.Diagrama;
import br.edu.iff.ccc.webdev.service.DiagramaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/diagramas")
public class RestDiagramaApiController {

    @Autowired
    private DiagramaService diagramaService;

    @PostMapping
    public ResponseEntity<DiagramaDTO> criar(@Valid @RequestBody DiagramaDTO dto) {
        Diagrama novoDiagrama = diagramaService.salvar(dto);
        return new ResponseEntity<>(converterParaDTO(novoDiagrama), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiagramaDTO>> listar() {
        List<DiagramaDTO> diagramas = diagramaService.listarTodos().stream()
            .map(this::converterParaDTO)
            .toList();
        return ResponseEntity.ok(diagramas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagramaDTO> buscarPorId(@PathVariable Integer id) {
        Diagrama diagrama = diagramaService.buscarPorId(id);
        
        if (diagrama == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converterParaDTO(diagrama));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagramaDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody DiagramaDTO dto) {
        Diagrama diagramaAtualizado = diagramaService.atualizar(id, dto);
        
        if (diagramaAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converterParaDTO(diagramaAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        diagramaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private DiagramaDTO converterParaDTO(Diagrama d) {
        DiagramaDTO dto = new DiagramaDTO();
        dto.setId(d.getId());
        dto.setNome(d.getNome());
        dto.setDescricao(d.getDescricao());
        
        if (d.getDono() != null) {
            dto.setDonoId(d.getDono().getId());
        }
        if (d.getTime() != null) {
            dto.setTimeId(d.getTime().getId());
        }
        return dto;
    }
}