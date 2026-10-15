package br.com.fiap.controller;

import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.entities.Usuario;
import br.com.fiap.excecoes.ExcecoesConexao;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    UsuarioBo usuarioBo = new UsuarioBo();

    // Selecionar
    @GetMapping
    public ArrayList<Usuario> selecionarRs() throws ExcecoesConexao {
        return usuarioBo.selecionarBo();
    }

    // Buscar por CPF
    @GetMapping("/{cpf}")
    public Usuario buscarPorCpfRs(@PathVariable String cpf) throws ExcecoesConexao, ExcecoesRegraNegocio {
        return usuarioBo.buscarPorCpfBo(cpf);
    }

    // Inserir
    @PostMapping
    public ResponseEntity<Void> inserirRs(@RequestBody Usuario usuario) throws ExcecoesConexao, ExcecoesRegraNegocio {
        usuarioBo.inserirBo(usuario);

        URI location = URI.create("/usuario/" + usuario.getCPF());
        return ResponseEntity.created(location).build();
    }

    // Atualizar
    @PutMapping
    public ResponseEntity<Void> atualizarRs(@RequestBody Usuario usuario) throws ExcecoesConexao, ExcecoesRegraNegocio {
        usuarioBo.atualizarBo(usuario);
        return ResponseEntity.ok().build();
    }

    // Deletar
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarRs(@PathVariable String cpf) throws ExcecoesConexao, ExcecoesRegraNegocio {
        usuarioBo.deletarBo(cpf);
        return ResponseEntity.ok().build();
    }
}
