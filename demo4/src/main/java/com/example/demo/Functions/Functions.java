package com.example.demo.Functions;

import com.example.demo.Models.Post;
import com.example.demo.Models.User;
import com.example.demo.Repo.Users.UserRepo;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class Functions {

    //Функція для зворотнього сортування даних отриманих з бд
    public static ArrayList reverseIterable(Iterable it){
        ArrayList p = new ArrayList<>();
        for (var x:
                it){
            p.add(x);
        }
        ArrayList p2 = new ArrayList<>();
        for(int i = p.size()-1; i >=0; i--) {
            p2.add(p.get(i));
        }
        return p2;
    }

    public static String changePassword(String password, String new_password, UserRepo usersRepo, String username, Model model,String User){
        User user = usersRepo.findByUsername(username);
        if (user.getPassword().equals(password)){
            user.setPassword(new_password);
            usersRepo.save(user);
            model.addAttribute("user",user);
            model.addAttribute("message","Пароль успішно змінено");
            return User+"/Main/myPage"+User;
        }
        model.addAttribute("user",user);
        model.addAttribute("message","Неправильний пароль");
        return User+"/Main/myPage"+User;
    }

}
