package br.com.fiap.main.produto;
//import do dao pq ele pega a função criada lá e adiciona apenas a funcionalidade aqui basicamente o que o usuario faz
import br.com.fiap.dao.ProdutosDao;
import br.com.fiap.entities.Produtos;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteInserirProdutos {

    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }
    static int inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }
    static double real(String j){
        return Double.parseDouble(JOptionPane.showInputDialog(j));
    }
    //funcionalidade ligada no nosso banco de dados
    // O throws avisa o java q se der algum erro com o Oracle (SQLException),
    // ele passa o erro para quem executou, o main e o main passa onde esta executando, a maquina e se der erro o programa para
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ProdutosDao dao = new ProdutosDao();
        Produtos objProduto = new Produtos();

        objProduto.setNome(texto("Nome do Produto:"));
        objProduto.setQuantidade(inteiro("Quantidade em Estoque:"));
        objProduto.setTipo(texto("Tipo:"));
        objProduto.setPontos(real("Custo em Pontos:"));

        JOptionPane.showMessageDialog(
                null,
                dao.inserir(objProduto));
    }
}