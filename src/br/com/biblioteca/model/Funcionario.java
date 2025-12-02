package br.com.biblioteca.model;

public class Funcionario {
    private int matricula;
    private String nome;
    private String senha;

    public Funcionario(int matricula, String nome, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }
    
    @Override
    public String toString() {
        return "Func: " + nome + " (" + matricula + ")";
    }
}