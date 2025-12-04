package br.com.biblioteca.service;

import br.com.biblioteca.model.*;
import br.com.biblioteca.repository.*;
import br.com.biblioteca.exception.*;
import br.com.biblioteca.util.GeradorMatricula;
import java.time.LocalDate;
import java.util.List;

/**
 * Fachada (Singleton) que centraliza todas as regras de negócio.
 * A GUI deve chamar APENAS esta classe, nunca os repositórios diretamente.
 */
public class BibliotecaFachada {
    
    private static BibliotecaFachada instance;
    
    // Repositórios (Injeção de dependência)
    private IRepositorio<Usuario> repositorioUsuarios;
    private IRepositorio<Livro> repositorioLivros;
    private IRepositorio<Emprestimo> repositorioEmprestimos;
    
    // Gerador de IDs simples para empréstimos (pode ser melhorado depois)
    private int idEmprestimoContador = 1;

    // Construtor Privado (Singleton)
    private BibliotecaFachada() {
        // Inicializa os repositórios (Essas classes devem ser criadas pelo Membro 2)
        // Se der erro aqui, é porque o Membro 2 ainda não criou as classes concretas.
        this.repositorioUsuarios = new UsuarioRepository();
        this.repositorioLivros = new LivroRepository();
        this.repositorioEmprestimos = new EmprestimoRepository();
        
        // Criando dados de teste (Opcional, para não abrir o sistema vazio)
        inicializarDadosTeste();
    }
    
    public static BibliotecaFachada getInstance() {
        if (instance == null) {
            instance = new BibliotecaFachada();
        }
        return instance;
    }

    // --- 1. GESTÃO DE USUÁRIOS ---

    public void cadastrarAluno(String nome, String senha, int matricula) {
        if (repositorioUsuarios.buscarPorId(matricula) != null) {
            throw new RegraNegocioException("Já existe um usuário com a matrícula " + matricula);
        }
        Aluno novo = new Aluno(matricula, nome, senha, LocalDate.now());
        repositorioUsuarios.salvar(novo);
    }

    public void cadastrarProfessor(String nome, String senha, int matricula) {
        if (repositorioUsuarios.buscarPorId(matricula) != null) {
            throw new RegraNegocioException("Já existe um usuário com a matrícula " + matricula);
        }
        Professor novo = new Professor(matricula, nome, senha, LocalDate.now());
        repositorioUsuarios.salvar(novo);
    }
    
    // Login simples
    public Usuario loginUsuario(int matricula, String senha) {
        Usuario u = repositorioUsuarios.buscarPorId(matricula);
        if (u != null && u.getSenha().equals(senha)) {
            return u;
        }
        throw new RegraNegocioException("Matrícula ou senha inválidos.");
    }
    
    public List<Usuario> listarUsuarios() {
        return repositorioUsuarios.listarTodos();
    }

    // --- 2. GESTÃO DE LIVROS ---

