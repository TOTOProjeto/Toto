package br.edu.iff.ccc.webdev.controller.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class RestMainApiController {

    @GetMapping()
    public ResponseEntity<String> getApiHome() {
        return ResponseEntity.ok("Bem vindo ao API REST de WebDev!");
    }
    
}
