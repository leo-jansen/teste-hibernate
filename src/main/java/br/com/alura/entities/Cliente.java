package br.com.alura.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String nome;
  private String cpf;
  @Embedded // os atrbutos vao ser embutidos nessa classe
  private Endereco endereco;

  public Cliente() {
  }

  public Cliente(String nome, String cpf, String logradouro, String bairro, String cidade, String estado, String cep) {
    this.nome = nome;
    this.cpf = cpf;
    this.endereco = new Endereco(logradouro, bairro, cidade, estado, cep);
  }

  @Override
  public String toString() {
    return String.format("Nome: %s | CPF: %s | Endereço: %s", this.nome, this.cpf, this.endereco.toString());
  }
}
