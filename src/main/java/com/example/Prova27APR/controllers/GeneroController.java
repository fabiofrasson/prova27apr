package com.example.Prova27APR.controllers;

import com.example.Prova27APR.dao.GeneroDao;
import com.example.Prova27APR.models.Genero;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("genero")
public class GeneroController {
    @GET
    @Path("/criatabela")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criaTabelaGenero() {
        GeneroDao generoDao = new GeneroDao();
        generoDao.criaTabelaGenero();
        return Response.ok(new Gson().toJson("Tabela Gêneros criada com sucesso!")).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criaGenero(Genero genero) {
        GeneroDao generoDao = new GeneroDao();
        generoDao.adicionaGenero(genero);
        return Response.ok(new Gson().toJson(genero)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaGeneros() {
        GeneroDao generoDao = new GeneroDao();
        List<Genero> generos = generoDao.listaGeneros();
        return Response.ok(new Gson().toJson(generos)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontraGeneroPorId(@PathParam("id") int id) {
        GeneroDao
    }
}