    public void cadastrarLivro(int id, String titulo, String autor, int quantidade) {
        if (repositorioLivros.buscarPorId(id) != null) {
            throw new RegraNegocioException("Já existe um livro com o ID " + id);
        }
        // Validação extra: Título duplicado? (Opcional)
        for (Livro l : repositorioLivros.listarTodos()) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                 throw new RegraNegocioException("Já existe um livro com este título: " + titulo);
            }
        }
        
        Livro novo = new Livro(id, titulo, autor, quantidade);
        repositorioLivros.salvar(novo);
    }
    
    public List<Livro> listarLivros() {
        return repositorioLivros.listarTodos();
    }
    
    public List<Livro> buscarLivroPorTitulo(String termo) {
        // O repositório poderia ter um método específico, mas podemos filtrar aqui
        List<Livro> todos = repositorioLivros.listarTodos();
        // Em Java 8+ daria pra usar Stream, mas vamos no clássico for
        // (Implementação simplificada: retorna todos por enquanto ou filtra na GUI)
        return todos; 
    }

    // --- 3. GESTÃO DE EMPRÉSTIMOS (O CORAÇÃO DO SISTEMA) ---

    public void realizarEmprestimo(int matriculaUsuario, int idLivro) {
        // 1. Buscas
        Usuario usuario = repositorioUsuarios.buscarPorId(matriculaUsuario);
        Livro livro = repositorioLivros.buscarPorId(idLivro);
        
        // 2. Validações Básicas
        if (usuario == null) throw new UsuarioNaoEncontradoException("Usuário não encontrado: " + matriculaUsuario);
        if (livro == null) throw new LivroNaoEncontradoException("Livro não encontrado: " + idLivro);
        
        // 3. Validações de Regra de Negócio
        if (!livro.isDisponivel()) {
            throw new LivroIndisponivelException("O livro '" + livro.getTitulo() + "' não tem exemplares disponíveis.");
        }
        
        // Verifica suspensão (método que criamos na classe Usuario)
        if (usuario.verificarSeEstaSuspenso(LocalDate.now())) {
             throw new RegraNegocioException("Usuário suspenso até " + usuario.getFimSuspensao());
        }

        // 4. Execução (Tenta usar crédito - se falhar, lança exceção do Model)
        usuario.usarCredito(); 
        livro.diminuirDisponibilidade();
        
        // 5. Cálculo da Devolução
        int diasPrazo = (usuario instanceof Professor) ? 14 : 7; // Exemplo: Professor tem mais tempo
        // Ou use o polimorfismo se tiver criado getPrazo() no Usuario
        
        LocalDate dataHoje = LocalDate.now();
        LocalDate dataDevolucao = dataHoje.plusDays(diasPrazo);
        
        // 6. Persistência
        Emprestimo emprestimo = new Emprestimo(idEmprestimoContador++, livro.getId(), usuario.getMatricula(), dataHoje, dataDevolucao);
        repositorioEmprestimos.salvar(emprestimo);
        
        // Atualiza o estado dos objetos nos repositórios (se necessário)
        // Como é em memória, a referência é a mesma, mas se fosse banco, precisaria de update.
    }
    
    public void devolverLivro(int idEmprestimo) {
        Emprestimo emp = repositorioEmprestimos.buscarPorId(idEmprestimo);
        if (emp == null) throw new RegraNegocioException("Empréstimo não encontrado.");
        if (emp.isDevolvido()) throw new RegraNegocioException("Este empréstimo já foi devolvido.");
        
        // Baixa no empréstimo
        emp.marcarComoDevolvido(LocalDate.now());
        
        // Devolve o crédito ao usuário
        Usuario u = repositorioUsuarios.buscarPorId(emp.getMatriculaUsuario());
        if (u != null) u.devolverCredito();
        
        // Devolve o livro ao estoque
        Livro l = repositorioLivros.buscarPorId(emp.getIdLivro());
        if (l != null) l.aumentarDisponibilidade();
        
        // Verifica multa/suspensão por atraso
        if (emp.isAtrasado(LocalDate.now())) {
            // Aplica suspensão de 7 dias como punição (Exemplo)
            if (u != null) u.suspenderPorDias(7, LocalDate.now());
        }
    }
    
    public List<Emprestimo> listarEmprestimos() {
        return repositorioEmprestimos.listarTodos();
    }


    // --- DADOS INICIAIS (Para teste) ---
    private void inicializarDadosTeste() {
        // Só cria se as listas estiverem vazias
        if (repositorioUsuarios.listarTodos().isEmpty()) {
            cadastrarAluno("Joao da Silva", "123", 1001);
            cadastrarProfessor("Prof. Girafales", "abc", 2001);
            cadastrarLivro(1, "Clean Code", "Robert C. Martin", 3);
            cadastrarLivro(2, "Design Patterns", "GoF", 2);
        }
    }
}