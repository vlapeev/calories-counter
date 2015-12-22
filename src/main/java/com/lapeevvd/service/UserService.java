package com.lapeevvd.service;

import com.lapeevvd.model.User;

import java.util.List;

public interface UserService {

    // TODO: 22.12.2015 Реализовать свой механиз обработки исключений

    User save(User user);

    void update(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}
