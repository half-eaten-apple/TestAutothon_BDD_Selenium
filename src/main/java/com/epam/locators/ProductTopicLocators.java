package com.epam.locators;/**/
import com.epam.elements.IHyperLink;
import com.epam.elements.impls.ButtonImpl;
import com.epam.elements.impls.DropDownImpl;
import com.epam.elements.impls.ElementImpl;
import com.epam.elements.IElement;
import com.epam.elements.IButton;
import com.epam.elements.IDropDown;
import com.epam.elements.impls.HyperLinkImpl;
import com.epam.utils.Components;
import com.epam.utils.FrameworkExceptions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductTopicLocators extends Components{
    @FindBy(xpath = "(//*[text()[contains(.,'Interaction Product Topics')]]//parent::a)[1]")
    private WebElement eleInteractionProductTopics;

    @FindBy(xpath = "//input[@name='ahm__newproducttopic']")
    private WebElement eleProductTopicNewProductTopic;

    @FindBy(xpath = "//*[text()[contains(.,'AHM Product')]]/parent::th/following-sibling::td[1]/descendant::select")
    private WebElement eleProductTopicAhmProduct;

    @FindBy(xpath = "//td[.=strProductTopic]/preceding-sibling::td/input")
    private WebElement eleProductTopicProductTopic;

    @FindBy(xpath = "(//input[@value='Save and Continue'])[1]")
    private WebElement eleProductTopicSaveAndContinue;

    @FindBy(xpath = "(//input[@value='Save'])[1]")
    private WebElement eleProductTopicSave;

    @FindBy(xpath = "//h3[text()='Participants']/ancestor::div/following-sibling::div/table[@class='list']//")
    private WebElement eleTableOrInteractionPage;

    @FindBy(css = ".list thead th")
    private List<WebElement> eleTableOrSearchableHeader;

    @FindBy(css = ".list tbody tr")
    private List<WebElement> eleTableOrSearchable;

    @FindBy(xpath = ".//a[contains(text(),'Interactions')]")
    private WebElement eleInteractionNewTabLink;

    //GETTERS
    public IHyperLink getInteractionProductTopicsTab() {
        return getComponent(eleInteractionProductTopics, HyperLinkImpl.class, this.getClass());
    }
    public IButton getProductTopicNewProductTopicBtn() {
        return getComponent(eleProductTopicNewProductTopic, ButtonImpl.class, this.getClass());
    }
    public IDropDown getProductTopic() {
        return getComponent(eleProductTopicAhmProduct, DropDownImpl.class, this.getClass());
    }
    public IButton getProductTopicSaveAndContinue() {
        return getComponent(eleProductTopicSaveAndContinue, ButtonImpl.class, this.getClass());
    }
    public IButton getProductTopicSaveBtn() {
        return getComponent(eleProductTopicSave, ButtonImpl.class, this.getClass());
    }
    public IElement getTableInteractionPage() {
        return getComponent(eleTableOrInteractionPage, ElementImpl.class, this.getClass());
    }
    public List<WebElement> getTableOrSearchableHeader() {
        return eleTableOrSearchableHeader;
    }
    public List<WebElement> getTableOrSearchableRow() {
        return eleTableOrSearchable;
    }
    public IHyperLink getInteractionTab() {
        return getComponent(eleInteractionNewTabLink, HyperLinkImpl.class, this.getClass());
    }
    //SETTERS
    public void setProductTopic(String ahmProduct) throws FrameworkExceptions {
        getProductTopic().selectByVisibleText(ahmProduct);
    }

}