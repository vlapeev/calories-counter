package com.lapeevvd.util.logger;


import com.lapeevvd.util.exception.ErrorInfo;
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

    public IllegalArgumentException getIllegalArgumentException(String msg, Throwable e) {
        logger.error(msg, e);
        return new IllegalArgumentException(msg, e);
    }

    public NotFoundException getNotFoundException(String msg){
        logger.error(msg);
        return new NotFoundException(msg);
    }

    public ErrorInfo getErrorInfo(CharSequence requestURL, Throwable e) {
        logger.error("Exception at request " + requestURL);
        return new ErrorInfo(requestURL, e);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void error(String msg) {
        logger.warn(msg);
    }
}
