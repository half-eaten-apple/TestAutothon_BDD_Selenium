package com.epam.locators;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.elements.impls.ButtonImpl;
import com.epam.elements.impls.CheckBoxImpl;
import com.epam.elements.IButton;
import com.epam.elements.ICheckBox;
import com.epam.elements.ITextField;
import com.epam.elements.impls.TextFieldImpl;
import com.epam.utils.Components;
import com.epam.utils.FrameworkExceptions;


public class DisclosureAgreementLocators extends Components{

    @FindBy(xpath = "//input[@type = 'submit' and @value = 'Accept']")
    private WebElement eleDisAckAcceptKnowledgement;

    @FindBy(xpath = "//th[text()[contains(.,'Disclosure')]]/parent::tr//img")
    private WebElement eleImgDisclosureAcknowledgement;

    @FindBy(xpath = "//*[@id='topButtonRow']/input[5]")
    private WebElement eleCloseoutSummary;

    @FindBy(xpath = "//a[contains(text(),'Disclosure Agreement')]")
    private WebElement eleDisClosureAgreement;

    @FindBy(xpath = "//label[contains(text(),'Have all participants been reconciled?')]/parent::th/following-sibling::td[1]//input")
    private WebElement elePartReconciled;

    @FindBy(xpath = "//label[contains(text(),'Have all participants been reconciled?')]/parent::th/following-sibling::td[2]//input")
    private WebElement eleSheetSubmitted;

    @FindBy(xpath = "//label[contains(text(),'Have all the expenses been reconciled and the appropriate documentation attached?')]/parent::th/following-sibling::td[1]//input")
    private WebElement eleExpReconciled;

    @FindBy(xpath = "//label[contains(text(),'Have all the expenses been reconciled and the appropriate documentation attached?')]/parent::th/following-sibling::td[2]//input")
    private WebElement eleSurveyCompleted;

    @FindBy(xpath = "//input[@value = 'Closeout']")
    private WebElement eleIntSummaryCloseout;

    @FindBy(xpath = "//*[@id='ep']/div[2]/div[2]/table/tbody/tr[1]/td[4]")
    private WebElement eleInteractionStatus;

    @FindBy(xpath = "//td[text()='Completed']")
    private WebElement eleInteractionCompleteStatus;


    //GETTERS
    public IButton getDisAckAcceptKnowledgement() {
        return getComponent(eleDisAckAcceptKnowledgement, ButtonImpl.class, this.getClass());
    }

    public IButton getImgDisclosureAcknowledgement() {
        return getComponent(eleImgDisclosureAcknowledgement, ButtonImpl.class, this.getClass());
    }
    public IButton getCloseOutSummary() {
        return getComponent(eleCloseoutSummary, ButtonImpl.class, this.getClass());
    }
    public IButton getDisclosureAgreement() {
        return getComponent(eleDisClosureAgreement, ButtonImpl.class, this.getClass());
    }

    public ICheckBox getPartReconciled() {
        return getComponent(elePartReconciled, CheckBoxImpl.class, this.getClass());
    }

    public ICheckBox getSheetSubmitted() {
        return getComponent(eleSheetSubmitted, CheckBoxImpl.class, this.getClass());
    }

    public ICheckBox getExpReconciled() {
        return getComponent(eleExpReconciled, CheckBoxImpl.class, this.getClass());
    }
    public ICheckBox getSurveyCompleted() {
        return getComponent(eleSurveyCompleted, CheckBoxImpl.class, this.getClass());
    }

    public IButton getIntSummaryCloseOut() {
        return getComponent(eleIntSummaryCloseout, ButtonImpl.class, this.getClass());
    }

    public ITextField getInterActionStatus() {
        return getComponent(eleInteractionStatus, TextFieldImpl.class, this.getClass());
    }

    public ITextField getInterActionCompleteStatus() {
        return getComponent(eleInteractionCompleteStatus, TextFieldImpl.class, this.getClass());
    }

}
