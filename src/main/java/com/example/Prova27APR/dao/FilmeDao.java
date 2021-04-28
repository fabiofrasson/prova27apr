package com.example.Prova27APR.dao;

import com.example.Prova27APR.factory.ConnectionFactory;
import com.example.Prova27APR.models.Filme;
import com.example.Prova27APR.models.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDao {
    private Connection connection;

    public FilmeDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void criaTabelaFilme() {
        String sql = "CREATE TABLE IF NOT EXISTS filme(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "nome VARCHAR(100) NOT NULL," +
                "anoLanc INT," +
                "duracao INT," +
                "idGenero INT," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY (idGenero) REFERENCES genero(id)" +
                ");";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Tabela Filme criada com sucesso!");
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public void adicionaFilme(Filme filme) {
        String sql = "INSERT INTO filme" +
                "(nome, anoLanc, duracao, idGenero) " +
                "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, filme.getNome());
            preparedStatement.setInt(2, filme.getAnoLanc());
            preparedStatement.setInt(3, filme.getDuracao());
            preparedStatement.setInt(4, filme.getGenero().getId());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                filme.setId(resultSet.getInt(1));
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List<Filme> listaFilmes() {
        String sql = "SELECT * FROM filme;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Filme> filmes = new ArrayList<>();
            Filme filme = new Filme();

            while (resultSet.next()) {
                filme.setId(resultSet.getInt("id"));
                filme.setNome(resultSet.getString("nome"));
                filme.setAnoLanc(resultSet.getInt("anoLanc"));
                filme.setDuracao(resultSet.getInt("duracao"));
                GeneroDao generoDao = new GeneroDao();
                Genero genero = generoDao.buscaGeneroPorId(resultSet.getInt("idGenero"));
                filme.setGenero(genero);

                filmes.add(filme);
            }
            return filmes;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public Filme buscaFilmePorId(int id) {
        String sql = "SELECT * FROM filme WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setId(resultSet.getInt("id"));
                filme.setNome(resultSet.getString("nome"));
                filme.setAnoLanc(resultSet.getInt("anoLanc"));
                filme.setDuracao(resultSet.getInt("duracao"));
                GeneroDao generoDao = new GeneroDao();
                Genero genero = generoDao.buscaGeneroPorId(resultSet.getInt("idGenero"));
                filme.setGenero(genero);

                return filme;
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return null;
    }
}
