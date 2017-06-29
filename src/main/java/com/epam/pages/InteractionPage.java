package com.epam.pages;

import com.epam.locators.DisclosureAgreementLocators;
import com.epam.utils.CommonFunctions;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.locators.InteractionPageLocators;

public class InteractionPage extends PageClass {
    private static final  Logger LOGGER = LoggerFactory.getLogger(InteractionPage.class);

    private InteractionPageLocators getPage() { return initElements(InteractionPageLocators.class); }
    private DisclosureAgreementLocators getDisclosureAgreementPage() {
        return initElements(DisclosureAgreementLocators.class);
    }
    private CommonFunctions getCommonFunctions() { return initElements(CommonFunctions.class); }
    public void validateUserLogin() throws FrameworkExceptions
    {
        try {
            Services.waitForPageLoadJS();
            getPage().getInteractionTab().isLoaded();
            LOGGER.info("User Login successful");
        } catch(Exception e) {
            LOGGER.error("Failed in user login " + e );
            throw new FrameworkExceptions("Failed in validateUserLogin() method of InteractionPage class");
        }
    }
        public void createNewInteraction() throws FrameworkExceptions{
        try {
            Services.waitForPageLoadJS();
            getPage().getInteractionTab().clickLink();
            Services.jsExecutor("arguments[0].click();", getPage().getInteractionBtnNew().getElement());

            if(Services.waitForElementInWhile(getPage().getNewInteractionInteractionCategory().getElement(), 3))
                Services.waitForPageLoadJS();

            getPage().setNewInteractionInteractionCategory(TestDataFieldNames.interactionCategory);
            if (getPage().getNewInteractionInterActionType().waitUntilSelectOptionsPopulated())
                getPage().setNewInteractionInterActionType(TestDataFieldNames.interactionType);
            getPage().setNewInteractionLocationType(TestDataFieldNames.locationType);
            getPage().setNewInteractionStartDate(TestDataFieldNames.startDate);
            getPage().setNewInteractionStartTime(TestDataFieldNames.startTime);
            getPage().setNewInteractionEndDate(TestDataFieldNames.endDate);
            getPage().getNewInteractionSave().click();



        } catch(Exception e) {
            LOGGER.error("Failed while creating interaction " + e );
            throw new FrameworkExceptions("Failed in createNewInteraction() method of InteractionPage class");
        }
    }

    public void validateInteractionCreated() throws FrameworkExceptions
    {
        try {
        String strInteractionStatus = getDisclosureAgreementPage().getInterActionStatus().getText();
        if("Draft".equalsIgnoreCase(strInteractionStatus)) {
            LOGGER.info("Interaction is created, and is in Draft State");
        } else {
            LOGGER.error("Interaction is in "+ strInteractionStatus + " status");
        }

    } catch(Exception e) {
        LOGGER.error("Failed to create interaction " + e );
        throw new FrameworkExceptions("Failed in validateInteractionCreated() method of InteractionPage class");
    }
    }

    public void interactionStatusAccepted() throws FrameworkExceptions {
        try {
            String[] arrSelectStatus = TestDataFieldNames.interactionStatus.split(";");
            getPage().getCreatedInteractionName().isLoaded();
            getPage().getCreatedInteractionName().click();

            getPage().getValidateBtn().isLoaded();
            getPage().getValidateBtn().click();

            if( "Interaction validated successfully.".equalsIgnoreCase(getPage().getEleInteractionValidation().getLabel()) ) {
                LOGGER.info("Interaction validated successfully");
            } else {
                LOGGER.error("Failed while validating interaction");
                throw new FrameworkExceptions("Failed while validating interaction");
            }

            getPage().getInteractionSubmitForApproval().clickAlert();
            getPage().getInteractionTab().isLoaded();

            getPage().getInteractionEdit().click();
            getPage().getNewInteractionStatus().isLoaded();

            if (getPage().getNewInteractionStatus().waitUntilSelectOptionsPopulated())
                getPage().setNewInteractionStatus(arrSelectStatus[0]);
            getPage().getNewInteractionSave().click();
            getPage().getInteractionEdit().click();

            Thread.sleep(2000);

            getPage().getNewInteractionSave().isLoaded();

            Thread.sleep(2000);

            if (getPage().getNewInteractionStatus().waitUntilSelectOptionsPopulated())
                getPage().setNewInteractionStatus(arrSelectStatus[1]);
            getPage().getNewInteractionSave().click();

            getPage().getInteractionTab().isLoaded();
        } catch(Exception e) {
            LOGGER.error("Failed to update accept status interaction " + e );
            throw new FrameworkExceptions("Failed in interactionStatusAccepted() method of InteractionPage class");
        }
    }

