package br.com.biblioteca.model;

import java.time.LocalDate;

public abstract class Usuario {
    private int matricula;
    private String nome;
    private String senha;
    private int creditos; // quantidade atual de créditos
    private int limiteCreditos; // limite inicial de créditos
    private LocalDate fimSuspensao;

    public Usuario(int matricula, String nome, String senha, LocalDate dataCadastro) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
        this.limiteCreditos = 5; // exemplo: cada usuário começa com 5 créditos
        this.creditos = limiteCreditos;
    }

    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }

    // --- Métodos de créditos ---
    public void usarCredito() {
        if (creditos <= 0) {
            throw new RuntimeException("Usuário sem créditos disponíveis.");
        }
        creditos--;
    }

    public void devolverCredito() {
        if (creditos < limiteCreditos) {
            creditos++;
        }
    }

    public int getCreditos() {
        return creditos;
    }

    public int getCreditosUsados() {
        return limiteCreditos - creditos;
    }

    public int getLimiteCreditos() {
        return limiteCreditos;
    }

    // --- Métodos de suspensão ---
    public void suspenderPorDias(int dias, LocalDate data) {
        fimSuspensao = data.plusDays(dias);
    }

    public boolean verificarSeEstaSuspenso(LocalDate data) {
        return fimSuspensao != null && data.isAfter(LocalDate.now()) && data.isBefore(fimSuspensao);
    }

    public boolean isSuspenso() {
        return verificarSeEstaSuspenso(LocalDate.now());
    }

    public LocalDate getFimSuspensao() {
        return fimSuspensao;
    }
}