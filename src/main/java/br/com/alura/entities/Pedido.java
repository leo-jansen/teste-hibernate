package br.com.alura.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
  private BigDecimal valorTotal;
  private LocalDate data = LocalDate.now();
  @ManyToOne
  private Cliente cliente;

  public Pedido() {

  }

  public Pedido(Cliente cliente) {
    this.cliente = cliente;
  }

  @Override
  public String toString() {
    return String.format("Cliente: %s | Valor Total: %.2f | Data: %t", this.cliente.toString(), this.valorTotal, this.data);
  }
}
