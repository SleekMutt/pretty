package org.example.demo5.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.demo5.entity.Role;
import org.example.demo5.entity.RoleType;
import org.example.demo5.repository.RoleRepository;

@ApplicationScoped
public class RoleInitializer {

  @Inject
  private RoleRepository roleRepository;

  @Transactional
  public void initializeRoles() {
    for (RoleType roleType : RoleType.values()) {
      if (roleRepository.findByName(roleType).isEmpty()) {
        roleRepository.save(Role.builder().role(roleType).build());
      }
    }
  }
}
