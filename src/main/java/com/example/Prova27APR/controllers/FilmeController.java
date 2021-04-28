package com.example.Prova27APR.controllers;

import com.example.Prova27APR.dao.FilmeDao;
import com.example.Prova27APR.models.Filme;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("filme")
public class FilmeController {
    @GET
    @Path("criatabelafilme")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criaTabelaFilme() {
        FilmeDao filmeDao = new FilmeDao();
        filmeDao.criaTabelaFilme();
        return Response.ok(new Gson().toJson("Tabela Filmes criada com sucesso!")).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criaFilme(Filme filme) {
        FilmeDao filmeDao = new FilmeDao();
        filmeDao.adicionaFilme(filme);
        return Response.ok(new Gson().toJson(filme)).build();
//        return Response.status(Response.Status.CREATED).entity(filme).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaFilmes() {
        FilmeDao filmeDao = new FilmeDao();
        List<Filme> filmes = filmeDao.listaFilmes();
        return Response.ok(new Gson().toJson(filmes)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontraFilmePorId(@PathParam("id") int id) {
        FilmeDao filmeDao = new FilmeDao();
        Filme filme = filmeDao.buscaFilmePorId(id);
        return Response.ok(new Gson().toJson(filme)).build();
    }
}
