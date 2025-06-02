package menu;

import dao.LivroDAO;
import model.Livro;
import utils.ConsoleUtils;

import java.sql.SQLException;
import java.util.List;

public class MenuLivro {
    private static final LivroDAO livroDAO = new LivroDAO();

    /**
     * Exibe o menu de operações relacionadas a livros e executa a ação escolhida pelo usuário.
     *
     * @throws SQLException se ocorrer erro ao acessar o banco de dados durante alguma operação
     */
    public void exibir() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- MENU LIVRO ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Editar Livro");
            System.out.println("4. Excluir Livro");
            System.out.println("0. Voltar");

            opcao = ConsoleUtils.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    executarCadastrarLivro();
                    break;
                case 2:
                    executarListarLivros();
                    break;
                case 3:
                    executarAtualizarLivro();
                    break;
                case 4:
                    executarExcluirLivro();
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
     * Lê os dados do livro via console e executa o cadastro do novo livro.
     *
     * @throws SQLException se ocorrer erro ao registrar o livro no banco de dados.
     */
    public static void executarCadastrarLivro() throws SQLException {
        System.out.println("\n--- Cadastro de Livro ---");

        String titulo = ConsoleUtils.readString("Titulo: ");
        String autor = ConsoleUtils.readString("Autor: ");
        int anoDePublicacao = ConsoleUtils.readInt("Ano de Publicação: ");
        int quantidadeEmEstoque = ConsoleUtils.readInt("Quantidade em estoque: ");

        Livro livro = new Livro(titulo, autor, anoDePublicacao, quantidadeEmEstoque);
        livroDAO.registraLivro(livro);
    }

    /**
     * Lista todos os livros cadastrados no sistema.
     *
     * @throws SQLException se ocorrer erro ao buscar os livros no banco de doaos.
     */
    public static void executarListarLivros() throws SQLException {
        System.out.println("\n--- Lista de Livros ---");

        List<Livro> livros = livroDAO.listarLivros();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado!");
        }

        livros.forEach(System.out::println);
    }

    /**
     * Lê os dados do livro via console e executa a atualização das informações de um livro existente.
     *
     * @throws SQLException se ocorrer erro ao atualizar o livro no banco de dados.
     */
    public static void executarAtualizarLivro() throws SQLException {
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
     * Lê o ID do livro via console e executa a exclusão do livro após confirmação do usuário.
     *
     * @throws SQLException se ocorrer erro ao excluir o livro no banco de dados.
     */
    public static void executarExcluirLivro() throws SQLException {
        System.out.println("\n--- Excluir Livro ---");

        int id = ConsoleUtils.readInt("ID: ");
        boolean confirmacao = ConsoleUtils.confirm("Essa ação é irreverssivel, você confirma a exclusão? ");

        if (confirmacao) {
            livroDAO.deletarLivro(id);
        }
    }
}

