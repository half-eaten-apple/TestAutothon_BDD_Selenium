package com.epam.driver;
import java.util.Iterator;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.utils.ClassFinder;


/**
 * Created by vsharma on 15-11-2016.
 */
public class Runner extends BaseTest{
    @Test
    public void testRunner() {
        //TestListenerAdapter tla = new TestListenerAdapter();
        ReportPortalTestNGListener listener = new ReportPortalTestNGListener();
        TestNG testng = new TestNG();
        String path = "com.epam.scenarios";
        List<Class<?>> classes = ClassFinder.find(path);
        if(classes.size() == 0) {
            System.out.println("No classes found in the folder specified " + path);
            LOGGER.error("No classes found in the folder specified " + path);
        }
        Class[] clazz = new Class[classes.size()];
        Iterator iterator = classes.iterator();
        int i =0;
        while (iterator.hasNext())
        {
            clazz[i] = classes.get(i);
            i++;
            iterator.next();
        }
        testng.setTestClasses(clazz);
        testng.addListener((Object)listener);
        testng.run();
    }
}