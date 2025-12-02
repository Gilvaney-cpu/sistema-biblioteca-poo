package br.com.biblioteca.model;

public class Livro {
    private int id; // Pode ser gerado automaticamente ou digitado
    private String titulo;
    private String autor;
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public Livro(int id, String titulo, String autor, int quantidadeTotal) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeTotal; // Começa com todos disponíveis
    }

    // --- LÓGICA DO DOCUMENTO ---
    
    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public void incrementarQuantidade(int qtd) {
        this.quantidadeTotal += qtd;
        this.quantidadeDisponivel += qtd;
    }

    public void removerQuantidade(int qtd) {
        // Validação simples para não ficar negativo
        if (qtd <= quantidadeDisponivel) {
            this.quantidadeTotal -= qtd;
            this.quantidadeDisponivel -= qtd;
        } else {
            // Opcional: Lançar exceção se tentar remover mais do que existe
            System.out.println("ERRO: Não é possível remover essa quantidade.");
        }
    }
    
    // Método auxiliar para o sistema de empréstimo usar
    public void diminuirDisponibilidade() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
        }
    }
    
    public void aumentarDisponibilidade() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
        }
    }

    // --- GETTERS E SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getQuantidadeTotal() { return quantidadeTotal; }

    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }

    @Override
    public String toString() {
        return id + " - " + titulo + " (" + quantidadeDisponivel + " disp.)";
    }
}