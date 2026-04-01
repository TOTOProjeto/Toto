package br.edu.iff.ccc.webdev.exception;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiGlobalAdviceException {

    @ExceptionHandler(TarefaNaoEncontrada.class)
    public ProblemDetail handleTarefaNaoEncontrada(HttpServletRequest req, TarefaNaoEncontrada e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        
        problemDetail.setTitle("Tarefa Não Encontrada");
        problemDetail.setProperty("url", req.getRequestURL().toString());
        problemDetail.setProperty("timestamp", LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString());
        problemDetail.setProperty("status", HttpStatus.NOT_FOUND.value() + " " + HttpStatus.NOT_FOUND.name());
        problemDetail.setProperty("message", e.getMessage());
        problemDetail.setProperty("exception", e.getClass().getName());
        problemDetail.setProperty("path", req.getRequestURI());
        
        return problemDetail;
    }
}