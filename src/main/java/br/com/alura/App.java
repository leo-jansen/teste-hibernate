package br.com.alura;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    // classe que faz a conexao no BD
    EntityManager entityManager = Persistence.createEntityManagerFactory("loja").createEntityManager();
    Categoria categoria = entityManager.find(Categoria.class, 2l);     // busca na tabela categorias o id 1
    Produto celular = new Produto("Xiome", "celular chines", new BigDecimal("1800"), categoria);
    // teste para persistir um produto
    entityManager.getTransaction().begin();     // abrindo conexao
    entityManager.persist(celular);             // persistindo o objeto
    entityManager.flush();                      // atualiza no banco de dados todos os objetos MANAGED
    entityManager.clear();                      // coloca todos os objetos em DETACHED
    celular = entityManager.merge(celular);     // devolve um novo objeto MANAGED (necessica ter o construtor padrão) 
    celular.setNome("Poco");
    entityManager.remove(celular);              // faz um delete no BD                   
    entityManager.getTransaction().commit();    // comita a atualização no BD 
    String jpql = "SELECT p FROM Produto p";    // jpql -> liguangen para consulta sql em jpa
    List<Produto> listaProdutos = entityManager.createQuery(jpql, Produto.class)
                                                .getResultList();  // pega todos os elementos da tabela produtos
    String nomeProduto = "Xiome";
    String jpqlComFiltro = "SELECT p FROM Produto p WHERE p.nome = :nome";
    // "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";    para filtrar pelo relacionamento
    List<Produto> listaProdutosFiltrados = entityManager.createQuery(jpqlComFiltro, Produto.class)
                                                        .setParameter("nome", nomeProduto)
                                                        .getResultList();  // pega todos os elemento de produto quando o nome for Xiome
    entityManager.close();                      // fechando a conexao

    System.out.println(listaProdutos);
    System.out.println("====================");
    System.out.println(listaProdutosFiltrados);
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