package br.edu.iff.ccc.webdev.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.ccc.webdev.dto.TarefaDTO;
import br.edu.iff.ccc.webdev.entities.Equipe;
import br.edu.iff.ccc.webdev.entities.Particao;
import br.edu.iff.ccc.webdev.entities.Tarefa;
import br.edu.iff.ccc.webdev.entities.Usuario;
import br.edu.iff.ccc.webdev.service.EquipeService;
import br.edu.iff.ccc.webdev.service.ParticaoService;
import br.edu.iff.ccc.webdev.service.TarefaService;
import br.edu.iff.ccc.webdev.service.UsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tarefas")
public class TarefaViewController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EquipeService equipeService;

    @Autowired
    private ParticaoService particaoService;

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
        }
        model.addAttribute("tarefa", tarefa);
        return "tarefa-detalhes";
    }

    @GetMapping("/new")
    public String exibirFormularioCriacao(Model model) {
        model.addAttribute("tarefaDTO", new TarefaDTO());
        carregarDadosFormulario(model);
        return "tarefa-form";
    }

    @PostMapping("/new")
    public String salvarTarefa(@Valid TarefaDTO tarefaDTO,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            carregarDadosFormulario(model);
            return "tarefa-form";
        }

        try {
            tarefaService.salvar(tarefaDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Tarefa criada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar: " + e.getMessage());
        }
        return "redirect:/tarefas";
    }

    @GetMapping("/{id}/edit")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model,
                                          RedirectAttributes redirectAttributes) {
        Tarefa tarefa = tarefaService.buscarPorId(id);
        if (tarefa == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tarefa não encontrada para edição.");
            return "redirect:/tarefas";
        }

        TarefaDTO dto = new TarefaDTO();
        dto.setId(tarefa.getId());
        dto.setNome(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setPrazo(tarefa.getPrazo());
        dto.setPrioridade(tarefa.getPrioridade());
        if (tarefa.getResponsavel() != null) dto.setUsuarioId(tarefa.getResponsavel().getId());
        if (tarefa.getEquipe()      != null) dto.setEquipeId(tarefa.getEquipe().getId());
        if (tarefa.getStatus()      != null) dto.setParticaoId(tarefa.getStatus().getId());

        model.addAttribute("tarefaDTO", dto);
        carregarDadosFormulario(model);
        return "tarefa-form";
    }

    @PostMapping("/{id}/edit")
    public String atualizarTarefa(@PathVariable Long id,
                                  @Valid TarefaDTO tarefaDTO,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            tarefaDTO.setId(id);
            model.addAttribute("tarefaDTO", tarefaDTO);
            carregarDadosFormulario(model);
            return "tarefa-form";
        }

        try {
            tarefaService.atualizar(id, tarefaDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Tarefa atualizada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar: " + e.getMessage());
        }
        return "redirect:/tarefas";
    }

    @PostMapping("/{id}/delete")
    public String excluirTarefa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            tarefaService.deletar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Tarefa excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/tarefas";
    }

    private void carregarDadosFormulario(Model model) {
        List<Usuario>  usuarios = usuarioService.listarTodos();
        List<Equipe>   equipes  = equipeService.listarTodas();
        List<Particao> particoes = particaoService.listarTodas();
        model.addAttribute("usuarios",  usuarios);
        model.addAttribute("equipes",   equipes);
        model.addAttribute("particoes", particoes);
    }
}