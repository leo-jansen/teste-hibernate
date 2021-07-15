package br.com.alura.dao;

import javax.persistence.EntityManager;

import br.com.alura.entities.Pedido;

public class PedidoDao {
  private EntityManager eManager;

  public PedidoDao(EntityManager eManager) {
    this.eManager = eManager;
  }

  public void cadastrar(Pedido pedido) {
    this.eManager.persist(pedido);
  }
}
