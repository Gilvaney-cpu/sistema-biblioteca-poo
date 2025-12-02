package br.com.biblioteca.model;

import java.time.LocalDate;

public class Professor extends Usuario {

    public Professor(int matricula, String nome, String senha, LocalDate dataNascimento) {
        super(matricula, nome, senha, dataNascimento);
    }

    @Override
    public int getLimiteCreditos() {
        return 10; // Professor pode pegar 10 livros
    }
}