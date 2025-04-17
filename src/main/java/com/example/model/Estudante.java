package com.example.model;

public class Estudante {
    private int id;
    private String nome;
    private int idade;
    private String curso;

    public Estudante(int id, String nome, int idade, String curso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    @Override
    public String toString() {
        return "Estudante [id=" + id + ", nome=" + nome + ", idade=" + idade + ", curso=" + curso + "]";
    }
}