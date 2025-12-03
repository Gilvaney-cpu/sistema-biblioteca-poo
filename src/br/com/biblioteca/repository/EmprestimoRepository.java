/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.repository;

import br.com.biblioteca.model.Emprestimo;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {
    private List<Emprestimo> emprestimos;

    public EmprestimoRepository() {
        emprestimos = new ArrayList<>();
    }

    // Adicionar empréstimo
    public void adicionar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    // Buscar por ID
    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // Buscar por matrícula do usuário
    public List<Emprestimo> buscarPorUsuario(int matriculaUsuario) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getMatriculaUsuario() == matriculaUsuario) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // Remover empréstimo
    public boolean remover(int id) {
        Emprestimo e = buscarPorId(id);
        if (e != null) {
            emprestimos.remove(e);
            return true;
        }
        return false;
    }

    // Listar todos
    public List<Emprestimo> listarTodos() {
        return emprestimos;
    }
}