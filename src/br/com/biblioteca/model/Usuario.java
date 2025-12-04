package br.com.biblioteca.model;

import java.time.LocalDate;

public abstract class Usuario {
    private int matricula;
    private String nome;
    private String senha;
    private int creditos = 3; // exemplo: cada usuário começa com 3 créditos
    private LocalDate fimSuspensao;

    public Usuario(int matricula, String nome, String senha, LocalDate dataCadastro) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }

    // Métodos chamados pela Fachada
    public boolean verificarSeEstaSuspenso(LocalDate data) {
        return fimSuspensao != null && data.isBefore(fimSuspensao);
    }

    public LocalDate getFimSuspensao() {
        return fimSuspensao;
    }

    public void usarCredito() {
        if (creditos <= 0) {
            throw new RuntimeException("Usuário sem créditos disponíveis.");
        }
        creditos--;
    }

    public void devolverCredito() {
        creditos++;
    }

    public void suspenderPorDias(int dias, LocalDate data) {
        fimSuspensao = data.plusDays(dias);
    }
}