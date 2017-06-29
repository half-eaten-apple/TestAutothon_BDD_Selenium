package com.epam.locators;

import com.epam.elements.*;
import com.epam.elements.impls.*;

import com.epam.utils.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.epam.utils.FrameworkExceptions;

public class InteractionPageLocators extends Components {

    @FindBy(xpath = ".//a[contains(text(),'Interactions')]")
    private WebElement eleInteractionNewTabLink;

    @FindBy(css = "input.btn[name='new']")
    private WebElement eleInteractionNewBtn;

    @FindBy(xpath = "//*[text()[contains(.,'Interaction Category')]]/parent::th/following-sibling::td[1]/descendant::select")
    private WebElement 	eleInteractionInteractionCategory;

    @FindBy(xpath = "//*[text()[contains(.,'Interaction Type')]]/parent::th/following-sibling::td[1]/descendant::select")
    private WebElement 	eleInteractionInteractionType;

    @FindBy(xpath = "//*[text()[contains(.,'LocationType')]]/parent::th/following-sibling::td[1]/descendant::select")
    private WebElement 	eleInteractionLocationType;

    @FindBy(xpath = "(//*[text()[contains(.,'Start Date')]]//parent::th/following-sibling::td//input)[1]")
    private WebElement 	eleInteractionStartDate;

    @FindBy(xpath = "//*[text()[contains(.,'Start Time')]]//parent::th//following-sibling::td//select")
    private WebElement 	eleInteractionStartTime;

    @FindBy(xpath = "(//*[text()[contains(.,'End Date')]]//parent::th/following-sibling::td//input)[1]")
    private WebElement 	eleInteractionEndDate;

    @FindBy(xpath = "(.//input[@class='btn' and @value='Save'])[1]")
    private WebElement 	eleInteractionSave;

    @FindBy(xpath = "//*[text()[contains(.,'Interaction')]]/following-sibling::td/div/a")
    private WebElement eleCreatedInteractionName;

    @FindBy(xpath = "//input[@value='Validate']")
    private WebElement eleValidate;

    @FindBy(xpath = "//input[@value='Submit for Approval']")
    private WebElement eleInteractionSubmitForApproval;

    @FindBy(xpath = "//input[@name='edit']")
    private WebElement eleInteractionEdit;

    @FindBy(xpath = "//td/span/select")
    private WebElement eleInteractionLabelStatus;

    @FindBy(xpath = "//*[text()[contains(.,'Status')]]/parent::th/following-sibling::td[1]/descendant::select")
    private WebElement eleNewInteractionStatus;

    @FindBy(xpath = "//a/span[text()='Locations']")
    private WebElement eleLocationTab;

    @FindBy(xpath = "//*[text()[contains(.,'Interaction validated successfully')]]")
    private WebElement eleInteractionValidation;

    @FindBy(xpath = "//h4[text()='Error:']/../..")
    private WebElement eleVerifyErrorMessage;
    @FindBy(xpath = "//*[text()[contains(.,'Status')]]/following-sibling::td")
    private WebElement eleInteractionStatus;
    //GETTERS
    public ILabel getEleInteractionValidation() {
        return getComponent(eleInteractionValidation, LabelImpl.class, this.getClass());
    }

    public IHyperLink getInteractionTab() {
        return getComponent(eleInteractionNewTabLink, HyperLinkImpl.class, this.getClass());
    }

    public IButton getInteractionBtnNew() {
        return getComponent(eleInteractionNewBtn, ButtonImpl.class, this.getClass());
    }

    public IDropDown getNewInteractionInteractionCategory() {
        return getComponent(eleInteractionInteractionCategory, DropDownImpl.class, this.getClass());
    }

    public IDropDown getNewInteractionInterActionType() {
        return getComponent(eleInteractionInteractionType, DropDownImpl.class, this.getClass());
    }

    public IDropDown getNewInteractionLocationType() {
        return getComponent(eleInteractionLocationType, DropDownImpl.class, this.getClass());
    }

    public ITextField getNewInteractionStartDate() {
        return getComponent(eleInteractionStartDate, TextFieldImpl.class, this.getClass());
    }

    public ITextField getNewInteractionStartTime() {
        return getComponent(eleInteractionStartTime, TextFieldImpl.class, this.getClass());
    }

    public ITextField getNewInteractionEndDate() {
        return getComponent(eleInteractionEndDate, TextFieldImpl.class, this.getClass());
    }

    public IButton getNewInteractionSave() {
        return getComponent(eleInteractionSave, ButtonImpl.class, this.getClass());
    }
    public IButton getCreatedInteractionName() {
        return getComponent(eleCreatedInteractionName, ButtonImpl.class, this.getClass());
    }
    public IButton getValidateBtn() {
        return getComponent(eleValidate, ButtonImpl.class, this.getClass());
    }
    public IButton getInteractionSubmitForApproval() {
        return getComponent(eleInteractionSubmitForApproval, ButtonImpl.class, this.getClass());
    }
    public IButton getInteractionEdit() {
        return getComponent(eleInteractionEdit, ButtonImpl.class, this.getClass());
    }
    public IDropDown getInteractionLblStatus() {
        return getComponent(eleInteractionLabelStatus, DropDownImpl.class, this.getClass());
    }

    public IDropDown getNewInteractionStatus() {
        return getComponent(eleNewInteractionStatus, DropDownImpl.class, this.getClass());
    }
    public IButton getNewLocationTab() {
        return getComponent(eleLocationTab, ButtonImpl.class, this.getClass());
    }
    public ITextField getVerifyErrorMessage() {
        return getComponent(eleVerifyErrorMessage, TextFieldImpl.class, this.getClass());
    }

    public ILabel getInteractionStatus() { return getComponent(eleInteractionStatus, LabelImpl.class, this.getClass());}

    //SETTERS
    public void setNewInteractionInteractionCategory(String interactionCategory) throws FrameworkExceptions {
        getNewInteractionInteractionCategory().selectByValue(interactionCategory);
    }

    public void setNewInteractionInterActionType(String interactionType) throws FrameworkExceptions{
        getNewInteractionInterActionType().selectByVisibleText(interactionType);
    }

    public void setNewInteractionLocationType(String locationType) throws FrameworkExceptions {
        getNewInteractionLocationType().selectByIgnoringSpecialCharacters(locationType);
    }

    public void setNewInteractionStartDate(String startDate) throws FrameworkExceptions{
        getNewInteractionStartDate().setText(startDate);
    }
    public void setNewInteractionStartTime(String startTime) throws FrameworkExceptions{
        getNewInteractionStartTime().setText(startTime);
    }
    public void setNewInteractionEndDate(String endDate) throws FrameworkExceptions{
        getNewInteractionEndDate().setText(endDate);
    }

    public void setNewInteractionStatus(String arrSelectStatus) throws FrameworkExceptions{
        getNewInteractionStatus().selectByVisibleText(arrSelectStatus);
    }


}
