package com.lapeevvd.repository;

import com.lapeevvd.model.UserMeal;
import com.lapeevvd.util.TimeUtil;
import com.lapeevvd.util.UserMealUtil;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class UserMealRepositoryImpl implements UserMealRepository{

    // Map  <userId, <mealId, userMeal>>
    private Map<Integer, Map<Integer, UserMeal>> usersMealMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        int USER_ID = 1;
        int ADMIN_ID = 2;

        UserMealUtil.USER_MEAL_LIST.forEach(userMeal -> save(userMeal, USER_ID));
        UserMealUtil.ADMIN_MEAL_LIST.forEach(userMeal -> save(userMeal, ADMIN_ID));
    }
    /**
     * @return null, если редактируемая еда не принадлежит пользователю
     */
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        /*Objects.requireNonNull(userMeal);*/
        Integer mealId = userMeal.getId();

        if (userMeal.isNew()){
            mealId = counter.incrementAndGet();
            userMeal.setId(mealId);
        } else if (get(mealId, userId) == null){
            return null;
        }
        Map<Integer, UserMeal> map = usersMealMap.computeIfAbsent(userId, ConcurrentHashMap::new);
        map.put(mealId, userMeal);
        return userMeal;
    }

    /**
     * @return false, если еда не принадлежит пользователю
     */
    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, UserMeal> map = usersMealMap.get(userId);
        return map != null && map.remove(id) != null;
    }

    /**
     * @return null, если еда не принадлежит пользователю
     */
    @Override
    public UserMeal get(int id, int userId) {
        Map<Integer, UserMeal> map = usersMealMap.get(userId);
        return map != null ? map.get(id) : null;
    }

    /**
     * @return вся еда
     */
    @Override
    public List<UserMeal> getAll(int userId) {
        Map<Integer, UserMeal> map = usersMealMap.get(userId);
        return map != null ? map.values().stream().sorted((um1, um2) -> um2.getDateTime().compareTo(um1.getDateTime()))
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    /**
     * @return вся еда в промежуток времени
     */
    @Override
    public List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end, int userId) {
        /*Objects.requireNonNull(start);
        Objects.requireNonNull(end);*/
        return getAll(userId).stream()
                .filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime(), start, end))
                .collect(Collectors.toList());
    }
}
