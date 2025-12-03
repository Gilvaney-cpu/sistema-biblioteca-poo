/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.repository;

import br.com.biblioteca.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {
    private List<Livro> livros;

    public LivroRepository() {
        livros = new ArrayList<>();
    }

    // Adicionar livro
    public void adicionar(Livro livro) {
        livros.add(livro);
    }

    // Buscar por ID
    public Livro buscarPorId(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    // Buscar por título
    public Livro buscarPorTitulo(String titulo) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    // Remover livro
    public boolean remover(int id) {
        Livro l = buscarPorId(id);
        if (l != null) {
            livros.remove(l);
            return true;
        }
        return false;
    }

    // Listar todos
    public List<Livro> listarTodos() {
        return livros;
    }
}