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
    entityManager.persist(categoria);           // abrindo conexao
    entityManager.persist(celular);             // persistindo o objeto
    entityManager.flush();                      // atualiza no banco de dados todos os objetos MANAGED
    entityManager.clear();                      // coloca todos os objetos em DETACHED
    celular = entityManager.merge(celular);     // devolve um novo objeto MANAGED (necessica ter o construtor padrão) 
    celular.setNome("Poco");
    entityManager.remove(celular);              // faz um delete no BD                   
    entityManager.getTransaction().commit();    // comita a atualização no BD 
    entityManager.close();                      // fechando a conexao
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