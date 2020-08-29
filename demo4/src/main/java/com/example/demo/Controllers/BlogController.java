package com.example.demo.Controllers;

import com.example.demo.Functions.Functions;
import com.example.demo.Functions.PostFunctions;
import com.example.demo.Models.Post;
import com.example.demo.Repo.Posts.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class BlogController {

    @Autowired
    Posts postRepository;

    @GetMapping("/user/blog")
    public String homeUser(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        ArrayList<Post> p = Functions.reverseIterable(posts);
        model.addAttribute("posts", p);
        return "User/Posts/blog_user";
    }

    @GetMapping("/admin/blog")
    public String home(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        ArrayList<Post> p = Functions.reverseIterable(posts);
        model.addAttribute("posts", p);
        return "Admin/Posts/blog";
    }

    @GetMapping("/admin/blog/add")
    public String addPost(Model model) {
        return "Admin/Posts/add_Post";
    }

    @PostMapping("/blog/add")
    public String newPost(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        LocalDateTime date = LocalDateTime.now();
        Post p = new Post(title, anons, full_text, date);
        postRepository.save(p);
        return "redirect:/admin/blog";
    }

    @GetMapping("/user/blog/{id}")
    public String post(@PathVariable(value = "id") long id, Model model) {
        PostFunctions.presentPost(model,postRepository,id);
        return "User/Posts/Post_template_user";
    }

    @GetMapping("/admin/blog/{id}")
    public String getPost(@PathVariable(value = "id") long id, Model model) {
        ArrayList<Post> p = new ArrayList<>();
        postRepository.findById(id).ifPresent(p::add);
        model.addAttribute("posts", p);
        model.addAttribute("name", "Blog");
        model.addAttribute("count", postRepository.count());
        return "Admin/Posts/Post_template";
    }

    @GetMapping("/admin/blog/{id}/edit")
    public String editPostPage(@PathVariable(value = "id") long id, Model model) {
        ArrayList<Post> p = new ArrayList<>();
        postRepository.findById(id).ifPresent(p::add);
        model.addAttribute("posts", p);
        model.addAttribute("name", "Blog");
        model.addAttribute("count", postRepository.count());
        return "Admin/Posts/edit";
    }

    @PostMapping("/admin/blog/{id}/edit")
    public String editPost(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setAnons(anons);
        post.setDate(LocalDateTime.now());
        post.setTitle(title);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/admin/blog";
    }

    @GetMapping("/admin/blog/{id}/remove")
    public String removePost(@PathVariable(value = "id") long id, Model model) {
        postRepository.deleteById(id);
        return "redirect:/admin/blog";
    }


    @PostMapping("user/word")
    public String searchByWordUser(@RequestParam String word, Model model) {
        PostFunctions.searchByWord(word, model, postRepository);
        return "User/Posts/blog_user";
    }

    @PostMapping("admin/word")
    public String searchByWordAdmin(@RequestParam String word, Model model) {
        PostFunctions.searchByWord(word, model, postRepository);
        return "Admin/Posts/blog";
    }


}
