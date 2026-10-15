package br.com.fiap.main.usuario;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.entities.Usuario;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteInserirUsuario {

    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }
    static int inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }
    static double real(String j){
        return Double.parseDouble(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Usuario objUsuario = new Usuario();
        UsuarioDao dao = new UsuarioDao();

        objUsuario.setCPF(texto("Digite o CPF nesse formato: (xxxxxxxxxx):"));
        objUsuario.setNome(texto("Nome do Usuário:"));
        objUsuario.setPontos(real("Pontuação Inicial (ex: 0):"));
        objUsuario.setCelular(texto("Digite o numero ex:' (xx)xxxxxxxxx ':"));
        objUsuario.setEmail(texto("digite o email:"));
        objUsuario.setDataNascimento(texto("digite a data de nascimento no formato DD/MM/AA "));
        objUsuario.setSituacao(texto("Digite a Situação (ex: ATIVO / INATIVO):").toUpperCase());

        JOptionPane.showMessageDialog(
                null, dao.inserir(objUsuario));
    }
}