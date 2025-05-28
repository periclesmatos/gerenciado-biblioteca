package menu;

import dao.AlunoDAO;
import model.Aluno;
import utils.ConsoleUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MenuAluno {
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

        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.registrarAluno(aluno);
    }

    public static void listarAlunos() throws SQLException {
        System.out.println("\n--- Listagem de Alunos ---");

        AlunoDAO alunoDAO = new AlunoDAO();
        List<Aluno> alunos = alunoDAO.findAll();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
        }

        alunos.forEach(System.out::println);
    }
}
