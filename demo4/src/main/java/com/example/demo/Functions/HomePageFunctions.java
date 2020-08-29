package com.example.demo.Functions;

import com.example.demo.Models.Material;
import com.example.demo.Models.Post;
import com.example.demo.Repo.Materials.Materials;
import com.example.demo.Repo.Posts.Posts;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HomePageFunctions {

    public static void createMain(Model model, Posts postRepository, Materials posRepo) {
        model.addAttribute("name", "AriAri");
        Iterable<Post> popa = postRepository.findAll();
        ArrayList<Post> p = new ArrayList<>();
        for (Post x : popa) {
            p.add(x);
        }
        Post post = new Post("Відсутня інформація", "Відсутня інформація", "Відсутня інформація", LocalDateTime.now());

        if (p.size() > 0) {
            model.addAttribute("lastPost", p.get(p.size() - 1));
            if (p.size() > 1) {
                model.addAttribute("penultimatePost", p.get(p.size() - 2));
            } else {
                model.addAttribute("penultimatePost", post);
            }
        } else {
            model.addAttribute("lastPost", post);
            model.addAttribute("penultimatePost", post);
        }


        ArrayList<Material> dupa = Functions.reverseIterable(posRepo.findAll());
        if (dupa.size() > 0) {
            model.addAttribute("mat", dupa.get(0));
        } else {
            model.addAttribute("mat",new Material());
        }
        int counter = Math.min(3, dupa.size());
        ArrayList<Material> answer = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            answer.add(dupa.get(i));
        }
        model.addAttribute("materials", answer);
    }
}
