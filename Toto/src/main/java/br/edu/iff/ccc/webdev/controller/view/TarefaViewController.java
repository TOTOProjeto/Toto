package br.edu.iff.ccc.webdev.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.webdev.entities.Tarefa;
import br.edu.iff.ccc.webdev.service.TarefaService;

@Controller
@RequestMapping("/tarefas")
public class TarefaViewController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/{id}")
    public String detalhesTarefa(@PathVariable Long id, Model model) {
        Tarefa tarefa = tarefaService.buscarPorId(id);

        if (tarefa == null) {
            model.addAttribute("errorMessage", "Tarefa com ID " + id + " não encontrada.");
            return "tarefa-detalhes";
        }

        model.addAttribute("tarefa", tarefa);
        return "tarefa-detalhes";
    }
}