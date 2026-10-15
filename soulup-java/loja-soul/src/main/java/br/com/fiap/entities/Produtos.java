package br.com.fiap.entities;

public class Produtos {

    private int id;
    private String nome;
    private int quantidade;
    private String tipo;
    private double pontos;

    public Produtos() {
    }

    public Produtos(int id, String nome, int quantidade, String tipo, double pontos) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.pontos = pontos;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "\n\nid: " + id +
                "\nnome: " + nome + '\'' +
                "\nquantidade:" + quantidade +
                "\ntipo: " + tipo + '\'' +
                "\npontos: " + pontos +
                '}';
    }
}