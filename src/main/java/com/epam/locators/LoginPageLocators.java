package com.epam.locators;/**/

import com.epam.elements.ILabel;
import com.epam.elements.impls.ButtonImpl;
import com.epam.elements.IButton;
import com.epam.elements.ITextField;
import com.epam.elements.impls.LabelImpl;
import com.epam.elements.impls.TextFieldImpl;
import com.epam.utils.Components;
import com.epam.utils.FrameworkExceptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators extends Components {

    @FindBy(xpath = ".//a[contains(text(),'Interactions')]")
    private WebElement eleInteractionNewTabLink;


    @FindBy(xpath = ".//*[@id='username']")
    private WebElement eleUserName;

    @FindBy(xpath = ".//*[@id='Login']")
    private WebElement eleLoginButton;

    @FindBy(xpath = ".//*[@id='password']")
    private WebElement elePassword;

    @FindBy(xpath = ".//*[@id='userNavButton']")
    private WebElement eleTestOperations;

    @FindBy(xpath = ".//*[@id='userNav-menuItems']/a[3]")
    private WebElement eleLogout;

    @FindBy(css = "#userNavLabel")
    private WebElement eleLoggedUser;

    //GETTERS
    public IButton getLoginButton() {
        return getComponent(eleLoginButton, ButtonImpl.class, this.getClass());
    }

    public ITextField getUserName() {
        return getComponent(eleUserName, TextFieldImpl.class, this.getClass());
    }

    public ITextField getPassword() {
        return getComponent(elePassword, TextFieldImpl.class, this.getClass());
    }

    public IButton getTestOperations() {
        return getComponent(eleTestOperations, ButtonImpl.class, this.getClass());
    }

    public IButton getLogOut() {
        return getComponent(eleLogout, ButtonImpl.class, this.getClass());
    }

    public ITextField getLoggedUser() { return getComponent(eleLoggedUser, TextFieldImpl.class, this.getClass()); }


    //SETTERS
    public void setUserName(String userName) throws FrameworkExceptions {
        getUserName().setText(userName);
    }

    public void setPassword(String password) throws FrameworkExceptions{
        getPassword().setText(password);
    }


    //GETTERS
    public ILabel getEleInteractionValidation1() {
        return getComponent(eleInteractionNewTabLink, LabelImpl.class, this.getClass());
    }
}
