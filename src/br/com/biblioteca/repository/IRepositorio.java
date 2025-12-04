/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.repository;

import java.util.List;

public interface IRepositorio<T> {
    void salvar(T obj);
    T buscarPorId(int id);
    List<T> listarTodos();
}