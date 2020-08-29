package com.example.demo.Functions;

import com.example.demo.Models.Post;
import com.example.demo.Repo.Posts.Posts;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class PostFunctions {

    public static void searchByWord(String word, Model model, Posts postRepository) {
        Iterable<Post> allPosts = postRepository.findAll();
        ArrayList<Post> acceptable = new ArrayList<>();
        for (Post x : allPosts) {
            String s = x.getTitle();
            if (s.toLowerCase().contains(word.toLowerCase())) {
                acceptable.add(x);
            }
        }
        model.addAttribute("posts", acceptable);
    }

    public static void presentPost(Model model, Posts postRepository, long id) {
        ArrayList<Post> p = new ArrayList<>();
        postRepository.findById(id).ifPresent(p::add);
        model.addAttribute("posts", p);
        model.addAttribute("name", "Blog");
        model.addAttribute("count", postRepository.count());
    }
}
