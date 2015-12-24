package com.lapeevvd.controller;

import com.lapeevvd.model.User;
import com.lapeevvd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl service;

    public User create(User user) {
        user.setId(null);
        return service.save(user);
    }

    public void update(User user, int id) {
        user.setId(id);
        service.save(user);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public User get(int id) {
        return service.get(id);
    }

    public User getByEmail(String email){
        return service.getByEmail(email);
    }

    public List<User> getAll(){
        return service.getAll();
    }
}
