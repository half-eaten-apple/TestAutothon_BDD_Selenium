package com.epam.steps.hooks;

import com.epam.driver.BaseTest;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by vsharma on 02-11-2016.
 */
public class TestBaseHook extends BaseTest {
    @Before
    public void beforeScenario() {
        try {
            init();
        }catch (Exception e) {
            LOGGER.error("Failed in Before scenario"+e);
        }
    }

    @After
    public void afterScenario() {
        try {
            closeMethodsOpenedBrowserIfAny();
        } catch (Exception e) {
            LOGGER.error("Failed in After scenario"+e);
        }
    }
}
