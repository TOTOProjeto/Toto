package br.edu.iff.ccc.webdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.webdev.dto.TarefaDTO;
import br.edu.iff.ccc.webdev.entities.Equipe;
import br.edu.iff.ccc.webdev.entities.Particao;
import br.edu.iff.ccc.webdev.entities.Tarefa;
import br.edu.iff.ccc.webdev.entities.Usuario;
import br.edu.iff.ccc.webdev.exception.TarefaNaoEncontrada;
import br.edu.iff.ccc.webdev.repository.EquipeRepository;
import br.edu.iff.ccc.webdev.repository.LabelsRepository;
import br.edu.iff.ccc.webdev.repository.ParticaoRepository;
import br.edu.iff.ccc.webdev.repository.TarefaRepository;
import br.edu.iff.ccc.webdev.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
@Service
public class TarefaService {

    @Autowired 
    private TarefaRepository tarefaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private ParticaoRepository particaoRepository;
    @Autowired
    private LabelsRepository labelsRepository;

    @Transactional
    public Tarefa salvar(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(tarefaDTO.getNome());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setPrazo(tarefaDTO.getPrazo());
        tarefa.setPrioridade(tarefaDTO.getPrioridade());

        if (tarefaDTO.getUsuarioId() != null) {
            Usuario resp = usuarioRepository.findById(tarefaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            tarefa.setResponsavel(resp);
        }

        if (tarefaDTO.getEquipeId() != null) {
            Equipe eqp = equipeRepository.findById(tarefaDTO.getEquipeId())
                .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
            tarefa.setEquipe(eqp);
        }
        
        if (tarefaDTO.getParticaoId() != null) {
            Particao part = particaoRepository.findById(tarefaDTO.getParticaoId())
                .orElseThrow(() -> new RuntimeException("Particao não encontrada"));
            tarefa.setStatus(part);
            part.adicionarAtividade(tarefa);
        }

        if (tarefaDTO.getLabelsIds() != null && !tarefaDTO.getLabelsIds().isEmpty()) {
            for (Integer labelId : tarefaDTO.getLabelsIds()) {
                labelsRepository.findById(labelId).ifPresent(tarefa::adicionarMarca);
            }
        }
        
        return tarefaRepository.save(tarefa);
    }
   
    @Transactional
public Tarefa atualizar(Long id, TarefaDTO dto) {
    Tarefa tarefaExistente = tarefaRepository.findById(id).orElse(null);
    if (tarefaExistente == null) return null;

    tarefaExistente.setTitulo(dto.getNome());
    tarefaExistente.setDescricao(dto.getDescricao());
    tarefaExistente.setPrazo(dto.getPrazo());
    tarefaExistente.setPrioridade(dto.getPrioridade());

    if (dto.getUsuarioId() != null) {
        Usuario resp = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        tarefaExistente.setResponsavel(resp);
    } else {
        tarefaExistente.setResponsavel(null);
    }

    if (dto.getEquipeId() != null) {
        Equipe eqp = equipeRepository.findById(dto.getEquipeId())
            .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
        tarefaExistente.setEquipe(eqp);
    } else {
        tarefaExistente.setEquipe(null);
    }

    if (dto.getParticaoId() != null) {
        Particao part = particaoRepository.findById(dto.getParticaoId())
            .orElseThrow(() -> new RuntimeException("Partição não encontrada"));
        tarefaExistente.setStatus(part);
        part.adicionarAtividade(tarefaExistente);
    } else {
        tarefaExistente.setStatus(null);
    }

    return tarefaRepository.save(tarefaExistente);
}

    public Tarefa buscarPorIdExcessaoTarefa(Long id) {
    return tarefaRepository.findById(id)
            .orElseThrow(() -> new TarefaNaoEncontrada("Tarefa com id " + id + " não encontrada no quadro."));
}
    public List<Tarefa> buscarUrgentes(Integer prioridade) {
        return tarefaRepository.buscarUrgentesPorPrioridade(prioridade);
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id).orElse(null);
    }
}