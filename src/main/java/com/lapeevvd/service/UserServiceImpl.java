package com.lapeevvd.service;

import com.lapeevvd.model.User;
import com.lapeevvd.repository.UserRepository;
import com.lapeevvd.util.exception.CheckException;
import com.lapeevvd.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        CheckException.check(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return CheckException.check(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return CheckException.check(repository.getByEmail(email), "email = " + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
