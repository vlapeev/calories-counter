package com.lapeevvd.repository.jdbc;

import com.lapeevvd.model.UserMeal;
import com.lapeevvd.repository.UserMealRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository{
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return null;
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end, int userId) {
        return null;
    }
}
