package br.com.biblioteca.exception;

public class LimiteEmprestimoExcedidoException extends RegraNegocioException {
    public LimiteEmprestimoExcedidoException(String msg) {
        super(msg);
    }
}