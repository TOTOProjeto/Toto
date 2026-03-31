package br.edu.iff.ccc.webdev.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.webdev.service.DiagramaService;

@Controller
@RequestMapping("/")
public class MainViewController {

    private final DiagramaService diagramaService;

    public MainViewController(DiagramaService diagramaService) {
        this.diagramaService = diagramaService;
    }

    @GetMapping()
   public String paginaPrincipal() {
      return "index.html";
   }
    public String paginaPrincipal(Model model) {
        model.addAttribute("diagramas", diagramaService.listarTodos());
        return "index";
    }

}