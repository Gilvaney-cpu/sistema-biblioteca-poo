/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.repository;

import br.com.biblioteca.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository implements IRepositorio<Livro> {
    private List<Livro> livros = new ArrayList<>();

    @Override
    public void salvar(Livro obj) {
        livros.add(obj);
    }

    @Override
    public Livro buscarPorId(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    @Override
    public List<Livro> listarTodos() {
        return livros;
    }
}