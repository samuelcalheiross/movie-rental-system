/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author samuel
 */

import factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

import model.Filme;

public class CategoriaDao {

    private Connection connection;

    public CategoriaDao(Connection connection) {
        this.connection = connection;
    }

    public int getIdByNome(String nomeCategoria) throws SQLException {
        String sql = "SELECT id FROM categorias WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoria);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Categoria não encontrada: " + nomeCategoria);
            }
        }
    }

    public boolean categoriaExiste(int categoriaId) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, categoriaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        }
        return false;
    }

    public void create(Filme filme) throws SQLException, Exception {
    String sql = "INSERT INTO filmes (nome_filme, ano_lancamento, duracao, quantidade_total, categoria_id) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = ConnectionFactory.createConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, filme.getNomeFilme());
        stmt.setInt(2, filme.getAnoLancamento());
        stmt.setInt(3, Integer.parseInt(filme.getDuracao()));
        stmt.setInt(4, filme.getQuantidade());
        stmt.setInt(5, filme.getCategoria().getId());

        stmt.executeUpdate();

        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                filme.setId(rs.getInt(1));
            }
        }
    }
}

    public List<Categoria> read() throws SQLException {
        String sql = "SELECT * FROM categorias";
        List<Categoria> categorias = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));

                categorias.add(categoria);
            }
        }

        return categorias;
    }

    public void update(Categoria categoria) throws SQLException {
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
        }
    }
}
