package com.example.demo.Controllers.Security;


import com.example.demo.Models.MyTimerTask;
import com.example.demo.Repo.Users.UserRepo;
import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "Registration&Login/registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "Registration&Login/registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        Timer timer = new Timer();
        MyTimerTask task = new MyTimerTask().setUser(user,userRepo);
        timer.schedule(task, TimeUnit.DAYS.toMillis(30),TimeUnit.DAYS.toMillis(30));
        return "redirect:/login";
    }

}
