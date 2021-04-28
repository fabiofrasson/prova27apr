package com.example.Prova27APR.models;

import javax.persistence.Entity;

@Entity
public class Filme {
    private int id;
    private String nome;
    private int anoLanc;
    private int duracao;
    private Genero genero;

    public Filme() {
    }

    public Filme(String nome, int anoLanc, int duracao, Genero genero) {
        this.nome = nome;
        this.anoLanc = anoLanc;
        this.duracao = duracao;
        this.genero = genero;
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

    public int getAnoLanc() {
        return anoLanc;
    }

    public void setAnoLanc(int anoLanc) {
        this.anoLanc = anoLanc;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Genero getGenero() {
        if (genero == null) {
            genero = new Genero();
        }
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoLanc=" + anoLanc +
                ", duracao=" + duracao +
                ", genero=" + genero +
                '}';
    }
}
