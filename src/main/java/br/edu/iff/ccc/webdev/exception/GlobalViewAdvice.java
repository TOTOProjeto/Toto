package br.edu.iff.ccc.webdev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalViewAdvice {

    @ModelAttribute("currentUri")
    public String currentUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Exception ex, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("titulo", "Página não encontrada");
        model.addAttribute("mensagem", "A página que você tentou acessar não existe ou foi removida.");
        model.addAttribute("detalhe", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(TarefaNaoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTarefaNaoEncontrada(TarefaNaoEncontrada ex, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("titulo", "Tarefa não encontrada");
        model.addAttribute("mensagem", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericError(Exception ex, Model model) {
        model.addAttribute("status", 500);
        model.addAttribute("titulo", "Erro interno do servidor");
        model.addAttribute("mensagem", "Ocorreu um erro inesperado. Por favor, tente novamente em instantes.");
        model.addAttribute("detalhe", ex.getMessage());
        return "error/500";
    }
}