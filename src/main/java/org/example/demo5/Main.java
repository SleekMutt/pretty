package org.example.demo5;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.SecurityContext;
import org.example.demo5.config.RoleInitializer;
import org.example.demo5.config.UserInitializer;

@Startup
@DeclareRoles({"admin", "user"})
@Singleton
public class Main {
  @Inject
  private RoleInitializer roleInitializer;
  @Inject
  private UserInitializer userInitializer;

  @PostConstruct
  public void onStartup() {
    roleInitializer.initializeRoles();
    userInitializer.initializeUsers();
  }
}
