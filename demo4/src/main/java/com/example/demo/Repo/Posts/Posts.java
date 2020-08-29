package com.example.demo.Repo.Posts;

import com.example.demo.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface Posts extends CrudRepository<Post,Long> {
}
