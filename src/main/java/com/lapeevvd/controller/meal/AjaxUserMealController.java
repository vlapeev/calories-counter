package com.lapeevvd.controller.meal;

import com.lapeevvd.dataTransferObject.UserMealTo;
import com.lapeevvd.dataTransferObject.UserMealWithExceed;
import com.lapeevvd.model.UserMeal;
import com.lapeevvd.util.TimeUtil;
import com.lapeevvd.util.UserMealUtil;
import com.lapeevvd.util.exception.ValidationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "ajax/profile/meals")
public class AjaxUserMealController extends AbstractUserMealController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    /* Use Binding*/
    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@Valid UserMealTo userMealTo, BindingResult result, SessionStatus status) {
        if (result.hasErrors()){
            throw new ValidationException(result);
        }
        status.setComplete();
        if (userMealTo.getId() == 0) {
            super.save(UserMealUtil.createFromUserMealTo(userMealTo));
        } else {
            super.update(userMealTo);
        }
    }

    /* Use Binding
    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(UserMealTo userMealTo) {
        if (userMealTo.getId() == 0) {
            super.save(UserMealUtil.createFromUserMealTo(userMealTo));
        } else {
            super.update(userMealTo);
        }
    }*/

    /*@RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@RequestParam("id") int id,
                               @RequestParam("datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") int calories) {
        UserMeal meal = new UserMeal(id, dateTime, description, calories);
        if (id == 0) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }
    }*/

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getBetween(@RequestParam(value = "startDate", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                               LocalDate startDate,
                                               @RequestParam(value = "endDate", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                               LocalDate endDate,
                                               @RequestParam(value = "startTime", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                               LocalTime startTime,
                                               @RequestParam(value = "endTime", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                               LocalTime endTime) {
        return super.getBetween(startDate != null ? startDate : TimeUtil.MIN_DATE,
                endDate != null ? endDate : TimeUtil.MAX_DATE,
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX);
    }
}
