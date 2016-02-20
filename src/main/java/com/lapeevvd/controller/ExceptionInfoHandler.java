package com.lapeevvd.controller;

import com.lapeevvd.util.exception.ErrorInfo;
import com.lapeevvd.util.exception.NotFoundException;
import com.lapeevvd.util.exception.ValidationException;
import com.lapeevvd.util.logger.LoggerWrapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    private final LoggerWrapper LOG = LoggerWrapper.get(ExceptionInfoHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
        return LOG.getErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return LOG.getErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    ErrorInfo validationError(HttpServletRequest req, ValidationException e) {
        return LOG.getErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return LOG.getErrorInfo(req.getRequestURL(), e);
    }
}
