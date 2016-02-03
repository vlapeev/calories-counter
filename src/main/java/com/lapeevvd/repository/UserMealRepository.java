package com.lapeevvd.repository;

import com.lapeevvd.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

// Пока что заглушки в виде userId
public interface UserMealRepository {
    /**
     * @return null, если редактируемая еда не принадлежит пользователю
     */
    UserMeal save(UserMeal userMeal, int userId);

    /**
     * @return false, если еда не принадлежит пользователю
     */
    boolean delete(int id, int userId);

    /**
     * @return null, если еда не принадлежит пользователю
     */
    UserMeal get(int id, int userId);

    /**
     * @return вся еда
     */
    List<UserMeal> getAll(int userId);

    /**
     * @return вся еда в промежуток времени
     */
    List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end, int userId);

    default UserMeal getWithUser(int id, int userId){
        throw new UnsupportedOperationException();
    }
}
