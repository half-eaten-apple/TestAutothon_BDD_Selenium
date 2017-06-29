package com.epam.utils;

import com.epam.elements.IElement;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.Assertion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class Components {
    private static final Logger LOGGER = LoggerFactory.getLogger(Components.class);

    static BidiMap locatorsMap = new TreeBidiMap();

    public <E extends IElement> E getComponent(final WebElement element, Class<E> clazz, Class callClazz) {
        try {
            Services.waitForPageLoadJS();
            Services.fluentWait(element, 60);
            String xp = element.toString().split("-> ")[1].trim();
            String elementName = getVariableNameFromValue(callClazz, xp.substring(0, xp.length() - 1).split(": ")[1]);

            Constructor<E> constructor = clazz.getConstructor(WebElement.class, String.class);
            return constructor.newInstance(element, elementName);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(String.valueOf(e));
        }
        catch (FrameworkExceptions fe) {
            String s = getVariableCausedException(callClazz, element);
            Assertion hardAssert = new Assertion();
            hardAssert.fail("Failed to creating interaction :: " + s + " -- " +fe);
            LOGGER.error("Failed to creating interaction :: " + s + " -- " +fe );
            LOGGER.error("Failed in getComponents() method of Components class");
        }
        return null;
    }

    public String getVariableNameFromValue(Class clazz, String value) {
        if(locatorsMap.containsValue(value)) {
            System.out.println("Already contains value in map");
            String key = (String) locatorsMap.getKey(value);
            String[] arrOfPackages = key.split(clazz.getName() + ".");
            return arrOfPackages[arrOfPackages.length-1];
        } else {
            Field[] fields = clazz.getDeclaredFields();
            for (Field fld : fields)
                try {
                    String variableName = fld.getDeclaredAnnotations()[0].toString();
                    if (variableName.contains(value)) {
                        locatorsMap.put(clazz.getName() + "." + fld.getName(), value);
                        return fld.getName();
                    }
                } catch (Exception e) {
                    LOGGER.error(String.valueOf(e));
                }

            return  value;
        }
    }

    public String getVariableCausedException(Class callClass, WebElement element) {
        Field field[] = callClass.getDeclaredFields();
        String var = null;
        for (Field fld:field) {
            if(fld.isAnnotationPresent(FindBy.class))
            {
                fld.setAccessible(true);
                try {
                    if(fld.get(this).toString().equalsIgnoreCase(element.toString())) {
                        var = fld.getName();
                        break;
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.error(String.valueOf(e));
                }
            }
        }
        return var;
    }
}
