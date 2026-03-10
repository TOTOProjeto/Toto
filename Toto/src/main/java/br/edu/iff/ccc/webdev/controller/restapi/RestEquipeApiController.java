package br.edu.iff.ccc.webdev.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.webdev.dto.EquipeDTO;
import br.edu.iff.ccc.webdev.entities.Equipe;
import br.edu.iff.ccc.webdev.service.EquipeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/equipes")
public class RestEquipeApiController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping
    public ResponseEntity<EquipeDTO> criar(@Valid @RequestBody EquipeDTO equipeDTO) {
        Equipe novaEquipe = equipeService.salvar(equipeDTO);
        return new ResponseEntity<>(converterParaDTO(novaEquipe), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EquipeDTO>> listar() {
        List<EquipeDTO> equipes = equipeService.listarTodas().stream()
            .map(this::converterParaDTO)
            .toList();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeDTO> buscarPorId(@PathVariable Long id) {
        Equipe equipe = equipeService.buscarPorId(id);
        if (equipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converterParaDTO(equipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        equipeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Método auxiliar de conversão
    private EquipeDTO converterParaDTO(Equipe e) {
        EquipeDTO dto = new EquipeDTO();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setDescricao(e.getDescricao());
        dto.setResponsavelId(e.getResponsavel() != null ? e.getResponsavel().getId() : null);
        
        if (e.getMembros() != null) {
            dto.setMembrosIds(e.getMembros().stream().map(br.edu.iff.ccc.webdev.entities.Usuario::getId).toList());
        }
        return dto;
    }
}