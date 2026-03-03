package br.edu.iff.ccc.webdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.webdev.dto.TarefaDTO;
import br.edu.iff.ccc.webdev.entities.Tarefa;
import br.edu.iff.ccc.webdev.repository.TarefaRepository;

@Service
public class TarefaService {

    @Autowired 
    private TarefaRepository tarefaRepository;

    public Tarefa salvar(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();
        
        tarefa.setTitulo(tarefaDTO.getNome());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        
        return tarefaRepository.save(tarefa);
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