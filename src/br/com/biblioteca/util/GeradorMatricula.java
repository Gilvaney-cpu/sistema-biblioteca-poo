package br.com.biblioteca.util;

public class GeradorMatricula {
    // Começa em 300000 conforme o documento
    private static int proximoNumero = 300001;

    public static int gerar() {
        return proximoNumero++;
    }
}