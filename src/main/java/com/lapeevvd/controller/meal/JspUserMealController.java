package com.lapeevvd.controller.meal;

import com.lapeevvd.model.UserMeal;
import com.lapeevvd.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspUserMealController extends AbstractUserMealController {

    /*@RequestMapping(method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", super.getAll());
        return "mealList";
    }*/

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("meal", new UserMeal(LocalDateTime.now(), "", 500));
        return "mealEditor";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String editForUpdate(HttpServletRequest request, Model model) {
        model.addAttribute("meal", super.get(Integer.valueOf(request.getParameter("id"))));
        return "mealEditor";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveChanges(HttpServletRequest req) {
        String id = req.getParameter("id");
        UserMeal um = new UserMeal(id.isEmpty() ? null : Integer.parseInt(id),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));
        if (um.isNew()){
            super.save(um);
        } else {
            super.update(um);
        }
        return "redirect:/meals";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, Model model) {
        super.delete(Integer.valueOf(request.getParameter("id")));
        return "redirect:/meals";
    }

    @RequestMapping(value = "/filter")
    public String filter(HttpServletRequest req, Model model){
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", req), TimeUtil.MIN_DATE);
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", req), TimeUtil.MAX_DATE);
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", req), LocalTime.MIN);
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", req), LocalTime.MAX);
        model.addAttribute("mealList", super.getBetween(startDate, endDate, startTime, endTime));
        return "mealList";
    }

    private String resetParam(String param, HttpServletRequest req){
        String value = req.getParameter(param);
        req.setAttribute(param, value);
        return value;
    }
}
