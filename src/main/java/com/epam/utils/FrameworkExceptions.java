package com.epam.utils;/**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameworkExceptions extends Exception {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrameworkExceptions.class);

    private String errorMessage = null;

    public FrameworkExceptions(String message) {
        super(message);
        errorMessage = message;
    }

    public FrameworkExceptions(String page, String object) {
        super();
        StringBuffer message = new StringBuffer();

        try {
            if((page != null) && !("".equals(page))  && (object != null) && !("".equals(object)) ) {
                String key = page.toUpperCase() + " . " + object.toUpperCase();
                message.append(key);
            }
        } catch (Exception e) {
            message.append(e.getMessage());
            LOGGER.error("Exception occurred in framework exception constructor ", e);
        }
        this.errorMessage = message.toString();
    }

    public FrameworkExceptions(Throwable e) {
        super(e.getLocalizedMessage().split("\n")[0]);
        errorMessage = e.getMessage();
    }

    public FrameworkExceptions(String page, Throwable e) {
        super(page, e);
        errorMessage = e.getMessage();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
