package br.com.biblioteca.model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int quantidadeDisponivel;
    private int anoPublicacao;  
    private String genero; 

    public Livro(int id, String titulo, String autor, int quantidade, int anoPublicacao, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.quantidadeDisponivel = quantidade;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public String getGenero() { return genero; }

    // Métodos chamados pela Fachada
    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public void diminuirDisponibilidade() {
        if (quantidadeDisponivel <= 0) {
            throw new RuntimeException("Livro indisponível.");
        }
        quantidadeDisponivel--;
    }

    public void aumentarDisponibilidade() {
        quantidadeDisponivel++;
    }
}
