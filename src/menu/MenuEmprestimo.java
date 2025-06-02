package menu;

import dao.EmprestimoDAO;
import model.Emprestimo;
import utils.ConsoleUtils;

import java.sql.SQLException;
import java.util.List;

public class MenuEmprestimo {
    private static final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    /**
     * Exibe o menu de operações relacionadas a empréstimos e executa a ação escolhida pelo usuário.
     *
     * @throws SQLException se ocorrer erro ao acessar o banco de dados durante alguma operação
     */
    public void exibir() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- MENU EMPRESTIMO ---");
            System.out.println("1. Registrar emprestimo");
            System.out.println("2. Registrar devolução");
            System.out.println("3. Listar emprestimos ativos");
            System.out.println("4. Listar todos os emprestimos resgistrados");
            System.out.println("0. Voltar");

            opcao = ConsoleUtils.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    executarRegistrarEmprestimo();
                    break;
                case 2:
                    exeutarRegistrarDevolucoo();
                    break;
                case 3:
                    executarListarEmprestimoAtivos();
                    break;
                case 4:
                    executarListarEmprestimos();
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
     * Lê o ID do aluno e do livro via console e executa o registro de um novo empréstimo.
     *
     * @throws SQLException se ocorrer erro ao registrar o empréstimo no banco de dados.
     */
    public static void executarRegistrarEmprestimo() throws SQLException {
        System.out.println("\n--- Registrar Emprestimo ---");

        int idAluno = ConsoleUtils.readInt("ID do Aluno: ");
        int idLivro = ConsoleUtils.readInt("ID do Livro: ");

        emprestimoDAO.registrarEmprestimo(idAluno, idLivro);
    }

    /**
     * Lê o ID do empréstimo via console e executa o registro da devolução.
     *
     * @throws SQLException se ocorrer erro ao registrar a devolução no banco de dados.
     */
    public static void exeutarRegistrarDevolucoo() throws SQLException {
        System.out.println("\n--- Registrar Devolução ---");

        int idEmprestimo = ConsoleUtils.readInt("ID do Emprestimo: ");

        emprestimoDAO.registrarDevolucao(idEmprestimo);
    }

    /**
     * Lista todos os empréstimos cadastrados no sistema e imprime no console.
     *
     * @throws SQLException se ocorrer erro ao buscar os empréstimos no banco de dados.
     */
    public static void executarListarEmprestimoAtivos() throws SQLException {
        System.out.println("\n--- Listagem de emprestimos ativos ---");

        List<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimosAtivos();

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum emprestimo ativo registrado!");
        }

        emprestimos.forEach(System.out::println);
    }

    public static void executarListarEmprestimos() throws SQLException {
        System.out.println("\n--- Listagem de todos os emprestimos ---");

        List<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimos();

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum emprestimo registrado!");
        }

        emprestimos.forEach(System.out::println);
    }
}
