package com.lapeevvd.controller;

import com.lapeevvd.model.User;
import com.lapeevvd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService service;

    public void delete(int id) {
        service.delete(id);
    }

    public List<User> getAll() {
        return service.getAll();
    }
}
