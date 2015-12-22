package com.lapeevvd.repository;

import com.lapeevvd.model.UserMeal;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserMealRepositoryImpl implements UserMealRepository{
    /**
     * @return null, если редактируемая еда не принадлежит пользователю
     */
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        return null;
    }

    /**
     * @return false, если еда не принадлежит пользователю
     */
    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    /**
     * @return null, если еда не принадлежит пользователю
     */
    @Override
    public UserMeal get(int id, int userId) {
        return null;
    }

    /**
     * @return вся еда
     */
    @Override
    public List<UserMeal> getAll(int userId) {
        return null;
    }

    /**
     * @return вся еда в промежуток времени
     */
    @Override
    public List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end, int userId) {
        return null;
    }
}
