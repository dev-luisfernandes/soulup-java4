package br.com.fiap.main.usuario;

import br.com.fiap.dao.UsuarioDao;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteDeletarUsuario {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UsuarioDao dao = new UsuarioDao();
        String cpf = texto("Digite o CPF do usuário que será DELETADO:");

        JOptionPane.showMessageDialog(
                null, dao.deletar(cpf));
    }
}