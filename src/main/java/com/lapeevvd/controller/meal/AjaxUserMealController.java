package com.lapeevvd.controller.meal;

import com.lapeevvd.dataTransferObject.UserMealTo;
import com.lapeevvd.dataTransferObject.UserMealWithExceed;
import com.lapeevvd.model.UserMeal;
import com.lapeevvd.util.TimeUtil;
import com.lapeevvd.util.UserMealUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public UserMeal get(@PathVariable("id") int id){
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    /* Use Binding*/
    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(UserMealTo userMealTo) {
        if (userMealTo.getId() == 0) {
            super.save(UserMealUtil.createFromUserMealTo(userMealTo));
        } else {
            super.update(userMealTo);
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getBetween(@RequestParam(value = "startDate", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                               LocalDate startDate,
                                               @RequestParam(value = "endDate", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                               LocalDate endDate,
                                               @RequestParam(value = "startTime", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                               LocalTime startTime,
                                               @RequestParam(value = "endTime", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                               LocalTime endTime) {
        return super.getBetween(startDate != null ? startDate : TimeUtil.MIN_DATE,
                endDate != null ? endDate : TimeUtil.MAX_DATE,
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX);
    }
}
