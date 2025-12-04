package br.com.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private int idLivro;
    private int matriculaUsuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido = false;
    private LocalDate dataDevolvido;

    public Emprestimo(int id, int idLivro, int matriculaUsuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.idLivro = idLivro;
        this.matriculaUsuario = matriculaUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() { return id; }
    public int getIdLivro() { return idLivro; }
    public int getMatriculaUsuario() { return matriculaUsuario; }

    // Métodos chamados pela Fachada
    public boolean isDevolvido() {
        return devolvido;
    }

    public void marcarComoDevolvido(LocalDate data) {
        this.devolvido = true;
        this.dataDevolvido = data;
    }

    public boolean isAtrasado(LocalDate hoje) {
        return !devolvido && hoje.isAfter(dataDevolucao);
    }
}