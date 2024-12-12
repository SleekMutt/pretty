package org.example.demo5.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.ws.rs.core.SecurityContext;
import lombok.Getter;
import lombok.Setter;
import org.example.demo5.entity.RoleType;
import org.example.demo5.entity.User;
import org.example.demo5.repository.RoleRepository;
import org.example.demo5.repository.UserRepository;

import java.io.IOException;

@Named
@RequestScoped
@Setter
@Getter
public class SignupBean {

  private String username;
  private String password;

  @Inject
  private UserRepository userRepository;
  @Inject
  private RoleRepository roleRepository;
  @Inject
  private Pbkdf2PasswordHash passwordHash;

  public void logout() throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    FacesContext.getCurrentInstance().getExternalContext()
            .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/");
  }

  public String register() {
    try {
      userRepository.save(User.builder().username(username).password(passwordHash.generate(password.toCharArray())).role(roleRepository.findByName(RoleType.USER).get()).build());
      return "/login.xhtml?faces-redirect=true";
    } catch (Exception e) {
      return null;
    }
  }
}
