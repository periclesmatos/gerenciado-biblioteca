package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Emprestimo {
    private int id;
    private int idAluno;
    private int idLivro;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(int idAluno, int idLivro, Date dataEmprestimo, Date dataDevolucao) {
        this.idAluno = idAluno;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id_emprestimo");
        this.idAluno = resultSet.getInt("id_aluno");
        this.idLivro = resultSet.getInt("id_livro");
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
}
