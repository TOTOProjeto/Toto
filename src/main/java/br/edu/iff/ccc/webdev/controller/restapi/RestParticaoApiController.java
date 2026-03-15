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

import br.edu.iff.ccc.webdev.dto.ParticaoDTO;
import br.edu.iff.ccc.webdev.entities.Particao;
import br.edu.iff.ccc.webdev.service.ParticaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/particoes")
public class RestParticaoApiController {

    @Autowired
    private ParticaoService particaoService;

    @PostMapping
    public ResponseEntity<ParticaoDTO> criar(@Valid @RequestBody ParticaoDTO dto) {
        Particao novaParticao = particaoService.salvar(dto);
        return new ResponseEntity<>(converterParaDTO(novaParticao), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParticaoDTO>> listar() {
        List<ParticaoDTO> particoes = particaoService.listarTodas().stream()
            .map(this::converterParaDTO)
            .toList();
        return ResponseEntity.ok(particoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticaoDTO> buscarPorId(@PathVariable Integer id) {
        Particao particao = particaoService.buscarPorId(id);
        
        if (particao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converterParaDTO(particao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticaoDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody ParticaoDTO dto) {
        Particao particaoAtualizada = particaoService.atualizar(id, dto);
        
        if (particaoAtualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converterParaDTO(particaoAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        particaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private ParticaoDTO converterParaDTO(Particao p) {
        ParticaoDTO dto = new ParticaoDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        
        if (p.getDiagrama() != null) {
            dto.setDiagramaId(p.getDiagrama().getId());
        }
        return dto;
    }
}