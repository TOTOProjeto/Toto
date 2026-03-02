package br.edu.iff.ccc.webdev.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.webdev.service.TarefaService;

@Controller
@RequestMapping("/tarefas")
public class TarefaViewController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public String listarTarefas(Model model) {
        model.addAttribute("tarefas", tarefaService.listarTodas());
        return "tarefas"; 
    }
}