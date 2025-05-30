package dao;

import model.Emprestimo;
import utils.ConexaoDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por gerenciar as operações de empréstimo de livros no banco de dados.
 */
public class EmprestimoDAO {
    /**
     * Registra um novo empréstimo de livro para um aluno.
     *
     * <p>Esse método realiza as seguintes ações:</p>
     * <ul>
     *   <li>Verifica se o livro existe e se há unidades disponíveis em estoque.</li>
     *   <li>Atualiza o estoque do livro, reduzindo a quantidade em 1.</li>
     *   <li>Insere um registro de empréstimo com data de devolução null</li>
     * </ul>
     *
     * @param idAluno o identificador do aluno que está realizando o empréstimo
     * @param idLivro o identificador do livro que está sendo emprestado
     * @throws SQLException se ocorrer algum erro de acesso ao banco de dados
     */
    public void registrarEmprestimo(int idAluno, int idLivro) throws SQLException {
        try (Connection connection = ConexaoDB.getConnection()) {
            // Verificar se o livro esta disponivel
            String selectLivroEstoqueByIdSql = "SELECT quantidade_estoque FROM Livros " +
                                               "WHERE id_livro = ?";

            try (PreparedStatement checkEstoqueStmt = connection.prepareStatement(selectLivroEstoqueByIdSql)) {
                checkEstoqueStmt.setInt(1, idLivro);

                ResultSet resultSet = checkEstoqueStmt.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("Livro não encontrado.");
                    return;
                }

                int quantidadeEmEstoque = resultSet.getInt("quantidade_estoque");
                if (quantidadeEmEstoque <= 0) {
                    System.out.println("Livro indisponivel!");
                    return;
                }
            }
            // Atualizar quantidade do livro no estoque.
            String updateLivroEstoqueSql = "UPDATE Livros " +
                                           "SET quantidade_estoque = quantidade_estoque - 1 " +
                                           "WHERE id_livro = ?";

            try (PreparedStatement updateEstoqueStmt = connection.prepareStatement(updateLivroEstoqueSql)) {
                updateEstoqueStmt.setInt(1, idLivro);
                updateEstoqueStmt.executeUpdate();
            }

            // Registrar emprestimo no banco de dados.
            String insertEmprestimoSql = "INSERT INTO Emprestimos (id_aluno, id_livro, data_emprestimo) " +
                                         "VALUES (?, ?, ?)";

            try (PreparedStatement insertEmprestimoStmt = connection.prepareStatement(insertEmprestimoSql)) {
                insertEmprestimoStmt.setInt(1, idAluno);
                insertEmprestimoStmt.setInt(2, idLivro);
                insertEmprestimoStmt.setDate(3, Date.valueOf(LocalDate.now()));
                insertEmprestimoStmt.executeUpdate();
                System.out.println("Empréstimo registrado com sucesso.");
            }
        } catch (SQLException e) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, "Erro ao registrar empréstimo", e);
            throw e;
        }
    }

    /**
     * Registra a devolução de um empréstimo e atualiza o estoque do livro.
     *
     * <p>Esse método realiza as seguintes ações:</p>
     * <ul>
     *   <li>Verifica se o empréstimo existe e se ainda não foi devolvido.</li>
     *   <li>Atualiza o campo {@code data_devolucao} do empréstimo com a data atual.</li>
     *   <li>Incrementa a quantidade de estoque do livro devolvido.</li>
     * </ul>
     *
     * @param idEmprestimo o identificador do empréstimo a ser finalizado
     * @throws SQLException se ocorrer um erro durante a execução da operação
     */
    public void registrarDevolucao(int idEmprestimo) throws SQLException {
        String selectAllEmprestimosAtivoSql = "SELECT id_livro FROM Emprestimos " +
                                              "WHERE id_emprestimo = ? " +
                                              "AND data_devolucao IS NULL";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement getEmprestimoAtivoStmt = connection.prepareStatement(selectAllEmprestimosAtivoSql))
        {
            // Verificar se o emprestimo existe e ainda não foi devolvido
            getEmprestimoAtivoStmt.setInt(1, idEmprestimo);
            ResultSet resultSet = getEmprestimoAtivoStmt.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Empréstimo não encontrado ou já devolvido.");
                return;
            }

            int idLivro = resultSet.getInt("id_livro");

            // Atualizar a data de devolução do emprestimo
            String updateEmprestimoDevolucaoSql = "UPDATE Emprestimos " +
                                                  "SET data_devolucao = ? " +
                                                  "WHERE id_emprestimo = ?";

            try (PreparedStatement updateDevolucaoStmt = connection.prepareStatement(updateEmprestimoDevolucaoSql)) {
                updateDevolucaoStmt.setDate(1, Date.valueOf(LocalDate.now()));
                updateDevolucaoStmt.setInt(2, idEmprestimo);
                updateDevolucaoStmt.executeUpdate();
            }

            // Atualiza a quantida no estoque
            String updateLivroEstoqueSql = "UPDATE Livros " +
                                           "SET quantidade_estoque = quantidade_estoque + 1 " +
                                           "WHERE id_livro = ?";

            try (PreparedStatement updateEstoqueStmt = connection.prepareStatement(updateLivroEstoqueSql)) {
                updateEstoqueStmt.setInt(1, idLivro);
                updateEstoqueStmt.executeUpdate();
                System.out.println("Devolução registrada com sucesso.");
            }
        } catch (SQLException e) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, "Erro ao registrar devolução", e);
            throw e;
        }
    }

    /**
     * Recupera todos os empréstimos ativos registrados no sistema.
     *
     * <p><b>RETORNA:</b> Uma lista contendo todos os objetos {@code Emprestimo} ativos encontrados.</p>
     *
     * @return uma lista de objetos {@code Emprestimo} representando os registros no banco de dados
     * @throws SQLException se ocorrer um erro durante a consulta
     */
    public List<Emprestimo> listarEmprestimosAtivos() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String selectAllEmprestimosSql = "SELECT * FROM Emprestimos" +
                                         "WHERE data_devolucao IS NULL";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement selectAllEmprestimosStmt = connection.prepareStatement(selectAllEmprestimosSql);
             ResultSet resultSet = selectAllEmprestimosStmt.executeQuery()) {

            while (resultSet.next()) {
                emprestimos.add(new Emprestimo(resultSet));
            }
            return emprestimos;
        } catch (SQLException e) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, "Erro ao listar empréstimos", e);
            throw e;
        }
    }

    /**
     * Recupera todos os empréstimos registrados no sistema.
     *
     * <p><b>RETORNA:</b> Uma lista contendo todos os objetos {@code Emprestimo} encontrados.</p>
     *
     * @return uma lista de objetos {@code Emprestimo} representando os registros no banco de dados
     * @throws SQLException se ocorrer um erro durante a consulta
     */
    public List<Emprestimo> listarEmprestimos() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String selectAllEmprestimosSql = "SELECT * FROM Emprestimos";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement selectAllEmprestimosStmt = connection.prepareStatement(selectAllEmprestimosSql);
             ResultSet resultSet = selectAllEmprestimosStmt.executeQuery()) {

            while (resultSet.next()) {
                emprestimos.add(new Emprestimo(resultSet));
            }
            return emprestimos;
        } catch (SQLException e) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, "Erro ao listar empréstimos", e);
            throw e;
        }
    }
}
