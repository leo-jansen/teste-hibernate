package br.com.alura.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "informatica")
public class Informatica extends Produto{
  private String marca;
  private String modelo;

  public Informatica(){
  }

  public Informatica(String nome, String descricao, BigDecimal preco, Categoria categoria, String marca, String modelo){
    super(nome, descricao, preco, categoria);
    this.marca = marca;
    this.modelo = modelo;
  }

  @Override
  public String toString() {
    return String.format("Nome: %s | Descriçao: %s | Preço: %.2f | Marca: %s | Modelo: %s | Categoria: %s",
        this.getNome(), this.getDescricao(), this.getPreco(), this.marca, this.modelo,
        this.getCategoria().toString());
  }
}
