package org.example.demo5.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T, ID> {

  @PersistenceContext
  protected EntityManager entityManager;

  private final Class<T> entityClass;

  protected BaseRepository(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public Optional<T> findById(ID id) {
    return Optional.ofNullable(entityManager.find(entityClass, id));
  }

  public List<T> findAll() {
    return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
            .getResultList();
  }

  @Transactional
  public T save(T entity) {
    entityManager.persist(entity);
    return entity;
  }

  @Transactional
  public T update(T entity) {
    return entityManager.merge(entity);
  }

  @Transactional
  public void delete(T entity) {
    entityManager.remove(entity);
  }

  @Transactional
  public void deleteById(ID id) {
    findById(id).ifPresent(this::delete);
  }
}