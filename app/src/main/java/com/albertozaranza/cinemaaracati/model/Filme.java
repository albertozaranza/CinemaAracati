package com.albertozaranza.cinemaaracati.model;

/**
 * Created by Alberto Zaranza on 11/12/2017.
 */

public class Filme {

    String censura, duracao, genero, horario, imagem, imagem_card, nome, sala,  sinopse, tipo, trailer;

    public Filme() {
    }

    public Filme(String censura, String duracao, String genero, String horario, String imagem, String imagem_card, String nome, String sala, String sinopse, String tipo, String trailer) {
        this.censura = censura;
        this.duracao = duracao;
        this.genero = genero;
        this.horario = horario;
        this.imagem = imagem;
        this.imagem_card = imagem_card;
        this.nome = nome;
        this.sala = sala;
        this.sinopse = sinopse;
        this.tipo = tipo;
        this.trailer = trailer;
    }

    public String getCensura() {
        return censura;
    }

    public void setCensura(String censura) {
        this.censura = censura;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImagem_card() {
        return imagem_card;
    }

    public void setImagem_card(String imagem_card) {
        this.imagem_card = imagem_card;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
