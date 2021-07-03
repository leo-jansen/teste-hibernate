package br.com.alura.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
  private String nome;

  public Categoria(String nome) {
    this.nome = nome;
  }
}
