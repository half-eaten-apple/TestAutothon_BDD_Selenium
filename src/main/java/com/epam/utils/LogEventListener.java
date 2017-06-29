package com.epam.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.TestListenerAdapter;

public class LogEventListener extends TestListenerAdapter implements WebDriverEventListener {

    private Log LOGGER = LogFactory.getLog(this.getClass());
    private By lastFindBy;
    private String originalValue;

    @Override
    public void beforeNavigateTo(String url, WebDriver webDriver) {
        LOGGER.info("WebDriver navigating to:'"+url+"'");
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        lastFindBy = by;
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver webDriver) {
        String locator = element.toString().split("-> ")[1];
        LOGGER.info("WebDriver clicking on:'"+locator.substring(0, locator.length()-1)+"'");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void beforeChangeValueOf(WebElement element, WebDriver webDriver) {
        originalValue = element.getAttribute("value");
    }

    public void afterChangeValueOf(WebElement element, WebDriver webDriver) {
        LOGGER.info("WebDriver changing value in element found "+lastFindBy+" from '"+originalValue+"' to '"+element.getAttribute("value")+"'");
    }

    @Override
    public void onException(Throwable error, WebDriver webDriver) {
        if (error.getClass().equals(NoSuchElementException.class)){
            LOGGER.error("WebDriver error: Element not found "+lastFindBy);
        } else {
            if(error.toString().toLowerCase().contains("jquery")) {
                LOGGER.info("Suppressed WebDriver exception warning : JQUERY " );
            } else if(error.toString().toLowerCase().contains("sizzle")) {
                LOGGER.info("Suppressed WebDriver exception warning : SIZZLE " );
            } else {
                LOGGER.error("WebDriver error:", error);
            }
        }
    }

    @Override
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }
}
