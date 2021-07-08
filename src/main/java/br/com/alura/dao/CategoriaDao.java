package br.com.alura.dao;

import javax.persistence.EntityManager;

import br.com.alura.entities.Categoria;

public class CategoriaDao {
  private EntityManager eManager;

  public CategoriaDao(EntityManager eManager) {
    this.eManager = eManager;
  }

  public void cadastrar(Categoria categoria) {
    this.eManager.persist(categoria);
  }

  public void atualizar(Categoria categoria) {
    this.eManager.merge(categoria);
  }

  public void remover(Categoria categoria) {
    categoria = this.eManager.merge(categoria);
    this.eManager.remove(categoria);
  }
}
