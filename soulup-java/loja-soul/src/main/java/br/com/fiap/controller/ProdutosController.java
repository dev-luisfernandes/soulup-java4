package br.com.fiap.controller;

import br.com.fiap.bo.ProdutosBo;
import br.com.fiap.entities.Produtos;
import br.com.fiap.excecoes.ExcecoesConexao;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    ProdutosBo produtosBo = new ProdutosBo();

    // Selecionar
    @GetMapping
    public ArrayList<Produtos> selecionarRs() throws ExcecoesConexao {
        return produtosBo.selecionarBo();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Produtos buscarPorIdRs(@PathVariable int id) throws ExcecoesConexao, ExcecoesRegraNegocio {
        return produtosBo.buscarPorIdBo(id);
    }

    // Inserir
    @PostMapping
    public ResponseEntity<Void> inserirRs(@RequestBody Produtos produto) throws ExcecoesConexao, ExcecoesRegraNegocio {
        produtosBo.inserirBo(produto);

        URI location = URI.create("/produtos/" + produto.getId());
        return ResponseEntity.created(location).build();
    }

    // Atualizar
    @PutMapping
    public ResponseEntity<Void> atualizarRs(@RequestBody Produtos produto) throws ExcecoesConexao, ExcecoesRegraNegocio {
        produtosBo.atualizarBo(produto);
        return ResponseEntity.ok().build();
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRs(@PathVariable int id) throws ExcecoesConexao, ExcecoesRegraNegocio {
        produtosBo.deletarBo(id);
        return ResponseEntity.ok().build();
    }
}
