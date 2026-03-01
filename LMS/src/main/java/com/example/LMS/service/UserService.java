package com.example.LMS.service;

import com.example.LMS.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public  void registerUser(User user){
        users.add(user);
    }

    public  boolean login(String email,String password){
        for(User u : users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return  true;
            }
        }

        return false;
    }
}
