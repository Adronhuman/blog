package com.example.demo.Controllers;

import com.example.demo.Functions.Functions;
import com.example.demo.Functions.HomePageFunctions;
import com.example.demo.Models.User;
import com.example.demo.Repo.Materials.Materials;
import com.example.demo.Repo.Posts.Posts;
import com.example.demo.Repo.Users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    Posts postRepository;
    @Autowired
    Materials posRepo;
    @Autowired
    UserRepo usersRepo;


    @GetMapping("/user")
    public String home(Model model) {
        HomePageFunctions.createMain(model, postRepository, posRepo);
        return "User/Main/Main_user";
    }

    @GetMapping("/")
    public String redirect(Model model) {
        return "redirect:/user";
    }

    @GetMapping("admin")
    public String home_admin(Model model) {
        HomePageFunctions.createMain(model, postRepository, posRepo);
        return "Admin/Main/Main_admin";
    }

    @GetMapping("/admin/myPage")
    public String myPage(Model model, Principal user_) {
        User user = usersRepo.findByUsername(user_.getName());
        List<User> list = usersRepo.findAll();
        model.addAttribute("users",list);
        model.addAttribute("user",user);
        return "Admin/Main/myPageAdmin";
    }

    @GetMapping("/user/myPage")
    public String myPageUser(Model model, Principal user_) {
        User user = usersRepo.findByUsername(user_.getName());
        model.addAttribute("user",user);
        return "User/Main/myPageUser";
    }

    @PostMapping("/user/changePassword")
    public String changePassword(@RequestParam String password,@RequestParam String new_password,Principal user_,Model model){
        return Functions.changePassword(password,new_password,usersRepo,user_.getName(),model,"User");
    }

    @PostMapping("/admin/changePassword")
    public String changePasswordAdmin(@RequestParam String password,@RequestParam String new_password,Principal user_,Model model){
        List<User> list = usersRepo.findAll();
        User user = usersRepo.findByUsername(user_.getName());
        model.addAttribute("users",list);
        model.addAttribute("user",user);
        return Functions.changePassword(password,new_password,usersRepo,user_.getName(),model,"Admin");
    }


    @GetMapping("/user/About")
    public String getPageAbout(Model model) {
        return "User/Main/About";
    }

    @GetMapping("/admin/About")
    public String getPageAboutAdmin(Model model) {
        return "Admin/Main/About";
    }

    @PostMapping("/remove/user{id}")
    public String delUser(@PathVariable(value = "id") long id,Model model){
        User user = usersRepo.findById(id).orElseThrow();
        if(!user.getUsername().equals("admin")) {
            usersRepo.deleteById(id);
        }
        return "redirect:/admin/myPage";
    }

    @PostMapping("/addRate{id}")
    public String addRate(@PathVariable(value="id") long id,Model model,@RequestParam long extraRating){
        User user = usersRepo.findById(id).orElseThrow();
        user.setRank(user.getRank()+extraRating);
        usersRepo.save(user);
        return "redirect:/admin/myPage";
    }


}