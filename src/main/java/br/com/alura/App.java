package br.com.alura;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.alura.entities.Produto;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    Produto celular = new Produto("Xiome", "celular chines", new BigDecimal("1800"));

    // classe que faz a conexao no BD
    EntityManager entityManager = Persistence.createEntityManagerFactory("loja").createEntityManager();

    // teste para persistir um produto
    entityManager.getTransaction().begin();     // abrindo conexao
    entityManager.persist(celular);             // persistindo o objeto
    entityManager.getTransaction().commit();    // comitando a persistencia 
    entityManager.close();                      // fechando a conexao
  }
}
