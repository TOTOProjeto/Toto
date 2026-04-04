package br.edu.iff.ccc.webdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iff.ccc.webdev.dto.DiagramaDTO;
import br.edu.iff.ccc.webdev.entities.Diagrama;
import br.edu.iff.ccc.webdev.entities.Equipe;
import br.edu.iff.ccc.webdev.entities.Usuario;
import br.edu.iff.ccc.webdev.exception.DiagramaNaoEncontrado;
import br.edu.iff.ccc.webdev.repository.DiagramaRepository;
import br.edu.iff.ccc.webdev.repository.EquipeRepository;
import br.edu.iff.ccc.webdev.repository.UsuarioRepository;

@Service
public class DiagramaService {

    @Autowired
    private DiagramaRepository diagramaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Transactional
    public Diagrama salvar(DiagramaDTO dto) {
        Diagrama diagrama = new Diagrama();
        diagrama.setNome(dto.getNome());
        diagrama.setDescricao(dto.getDescricao());

        if (dto.getDonoId() != null) {
            Usuario dono = usuarioRepository.findById(dto.getDonoId())
                .orElseThrow(() -> new RuntimeException("Usuário (Dono) não encontrado"));
            diagrama.setDono(dono);
        }

        if (dto.getTimeId() != null) {
            Equipe time = equipeRepository.findById(dto.getTimeId())
                .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
            diagrama.setTime(time);
        }

        return diagramaRepository.save(diagrama);
    }

    public List<Diagrama> listarTodos() {
        return diagramaRepository.findAll();
    }

    public Diagrama buscarPorId(Integer id) {
        return diagramaRepository.findById(id)
            .orElseThrow(() -> new DiagramaNaoEncontrado("Diagrama #" + id + " não encontrado."));
    }

    @Transactional
    public Diagrama atualizar(Integer id, DiagramaDTO dto) {
        Diagrama diagramaExistente = diagramaRepository.findById(id)
            .orElseThrow(() -> new DiagramaNaoEncontrado("Diagrama #" + id + " não encontrado."));

        diagramaExistente.setNome(dto.getNome());
        diagramaExistente.setDescricao(dto.getDescricao());

        if (dto.getDonoId() != null) {
            Usuario dono = usuarioRepository.findById(dto.getDonoId())
                .orElseThrow(() -> new RuntimeException("Usuário (Dono) não encontrado ao atualizar"));
            diagramaExistente.setDono(dono);
        } else {
            diagramaExistente.setDono(null);
        }

        if (dto.getTimeId() != null) {
            Equipe time = equipeRepository.findById(dto.getTimeId())
                .orElseThrow(() -> new RuntimeException("Equipe não encontrada ao atualizar"));
            diagramaExistente.setTime(time);
        } else {
            diagramaExistente.setTime(null);
        }

        return diagramaRepository.save(diagramaExistente);
    }

    public void deletar(Integer id) {
        diagramaRepository.deleteById(id);
    }
}