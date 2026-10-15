package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDao {

    public Connection minhaConexao;

    public ProdutosDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Produtos produto) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO TB_SOUL_PRODUTOS (ID, NOME, QUANTIDADE, TIPO, PONTOS) VALUES (?, ?, ?, ?, ?)");

        stmt.setInt(1, produto.getId());
        stmt.setString(2, produto.getNome());
        stmt.setInt(3, produto.getQuantidade());
        stmt.setString(4, produto.getTipo());
        stmt.setDouble(5, produto.getPontos());

        stmt.execute();
        stmt.close();
        return "Produto Cadastrado com Sucesso!!!";
    }

    public String atualizar(Produtos produto) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE TB_SOUL_PRODUTOS SET NOME = ?, QUANTIDADE = ?, TIPO = ?, PONTOS = ? WHERE ID = ?");

        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getQuantidade());
        stmt.setString(3, produto.getTipo());
        stmt.setDouble(4, produto.getPontos());
        stmt.setInt(5, produto.getId());

        stmt.executeUpdate();
        stmt.close();
        return "Produto Atualizado com Sucesso!!!";
    }

    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement("DELETE FROM TB_SOUL_PRODUTOS WHERE ID = ?");
        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();
        return "Produto Deletado com Sucesso!!!";
    }

    public Produtos buscarPorId(int id) throws SQLException {
        Produtos produto = null;
        PreparedStatement stmt = minhaConexao.prepareStatement("SELECT ID, NOME, QUANTIDADE, TIPO, PONTOS FROM TB_SOUL_PRODUTOS WHERE ID = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            produto = new Produtos();
            produto.setId(rs.getInt(1));
            produto.setNome(rs.getString(2));
            produto.setQuantidade(rs.getInt(3));
            produto.setTipo(rs.getString(4));
            produto.setPontos(rs.getDouble(5));
        }
        rs.close();
        stmt.close();
        return produto;
    }

    public ArrayList<Produtos> selecionar() throws SQLException {
        ArrayList<Produtos> listaProdutos = new ArrayList<>();

        PreparedStatement stmt = minhaConexao.prepareStatement("SELECT ID, NOME, QUANTIDADE, TIPO, PONTOS FROM TB_SOUL_PRODUTOS");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Produtos produto = new Produtos();
            produto.setId(rs.getInt(1));
            produto.setNome(rs.getString(2));
            produto.setQuantidade(rs.getInt(3));
            produto.setTipo(rs.getString(4));
            produto.setPontos(rs.getDouble(5));
            listaProdutos.add(produto);
        }
        rs.close();
        stmt.close();
        return listaProdutos;
    }

    public void diminuirEstoque(int id) throws SQLException {
        Produtos produto = buscarPorId(id);
        if (produto != null && produto.getQuantidade() > 0) {
            int novaQuantidade = produto.getQuantidade() - 1;
            PreparedStatement stmt = minhaConexao.prepareStatement(
                    "UPDATE TB_SOUL_PRODUTOS SET QUANTIDADE = ? WHERE ID = ?");
            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
        }
    }

}