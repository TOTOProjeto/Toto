package br.edu.iff.ccc.webdev.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.ccc.webdev.dto.TarefaDTO;
import br.edu.iff.ccc.webdev.entities.Tarefa;
import br.edu.iff.ccc.webdev.service.TarefaService;

@Controller
@RequestMapping("/tarefas")
public class TarefaViewController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public String listarTarefas(Model model) {
        model.addAttribute("tarefas", tarefaService.listarTodas());
        return "tarefa-lista";
    }

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

    @GetMapping("/new")
    public String exibirFormulario(Model model) {
        model.addAttribute("tarefaDTO", new TarefaDTO());
        return "tarefa-form";
    }

    @PostMapping("/new")
    public String salvarTarefa(TarefaDTO tarefaDTO,
                               RedirectAttributes redirectAttributes) {
        try {
            tarefaService.salvar(tarefaDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Tarefa salva com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar: " + e.getMessage());
        }
        return "redirect:/tarefas";
    }
}