package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno {
    private int id;
    private String nome;
    private String matricula;
    private Date dataDeNascimento;

    public Aluno(String nome, String matricula, Date dataDeNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataDeNascimento = dataDeNascimento;
    }
    public Aluno(String nome, String matricula, Date dataDeNascimento, int id) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataDeNascimento = dataDeNascimento;
        this.id = id;
    }

    public Aluno(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id_aluno");
        this.nome = resultSet.getString("nome_aluno");
        this.matricula = resultSet.getString("matricula");
        this.dataDeNascimento = resultSet.getDate("data_nascimento");
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDenascimento) {
        this.dataDeNascimento = dataDenascimento;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", nome: '" + nome + '\'' +
                ", matricula: '" + matricula + '\'' +
                ", dataDeNascimento: " + dataDeNascimento;
    }
}
