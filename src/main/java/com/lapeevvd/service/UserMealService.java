package com.lapeevvd.service;

import com.lapeevvd.dataTransferObject.UserMealTo;
import com.lapeevvd.model.UserMeal;
import com.lapeevvd.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface UserMealService {
    UserMeal save(UserMeal userMeal, int userId);

    UserMeal update(UserMeal userMeal, int userId) throws NotFoundException;

    void update(UserMealTo userMealTo);

    void delete(int id, int userId) throws NotFoundException;

    UserMeal get(int id, int userId) throws NotFoundException;

    List<UserMeal> getAll(int userId);

    List<UserMeal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    default List<UserMeal> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetween(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    UserMeal getWithUser(int id, int userId);
}
