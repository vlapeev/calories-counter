package com.lapeevvd.controller;

import com.lapeevvd.util.LoggedUser;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @Order(value = Ordered.LOWEST_PRECEDENCE)
    ModelAndView defaultErrorHandler(Exception e) throws Exception {
        ModelAndView model = new ModelAndView("exception");
        model.addObject("exception", e);
        LoggedUser loggedUser = LoggedUser.safeGet();

        // Interceptor is not invoked, put userTo
        if (loggedUser != null) {
            model.addObject("userTo", loggedUser.getUserTo());
        }
        return model;
    }
}
