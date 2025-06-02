package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe que representa um aluno no sistema.
 * Contém informações como ID, nome, matrícula e data de nascimento.
 */
public class Emprestimo {
    private int id;
    private String nomeAluno;
    private String tituloLivro;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int idAluno;
    private int idLivro;

    /**
     * Construtor para criar um novo empréstimo com os dados do aluno e livro.
     *
     * @param idAluno ID do aluno que está realizando o empréstimo
     * @param idLivro ID do livro que está sendo emprestado
     * @param dataEmprestimo Data em que o empréstimo foi realizado
     * @param dataDevolucao Data prevista para devolução do livro
     */
    public Emprestimo(int idAluno, int idLivro, Date dataEmprestimo, Date dataDevolucao) {
        this.idAluno = idAluno;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * Construtor para criar um novo empréstimo com os dados do aluno, livro e ID do empréstimo.
     *
     * @param resultSet ID do aluno que está realizando o empréstimo
     */
    public Emprestimo(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id_emprestimo");
        this.nomeAluno = resultSet.getNString("nome_aluno");
        this.tituloLivro = resultSet.getNString("titulo_livro");
        this.dataEmprestimo = resultSet.getDate("data_emprestimo");
        this.dataDevolucao = resultSet.getDate("data_devolucao");
    }

    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id do Emprestimo: ").append(id)
                .append(", Aluno: '").append(nomeAluno).append('\'')
                .append(", Livro: '").append(tituloLivro).append('\'')
                .append(", Data do emprestimo: ").append(dataEmprestimo);
        if (dataDevolucao != null) {
            sb.append(", Data de devolução: ").append(dataDevolucao);
        }
        return sb.toString();
    }
}
