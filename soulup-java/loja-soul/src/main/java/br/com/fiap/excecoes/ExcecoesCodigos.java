
package br.com.fiap.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@RestControllerAdvice
public class ExcecoesCodigos extends Exception{

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> erro404(
            NoResourceFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "erro", "404",
                        "mensagem", "Recurso não encontrado."
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> erro500(Exception e) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of(
                        "erro", "500",
                        "mensagem", "Ocorreu um erro interno no servidor."
                )
        );
    }
}
