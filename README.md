# 📚 Sistema de Gerenciamento de Biblioteca

Projeto da disciplina de Programação Orientada a Objetos. O sistema gerencia empréstimos de livros para alunos e professores, aplicando conceitos de POO como Herança, Polimorfismo e Encapsulamento.

## 👥 Equipe
* **Membro 1:** [Gilvaney Leandro]
* **Membro 2:** [Fábio André]
* **Membro 3:** [Rafaelly Cristine]

## 🛠️ Arquitetura do Projeto
O projeto segue a arquitetura em camadas (MVC simplificado):
* `src/br/com/biblioteca/model`: Classes de domínio (Livro, Usuario, etc).
* `src/br/com/biblioteca/repository`: Armazenamento de dados em memória.
* `src/br/com/biblioteca/service`: Regras de negócio (Empréstimos, Validações).
* `src/br/com/biblioteca/view`: Telas (Swing).

## 📊 Diagrama de Classes
```mermaid
classDiagram
    class Usuario {
        <<abstract>>
        -int matricula
        -String nome
        -String senha
        +getLimiteCreditos()* int
    }
    class Aluno {
        +getLimiteCreditos() int
    }
    class Professor {
        +getLimiteCreditos() int
    }
    class Funcionario {
        -int matricula
        -String nome
    }
    class Livro {
        -String titulo
        -int quantidadeDisponivel
        +isDisponivel() boolean
    }
    class Emprestimo {
        -LocalDate dataEmprestimo
        -LocalDate dataPrevistaDevolucao
        +isAtrasado() boolean
    }
    class BibliotecaFachada {
        +realizarEmprestimo(...)
        +cadastrarLivro(...)
        +cadastrarUsuario(...)
    }

    Usuario <|-- Aluno
    Usuario <|-- Professor
    BibliotecaFachada o--> Usuario
    BibliotecaFachada o--> Livro
    BibliotecaFachada o--> Emprestimo
