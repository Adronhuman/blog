package com.example.demo.Models;

import com.example.demo.Repo.Users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class MyTimerTask extends java.util.TimerTask {


    private UserRepo userRepo;

    private User user;

    public MyTimerTask setUser(User user,UserRepo userRepo){
        this.user = user;
        this.userRepo = userRepo;
        return this;
    }

    @Override
    public void run() {
        user.setRank(user.getRank()+10);
        userRepo.save(user);
    }
}
