package menu;

import dao.AlunoDAO;
import model.Aluno;
import utils.ConsoleUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MenuAluno {
    private static final AlunoDAO alunoDAO = new AlunoDAO();

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
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    editarAluno();
                    break;
                case 4:
                    deletarAluno();
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
     * Lê os dados do aluno via console e o registra no banco de dados.
     *
     * @throws SQLException se ocorrer erro ao registrar o aluno
     */
    public static void cadastrarAluno() throws SQLException {
        System.out.println("\n--- Cadastro de Aluno ---");

        String nome = ConsoleUtils.readString("Nome: ");
        String matricula = ConsoleUtils.readString("Matrícula: ");
        String dataNascimentoStr = ConsoleUtils.readString("Data de nascimento (AAAA-MM-DD): ");
        Date dataNascimento = Date.valueOf(dataNascimentoStr);

        Aluno aluno = new Aluno(nome, matricula, dataNascimento);
        alunoDAO.registrarAluno(aluno);
    }

    /**
     * Lista todos os alunos cadastrados no banco de dados e imprime no console.
     *
     * @throws SQLException se ocorrer erro ao buscar os alunos
     */
    public static void listarAlunos() throws SQLException {
        System.out.println("\n--- Listagem de Alunos ---");

        List<Aluno> alunos = alunoDAO.findAll();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
        }

        alunos.forEach(System.out::println);
    }

    /**
     * Lê os dados do aluno via console e atualiza as informações no banco de dados.
     *
     * @throws SQLException se ocorrer erro ao atualizar o aluno
     */
    public static void editarAluno() throws SQLException {
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
     * Lê o ID do aluno via console e exclui as informações no banco de dados apos confirmação.
     *
     * @throws SQLException se ocorrer erro ao atualizar o aluno
     */
    public static void deletarAluno() throws SQLException {
        System.out.println("\n--- Excluir Aluno ---");

        int id = ConsoleUtils.readInt("ID: ");
        boolean confirmacao = ConsoleUtils.confirm("Essa ação é irreverssivel, você confirma a exclusão? ");

        if (confirmacao) {
            alunoDAO.deletarAluno(id);
        }
    }
}
