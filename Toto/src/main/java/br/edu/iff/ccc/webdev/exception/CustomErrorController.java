package br.edu.iff.ccc.webdev.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.webmvc.autoconfigure.error.BasicErrorController;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@Primary
@ConditionalOnMissingBean(BasicErrorController.class)
public class CustomErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object path   = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        int statusCode = (status != null) ? Integer.parseInt(status.toString()) : 500;

        model.addAttribute("caminho", path != null ? path : "desconhecido");

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("mensagem", "A página que você tentou acessar não existe ou foi removida.");
            return "error/404";
        }

        model.addAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente em instantes.");
        return "error/500";
    }
}