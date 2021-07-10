package br.com.alura;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.entities.Categoria;
import br.com.alura.entities.Produto;
import br.com.alura.util.JPAUtil;

public class App {
  public static void main(String[] args) {
    Categoria eletronico = new Categoria("ELETRONICO");
    Produto celular = new Produto("Galaxy S20 FE", "celular feito para você", new BigDecimal("2400"), eletronico);

    EntityManager entityManager = JPAUtil.gEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);

    entityManager.getTransaction().begin();

    categoriaDao.cadastrar(eletronico);
    produtoDao.cadastrar(celular);

    entityManager.getTransaction().commit();
    
    Produto novoProduto = produtoDao.buscarProdutoPorId(1l);
    List<Produto> todosProdutos = produtoDao.buscarProdutos();
    System.out.println(novoProduto);
    System.out.println("======================");
    todosProdutos.forEach(p -> System.out.println(p.toString()));
    
    
    entityManager.close();
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