package com.lapeevvd.controller;

import com.lapeevvd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractUserController {

    @Autowired
    private UserServiceImpl service;
}
