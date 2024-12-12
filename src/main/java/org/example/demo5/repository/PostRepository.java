package org.example.demo5.repository;

import org.example.demo5.entity.Post;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository extends BaseRepository<Post, Long> {
  protected PostRepository() {
    super(Post.class);
  }
}