    public void interactionStatusComplete() throws FrameworkExceptions{
        try{
            String[] arrSelectStatus = TestDataFieldNames.interactionStatus.split(";");
            getPage().getInteractionEdit().click();
            Thread.sleep(2000);
            if(getPage().getNewInteractionStatus().waitUntilSelectOptionsPopulated())
                getPage().setNewInteractionStatus(arrSelectStatus[2]);
            getPage().getNewInteractionSave().click();
            getPage().getInteractionEdit().click();
            getPage().getNewInteractionSave().isLoaded();

            if(getPage().getNewInteractionStatus().waitUntilSelectOptionsPopulated())
                getPage().setNewInteractionStatus(arrSelectStatus[3]);
            getPage().getNewInteractionSave().click();

            getPage().getNewLocationTab().isLoaded();

        } catch(Exception e){
            LOGGER.error("Failed to Update interaction status " + e );
            throw new FrameworkExceptions("Failed in interactionStatusComplete() method of InteractionPage class");
        }
    }

    public void validateInteractionIsReadyTobeClosed() throws FrameworkExceptions{
        try{
            Services.waitForPageLoadJS();

                String strStatus = getPage().getInteractionStatus().getText();
                if ("Closeout Required".equalsIgnoreCase(strStatus))
                {
                    LOGGER.info("Interaction is ready to be closed");
                }
                else {
                    LOGGER.error("Failed to make the Interaction ready for closure");
                }

        } catch(Exception e){
            LOGGER.error("Failed to Update interaction status to 'Closeout Required'" + e );
            throw new FrameworkExceptions("Failed in validateInteractionIsReadyTobeClosed() method of InteractionPage class");
        }
    }

    public void createInteractionWithMissingDataAndVerifyErrMessage() throws FrameworkExceptions
    {
        try {
            Services.waitForPageLoadJS();
            getPage().getInteractionTab().clickLink();
            Services.jsExecutor("arguments[0].click();", getPage().getInteractionBtnNew().getElement());

            if(Services.waitForElementInWhile(getPage().getNewInteractionInteractionCategory().getElement(), 3))
                Services.waitForPageLoadJS();

            getPage().setNewInteractionInteractionCategory(TestDataFieldNames.interactionCategory);
            if (getPage().getNewInteractionInterActionType().waitUntilSelectOptionsPopulated())
                getPage().setNewInteractionInterActionType(TestDataFieldNames.interactionType);
            getPage().setNewInteractionLocationType(TestDataFieldNames.locationType);
            getPage().setNewInteractionStartTime(TestDataFieldNames.startTime);
            getPage().setNewInteractionEndDate(TestDataFieldNames.endDate);
            getPage().getNewInteractionSave().click();
            getCommonFunctions().validateErrorMessage(TestDataFieldNames.newInteractionValidateErrorMessage);
        }
        catch(Exception e){
            LOGGER.error("Failed to create createInteractionWithMissingDataAndVerifyErrMessage");
            throw new FrameworkExceptions("Failed in createInteractionWithMissingDataAndVerifyErrMessage() method of InteractionPage class");
        }
    }
}

