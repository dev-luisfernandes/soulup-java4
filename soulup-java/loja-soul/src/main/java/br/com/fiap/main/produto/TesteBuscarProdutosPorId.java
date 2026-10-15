package br.com.fiap.main.produto;

import br.com.fiap.dao.ProdutosDao;
import br.com.fiap.entities.Produtos;
import javax.swing.*;
import java.sql.SQLException;

public class TesteBuscarProdutosPorId {

    static int inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j)); }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Produtos objProdutos = new Produtos();
        ProdutosDao dao = new ProdutosDao();

        objProdutos.setId(inteiro("Informe o ID para busca"));
        objProdutos = dao.buscarPorId(objProdutos.getId());

        //aqui se o produto existir mesmo ele vai retornar
        if (objProdutos != null) {
            System.out.println(objProdutos.toString());
            //aqui, padrão... ele só aparece que n foi encontrado mesmo
        } else {
            System.out.println("Não encontrado no banco de dados!");
        }
    }
}