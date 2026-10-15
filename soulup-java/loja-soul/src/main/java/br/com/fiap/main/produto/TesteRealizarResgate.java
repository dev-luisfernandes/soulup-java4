package br.com.fiap.main.produto;

import br.com.fiap.dao.ProdutosDao;
import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.entities.Produtos;
import br.com.fiap.entities.Usuario;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteRealizarResgate {

    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }
    static int inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }
    static double real(String j){
        return  Double.parseDouble(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UsuarioDao daoUsuario = new UsuarioDao();
        ProdutosDao daoProduto = new ProdutosDao();

        String cpfUsuario = texto("RESGATE: Digite o CPF do Usuário:");
        int idProduto = inteiro("RESGATE: Digite o ID do Produto desejado:");

        //ele vai la no nosso db e busca u cpf do caba e o produto que ele quer
        Usuario usuario = daoUsuario.buscarPorCpf(cpfUsuario);
        Produtos produto = daoProduto.buscarPorId(idProduto);

        // ve se o usuario e o produto estão lá no banco
        if (usuario != null && produto != null) {

            /*aqui ele vai ver se o o estoque é maior que zero, ou seja
             * se o produto estiver disponivel no estoque beleza, ele vai para a parte se os pontos
             * do usuario bate com os pontos que precisa para o resgate*/
            if (produto.getQuantidade() > 0)
            {



                if (usuario.getPontos() >= produto.getPontos()) {
                    //se der ele faz o resgate e tira os pontos do saldo do usuario e atualiza o saldo
                    double saldoRestante = usuario.getPontos() - produto.getPontos();
                    usuario.setPontos(saldoRestante);
                    //aqui ele vai tirar 1 la do estoque
                    int estoqueRestante = produto.getQuantidade() - 1;
                    produto.setQuantidade(estoqueRestante);

                    daoUsuario.atualizar(usuario);
                    daoProduto.atualizar(produto);
                    JOptionPane.showMessageDialog(
                            null,
                            "Parabéns, " + usuario.getNome() +
                                    "! Resgate realizado com sucesso.\n" +
                                    "Produto: " + produto.getNome() + "\n" +
                                    "Seu novo saldo de pontos é: " + saldoRestante);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Ops! Saldo insuficiente. Você tem " + usuario.getPontos() +
                                    " pontos, mas precisa de " + produto.getPontos() + ".");
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Poxa, esse produto está fora de estoque no momento!");
            }

        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro: Usuário ou Produto não encontrados no sistema!");
        }
    }
}