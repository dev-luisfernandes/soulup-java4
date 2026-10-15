package br.com.fiap.bo;

import br.com.fiap.dao.ProdutosDao;
import br.com.fiap.entities.Produtos;
import br.com.fiap.excecoes.ExcecoesConexao;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;

import java.util.ArrayList;

public class ProdutosBo {

    private ProdutosDao produtosDao;

    // Selecionar
    public ArrayList<Produtos> selecionarBo() throws ExcecoesConexao {
        try {
            produtosDao = new ProdutosDao();
            // Regra de negocios
            return produtosDao.selecionar();
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Buscar por ID
    public Produtos buscarPorIdBo(int id) throws ExcecoesConexao {
        try {
            produtosDao = new ProdutosDao();
            return produtosDao.buscarPorId(id);
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Inserir
    public void inserirBo(Produtos produto) throws ExcecoesConexao, ExcecoesRegraNegocio {
        // Regra de negocios: validar os dados antes de ir ao banco
        validarProduto(produto);

        try {
            produtosDao = new ProdutosDao();

            // Regra de negocios: não pode ter dois produtos com o mesmo ID
            if (produtosDao.buscarPorId(produto.getId()) != null) {
                throw new ExcecoesRegraNegocio("Já existe um produto cadastrado com este ID.");
            }

            produtosDao.inserir(produto);
        } catch (ExcecoesRegraNegocio e) {
            throw e;
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Atualizar
    public void atualizarBo(Produtos produto) throws ExcecoesConexao, ExcecoesRegraNegocio {
        validarProduto(produto);

        try {
            produtosDao = new ProdutosDao();

            // Regra de negocios: só atualiza produto que existe
            if (produtosDao.buscarPorId(produto.getId()) == null) {
                throw new ExcecoesRegraNegocio("Produto não encontrado para o ID informado.");
            }

            produtosDao.atualizar(produto);
        } catch (ExcecoesRegraNegocio e) {
            throw e;
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Deletar
    public void deletarBo(int id) throws ExcecoesConexao, ExcecoesRegraNegocio {
        try {
            produtosDao = new ProdutosDao();

            // Regra de negocios: só deleta produto que existe
            if (produtosDao.buscarPorId(id) == null) {
                throw new ExcecoesRegraNegocio("Produto não encontrado para o ID informado.");
            }

            produtosDao.deletar(id);
        } catch (ExcecoesRegraNegocio e) {
            throw e;
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Validações dos dados do produto
    private void validarProduto(Produtos produto) throws ExcecoesRegraNegocio {
        if (produto == null) {
            throw new ExcecoesRegraNegocio("Os dados do produto não foram informados.");
        }

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new ExcecoesRegraNegocio("O nome do produto é obrigatório.");
        }

        if (produto.getTipo() == null || produto.getTipo().trim().isEmpty()) {
            throw new ExcecoesRegraNegocio("O tipo do produto é obrigatório.");
        }

        if (produto.getQuantidade() < 0) {
            throw new ExcecoesRegraNegocio("A quantidade em estoque não pode ser negativa.");
        }

        if (produto.getPontos() <= 0) {
            throw new ExcecoesRegraNegocio("Os pontos do produto devem ser maiores que zero.");
        }
    }
}
