package com.lapeevvd.controller.meal;

import com.lapeevvd.dataTransferObject.UserMealWithExceed;
import com.lapeevvd.model.UserMeal;
import com.lapeevvd.service.UserMealService;
import com.lapeevvd.util.LoggedUser;
import com.lapeevvd.util.UserMealUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class AbstractUserMealController {

    @Autowired
    protected UserMealService service;

    public UserMeal save(UserMeal userMeal){
        return service.save(userMeal, LoggedUser.getId());
    }

    public void update(UserMeal userMeal){
        service.save(userMeal, LoggedUser.getId());
    }

    public void delete(int id){
        service.delete(id, LoggedUser.getId());
    }

    public UserMeal get(int id){
        return service.get(id, LoggedUser.getId());
    }

    public List<UserMealWithExceed> getAll(){
        return UserMealUtil.getFilteredWithExceeded(service.getAll(LoggedUser.getId()),
                LocalTime.MIN, LocalTime.MAX, UserMealUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        return UserMealUtil.getFilteredWithExceeded(service.getBetweenDates(startDate, endDate, LoggedUser.getId()),
                startTime, endTime, UserMealUtil.DEFAULT_CALORIES_PER_DAY);
    }
}
