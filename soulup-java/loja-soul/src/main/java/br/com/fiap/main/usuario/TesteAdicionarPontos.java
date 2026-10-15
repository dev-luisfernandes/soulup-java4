package br.com.fiap.main.usuario;

import br.com.fiap.dao.UsuarioDao;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteAdicionarPontos {

    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }
    static double real(String j){
        return Double.parseDouble(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UsuarioDao dao = new UsuarioDao();
        String cpf = texto("Digite o CPF do usuário:");

        double pontosGanhos = real("Quantos pontos ele ganhou por essa atitude ecológica?: ");

        JOptionPane.showMessageDialog(
                null, dao.adicionarPontosEcologicos(cpf, pontosGanhos));
    }
}