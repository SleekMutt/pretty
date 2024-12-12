package org.example.demo5.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.transaction.Transactional;
import org.example.demo5.entity.Role;
import org.example.demo5.entity.RoleType;
import org.example.demo5.entity.User;
import org.example.demo5.repository.RoleRepository;
import org.example.demo5.repository.UserRepository;

import java.util.Optional;

@ApplicationScoped
public class UserInitializer {

  @Inject
  private RoleRepository roleRepository;

  @Inject
  private UserRepository userRepository;
  @Inject
  private Pbkdf2PasswordHash passwordHash;

  @Transactional
  public void initializeUsers() {
    Optional<User> admin = userRepository.getUserByUsername("admin");
    if (admin.isEmpty()) {
      userRepository.save(User.builder()
              .username("admin")
              .password(passwordHash.generate("admin".toCharArray()))
              .role(roleRepository.findByName(RoleType.ADMIN).get()).build());
    }

    Optional<User> user = userRepository.getUserByUsername("user");
    if (user.isEmpty()) {
      userRepository.save(User.builder()
              .username("user")
              .password(passwordHash.generate("user".toCharArray()))
              .role(roleRepository.findByName(RoleType.USER).get()).build());
    }
  }
}
