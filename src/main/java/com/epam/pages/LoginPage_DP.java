package com.epam.pages;

import com.epam.locators.LoginPageLocators;
import com.epam.utils.FrameworkExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vsharma on 01-12-2016.
 */
public class LoginPage_DP extends PageClass {
    private static Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    private LoginPageLocators getPage() {return initElements(LoginPageLocators.class);}

    public boolean loginToSalesForce(String username, String password) throws FrameworkExceptions {
        try{
            getPage().setUserName(username);
            getPage().setPassword(password);
            getPage().getLoginButton().click();
            return getPage().getLoggedUser().getText() != null;
        } catch (Exception e) {
            LOGGER.error("Failed to login to sales force " + e );
            throw new FrameworkExceptions("Failed in loginToSalesForce() method of LoginPage class");
        }
    }
}
