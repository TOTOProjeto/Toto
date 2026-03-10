package br.edu.iff.ccc.webdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iff.ccc.webdev.dto.EquipeDTO;
import br.edu.iff.ccc.webdev.entities.Equipe;
import br.edu.iff.ccc.webdev.entities.Usuario;
import br.edu.iff.ccc.webdev.repository.EquipeRepository;
import br.edu.iff.ccc.webdev.repository.UsuarioRepository;

@Service
public class EquipeService {
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Equipe atualizar(Long id, EquipeDTO dto) {
        Equipe equipeExistente = equipeRepository.findById(id).orElse(null);
        if (equipeExistente == null) {
            return null;
        }

        equipeExistente.setNome(dto.getNome());
        equipeExistente.setDescricao(dto.getDescricao());

        // Atualiza o responsável
        if (dto.getResponsavelId() != null) {
            Usuario resp = usuarioRepository.findById(dto.getResponsavelId()).orElse(null);
            equipeExistente.setResponsavel(resp);
        } else {
            equipeExistente.setResponsavel(null);
        }

        // Atualiza os membros (limpa a lista atual e adiciona os novos)
        equipeExistente.getMembros().clear();
        if (dto.getMembrosIds() != null && !dto.getMembrosIds().isEmpty()) {
            for (Long membroId : dto.getMembrosIds()) {
                usuarioRepository.findById(membroId).ifPresent(equipeExistente::adicionarMembro);
            }
        }

        return equipeRepository.save(equipeExistente);
    }
    
    @Transactional
    public Equipe salvar(EquipeDTO dto) {
    Equipe equipe = new Equipe();
    equipe.setNome(dto.getNome());
    equipe.setDescricao(dto.getDescricao());

    if (dto.getResponsavelId() != null) {
        Usuario resp = usuarioRepository.findById(dto.getResponsavelId()).orElse(null);
        equipe.setResponsavel(resp);
    }

    if (dto.getMembrosIds() != null && !dto.getMembrosIds().isEmpty()) {
        for (Long id : dto.getMembrosIds()) {
            usuarioRepository.findById(id).ifPresent(membro -> {
                equipe.adicionarMembro(membro);
            });
        }
    }
    return equipeRepository.save(equipe);
}

    public List<Equipe> listarTodas() {
    return equipeRepository.findAll();
}

    public Equipe buscarPorId(Long id) {
    return equipeRepository.findById(id).orElse(null);
}

    public void deletar(Long id) {
    equipeRepository.deleteById(id);
}
}