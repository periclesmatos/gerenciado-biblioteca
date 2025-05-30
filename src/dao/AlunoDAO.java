package dao;

import model.Aluno;
import utils.ConexaoDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    /**
     * Insere um novo aluno no banco de dados.
     *
     * <p><b>NOTE:</b> O campo {@code id} do objeto {@code aluno} pode ser ignorado,
     * pois será gerado automaticamente pelo banco de dados.</p>
     *
     * @param aluno objeto contendo os dados do aluno a serem registrados, incluindo nome, matrícula e data de nascimento
     * @throws SQLException se ocorrer um erro durante a inserção no banco de dados
     */
    public void registrarAluno(Aluno aluno) throws SQLException {
        String insertAlunoSql = "INSERT INTO Alunos (nome_aluno, matricula, data_nascimento) " +
                                "VALUES (?, ?, ?)";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement insertAlunoStmt = connection.prepareStatement(insertAlunoSql)
        ) {
            insertAlunoStmt.setString(1, aluno.getNome());
            insertAlunoStmt.setString(2, aluno.getMatricula());
            insertAlunoStmt.setDate(3, Date.valueOf(aluno.getDataDeNascimento().toLocalDate()));
            insertAlunoStmt.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(AlunoDAO.class.getName()).log(java.util.logging.Level.SEVERE, "Erro ao cadastrar aluno", e);
        }
    }

    /**
     * Atualiza no banco de dados as informações de um aluno existente com base no seu ID.
     *
     * <p><b>TIP:</b> O campo {@code id} do objeto {@code aluno} deve estar preenchido corretamente,
     * pois ele é utilizado para localizar o registro a ser atualizado.</p>
     *
     * @param aluno objeto contendo os dados atualizados do aluno, incluindo o ID, nome, matrícula e data de nascimento
     * @throws SQLException se ocorrer um erro durante a execução da operação no banco de dados
     */
    public void atualizarAluno(Aluno aluno) throws SQLException{
        String updateAlunoSql = "UPDATE Alunos " +
                                "SET nome_aluno = ?, matricula = ?, data_nascimento = ? " +
                                "WHERE id_aluno = ?";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement updateAlunoStmt = connection.prepareStatement(updateAlunoSql))
        {
            updateAlunoStmt.setString(1, aluno.getNome());
            updateAlunoStmt.setString(2, aluno.getMatricula());
            updateAlunoStmt.setDate(3, Date.valueOf(aluno.getDataDeNascimento().toLocalDate()));
            updateAlunoStmt.setInt(4, aluno.getId());
            updateAlunoStmt.executeUpdate();
            System.out.println("Aluno atualizado com sucesso!");
        }
    }

    /**
     * Remove um aluno do banco de dados com base no seu ID.
     *
     * <p><b>IMPORTANTE:</b> Esta operação é irreversível. Certifique-se de que o ID fornecido corresponde
     * a um aluno existente antes de executar este método.</p>
     *
     * @param idAluno o identificador único do aluno a ser removido
     * @throws SQLException se ocorrer um erro durante a execução da operação no banco de dados
     */
    public void deletarAluno(int idAluno) throws SQLException {
        String deleteAlunoSql = "DELETE FROM Alunos " +
                                "WHERE id_aluno = ?";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement deleteAlunoStmt = connection.prepareStatement(deleteAlunoSql))
        {
            deleteAlunoStmt.setInt(1, idAluno);
            deleteAlunoStmt.executeUpdate();
            System.out.println("Aluno deletado com sucesso!");
        }
    }

    /**
     * Recupera um aluno do banco de dados com base no seu ID.
     *
     * <p><b>RETORNA:</b> Um objeto {@code Aluno} se o ID for encontrado, ou {@code null} caso não exista
     * nenhum aluno com o ID informado.</p>
     *
     * @param idAluno o identificador único do aluno a ser consultado
     * @return o objeto {@code Aluno} correspondente ao ID fornecido, ou {@code null} se não encontrado
     * @throws SQLException se ocorrer um erro durante a consulta ao banco de dados
     */
    public Aluno buscarAlunoPorId(int idAluno) throws SQLException {
        String selectAlunoByIdSql = "SELECT * FROM Alunos " +
                                    "WHERE id_aluno = ?";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement selectAlunoByIdStmt = connection.prepareStatement(selectAlunoByIdSql))
        {
            selectAlunoByIdStmt.setInt(1, idAluno);
            try (ResultSet resultSet = selectAlunoByIdStmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Aluno(resultSet);
                }
                return null;
            }
        }
    }

    /**
     * Recupera todos os alunos registrados no banco de dados.
     *
     * <p><b>RETORNA:</b> Uma lista contendo todos os objetos {@code Aluno} encontrados.</p>
     *
     * @return uma lista de todos os alunos armazenados no banco
     * @throws SQLException se ocorrer um erro durante a execução da consulta
     */
    public List<Aluno> listarAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String selectAllAlunosSql = "SELECT * FROM Alunos";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement selectAllAlunosStmt = connection.prepareStatement(selectAllAlunosSql);
             ResultSet resultSet = selectAllAlunosStmt.executeQuery())
        {
            while (resultSet.next()) {
                alunos.add(new Aluno(resultSet));
            }
        }
        return alunos;
    }
}
