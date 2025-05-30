package menu;

import dao.AlunoDAO;
import model.Aluno;
import utils.ConsoleUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MenuAluno {
    private static final AlunoDAO alunoDAO = new AlunoDAO();

    /**
     * Exibe o menu de operações relacionadas a alunos e executa a ação escolhida pelo usuário.
     *
     * @throws SQLException se ocorrer erro ao acessar o banco de dados durante alguma operação.
     */
    public void exibir() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- MENU ALUNO ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("3. Editar Aluno");
            System.out.println("4. Excluir Aluno");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = ConsoleUtils.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    executarCadastrarAluno();
                    break;
                case 2:
                    executarListarAlunos();
                    break;
                case 3:
                    executarAtualizarAluno();
                    break;
                case 4:
                    executarExcluirAluno();
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
     * Lê os dados do aluno via console e executa o cadastro de um novo aluno.
     *
     * @throws SQLException se ocorrer erro ao registrar o aluno no banco de dados.
     */
    public static void executarCadastrarAluno() throws SQLException {
        System.out.println("\n--- Cadastro de Aluno ---");

        String nome = ConsoleUtils.readString("Nome: ");
        String matricula = ConsoleUtils.readString("Matrícula: ");
        String dataNascimentoStr = ConsoleUtils.readString("Data de nascimento (AAAA-MM-DD): ");
        Date dataNascimento = Date.valueOf(dataNascimentoStr);

        Aluno aluno = new Aluno(nome, matricula, dataNascimento);
        alunoDAO.registrarAluno(aluno);
    }

    /**
     * Lista todos os alunos cadastrados no sistema e imprime no console.
     *
     * @throws SQLException se ocorrer erro ao buscar os alunos no banco de dados.
     */
    public static void executarListarAlunos() throws SQLException {
        System.out.println("\n--- Listagem de Alunos ---");

        List<Aluno> alunos = alunoDAO.listarAlunos();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
        }

        alunos.forEach(System.out::println);
    }

    /**
     * Lê os dados do aluno via console e executa a atualização das informações de um aluno existente.
     *
     * @throws SQLException se ocorrer erro ao atualizar o aluno no banco de dados.
     */
    public static void executarAtualizarAluno() throws SQLException {
        System.out.println("\n--- Editar Aluno ---");

        String nome = ConsoleUtils.readString("Nome: ");
        String matricula = ConsoleUtils.readString("Matrícula: ");
        String dataNascimentoStr = ConsoleUtils.readString("Data de nascimento (AAAA-MM-DD): ");
        Date dataNascimento = Date.valueOf(dataNascimentoStr);
        int id = ConsoleUtils.readInt("ID: ");

        Aluno aluno = new Aluno(nome, matricula, dataNascimento, id);
        alunoDAO.atualizarAluno(aluno);
    }

    /**
     * Lê o ID do aluno via console e executa a exclusão do aluno após confirmação do usuário.
     *
     * @throws SQLException se ocorrer erro ao excluir o aluno no banco de dados.
     */
    public static void executarExcluirAluno() throws SQLException {
        System.out.println("\n--- Excluir Aluno ---");

        int id = ConsoleUtils.readInt("ID: ");
        boolean confirmacao = ConsoleUtils.confirm("Essa ação é irreverssivel, você confirma a exclusão? ");

        if (confirmacao) {
            alunoDAO.deletarAluno(id);
        }
    }
}
