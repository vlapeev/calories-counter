package com.lapeevvd.service;

import com.lapeevvd.model.UserMeal;
import com.lapeevvd.repository.UserMealRepository;
import com.lapeevvd.util.exception.CheckException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserMealServiceImpl implements UserMealService{

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        return repository.save(userMeal, userId);
    }

    // может кинуть исключение
    @Override
    public UserMeal update(UserMeal userMeal, int userId) {
        return CheckException.check(repository.save(userMeal, userId), userMeal.getId());
    }

    // может кинуть исключение
    @Override
    public void delete(int id, int userId) {
        CheckException.check(repository.delete(id, userId), id);
    }

    // может кинуть исключение
    @Override
    public UserMeal get(int id, int userId) {
        return CheckException.check(repository.get(id, userId), id);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }


}
