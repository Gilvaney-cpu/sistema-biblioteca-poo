package br.com.biblioteca.view;

import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.model.Professor;
import java.time.LocalDate;

public class TesteConsole {
    
    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTES DO MODEL ---");
        
        // 1. Testando Herança e Polimorfismo
        Aluno a1 = new Aluno(1001, "Joao Silva", "123", LocalDate.now());
        Professor p1 = new Professor(2001, "Dr. House", "abc", LocalDate.now());
        
        System.out.println("Aluno criado: " + a1.getNome() + " | Limite: " + a1.getLimiteCreditos());
        System.out.println("Prof. criado: " + p1.getNome() + " | Limite: " + p1.getLimiteCreditos());
        
        // 2. Testando Consumo de Créditos (Lógica de Negócio)
        System.out.println("\n--- TESTANDO CRÉDITOS ---");
        try {
            // Vamos simular o aluno pegando 6 livros (o limite é 5)
            for (int i = 1; i <= 6; i++) {
                System.out.println("Tentando pegar livro " + i + "...");
                a1.usarCredito();
            }
        } catch (Exception e) {
            System.out.println("ERRO ESPERADO: " + e.getMessage());
        }
        
        System.out.println("Créditos usados pelo aluno: " + a1.getCreditosUsados());
        
        // 3. Testando Suspensão
        System.out.println("\n--- TESTANDO SUSPENSÃO ---");
        a1.suspenderPorDias(3, LocalDate.now());
        System.out.println("Aluno está suspenso? " + a1.isSuspenso());
        
        // Simulando tentativa de empréstimo futuro
        boolean aindaSuspenso = a1.verificarSeEstaSuspenso(LocalDate.now().plusDays(5));
        System.out.println("Daqui a 5 dias estará suspenso? " + aindaSuspenso);
    }
}