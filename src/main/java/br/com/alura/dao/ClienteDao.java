package br.com.alura.dao;

import javax.persistence.EntityManager;

import br.com.alura.entities.Cliente;

public class ClienteDao {
  private EntityManager eManager;

  public ClienteDao(EntityManager eManager) {
    this.eManager = eManager;
  }

  public void cadastrar(Cliente cliente) {
    this.eManager.persist(cliente);
  }
}
