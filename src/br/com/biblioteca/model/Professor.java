package br.com.biblioteca.model;

import java.time.LocalDate;

public class Professor extends Usuario {

    public Professor(int matricula, String nome, String senha, LocalDate dataCadastro) {
        super(matricula, nome, senha, dataCadastro);
    }

    // Caso queira diferenciar regras de negócio:
    // Exemplo: prazo maior para empréstimos
    public int getPrazoEmprestimo() {
        return 14; // Professores têm 14 dias de prazo
    }
}