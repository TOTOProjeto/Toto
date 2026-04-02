package br.edu.iff.ccc.webdev.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.ccc.webdev.dto.ParticaoDTO;
import br.edu.iff.ccc.webdev.service.ParticaoService;

@Controller
@RequestMapping("/particoes")
public class ParticaoViewController {

    @Autowired
    private ParticaoService particaoService;


    @PostMapping("/new")
    public String criarParticao(@RequestParam String nome,
                                @RequestParam Integer diagramaId,
                                RedirectAttributes ra) {
        if (nome == null || nome.isBlank()) {
            ra.addFlashAttribute("errorMessage", "O nome da partição não pode estar vazio.");
            return "redirect:/diagramas/" + diagramaId;
        }
        try {
            ParticaoDTO dto = new ParticaoDTO();
            dto.setNome(nome.trim());
            dto.setDiagramaId(diagramaId);
            particaoService.salvar(dto);
            ra.addFlashAttribute("successMessage", "Coluna \"" + nome.trim() + "\" criada com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao criar coluna: " + e.getMessage());
        }
        return "redirect:/diagramas/" + diagramaId;
    }

    @PostMapping("/{id}/delete")
    public String excluirParticao(@PathVariable Integer id,
                                  @RequestParam(required = false) Integer diagramaId,
                                  RedirectAttributes ra) {
        try {
            particaoService.deletar(id);
            ra.addFlashAttribute("successMessage", "Coluna excluída com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Erro ao excluir coluna: " + e.getMessage());
        }
        if (diagramaId != null) {
            return "redirect:/diagramas/" + diagramaId;
        }
        return "redirect:/diagramas";
    }
}