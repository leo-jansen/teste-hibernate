package br.com.alura.entities;

import javax.persistence.Embeddable;

@Embeddable // os atributos da classe são embutidas em uma entidade, precisa ter a anotação @Embedded no atributo da entidade
public class Endereco {
  private String logradouro;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;

  public Endereco() {
  }

  public Endereco(String logradouro, String bairro, String cidade, String estado, String cep) {
    this.logradouro = logradouro;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.cep = cep;
  }

  @Override
  public String toString() {
    return String.format("%s, %s, %s - %s, %s", this.logradouro, this.bairro, this.cidade, this.estado, this.cep);
  }
}
