/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.repository;

import br.com.biblioteca.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios;

    public UsuarioRepository() {
        usuarios = new ArrayList<>();
    }

    // Adicionar usuário
    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Buscar por matrícula
    public Usuario buscarPorMatricula(int matricula) {
        for (Usuario u : usuarios) {
            if (u.getMatricula() == matricula) {
                return u;
            }
        }
        return null;
    }

    // Remover usuário
    public boolean remover(int matricula) {
        Usuario u = buscarPorMatricula(matricula);
        if (u != null) {
            usuarios.remove(u);
            return true;
        }
        return false;
    }

    // Listar todos
    public List<Usuario> listarTodos() {
        return usuarios;
    }
}