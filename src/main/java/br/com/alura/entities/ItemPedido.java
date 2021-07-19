package br.com.alura.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "itens_pedidos")
public class ItemPedido {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(name = "preco_unitario")
  private BigDecimal precoUnitario;
  private int quantidade;
  @ManyToOne
  private Pedido pedido;
  @ManyToOne
  private Produto produto;

  public ItemPedido() {

  }

  public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
    this.precoUnitario = produto.getPreco();
    this.quantidade = quantidade;
    this.pedido = pedido;
    this.produto = produto;
  }

  public BigDecimal getValor() {
    return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }
  
}
