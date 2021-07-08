package br.com.alura.dao;

import java.util.List;

import javax.persistence.EntityManager;

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

}
