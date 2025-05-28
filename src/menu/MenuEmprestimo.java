package menu;

import utils.ConsoleUtils;

import java.sql.SQLException;

public class MenuEmprestimo {
    public void exibir() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- MENU EMPRESTIMO ---");
            System.out.println("1. Registrar emprestimo");
            System.out.println("2. Listar emprestimos");
            System.out.println("3. Registrar devolução");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = ConsoleUtils.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    registrarEmprestimo();
                    break;
                case 2:
                    System.out.println("Listando livros...");
                    break;
                case 3:
                    System.out.println("Editando livro...");
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void registrarEmprestimo() {

    }
}
