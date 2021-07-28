package br.com.alura.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
  private LocalDate dataCadastro = LocalDate.now();
  @ManyToOne(fetch = FetchType.LAZY) //lazy - carregamento só quando acessado (boa pratica), evita efeito cascata
  private Categoria categoria;

  public Produto() {

  }
  
  public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.categoria = categoria;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return String.format("Nome: %s | Preço: %.2f | Categoria: %s | Descrição: %s | Data Cadastro: %s", this.nome, this.preco, this.categoria.toString(), this.descricao, this.dataCadastro.toString());
  }
}
