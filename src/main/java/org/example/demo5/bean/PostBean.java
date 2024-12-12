package org.example.demo5.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.Getter;
import lombok.Setter;
import org.example.demo5.entity.Post;
import org.example.demo5.entity.User;
import org.example.demo5.repository.PostRepository;
import org.example.demo5.repository.UserRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Named
@ViewScoped
@Getter
@Setter
public class PostBean implements Serializable {
  @Valid
  private Post post;
  @Inject
  private HttpServletRequest request;
  @Inject
  private PostRepository postRepository;
  @Inject
  private UserRepository userRepository;
  private List<Post> posts;
  private String id;

  public PostBean() {
    this.id  =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
  }
  @PostConstruct
  public void init(){
    this.post = id != null ? postRepository.findById(Long.valueOf(id)).get() : new Post();
  }

  public List<Post> getPosts() {
    if (posts == null) {
      posts = postRepository.findAll();
    }
    return posts;
  }

  public String createPost(){
    User user = userRepository.getUserByUsername(request.getUserPrincipal().getName()).get();
    post.setUser(user);
    postRepository.save(post);

    return "home?faces-redirect=true";
  }

  public void deletePost(Long id) {
    postRepository.deleteById(id);
    posts = postRepository.findAll();
  }

  public String editPost(Long id){
    return "edit?id=" + id + "&faces-redirect=true";
  }

  public String updatePost(){
    post.setId(post.getId());
    System.out.println(post.getId());
    postRepository.update(post);
    return "home?faces-redirect=true";
  }
}
