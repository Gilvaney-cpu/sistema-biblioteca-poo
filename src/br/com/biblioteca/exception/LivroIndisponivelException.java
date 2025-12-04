/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.exception;

public class LivroIndisponivelException extends RuntimeException {
    public LivroIndisponivelException(String msg) {
        super(msg);
    }
}