package com.epam.pages;

import com.epam.driver.SetupSelenium;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AllPages {
    public static <T> T initPages(WebDriver driver, Class<T> pageClass) {
        try {
            try {
                Constructor<T> cons = pageClass.getConstructor(WebDriver.class);
                return cons.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClass.newInstance();
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getPage(Class<T> clazz) {
        T page = initPages(SetupSelenium.getDriver(), clazz);
        return page;
    }
}
