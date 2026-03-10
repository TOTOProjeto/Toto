package br.edu.iff.ccc.webdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iff.ccc.webdev.dto.UsuarioDTO;
import br.edu.iff.ccc.webdev.entities.Usuario;
import br.edu.iff.ccc.webdev.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(UsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        return repository.save(u);
    }

    public List<Usuario> listarTodos() { 
        return repository.findAll(); 
    }
    public Usuario buscarPorId(Long id) { 
        return repository.findById(id).orElse(null); 
    }

    public void deletar(Long id) { 
        repository.deleteById(id); 
    }
}

