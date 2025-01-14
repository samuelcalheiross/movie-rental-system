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

public class FilmeDao {

    private Connection connection;

    public FilmeDao(Connection connection) {
        this.connection = connection;
    }

    public FilmeDao() {
    }

    public Filme getById(int id) throws SQLException, Exception {
        String sql = "SELECT f.id, f.nome_filme, f.ano_lancamento, f.duracao, f.quantidade_total, "
                + "c.id AS categoria_id, c.nome AS categoria_nome "
                + "FROM filmes f "
                + "LEFT JOIN categorias c ON f.categoria_id = c.id "
                + "WHERE f.id = ?";
        try (Connection conn = ConnectionFactory.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    
                    Filme filme = new Filme();
                    filme.setId(rs.getInt("id"));
                    filme.setNomeFilme(rs.getString("nome_filme"));
                    filme.setAnoLancamento(rs.getInt("ano_lancamento"));
                    filme.setDuracao(rs.getString("duracao"));
                    filme.setQuantidade(rs.getInt("quantidade_total"));

                    
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("categoria_id"));
                    categoria.setNome(rs.getString("categoria_nome"));

                    
                    filme.setCategoria(categoria);

                    return filme;
                }
            }
        }
        return null;
    }

    
    public void create(Filme filme) throws SQLException, Exception {
        String sql = "INSERT INTO filmes (nome_filme, ano_lancamento, duracao, quantidade_total, categoria_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, filme.getNomeFilme());
            stmt.setInt(2, filme.getAnoLancamento());
            stmt.setString(3, filme.getDuracao());
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

    public List<Filme> read() throws SQLException, Exception {
        String sql = "SELECT f.id, f.nome_filme, f.ano_lancamento, f.duracao, f.quantidade_total, "
                + "c.id AS categoria_id, c.nome AS categoria_nome "
                + "FROM filmes f "
                + "LEFT JOIN categorias c ON f.categoria_id = c.id";

        List<Filme> filmes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                categoria.setNome(rs.getString("categoria_nome"));

                
                Filme filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setNomeFilme(rs.getString("nome_filme"));
                filme.setAnoLancamento(rs.getInt("ano_lancamento"));
                filme.setDuracao(rs.getString("duracao"));
                filme.setQuantidade(rs.getInt("quantidade_total"));
                filme.setCategoria(categoria);

                
                filmes.add(filme);
            }
        }

        return filmes;
    }

    public void update(Filme filme) throws SQLException, Exception {
        String sql = "UPDATE filmes SET nome_filme = ?, ano_lancamento = ?, duracao = ?, categoria_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, filme.getNomeFilme());
            stmt.setInt(2, filme.getAnoLancamento());
            stmt.setString(3, filme.getDuracao());
            stmt.setInt(4, filme.getCategoria().getId());
            stmt.setInt(5, filme.getId());

            stmt.executeUpdate();
        }
    }

    public void atualizarQuantidade(int idFilme, int novaQuantidade) throws SQLException, Exception {
        String sql = "UPDATE filmes SET quantidade_total = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, idFilme);
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException, Exception {
        String sql = "DELETE FROM filmes WHERE id = ?";

        try (Connection conn = ConnectionFactory.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
