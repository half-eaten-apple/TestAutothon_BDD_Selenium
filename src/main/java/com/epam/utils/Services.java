package com.epam.utils;/**/

import com.epam.driver.SetupSelenium;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.testng.Reporter;

public class Services {
    private static Logger LOGGER = Logger.getLogger(Services.class);

    private Services(){}

    public static WebElement fluentWait(final WebElement element, long duration) throws FrameworkExceptions {
        try {
            return new FluentWait<>(SetupSelenium.getDriver()).withTimeout(duration, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .until(new Function<WebDriver, WebElement>() {
                        @Override
                        public WebElement apply(WebDriver input) {
                            LOGGER.info(">>>> Waiting for element <<<< : " + element);
                            Reporter.log("Waiting for element" + element);
                            boolean isPresent = element.isDisplayed() && element.isEnabled();
                            if (isPresent) {
                                return element;
                            } else {
                                return null;
                            }

                        }
                    });
        } catch (Exception e) {
            throw new FrameworkExceptions("Element: "+element+" not found"+e);
        }
    }


    public static void jsExecutor(String value) throws FrameworkExceptions {
        try {
            JavascriptExecutor js = (JavascriptExecutor) SetupSelenium.getDriver();
            js.executeScript(value);
        } catch (WebDriverException we) {
            throw new FrameworkExceptions("Failed in jsExecutor method"+we);
        }
    }

    public static void jsExecutor(String value, WebElement locator) throws FrameworkExceptions {
        try {
            waitForElementInWhile(locator, 3);
            getJavaScriptExec().executeScript(value, locator);
        } catch (WebDriverException we) {
            throw new FrameworkExceptions("Exception occurred"+we);
        }
    }

    public static boolean handleAlert() throws FrameworkExceptions {
        boolean flag = false;
        try {
            new WebDriverWait(SetupSelenium.getDriver(), Integer.parseInt(Config.getConfig().getConfigProperty(Constants.ELEMENTWAITTIME)))
                    .ignoring(NoAlertPresentException.class)
                    .until(ExpectedConditions.alertIsPresent());
            Alert alert = SetupSelenium.getDriver().switchTo().alert();
            alert.accept();
            flag = true;
        } catch (NoAlertPresentException ex) {
            throw new FrameworkExceptions("Alert not present"+ex);
        }
        catch (Exception e)
        {
            throw new FrameworkExceptions(e);
        }
        return flag;
    }

    public static WebElement waitUntilSelectOptionsPopulated(final WebElement elt) {
        try {
            final Select select = new Select(elt);
            return new FluentWait<>(SetupSelenium.getDriver())
                    .withTimeout(Integer.parseInt(Config.getConfig().getConfigProperty(Constants.ELEMENTWAITTIME)), TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .until(new Function<WebDriver, WebElement>() {
                        @Override
                        public WebElement apply(WebDriver input) {
                            if (select.getOptions().size() > 1) {
                                return elt;
                            } else {
                                return null;
                            }
                        }
                    });
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to populate the selected options"+e);
            return null;
        }
    }

    private static JavascriptExecutor getJavaScriptExec() {
        return (JavascriptExecutor) SetupSelenium.getDriver();
    }

    public static boolean waitForPageLoadJS() {
        try{
            new WebDriverWait(SetupSelenium.getDriver(), Integer.parseInt(Config.getConfig().getConfigProperty(Constants.ELEMENTWAITTIME)))
                    .until(new Predicate<WebDriver>() {
                               @Override
                               public boolean apply(WebDriver webDriver) {
                                   return ("complete").equals(getJavaScriptExec().executeScript("return document.readyState"));
                               }
                           }

                    );
            return ("complete").equals(getJavaScriptExec().executeScript("return document.readyState"));

        }
        catch (Exception e)
        {
            LOGGER.error("Page did not load"+e);
            return false;
        }
    }

    public static boolean waitForElementInWhile(WebElement element, int whileCounter) throws FrameworkExceptions {
        int intCounter = 0;
        boolean flag = false;
        try {
            do {
                intCounter++;
                if (fluentWait(element, Integer.parseInt(Config.getConfig().getConfigProperty(Constants.ELEMENTWAITTIME))) != null) {
                    flag = true;
                    break;
                }

            } while (intCounter < whileCounter);
            return flag;
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to wait for the element"+e);
            return flag;
        }
    }
}