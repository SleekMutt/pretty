package org.example.demo5.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class RedirectBean {
  public String redirectToCreate() {
    System.out.println("test");
    return "create?faces-redirect=true";
  }

  public String redirectToHome() {
    return "home?faces-redirect=true";
  }
  public String redirectToSignUp() {
    return "signup?faces-redirect=true";
  }

  public String redirectToLogin() {
    return "login?faces-redirect=true";
  }

}
