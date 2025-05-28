package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Livro {
    private int id;
    private String titulo;
    private  String autor;
    private int anoDePublicacao;
    private  int quantidadeEmEstoque;

    public Livro(String titulo, String autor, int anoDePublicacao, int quantidadeEmEstoque) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Livro(String titulo, String autor, int anoDePublicacao, int quantidadeEmEstoque, int id) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.id = id;
    }

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
