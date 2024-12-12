package org.example.demo5.repository;

import org.example.demo5.entity.Role;
import org.example.demo5.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepository extends BaseRepository<User, Long> {
  protected UserRepository() {
    super(User.class);
  }

  public Optional<User> getUserByUsername(String username) {
    return entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class)
            .setParameter("username", username)
            .getResultStream()
            .findFirst();  }
}
