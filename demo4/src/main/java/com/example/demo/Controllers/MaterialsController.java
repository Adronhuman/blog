package com.example.demo.Controllers;

import com.example.demo.Functions.MaterialsFunctions;
import com.example.demo.Repo.Materials.Materials;
import com.example.demo.Models.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class MaterialsController {
    @Autowired
    Materials fileRepository;


    @GetMapping("/user/Materials")
    public String Main(Model model){
        MaterialsFunctions.getMaterials(model,fileRepository);
        return "User/Materials/Materials_user";
    }
    @GetMapping("/admin/Materials")
    public String mainAdmin(Model model){
        MaterialsFunctions.getMaterials(model,fileRepository);
        return "Admin/Materials/Materials_admin";
    }
    @GetMapping("/admin/Materials/add")
    public String MaterialsPage(Model model) {
        return "Admin/Materials/add_Materials";
    }

    @GetMapping("/admin/Materials/remove{id}")
    public String removeMaterial(@PathVariable(value = "id") long id,Model model) {
        fileRepository.deleteById(id);
        return "redirect:/admin/Materials";
    }

    @PostMapping("/admin/Materials/add_Material")
    public String addMaterial(@RequestParam String name,@RequestParam String link ,@RequestParam String tags, Model model) {
        Material material = new Material(name,link, LocalDateTime.now(),tags);
        fileRepository.save(material);
        return "redirect:/admin/Materials";
    }

    @PostMapping("/user/tags")
    public String materialSWithTags(@RequestParam String tags,Model model){
        MaterialsFunctions.findWithTags(tags,model,fileRepository);
        return "User/Materials/Materials_user";
    }

    @PostMapping("/admin/tags")
    public String materialWithTags(@RequestParam String tags,Model model){
        MaterialsFunctions.findWithTags(tags,model,fileRepository);
        return "Admin/Materials/Materials_admin";
    }

}
