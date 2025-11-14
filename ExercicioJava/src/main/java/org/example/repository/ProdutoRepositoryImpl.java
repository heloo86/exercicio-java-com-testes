package org.example.repository;

import org.example.model.Produto;
import org.example.util.ConexaoBanco;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositoryImpl implements ProdutoRepository{

    @Override
    public Produto save(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, preco, quantidade, categoria) VALUES (?,?,?,?)";

        try (Connection connection = ConexaoBanco.conectar();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.setString(4, produto.getCategoria());

            statement.executeUpdate();


            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produto.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID do produto, nenhum ID foi gerado.");
                }
                return produto;
            }

        }
    }

    @Override
    public List<Produto> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<Produto> findById(int id) throws SQLException {

        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection connection = ConexaoBanco.conectar();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.getResultSet()){

            Produto produto = new Produto();

            statement.setInt(1, id);
            statement.executeQuery();

            produto.setId(id);
            produto.setCategoria(resultSet.getNString("categoria"));
            produto.setNome(resultSet.getNString("nome"));
            produto.setQuantidade(resultSet.getInt("quantidade"));
            produto.setPreco(resultSet.getDouble("preco"));

            return Optional.of(produto);
        }
    }

    @Override
    public Produto update(Produto produto) throws SQLException {
        return null;
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM  produto where id = ?";

        try (Connection connection = ConexaoBanco.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);
            statement.executeUpdate();
        }

    }
}
