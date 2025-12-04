/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.repository;

import br.com.biblioteca.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements IRepositorio<Usuario> {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void salvar(Usuario obj) {
        usuarios.add(obj);
    }

    @Override
    public Usuario buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getMatricula() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }
}