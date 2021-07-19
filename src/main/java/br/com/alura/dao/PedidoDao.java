package br.com.alura.dao;

import java.math.BigDecimal;

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

  public BigDecimal valorTotalVendido() {
    String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
    return eManager.createQuery(jpql, BigDecimal.class).getSingleResult();
  }
}
