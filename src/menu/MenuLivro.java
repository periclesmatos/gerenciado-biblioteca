package menu;

import dao.LivroDAO;
import model.Livro;
import utils.ConsoleUtils;

import java.sql.SQLException;
import java.util.List;

public class MenuLivro {
    private static final LivroDAO livroDAO = new LivroDAO();

    public void exibir() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- MENU LIVRO ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Editar Livro");
            System.out.println("4. Excluir Livro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = ConsoleUtils.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLviros();
                    break;
                case 3:
                    editarLivro();
                    break;
                case 4:
                    deletarLivro();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    /**
     * Lê os dados do livro via console e o registra no banco de dados.
     *
     * @throws SQLException se ocorrer erro ao registrar o aluno
     */
    public static void cadastrarLivro() throws SQLException {
        System.out.println("\n--- Cadastro de Livro ---");

        String titulo = ConsoleUtils.readString("Titulo: ");
        String autor = ConsoleUtils.readString("Autor: ");
        int anoDePublicacao = ConsoleUtils.readInt("Ano de Publicação: ");
        int quantidadeEmEstoque = ConsoleUtils.readInt("Quantidade em estoque: ");

        Livro livro = new Livro(titulo, autor, anoDePublicacao, quantidadeEmEstoque);
        livroDAO.registraLivro(livro);
    }

    /**
     * Lista todos os livros cadastrados no banco de dados e imprime no console.
     *
     * @throws SQLException se ocorrer erro ao buscar os alunos
     */
    public static void listarLviros() throws SQLException {
        System.out.println("\n--- Lista de Livros ---");

        List<Livro> livros = livroDAO.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado!");
        }

        livros.forEach(System.out::println);
    }

    /**
     * Lê os dados do livro via console e atualiza as informações no banco de dados.
     *
     * @throws SQLException se ocorrer erro ao atualizar o aluno
     */
    public static void editarLivro() throws SQLException {
        System.out.println("\n--- Editar Livro ---");

        String titulo = ConsoleUtils.readString("Titulo: ");
        String autor = ConsoleUtils.readString("Autor: ");
        int anoDePublicacao = ConsoleUtils.readInt("Ano de Publicação: ");
        int quantidadeEmEstoque = ConsoleUtils.readInt("Quantidade em estoque: ");
        int id = ConsoleUtils.readInt("ID: ");

        Livro livro = new Livro(titulo, autor, anoDePublicacao, quantidadeEmEstoque, id);
        livroDAO.atualizarLivro(livro);
    }

    /**
     * Lê o ID do livro via console e exclui as informações no banco de dados apos confirmação.
     *
     * @throws SQLException se ocorrer erro ao atualizar o aluno
     */
    public static void deletarLivro() throws SQLException {
        System.out.println("\n--- Excluir Livro ---");

        int id = ConsoleUtils.readInt("ID: ");
        boolean confirmacao = ConsoleUtils.confirm("Essa ação é irreverssivel, você confirma a exclusão? ");

        if (confirmacao) {
            livroDAO.deletarAluno(id);
        }
    }
}

