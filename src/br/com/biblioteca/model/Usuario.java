package br.com.biblioteca.model;

import java.time.LocalDate;

public abstract class Usuario {
    private int matricula;
    private String nome;
    private String senha;
    private LocalDate dataNascimento;
    private boolean suspenso;
    
    public Usuario(int matricula, String nome, String senha, LocalDate dataNascimento) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.suspenso = false;
    }

    // Método que Aluno e Professor são obrigados a implementar
    public abstract int getLimiteCreditos();

    // Getters e Setters
    public int getMatricula() { return matricula; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean isSuspenso() { return suspenso; }
    public void setSuspenso(boolean suspenso) { this.suspenso = suspenso; }
    
    public LocalDate getDataNascimento() { return dataNascimento; }

    @Override
    public String toString() {
        return matricula + " - " + nome;
    }
}