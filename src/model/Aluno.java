package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe que representa um aluno no sistema.
 * Contém informações como ID, nome, matrícula e data de nascimento.
 */
public class Aluno {
    private int id;
    private String nome;
    private String matricula;
    private Date dataDeNascimento;

    /**
     * Construtor padrão para criar um objeto Aluno com todos os atributos.
     *
     * @param nome o nome do aluno
     * @param matricula a matrícula do aluno
     * @param dataDeNascimento a data de nascimento do aluno
     */
    public Aluno(String nome, String matricula, Date dataDeNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataDeNascimento = dataDeNascimento;
    }

    /**
     * Construtor para criar um objeto Aluno com nome, data de nascimento e ID.
     *
     * @param nome o nome do aluno
     * @param dataDeNascimento a data de nascimento do aluno
     * @param id o ID do aluno
     */
    public Aluno(String nome, Date dataDeNascimento, int id) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.id = id;
    }

    /**
     * Construtor que cria um objeto Aluno a partir de um ResultSet.
     * Utilizado para mapear os dados retornados do banco de dados.
     *
     * @param resultSet o ResultSet contendo os dados do aluno
     * @throws SQLException se ocorrer um erro ao acessar os dados do ResultSet
     */
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
