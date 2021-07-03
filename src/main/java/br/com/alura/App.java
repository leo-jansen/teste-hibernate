package br.com.alura;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.alura.entities.Categoria;
import br.com.alura.entities.Produto;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    Categoria categoria = new Categoria("celulares");
    Produto celular = new Produto("Xiome", "celular chines", new BigDecimal("1800"), categoria);

    // classe que faz a conexao no BD
    EntityManager entityManager = Persistence.createEntityManagerFactory("loja").createEntityManager();

    // teste para persistir um produto
    entityManager.getTransaction().begin();
    entityManager.persist(categoria);     // abrindo conexao
    entityManager.persist(celular);             // persistindo o objeto
    entityManager.getTransaction().commit();    // comitando a persistencia 
    entityManager.close();                      // fechando a conexao
  }
}
