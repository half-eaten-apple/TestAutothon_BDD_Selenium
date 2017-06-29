package com.epam.pages;

import com.epam.locators.LoginPageLocators;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends PageClass {
    private static Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    private LoginPageLocators getPage() { return initElements(LoginPageLocators.class); }

    public void loginToSalesForce() throws FrameworkExceptions {
        try{
            getPage().setUserName(TestDataFieldNames.userName);
            getPage().setPassword(TestDataFieldNames.password);
            getPage().getLoginButton().click();

        } catch (Exception e) {
            LOGGER.error("Failed to login to sales force " + e );
            throw new FrameworkExceptions("Failed in loginToSalesForce() method of LoginPage class");
        }
    }
}