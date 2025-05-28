package dao;

import model.Livro;
import utils.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class LivroDAO {
    /**
     * Insere um novo livro no banco de dados.
     *
     * <p><b>NOTE:</b> O campo {@code id} do objeto {@code livro} pode ser ignorado,
     * pois será gerado automaticamente pelo banco de dados.</p>
     *
     * @param livro objeto contendo os dados do livro a serem registrados, incluindo titulo, autor, ano de publicação e quantidade em estoque
     * @throws SQLException se ocorrer um erro durante a inserção no banco de dados
     */
    public void registraLivro(Livro livro) throws SQLException {
        String insertLivroSql = "INSERT INTO Livros (titulo, autor, ano_publicacao, quantidade_estoque) VALUE (?, ?, ?, ?)";
        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement insertLivroStmt = connection.prepareStatement(insertLivroSql))
        {
            insertLivroStmt.setString(1, livro.getTitulo());
            insertLivroStmt.setString(2, livro.getAutor());
            insertLivroStmt.setInt(3,livro.getAnoDePublicacao());
            insertLivroStmt.setInt(4,livro.getQuantidadeEmEstoque());
            insertLivroStmt.executeUpdate();
            System.out.println("Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, "Erro ao cadastrar livro");

        }
    }

    /**
     * Atualiza no banco de dados as informações de um livro existente com base no seu ID.
     *
     * <p><b>TIP:</b> O campo {@code id} do objeto {@code livro} deve estar preenchido corretamente,
     * pois ele é utilizado para localizar o registro a ser atualizado.</p>
     *
     * @param livro objeto contendo os dados atualizados do livro, incluindo o ID, titulo, autor, ano de publicação e quantidade em estoque
     * @throws SQLException se ocorrer um erro durante a execução da operação no banco de dados
     */
    public void atualizarLivro(Livro livro) throws SQLException {
        String updateLivroSql = "UPDATE Livro SET titulo = ?, autor = ?, quantidade_estoque = ? WHERE id_livro = ?";
        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement updateLivroStmt = connection.prepareStatement(updateLivroSql))
        {
            updateLivroStmt.setString(1, livro.getTitulo());
            updateLivroStmt.setString(2, livro.getAutor());
            updateLivroStmt.setInt(3, livro.getAnoDePublicacao());
            updateLivroStmt.setInt(4, livro.getQuantidadeEmEstoque());
            updateLivroStmt.setInt(4, livro.getId());
            updateLivroStmt.executeUpdate();
            System.out.println("Livro atualizado com sucesso!");
        }
    }

    /**
     * Remove um livro do banco de dados com base no seu ID.
     *
     * <p><b>IMPORTANTE:</b> Esta operação é irreversível. Certifique-se de que o ID fornecido corresponde
     * a um livro existente antes de executar este método.</p>
     *
     * @param idLivro o identificador único do livro a ser removido
     * @throws SQLException se ocorrer um erro durante a execução da operação no banco de dados
     */
    public void deletarAluno(int idLivro) throws SQLException {
        String deleteLivroSql = "DELETE FROM Livros WHERE id_livro = ?";
        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement deleteLivroStmt = connection.prepareStatement(deleteLivroSql))
        {
            deleteLivroStmt.setInt(1, idLivro);
            deleteLivroStmt.executeUpdate();
            System.out.println("Livro deletado com sucesso!");
        }
    }

    /**
     * Recupera um livro do banco de dados com base no seu ID.
     *
     * <p><b>RETORNA:</b> Um objeto {@code Livro} se o ID for encontrado, ou {@code null} caso não exista
     * nenhum livro com o ID informado.</p>
     *
     * @param idLivro o identificador único do livro a ser consultado
     * @return o objeto {@code Livro} correspondente ao ID fornecido, ou {@code null} se não encontrado
     * @throws SQLException se ocorrer um erro durante a consulta ao banco de dados
     */
    public Livro findById(int idLivro) throws SQLException {
        String selectLivroByIdSql = "SELECT * FROM Livros WHERE id_livro = ?";
        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement selectLivroByIdStmt = connection.prepareStatement(selectLivroByIdSql))
        {
            selectLivroByIdStmt.setInt(1, idLivro);
            try (ResultSet resultSet = selectLivroByIdStmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Livro(resultSet);
                }
                return null;
            }
        }
    }

    /**
     * Recupera todos os livros registrados no banco de dados.
     *
     * <p><b>RETORNA:</b> Uma lista contendo todos os objetos {@code Livros} encontrados.</p>
     *
     * @return uma lista de todos os livros armazenados no banco
     * @throws SQLException se ocorrer um erro durante a execução da consulta
     */
    public List<Livro> findAll() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String selectAllLivrosSql = "SELECT * FROM Livros";
        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement selectAllLivrosStmt = connection.prepareStatement(selectAllLivrosSql);
             ResultSet resultSet = selectAllLivrosStmt.executeQuery()) {
            while (resultSet.next()) {
                livros.add(new Livro(resultSet));
            }
        }
        return livros;
    }
}
