package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDao {

    public Connection minhaConexao;

    public UsuarioDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO TB_SOUL_USUARIOS (CPF, NOME, PONTOS, CEP, CELULAR, EMAIL, DATA_NASCIMENTO, SITUACAO, TIER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, usuario.getCPF());
        stmt.setString(2, usuario.getNome());
        stmt.setDouble(3, usuario.getPontos());
        stmt.setInt(4, usuario.getCEP());
        stmt.setString(5, usuario.getCelular());
        stmt.setString(6, usuario.getEmail());
        stmt.setString(7, usuario.getDataNascimento());
        stmt.setString(8, usuario.getSituacao());
        stmt.setString(9, usuario.getTier());

        stmt.execute();
        stmt.close();
        return "uhul!! o usuário foi Cadastrado com Sucesso!!!";
    }

    public String atualizar(Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE TB_SOUL_USUARIOS SET NOME = ?, PONTOS = ?, CEP = ?, CELULAR = ?, EMAIL = ?, DATA_NASCIMENTO = ?, SITUACAO = ?, TIER = ? WHERE CPF = ?");

        stmt.setString(1, usuario.getNome());
        stmt.setDouble(2, usuario.getPontos());
        stmt.setInt(3, usuario.getCEP());
        stmt.setString(4, usuario.getCelular());
        stmt.setString(5, usuario.getEmail());
        stmt.setString(6, usuario.getDataNascimento());
        stmt.setString(7, usuario.getSituacao());
        stmt.setString(8, usuario.getTier());
        stmt.setString(9, usuario.getCPF());

        stmt.executeUpdate();
        stmt.close();
        return "legal! esse usuário foi atualizado com Sucesso!!!";
    }

    public String deletar(String cpf) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement("DELETE FROM TB_SOUL_USUARIOS WHERE CPF = ?");
        stmt.setString(1, cpf);
        stmt.execute();
        stmt.close();
        return "Usuário Deletado com Sucesso!!";
    }

    public Usuario buscarPorCpf(String cpf) throws SQLException {
        Usuario usuario = null;
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT CPF, NOME, PONTOS, CEP, CELULAR, EMAIL, DATA_NASCIMENTO, SITUACAO, TIER FROM TB_SOUL_USUARIOS WHERE CPF = ?"
        );
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setCPF(rs.getString(1));
            usuario.setNome(rs.getString(2));
            usuario.setPontos(rs.getDouble(3));
            usuario.setCEP(rs.getInt(4));
            usuario.setCelular(rs.getString(5));
            usuario.setEmail(rs.getString(6));
            usuario.setDataNascimento(rs.getString(7));
            usuario.setSituacao(rs.getString(8));
            usuario.setTier(rs.getString(9));
        }
        rs.close();
        stmt.close();
        return usuario;
    }

    public ArrayList<Usuario> selecionar() throws SQLException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT CPF, NOME, PONTOS, CEP, CELULAR, EMAIL, DATA_NASCIMENTO, SITUACAO, TIER FROM TB_SOUL_USUARIOS"
        );
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setCPF(rs.getString(1));
            usuario.setNome(rs.getString(2));
            usuario.setPontos(rs.getDouble(3));
            usuario.setCEP(rs.getInt(4));
            usuario.setCelular(rs.getString(5));
            usuario.setEmail(rs.getString(6));
            usuario.setDataNascimento(rs.getString(7));
            usuario.setSituacao(rs.getString(8));
            usuario.setTier(rs.getString(9));
            listaUsuarios.add(usuario);
        }
        rs.close();
        stmt.close();
        return listaUsuarios;
    }

    public String adicionarPontosEcologicos(String cpf, double pontosGanhos) throws SQLException {
        Usuario usuarioAtual = buscarPorCpf(cpf);

        if (usuarioAtual != null) {
            double saldoAtual = usuarioAtual.getPontos();
            double novoSaldo = saldoAtual + pontosGanhos;
            usuarioAtual.setPontos(novoSaldo);
            atualizar(usuarioAtual);

            return "CARAMBA!! Parabéns!! Você ganhou " + pontosGanhos + " pontos. Seu saldo Atualizado é de : " + novoSaldo;
        } else {
            return "Erro: Usuário não encontrado no sistema." +
                    "\n veriffique as informações e tente novamente!";
        }
    }
}