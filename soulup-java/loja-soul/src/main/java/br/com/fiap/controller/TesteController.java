
package br.com.fiap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/teste-erro")
    public String testeErro() {
        throw new RuntimeException("Erro de teste");
    }
}
