package br.com.alura.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

  public Produto(String nome, String descricao, BigDecimal preco) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
  }
}
