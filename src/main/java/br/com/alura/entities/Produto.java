package br.com.alura.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
  private String nome;
  private String descricao;
  private BigDecimal preco;
  @ManyToOne
  private Categoria categoria;

  public Produto() {

  }
  
  public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.categoria = categoria;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return String.format("Nome: %s | Preço: %.2f | Categoria: %s | Descrição: %s", this.nome, this.preco, this.categoria.toString(), this.descricao);
  }
}
