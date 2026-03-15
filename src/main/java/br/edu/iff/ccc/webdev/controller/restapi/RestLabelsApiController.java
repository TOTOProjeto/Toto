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

import br.edu.iff.ccc.webdev.dto.LabelsDTO;
import br.edu.iff.ccc.webdev.entities.Labels;
import br.edu.iff.ccc.webdev.service.LabelsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/labels")
public class RestLabelsApiController {

    @Autowired
    private LabelsService labelsService;

    @PostMapping
    public ResponseEntity<LabelsDTO> criar(@Valid @RequestBody LabelsDTO dto) {
        Labels novaLabel = labelsService.salvar(dto);
        return new ResponseEntity<>(converterParaDTO(novaLabel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LabelsDTO>> listar() {
        List<LabelsDTO> labels = labelsService.listarTodas().stream()
            .map(this::converterParaDTO)
            .toList();
        return ResponseEntity.ok(labels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabelsDTO> buscarPorId(@PathVariable Integer id) {
        Labels label = labelsService.buscarPorId(id);
        
        if (label == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converterParaDTO(label));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabelsDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody LabelsDTO dto) {
        Labels labelAtualizada = labelsService.atualizar(id, dto);
        
        if (labelAtualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converterParaDTO(labelAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        labelsService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private LabelsDTO converterParaDTO(Labels l) {
        LabelsDTO dto = new LabelsDTO();
        dto.setId(l.getId());
        dto.setNome(l.getNome());
        dto.setDescricao(l.getDescricao());
        return dto;
    }
}