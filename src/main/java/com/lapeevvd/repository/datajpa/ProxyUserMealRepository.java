package com.lapeevvd.repository.datajpa;

import com.lapeevvd.model.UserMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer>{
    @Transactional
    @Override
    UserMeal save(UserMeal userMeal);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId")
    int delete(@Param("id") int id,@Param("userId") int userId);

    @Query("SELECT um FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId")
    UserMeal findOne(@Param("id") int id,@Param("userId") int userId);

    @Query("SELECT um FROM UserMeal um WHERE um.user.id=:userId ORDER BY um.dateTime DESC")
    List<UserMeal> getAll(@Param("userId") int userId);

    @Query("SELECT um FROM UserMeal um WHERE um.user.id=:userId AND um.dateTime BETWEEN :startDate AND :endDate ORDER BY um.dateTime DESC")
    List<UserMeal> findBetween(@Param("startDate") LocalDateTime start, @Param("endDate") LocalDateTime end, @Param("userId") int userId);

    @Query("SELECT um FROM UserMeal um LEFT JOIN FETCH um.user WHERE um.id=?1 AND um.user.id=?2")
    UserMeal getWithUser(int id, int userId);
}
