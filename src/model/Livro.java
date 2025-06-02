package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe que representa um livro no sistema.
 * Contém informações como ID, título, autor, ano de publicação e quantidade em estoque.
 */
public class Livro {
    private int id;
    private String titulo;
    private  String autor;
    private int anoDePublicacao;
    private  int quantidadeEmEstoque;

    /**
     * Construtor padrão para criar um objeto Livro com todos os atributos.
     *
     * @param titulo o título do livro
     * @param autor o autor do livro
     * @param anoDePublicacao o ano de publicação do livro
     * @param quantidadeEmEstoque a quantidade em estoque do livro
     */
    public Livro(String titulo, String autor, int anoDePublicacao, int quantidadeEmEstoque) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    /**
     * Construtor para criar um objeto Livro com título, autor, ano de publicação, quantidade em estoque e ID.
     *
     * @param titulo o título do livro
     * @param autor o autor do livro
     * @param anoDePublicacao o ano de publicação do livro
     * @param quantidadeEmEstoque a quantidade em estoque do livro
     * @param id o ID do livro
     */
    public Livro(String titulo, String autor, int anoDePublicacao, int quantidadeEmEstoque, int id) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.id = id;
    }

    /**
     * Construtor que cria um objeto Livro a partir de um ResultSet.
     * Utilizado para mapear os dados retornados do banco de dados.
     *
     * @param resultSet o ResultSet contendo os dados do livro
     * @throws SQLException se ocorrer um erro ao acessar os dados do ResultSet
     */
    public Livro(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id_livro");
        this.titulo = resultSet.getString("titulo");
        this.autor = resultSet.getString("autor");
        this.anoDePublicacao = resultSet.getInt("ano_publicacao");
        this.quantidadeEmEstoque = resultSet.getInt("quantidade_estoque");
    }

    public int getId() {
        return  id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", titulo: '" + titulo + '\'' +
                ", autor: '" + autor + '\'' +
                ", ano de publicação: " + anoDePublicacao + '\'' +
                ", quantidade em estoque: " + quantidadeEmEstoque;
    }
}
