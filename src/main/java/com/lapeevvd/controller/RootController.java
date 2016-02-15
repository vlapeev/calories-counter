package com.lapeevvd.controller;

import com.lapeevvd.service.UserMealService;
import com.lapeevvd.service.UserService;
import com.lapeevvd.util.LoggedUser;
import com.lapeevvd.util.UserMealUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMealService mealService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:meals";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(){
        /*model.addAttribute("userList", userService.getAll());*/
        return "userList";
    }

    @RequestMapping(value = "/meals",method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", UserMealUtil.getWithExceeded(mealService.getAll(LoggedUser.getId()), LoggedUser.getCaloriesPerDay()));
        return "mealList";
    }

    /*@RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }*/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message){
        model.put("error", error);
        model.put("message", message);
        return "login";
    }
}
