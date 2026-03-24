package br.edu.iff.ccc.webdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iff.ccc.webdev.dto.LabelsDTO;
import br.edu.iff.ccc.webdev.entities.Labels;
import br.edu.iff.ccc.webdev.repository.LabelsRepository;

@Service
public class LabelsService {

    @Autowired
    private LabelsRepository labelsRepository;

    @Transactional
    public Labels salvar(LabelsDTO dto) {
        Labels label = new Labels();
        label.setNome(dto.getNome());
        label.setDescricao(dto.getDescricao());
        return labelsRepository.save(label);
    }

    public List<Labels> listarTodas() {
        return labelsRepository.findAll();
    }

    public Labels buscarPorId(Integer id) {
        return labelsRepository.findById(id).orElse(null);
    }

    @Transactional
    public Labels atualizar(Integer id, LabelsDTO dto) {
        Labels labelExistente = labelsRepository.findById(id).orElse(null);
        
        if (labelExistente == null) {
            return null;
        }

        labelExistente.setNome(dto.getNome());
        labelExistente.setDescricao(dto.getDescricao());

        return labelsRepository.save(labelExistente);
    }

    public void deletar(Integer id) {
        labelsRepository.deleteById(id);
    }
}