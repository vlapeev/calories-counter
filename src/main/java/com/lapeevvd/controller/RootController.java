package com.lapeevvd.controller;

import com.lapeevvd.controller.user.AbstractUserController;
import com.lapeevvd.dataTransferObject.UserTo;
import com.lapeevvd.util.LoggedUser;
import com.lapeevvd.util.UserUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:meals";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(){
        return "userList";
    }

    @RequestMapping(value = "/meals",method = RequestMethod.GET)
    public String mealList() {
        return "mealList";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message){
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(){
        return "profile";
    }

    /* Use Binding */
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            try {
                userTo.setId(LoggedUser.getId());
                super.update(userTo);
                status.setComplete();
                LoggedUser.get().setUserTo(userTo);
                return "redirect:meals";
            } catch (DataIntegrityViolationException e) {
                result.rejectValue("email", "error.user", "user with this email already present in application");
            }
        }
        return "profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model){
        model.put("userTo", new UserTo());
        model.put("register", true);
        return "profile";
    }

    /* Use Binding */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UserUtil.createFromUserTo(userTo));
                status.setComplete();
                return "redirect:login?message=app.registered";
            } catch (DataIntegrityViolationException e){
                result.rejectValue("email", "error.user", "user with this email already present in application");
            }
        }
        model.put("register", true);
        return "profile";
    }

    /*@RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model){
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }*/

    /*@RequestMapping(value = "/meals",method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", UserMealUtil.getWithExceeded(mealService.getAll(LoggedUser.getId()), LoggedUser.getCaloriesPerDay()));
        return "mealList";
    }*/

    /*@RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }*/
}
