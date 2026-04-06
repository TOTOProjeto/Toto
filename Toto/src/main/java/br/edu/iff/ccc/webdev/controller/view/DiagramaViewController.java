package br.edu.iff.ccc.webdev.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.ccc.webdev.dto.DiagramaDTO;
import br.edu.iff.ccc.webdev.entities.Diagrama;
import br.edu.iff.ccc.webdev.service.DiagramaService;
import br.edu.iff.ccc.webdev.service.EquipeService;
import br.edu.iff.ccc.webdev.service.UsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/diagramas")
public class DiagramaViewController {

    @Autowired private DiagramaService diagramaService;
    @Autowired private UsuarioService  usuarioService;
    @Autowired private EquipeService   equipeService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("diagramas", diagramaService.listarTodos());
        return "diagrama-lista";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Integer id, Model model) {
        Diagrama d = diagramaService.buscarPorId(id);
        model.addAttribute("diagrama", d);
        return "diagrama-detalhes";
    }

    @GetMapping("/new")
    public String formNovo(Model model) {
        model.addAttribute("diagramaDTO", new DiagramaDTO());
        carregarForm(model);
        return "diagrama-form";
    }

    @PostMapping("/new")
    public String salvar(@Valid DiagramaDTO dto, BindingResult br, Model model,
                         RedirectAttributes ra) {
        if (br.hasErrors()) {
            carregarForm(model);
            return "diagrama-form";
        }
        try {
            diagramaService.salvar(dto);
            ra.addFlashAttribute("successMessage", "Diagrama criado com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao criar diagrama: " + e.getMessage());
        }
        return "redirect:/diagramas";
    }

    @GetMapping("/{id}/edit")
    public String formEditar(@PathVariable Integer id, Model model) {
        Diagrama d = diagramaService.buscarPorId(id);

        DiagramaDTO dto = new DiagramaDTO();
        dto.setId(d.getId());
        dto.setNome(d.getNome());
        dto.setDescricao(d.getDescricao());
        if (d.getDono()  != null) dto.setDonoId(d.getDono().getId());
        if (d.getTime()  != null) dto.setTimeId(d.getTime().getId());

        model.addAttribute("diagramaDTO", dto);
        carregarForm(model);
        return "diagrama-form";
    }

    @PostMapping("/{id}/edit")
    public String atualizar(@PathVariable Integer id, @Valid DiagramaDTO dto,
                             BindingResult br, Model model, RedirectAttributes ra) {
        if (br.hasErrors()) {
            dto.setId(id);
            model.addAttribute("diagramaDTO", dto);
            carregarForm(model);
            return "diagrama-form";
        }
        try {
            diagramaService.atualizar(id, dto);
            ra.addFlashAttribute("successMessage", "Diagrama atualizado com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao atualizar: " + e.getMessage());
        }
        return "redirect:/diagramas";
    }

    @PostMapping("/{id}/delete")
    public String excluir(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            diagramaService.deletar(id);
            ra.addFlashAttribute("successMessage", "Diagrama excluído com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/diagramas";
    }

    private void carregarForm(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("equipes",  equipeService.listarTodas());
    }
}