package com.epam.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.elements.*;
import com.epam.elements.impls.ButtonImpl;
import com.epam.elements.impls.CheckBoxImpl;
import com.epam.elements.impls.DropDownImpl;
import com.epam.elements.IButton;
import com.epam.elements.IDropDown;
import com.epam.elements.ITextField;
import com.epam.elements.impls.TextFieldImpl;
import com.epam.utils.Components;
import com.epam.utils.FrameworkExceptions;

public class FeedsWorkBenchQueryLocators extends Components{

    @FindBy(xpath = "//select[@id='oauth_env']")
    private WebElement eleEnvironment;

    @FindBy(xpath = "//select[@id='oauth_apiVersion']")
    private WebElement eleApiVersion;

    @FindBy(xpath = "//input[@id='termsAccepted']")
    private WebElement eleAgreeForTerms;

    @FindBy(xpath = "//input[@id='loginBtn']")
    private WebElement eleLoginWithSalesForce;

    @FindBy(xpath = "//a/span[text()='queries']")
    private WebElement eleQueryLink;

    @FindBy(xpath = "//a[text()='SOQL Query']")
    private WebElement eleQueryTab;

    @FindBy(xpath = "//select[@id='QB_object_sel']")
    private WebElement eleObject;

    @FindBy(xpath = "//select[@id='QB_field_sel']")
    private WebElement eleFields;

    @FindBy(xpath = "//select[@id='QB_filter_field_0']")
    private WebElement eleFilterResults;

    @FindBy(xpath = "//input[@id='QB_filter_value_0']")
    private WebElement eleCreatedId;

    @FindBy(xpath = "//input[@id='export_action_async_csv']")
    private WebElement eleBulkCsv;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement eleQueryBtn;

    @FindBy(xpath = ".//*[@id='mainBlock']/table[3]/tbody/tr[2]/td[1]/a/img")
    private WebElement eleFileDownLoadImg;

    //GETTERS


    public IDropDown getEnvironment() {
        return getComponent(eleEnvironment, DropDownImpl.class, this.getClass());
    }
    public IDropDown getApiVersion() {
        return getComponent(eleApiVersion, DropDownImpl.class,this.getClass());
    }
    public ICheckBox getAgreeForTerms() {
        return getComponent(eleAgreeForTerms, CheckBoxImpl.class,this.getClass());
    }

    public IButton getLoginWithSalesForce() {
        return getComponent(eleLoginWithSalesForce, ButtonImpl.class,this.getClass());
    }

    public WebElement getQueryLink() {
        return eleQueryLink;
    }

    public WebElement getQueryTab() {
        return eleQueryTab;
    }

    public IDropDown getObject() {
        return getComponent(eleObject, DropDownImpl.class,this.getClass());
    }

    public IDropDown getFileds() {
        return getComponent(eleFields, DropDownImpl.class,this.getClass());
    }

    public IDropDown getFilterResults() {
        return getComponent(eleFilterResults, DropDownImpl.class,this.getClass());
    }

    public ITextField getCreatedId() {
        return getComponent(eleCreatedId, TextFieldImpl.class,this.getClass());
    }

    public IButton getBulkCsv() {
        return getComponent(eleBulkCsv, ButtonImpl.class,this.getClass());
    }

    public IButton getQueryBtn() {
        return getComponent(eleQueryBtn, ButtonImpl.class,this.getClass());
    }

    public IButton getDownLoadFileImg() {
        return getComponent(eleFileDownLoadImg, ButtonImpl.class,this.getClass());
    }

}
