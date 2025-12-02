package br.com.biblioteca.model;

import java.time.LocalDate;

public class Aluno extends Usuario {

    public Aluno(int matricula, String nome, String senha, LocalDate dataNascimento) {
        super(matricula, nome, senha, dataNascimento);
    }

    @Override
    public int getLimiteCreditos() {
        return 5; // Aluno pode pegar 5 livros
    }
}