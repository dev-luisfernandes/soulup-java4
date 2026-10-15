package br.com.fiap.main.produto;

import br.com.fiap.dao.ProdutosDao;
import br.com.fiap.entities.Produtos;
import javax.swing.*;
import java.sql.SQLException;

public class TesteAtualizarProdutos {

    static String texto(String j){
        return JOptionPane.showInputDialog(j); }
    static int inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j)); }
    static double real(String j){
        return Double.parseDouble(JOptionPane.showInputDialog(j)); }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Produtos objProdutos = new Produtos();
        ProdutosDao dao = new ProdutosDao();

        objProdutos.setId(inteiro("Informe o ID do produto que será atualizado"));
        objProdutos.setNome(texto("Novo Nome"));
        objProdutos.setQuantidade(inteiro("Nova Quantidade"));
        objProdutos.setTipo(texto("Novo Tipo"));
        objProdutos.setPontos(real("Novos Pontos"));

        System.out.println(dao.atualizar(objProdutos));
    }
}