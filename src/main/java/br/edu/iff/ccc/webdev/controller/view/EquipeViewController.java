package br.edu.iff.ccc.webdev.controller.view;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.ccc.webdev.dto.EquipeDTO;
import br.edu.iff.ccc.webdev.entities.Equipe;
import br.edu.iff.ccc.webdev.service.EquipeService;
import br.edu.iff.ccc.webdev.service.UsuarioService;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/equipes")
public class EquipeViewController {

    @Autowired
    private EquipeService equipeService;

    @Autowired
    private UsuarioService usuarioService;

    // FIX: converte string vazia ("") para null em campos Long,
    // evitando o erro 500 quando o select de responsável não é preenchido.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("equipes", equipeService.listarTodas());
        return "equipe-lista";
    }

    @GetMapping("/new")
    public String formNovo(Model model) {
        model.addAttribute("equipeDTO", new EquipeDTO());
        carregarOpcoes(model);
        return "equipe-form";
    }

    @PostMapping("/new")
    public String salvar(@Valid @ModelAttribute("equipeDTO") EquipeDTO dto, BindingResult br, Model model, RedirectAttributes ra) {
        if (br.hasErrors()) {
            carregarOpcoes(model);
            return "equipe-form";
        }
        try {
            equipeService.salvar(dto);
            ra.addFlashAttribute("successMessage", "Equipe criada com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao criar equipe: " + e.getMessage());
        }
        return "redirect:/equipes";
    }

    @GetMapping("/{id}/edit")
    public String formEditar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Equipe e = equipeService.buscarPorId(id);
        if (e == null) {
            ra.addFlashAttribute("errorMessage", "Equipe não encontrada.");
            return "redirect:/equipes";
        }

        EquipeDTO dto = new EquipeDTO();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setDescricao(e.getDescricao());

        if (e.getResponsavel() != null) {
            dto.setResponsavelId(e.getResponsavel().getId());
        }
        if (e.getMembros() != null && !e.getMembros().isEmpty()) {
            dto.setMembrosIds(e.getMembros().stream()
                .map(br.edu.iff.ccc.webdev.entities.Usuario::getId)
                .collect(Collectors.toList()));
        }

        model.addAttribute("equipeDTO", dto);
        carregarOpcoes(model);
        return "equipe-form";
    }

    @PostMapping("/{id}/edit")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("equipeDTO") EquipeDTO dto, BindingResult br, Model model, RedirectAttributes ra) {
        if (br.hasErrors()) {
            dto.setId(id);
            carregarOpcoes(model);
            return "equipe-form";
        }
        try {
            equipeService.atualizar(id, dto);
            ra.addFlashAttribute("successMessage", "Equipe atualizada com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao atualizar equipe: " + e.getMessage());
        }
        return "redirect:/equipes";
    }

    @PostMapping("/{id}/delete")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            equipeService.deletar(id);
            ra.addFlashAttribute("successMessage", "Equipe excluída com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao excluir equipe: " + e.getMessage());
        }
        return "redirect:/equipes";
    }

    private void carregarOpcoes(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
    }
}