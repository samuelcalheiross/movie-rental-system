/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author samuel
 */

public class Filme {

    private int id;
    private String nomeFilme;
    private int anoLancamento;
    private String duracao;
    private Categoria categoria;
    private int quantidade;

    
    public Filme(String nomeFilme, int anoLancamento, String duracao, Categoria categoria, int quantidade) {
        this.nomeFilme = nomeFilme;
        this.anoLancamento = anoLancamento;
        this.duracao = duracao;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    public Filme() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

