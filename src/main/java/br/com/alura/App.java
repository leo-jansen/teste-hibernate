package br.com.alura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ClienteDao;
import br.com.alura.dao.PedidoDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.entities.Categoria;
import br.com.alura.entities.Cliente;
import br.com.alura.entities.Informatica;
import br.com.alura.entities.ItemPedido;
import br.com.alura.entities.Livro;
import br.com.alura.entities.Pedido;
import br.com.alura.entities.Produto;
import br.com.alura.util.JPAUtil;

public class App {
  public static void main(String[] args) {
    Categoria eletronico = new Categoria("ELETRONICO");
    Categoria fantasia = new Categoria("FANTASIA");
    Informatica celular = new Informatica("Galaxy S20 FE", "celular feito para você", new BigDecimal("2400"), eletronico, "SAMSUNG", "SM-G780G");
    Livro livro = new Livro("Dragões de Éter", "Livro de fantasia", new BigDecimal("24.90"), fantasia, "Raphael Draccon", 700);
    Cliente cliente = new Cliente("Alura", "123456789", "R. do Ouvidor", "Centro", "Rio de Janeiro", "RJ", "20040-030");
    
    Pedido pedido = new Pedido(cliente);
    pedido.adicionarItem(new ItemPedido(10, pedido, celular));
    pedido.adicionarItem(new ItemPedido(10, pedido, livro));

    EntityManager entityManager = JPAUtil.gEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);
    ClienteDao clienteDao = new ClienteDao(entityManager);
    PedidoDao pedidoDao = new PedidoDao(entityManager);

    entityManager.getTransaction().begin();

    categoriaDao.cadastrar(eletronico);
    categoriaDao.cadastrar(fantasia);
    produtoDao.cadastrarInformatica(celular);
    produtoDao.cadastrarLivro(livro);
    clienteDao.cadastrar(cliente);
    pedidoDao.cadastrar(pedido);
    
    entityManager.getTransaction().commit();
    
    Produto novoProduto = produtoDao.buscarProdutoPorId(1l);
    List<Produto> todosProdutos = produtoDao.buscarProdutos();
    System.out.println(novoProduto);
    System.out.println("======================");
    todosProdutos.forEach(p -> System.out.println(p.toString()));
    BigDecimal valorTotalVendido = pedidoDao.valorTotalVendido();
    System.out.println(String.format("Valor total pedido foi: %.2f", valorTotalVendido));
    
    Pedido pedidoComCliente = pedidoDao.buscarPedidoComCliente(1l);
    List<Produto> tProdutos = produtoDao.buscarPorParametrosComCriteria(null, null, LocalDate.now());
    entityManager.close();
    System.out.println(pedidoComCliente.toString());
  }
}

//                               CICLO DE VIDA
//                                                                        ________
//        ___________               _________    |   commit()/flush()->  |   BD   |
// new-> | TRANSIENT | persist()-> |         |   |<-find()/createQuery() |________|
//        -----------              | MANAGED |----
//                                 |_________|   |                   __________
//                                               |close()/clear()-> | DETACHED |
//                                               |    <-merge()     |__________|
//                                               |
//                                               |            _______________
//                                               |remove()-> | REMOVED do BD |
//                                                            ---------------