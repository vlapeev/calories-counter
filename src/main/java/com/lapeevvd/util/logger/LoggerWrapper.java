package com.lapeevvd.util.logger;


import com.lapeevvd.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerWrapper {
    private Logger logger;

    public LoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    public static LoggerWrapper get(Class clazz){
        return new LoggerWrapper(LoggerFactory.getLogger(clazz));
    }

    public void info(String msg, Object ... arguments){
        logger.info(msg, arguments);
    }

    public void debug(String msg){
        logger.debug(msg);
    }

    // TODO: 21.01.2016 Класс не полный


    public NotFoundException getNotFoundException(String msg){
        logger.error(msg);
        return new NotFoundException(msg);
    }
}
