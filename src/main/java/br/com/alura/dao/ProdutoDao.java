package br.com.alura.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.entities.Produto;

public class ProdutoDao {
  private EntityManager eManager;

  public ProdutoDao(EntityManager eManager) {
    this.eManager = eManager;
  }

  public void cadastrar(Produto produto) {
    this.eManager.persist(produto);
  }

  public void atualizar(Produto produto) {
    this.eManager.merge(produto);
  }

  public void remover(Produto produto) {
    produto = this.eManager.merge(produto);
    this.eManager.remove(produto);
  }

  public Produto buscarProdutoPorId(long id) {
    return this.eManager.find(Produto.class, id);
  }

  public List<Produto> buscarProdutos() {
    String jpql = "SELECT p FROM Produto p";
    return this.eManager.createQuery(jpql, Produto.class).getResultList();
  }
  
  public List<Produto> buscarProdutosPorNome(String nome) {
    String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
    return this.eManager.createQuery(jpql, Produto.class).setParameter("name", nome).getResultList();
  }

  public List<Produto> buscarPorNomeDaCategoria(String nome) {
    String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
    return this.eManager.createQuery(jpql, Produto.class).setParameter("name", nome).getResultList();
  }

  public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
    // metodo monta a jpql automaticamente, parametro opcional
    CriteriaBuilder builder = eManager.getCriteriaBuilder();                // criação do builder
    CriteriaQuery<Produto> query = builder.createQuery(Produto.class);      // criação da query
    Root<Produto> from = query.from(Produto.class);                         // atribuindo entidade na query, FROM Produto
    Predicate where = builder.and();                                        // crianção de filtros, vazio

    if(nome != null && !nome.trim().isEmpty()) {                            // verifica se o nome é nullo e se a string não está vazia
      where = builder.and(where, builder.equal(from.get("nome"), nome));    // WHERE p.nome = :nome -JPQL-
    }
    if(preco != null) {                                                     
      where = builder.and(where, builder.equal(from.get("preco"), preco));                // WHERE p.preco = :preco -JPQL-
    }
    if(dataCadastro != null) {
      where = builder.and(where, builder.equal(from.get("dataCadastro"), dataCadastro)); 
    }

    query.where(where);                                                       
    
    return eManager.createQuery(query).getResultList();
  }

}
