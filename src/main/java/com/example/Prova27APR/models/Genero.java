package com.example.Prova27APR.models;

import javax.persistence.Entity;

@Entity
public class Genero {
    private int id;
    private String nome;

    public Genero() {
    }

    public Genero(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
