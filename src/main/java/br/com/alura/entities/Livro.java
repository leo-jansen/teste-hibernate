package br.com.alura.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro extends Produto {
  private String autor;
  private int numeroDePaginas;

  public Livro() {
  }

  public Livro(String nome, String descricao, BigDecimal preco, Categoria categoria, String autor, int numeroDePaginas) {
    super(nome, descricao, preco, categoria);
    this.autor = autor;
    this.numeroDePaginas = numeroDePaginas;
  }

  @Override
  public String toString() {
    return String.format("Nome: %s | Descriçao: %s | Preço: %.2f | Autor: %s | Numero de paginas: %d | Categoria: %s",
        this.getNome(), this.getDescricao(), this.getPreco(), this.autor, this.numeroDePaginas,
        this.getCategoria().toString());
  }
}
