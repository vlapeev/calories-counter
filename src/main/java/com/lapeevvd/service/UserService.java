package com.lapeevvd.service;

import com.lapeevvd.model.User;
import com.lapeevvd.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void update(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void evictCache();

    User getWithMeal(int id);
}
