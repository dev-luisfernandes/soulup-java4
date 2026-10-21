
package br.com.fiap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/teste-erro")
    public String testeErro() {
        throw new RuntimeException("Ocorreu um erro inesperado no servidor. Tente novamente mais tarde");
    }

    @GetMapping("/teste-erro400")
    public String testeErro400() {
        throw new IllegalArgumentException("Os dados enviados são inválidos. Verifique as informações e tente novamente");
    }

}
