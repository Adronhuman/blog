package com.example.demo.Functions;

import com.example.demo.Repo.Materials.Materials;
import com.example.demo.Models.Material;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class MaterialsFunctions {

    public static void findWithTags(String tags, Model model, Materials fileRepository){
        Iterable<Material> materials = fileRepository.findAll();
        ArrayList<Material> materialsReverse = Functions.reverseIterable(materials);
        ArrayList<Material> answers = new ArrayList<>();
        if (tags.trim().isEmpty()){
            model.addAttribute("materials",materialsReverse);
            return;
        }
        for (Material material : materialsReverse) {
            boolean check = true;
            for (String tag : tags.split(",")) {
                boolean presist = false;
                for (String tag2 : material.getTagsInWords()){
                    if (tag2.trim().toLowerCase().equals(tag.trim().toLowerCase())){
                        presist = true;
                        break;
                    }
                }
                if (!presist){
                    check = false;
                    break;
                }
            }
            if (check){
                answers.add(material);
            }
        }
        model.addAttribute("materials",answers);

    }

    public static void getMaterials(Model model,Materials fileRepository){
        Iterable<Material> materials = fileRepository.findAll();
        ArrayList<Material> p = Functions.reverseIterable(materials);
        model.addAttribute("materials",p);
    }

}
