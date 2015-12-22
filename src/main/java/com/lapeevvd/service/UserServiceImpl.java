package com.lapeevvd.service;

import com.lapeevvd.model.User;
import com.lapeevvd.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    // TODO: 22.12.2015 Тут тоже разобраться с исключениями

    @Autowired
    private UserRepositoryImpl repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    //сюда исключение
    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    //сюда исключение
    @Override
    public User get(int id) {
        return repository.get(id);
    }

    //сюда исключение
    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
