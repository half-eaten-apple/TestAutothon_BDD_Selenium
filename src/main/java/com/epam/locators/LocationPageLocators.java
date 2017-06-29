package com.epam.locators;

import com.epam.elements.ILabel;
import com.epam.elements.impls.ButtonImpl;
import com.epam.elements.impls.DropDownImpl;
import com.epam.elements.IButton;
import com.epam.elements.IDropDown;
import com.epam.elements.impls.LabelImpl;
import com.epam.utils.Components;
import com.epam.utils.FrameworkExceptions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LocationPageLocators extends Components{

    @FindBy(xpath = ".//a[contains(text(),'Interactions')]")
    private WebElement eleInteractionNewTabLink;

    @FindBy(xpath = "//a/span[text()='Locations']")
    private WebElement eleLocationTab;

    @FindBy(xpath = "//input[@title='New Location']")
    private WebElement eleLocationButton;

    @FindBy(xpath = "//input[@value='Search' and @class='btn']")
    private WebElement eleLocationPageSearch;

    @FindBy(css = ".btn")
    private WebElement eleSearchBtnCSS;

    @FindBy(xpath = "//*[text()[contains(.,'Location Priority')]]/parent::th/following-sibling::td[1]/descendant::select")
    private WebElement eleLocationPriority;

    @FindBy(xpath = "//input[@value='Add Location & Continue' and @class='btn']")
    private WebElement eleAddLocationContinue;

    @FindBy(xpath = "//input[@value='Add Location & Close' and @class='btn']")
    private WebElement eleLocationAndClose;

    @FindBy(xpath = ".//*[@id='topButtonRow']/input[1]")
    private WebElement eleInteractionEdit;

    @FindBy(xpath = "//input[@title='Save']")
    private WebElement eleLocationSave;

    @FindBy(xpath = "//a/span[text()='Participants']")
    private WebElement eleParticipantTab;

    @FindBy(xpath = "//*[text()[contains(.,'Status')]]/parent::th/following-sibling::td/descendant::select")
    private WebElement eleParticipants;

    @FindBy(xpath = "//*[@value = 'Save']")
    private WebElement eleParticipantSave;

    @FindBy(xpath = "//*[text()[contains(.,'Status')]]/parent::th/following-sibling::td/descendant::select")
    private WebElement eleParticipantStatus;

    @FindBy(xpath = "//a[text()[contains(.,'Back to Interaction')]]")
    private WebElement eleBackToInteraction;

    @FindBy(xpath = "//*[text()[contains(.,'Location Status')]]/parent::td/following-sibling::td[1]/descendant::select")
    private WebElement eleLocationStatus;


    //GETTERS
    public IButton getNewLocationTab() {
        return getComponent(eleLocationTab, ButtonImpl.class, this.getClass());
    }

    public IButton getNewLocationButton() {
        return getComponent(eleLocationButton, ButtonImpl.class, this.getClass());
    }

    public 	IButton getPageSearch() {
        return getComponent(eleLocationPageSearch, ButtonImpl.class, this.getClass());
    }

    public WebElement getSearchBtnCss() {
        return eleSearchBtnCSS;

    }

    public IDropDown getLocationPriority() {
        return getComponent(eleLocationPriority, DropDownImpl.class, this.getClass());
    }

    public IButton getAddLocationAndContinue() {
        return getComponent(eleAddLocationContinue, ButtonImpl.class, this.getClass());
    }
    public IButton getLocationAndClose() {
        return getComponent(eleLocationAndClose, ButtonImpl.class, this.getClass());
    }

    public IButton getInterActionEdit() {
        return getComponent(eleInteractionEdit, ButtonImpl.class, this.getClass());
    }

    public IButton getLocationEditSave() {
        return getComponent(eleLocationSave, ButtonImpl.class, this.getClass());
    }

    public IButton getParticipantTab() {
        return getComponent(eleParticipantTab, ButtonImpl.class, this.getClass());
    }

    public IDropDown getParticipants() {
        return getComponent(eleParticipants, DropDownImpl.class, this.getClass());
    }

    public IButton getParticipantSave() {
        return getComponent(eleParticipantSave, ButtonImpl.class, this.getClass());
    }

    public IDropDown getParticipantStatus() {
        return getComponent(eleParticipantStatus, DropDownImpl.class, this.getClass());
    }

    public IButton getBackToInteraction() {
        return getComponent(eleBackToInteraction, ButtonImpl.class, this.getClass());
    }

    public IDropDown getLocationStatus() {
        return getComponent(eleLocationStatus, DropDownImpl.class, this.getClass());
    }

    //SETTERS
    public void setLocationPriority(String arrSelectLocPriority) throws FrameworkExceptions {
        getLocationPriority().selectByVisibleText(arrSelectLocPriority);
    }

    public void setLocationStatus(String locationStatus) throws FrameworkExceptions{
        getLocationStatus().selectByVisibleText(locationStatus);
    }

    public void setParticipantStatus(String locationStatus) throws FrameworkExceptions{
        getParticipantStatus().selectByVisibleText(locationStatus);
    }


}

