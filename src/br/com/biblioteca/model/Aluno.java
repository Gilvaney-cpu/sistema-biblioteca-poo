package br.com.biblioteca.model;

import java.time.LocalDate;

public class Aluno extends Usuario {

    public Aluno(int matricula, String nome, String senha, LocalDate dataCadastro) {
        super(matricula, nome, senha, dataCadastro);
    }

    // Caso queira diferenciar regras de negócio:
    // Exemplo: prazo menor para empréstimos
    public int getPrazoEmprestimo() {
        return 7; // Alunos têm 7 dias de prazo
    }
}