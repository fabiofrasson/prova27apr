package com.example.Prova27APR.dao;

import com.example.Prova27APR.factory.ConnectionFactory;
import com.example.Prova27APR.models.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDao {
    private Connection connection;

    public GeneroDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void criaTabelaGenero() {
        String sql = "CREATE TABLE IF NOT EXISTS genero(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "nome VARCHAR(100) NOT NULL," +
                "PRIMARY KEY(id)" +
                ");";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Tabela Gênero criada com sucesso!");
        } catch (SQLException error) {
            throw new RuntimeException (error);
        }
    }

    public void adicionaGenero(Genero genero) {
        String sql = "INSERT INTO genero" +
                "(nome) VALUES (?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, genero.getNome());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                genero.setId(resultSet.getInt(1));
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

    public List<Genero> listaGeneros() {
        String sql = "SELECT * FROM genero;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Genero> generos = new ArrayList<>();
            Genero genero = new Genero();

            while (resultSet.next()) {
                genero.setId(resultSet.getInt("id"));
                genero.setNome(resultSet.getString("nome"));

                generos.add(genero);
            }
            return generos;
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public Genero buscaGeneroPorId(int id) {
        String sql = "SELECT * FROM genero WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Genero genero = new Genero();
                genero.setId(resultSet.getInt("id"));
                genero.setNome(resultSet.getString("nome"));
                return genero;
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
        return null;
    }
}
