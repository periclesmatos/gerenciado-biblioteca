package menu;

import utils.ConsoleUtils;

import java.sql.SQLException;

/**
 * Exibe o menu principal da biblioteca e executa as ações com base na escolha do usuário.
 *
 * @throws SQLException caso ocorra erro ao acessar o banco de dados
 */
public class MenuPrincipal {
    public void exibir() throws SQLException {
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Livros");
            System.out.println("2. Gerenciar Alunos");
            System.out.println("3. Gerenciar Empréstimos");
            System.out.println("0. Sair");

            opcao = ConsoleUtils.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    new MenuLivro().exibir();
                    break;
                case 2:
                    new MenuAluno().exibir();
                    break;
                case 3:
                    new MenuEmprestimo().exibir();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}

