package br.com.biblioteca.model;

// Importante: Importar a exceção que você criou
import br.com.biblioteca.exception.LimiteEmprestimoExcedidoException;
import java.time.LocalDate;

public abstract class Usuario {
    
    // --- ATRIBUTOS ---
    private int matricula;
    private String nome;
    private String senha;
    private LocalDate dataNascimento;
    
    private boolean suspenso;
    
    // FALTAVAM ESTES DOIS AQUI:
    private int creditosUsados = 0; // Começa com 0 livros pegos
    private LocalDate fimSuspensao; // Guarda a data que acaba o castigo
    
    // --- CONSTRUTOR ---
    public Usuario(int matricula, String nome, String senha, LocalDate dataNascimento) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.suspenso = false;
    }

    // --- MÉTODO ABSTRATO (Polimorfismo) ---
    public abstract int getLimiteCreditos();

    // --- MÉTODOS DE NEGÓCIO ---

    public void usarCredito() {
        // Verifica se já atingiu o limite (Aluno=5, Prof=10)
        if (creditosUsados >= getLimiteCreditos()) {
             // Agora usando sua exceção personalizada!
             throw new LimiteEmprestimoExcedidoException("Limite de créditos excedido! Você já tem " + creditosUsados + " livros.");
        }
        creditosUsados++;
    }

    public void devolverCredito() {
        if (creditosUsados > 0) {
            creditosUsados--;
        }
    }

    public void suspenderPorDias(int dias, LocalDate dataReferencia) {
        this.suspenso = true;
        this.fimSuspensao = dataReferencia.plusDays(dias);
    }
    
    public boolean verificarSeEstaSuspenso(LocalDate dataHoje) {
        if (suspenso && fimSuspensao != null) {
            if (dataHoje.isAfter(fimSuspensao)) {
                suspenso = false; // A pena acabou
                fimSuspensao = null;
                return false;
            }
            return true; // Ainda está suspenso
        }
        return false;
    }

    // --- GETTERS E SETTERS ---
    public int getMatricula() { return matricula; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean isSuspenso() { return suspenso; }
    public void setSuspenso(boolean suspenso) { this.suspenso = suspenso; }
    
    public LocalDate getDataNascimento() { return dataNascimento; }
    
    public int getCreditosUsados() { return creditosUsados; }
    
    // É bom ter esse getter caso precisem consultar a data da punição na tela
    public LocalDate getFimSuspensao() { return fimSuspensao; }

    @Override
    public String toString() {
        return matricula + " - " + nome;
    }
}