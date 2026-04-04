package br.edu.iff.ccc.webdev.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.ccc.webdev.dto.UsuarioDTO;
import br.edu.iff.ccc.webdev.entities.Usuario;
import br.edu.iff.ccc.webdev.service.UsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuario-lista";
    }

    @GetMapping("/new")
    public String formNovo(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "usuario-form";
    }

    @PostMapping("/new")
    public String salvar(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO dto,
                         BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            return "usuario-form";
        }
        try {
            usuarioService.salvar(dto);
            ra.addFlashAttribute("successMessage", "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao salvar usuário: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/edit")
    public String formEditar(@PathVariable Long id, Model model) {
        Usuario u = usuarioService.buscarPorId(id);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());

        model.addAttribute("usuarioDTO", dto);
        return "usuario-form";
    }

    @PostMapping("/{id}/edit")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("usuarioDTO") UsuarioDTO dto,
                            BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            dto.setId(id);
            return "usuario-form";
        }
        try {
            usuarioService.atualizar(id, dto);
            ra.addFlashAttribute("successMessage", "Usuário atualizado com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao atualizar usuário: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/delete")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            usuarioService.deletar(id);
            ra.addFlashAttribute("successMessage", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }
}