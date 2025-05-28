package menu;

import dao.LivroDAO;
import model.Livro;
import utils.ConsoleUtils;

import java.sql.SQLException;

public class MenuLivro {
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
                    System.out.println("Listando livros...");
                    break;
                case 3:
                    System.out.println("Editando livro...");
                    break;
                case 4:
                    System.out.println("Excluindo livro...");
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

        LivroDAO livroDAO = new LivroDAO();
        livroDAO.registraLivro(livro);
    }
}

