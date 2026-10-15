package br.com.fiap.main.produto;

import br.com.fiap.dao.ProdutosDao;
import br.com.fiap.entities.Produtos;
import java.sql.SQLException;
import java.util.ArrayList;

public class TesteSelecionarProdutos {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ProdutosDao dao = new ProdutosDao();
        ArrayList<Produtos> lista = dao.selecionar();

        // se a lista não estiver vazia ele vai dar o retorno do que tem nessa lista
        if (lista != null) {
            //pega os produto da lista define como p e imprime
            for (Produtos p : lista) {
                System.out.println(p.toString());
            }
        }
    }
}