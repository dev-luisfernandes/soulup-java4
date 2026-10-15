package br.com.fiap.main.usuario;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.entities.Usuario;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteBuscarUsuario {

    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UsuarioDao dao = new UsuarioDao();
        String cpfBusca = texto("Digite o CPF do usuário para ver a ficha completa:");

        Usuario usuarioEncontrado = dao.buscarPorCpf(cpfBusca);

        if (usuarioEncontrado != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "O USUÁRIO FOI ENCONTRADO!\n\n" +
                            "Nome: " + usuarioEncontrado.getNome() + "\n" +
                            "CPF: " + usuarioEncontrado.getCPF() + "\n" +
                            "Telefone: " + usuarioEncontrado.getCelular() + "\n" +
                            "Email: " + usuarioEncontrado.getEmail() + "\n" +
                            "Data de Nascimento: " + usuarioEncontrado.getDataNascimento() + "\n" +
                            "Situação: " + usuarioEncontrado.getSituacao() + "\n" +
                            "Saldo Ecológico: " + usuarioEncontrado.getPontos() + " pontos");

        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Ops! Nenhum usuário encontrado com esse CPF.");

        }
    }
}