/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.repository;

import br.com.biblioteca.model.Emprestimo;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository implements IRepositorio<Emprestimo> {
    private List<Emprestimo> emprestimos = new ArrayList<>();

    @Override
    public void salvar(Emprestimo obj) {
        emprestimos.add(obj);
    }

    @Override
    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return emprestimos;
    }
}