package org.example.demo5.repository;

import org.example.demo5.entity.Role;
import org.example.demo5.entity.RoleType;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class RoleRepository extends BaseRepository<Role, Long>{
  protected RoleRepository() {
    super(Role.class);
  }

  public Optional<Role> findByName(RoleType role) {
    return entityManager.createQuery(
                    "SELECT r FROM Role r WHERE r.role = :role", Role.class)
            .setParameter("role", role)
            .getResultStream()
            .findFirst();
  }
}
