package br.com.alura.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
  @Column(name = "valor_total")
  private BigDecimal valorTotal;
  private LocalDate data = LocalDate.now();
  @ManyToOne
  private Cliente cliente;
  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  private List<ItemPedido> itens = new ArrayList<>();

  public Pedido() {

  }

  public Pedido(Cliente cliente) {
    this.cliente = cliente;
  }

  public void adicionarItem(ItemPedido item) {
    item.setPedido(this);
    this.itens.add(item);
  }

  @Override
  public String toString() {
    return String.format("Cliente: %s | Valor Total: %.2f | Data: %t", this.cliente.toString(), this.valorTotal, this.data);
  }
}
