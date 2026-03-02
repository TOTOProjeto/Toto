package br.edu.iff.ccc.webdev.controller.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.webdev.dto.TarefaDTO;
import br.edu.iff.ccc.webdev.service.TarefaService;

@RestController
@RequestMapping("/api/v1/tarefas") 
public class RestTarefaApiController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public String criarTarefa(@ModelAttribute TarefaDTO tarefaDTO) {
        tarefaService.salvar(tarefaDTO);
        return "Tarefa Criada!";
    }

    @GetMapping
    public String listarTarefas() {
        return tarefaService.listarTodas().toString();
    }
}