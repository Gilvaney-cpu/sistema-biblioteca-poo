package br.com.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
    private int id; // Identificador do empréstimo
    private int idLivro; // Referência ao ID do Livro
    private int matriculaUsuario; // Referência à Matrícula do Usuário
    
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucaoReal; // Nulo enquanto não devolver
    private boolean devolvido;

    public Emprestimo(int id, int idLivro, int matriculaUsuario, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {
        this.id = id;
        this.idLivro = idLivro;
        this.matriculaUsuario = matriculaUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.devolvido = false;
    }

    // --- LÓGICA DO DOCUMENTO ---

    public void marcarComoDevolvido(LocalDate dataHoje) {
        this.devolvido = true;
        this.dataDevolucaoReal = dataHoje;
    }

    public boolean isAtrasado(LocalDate dataReferencia) {
        // Se já devolveu, não está atrasado (ou verifica se devolveu com atraso)
        if (devolvido) {
            return dataDevolucaoReal.isAfter(dataPrevistaDevolucao);
        }
        // Se ainda não devolveu, verifica se a data de hoje passou do prazo
        return dataReferencia.isAfter(dataPrevistaDevolucao);
    }

    // --- GETTERS E SETTERS ---
    public int getId() { return id; }
    public int getIdLivro() { return idLivro; }
    public int getMatriculaUsuario() { return matriculaUsuario; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public boolean isDevolvido() { return devolvido; }
    public LocalDate getDataDevolucaoReal() { return dataDevolucaoReal; }

    @Override
    public String toString() {
        return "Empréstimo [Livro=" + idLivro + ", Usuário=" + matriculaUsuario + ", Devolução=" + dataPrevistaDevolucao + "]";
    }
}