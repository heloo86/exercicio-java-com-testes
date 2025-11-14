package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepository;
import org.example.repository.ProdutoRepositoryImpl;
import org.example.util.ConexaoBanco;

import java.sql.*;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService{

    private final ProdutoRepositoryImpl repository = new ProdutoRepositoryImpl();

    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {
        if (produto.getPreco()< 0) { throw new IllegalArgumentException("Preço deve ser positivo."); }
        repository.save(produto);
        return produto;
    }


    @Override
    public List<Produto> listarProdutos() throws SQLException {
        return List.of();
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        return null;
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException {
        return null;
    }

    @Override
    public boolean excluirProduto(int id) {
        try {
                repository.deleteById(id);
                return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
