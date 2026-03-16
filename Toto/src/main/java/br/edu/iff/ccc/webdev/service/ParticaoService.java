package br.edu.iff.ccc.webdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iff.ccc.webdev.dto.ParticaoDTO;
import br.edu.iff.ccc.webdev.entities.Diagrama;
import br.edu.iff.ccc.webdev.entities.Particao;
import br.edu.iff.ccc.webdev.repository.DiagramaRepository;
import br.edu.iff.ccc.webdev.repository.ParticaoRepository;

@Service
public class ParticaoService {

    @Autowired
    private ParticaoRepository particaoRepository;

    @Autowired
    private DiagramaRepository diagramaRepository; 

    @Transactional
    public Particao salvar(ParticaoDTO dto) {
        Particao particao = new Particao();
        particao.setNome(dto.getNome());

        if (dto.getDiagramaId() != null) {
            Diagrama diagrama = diagramaRepository.findById(dto.getDiagramaId())
                .orElseThrow(() -> new RuntimeException("Diagrama não encontrado"));
            particao.setDiagrama(diagrama);
        }

        return particaoRepository.save(particao);
    }

    public List<Particao> listarTodas() {
        return particaoRepository.findAll();
    }

    public Particao buscarPorId(Integer id) {
        return particaoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Particao atualizar(Integer id, ParticaoDTO dto) {
        Particao particaoExistente = particaoRepository.findById(id).orElse(null);
        
        if (particaoExistente == null) {
            return null;
        }

        particaoExistente.setNome(dto.getNome());
        
        if (dto.getDiagramaId() != null) {
            Diagrama diagrama = diagramaRepository.findById(dto.getDiagramaId())
                .orElseThrow(() -> new RuntimeException("Diagrama não encontrado"));
            particaoExistente.setDiagrama(diagrama);
        }

        return particaoRepository.save(particaoExistente);
    }

    public void deletar(Integer id) {
        particaoRepository.deleteById(id);
    }
}