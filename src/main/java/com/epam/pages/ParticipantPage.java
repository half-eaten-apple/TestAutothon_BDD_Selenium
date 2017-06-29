package com.epam.pages;

import com.epam.locators.ParticipantPageLocators;
import com.epam.utils.CommonFunctions;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class ParticipantPage extends PageClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantPage.class);

    private ParticipantPageLocators getPage() { return initElements(ParticipantPageLocators.class); }
    private CommonFunctions getCommonFunctions() { return initElements(CommonFunctions.class); }

    public void createNewParticipant() throws FrameworkExceptions {
        try{
            String[] arrSelectParticipant = TestDataFieldNames.participantsRole.split(";");
            String[] arrRoles = TestDataFieldNames.numberOfRoles.split(";");
            getPage().getParticipantTab().click();
            getPage().getNewEmployeeTab().isLoaded();
            if (("employee").equalsIgnoreCase(TestDataFieldNames.speakerType)){
                getPage().getNewEmployeeTab().click();
                getPage().getNewHcpParticipantRole().isLoaded();
                getPage().setNewHcpParticipantRole(arrSelectParticipant[0]);
            }
            else
            {
                getPage().getNewHcpTab().click();
                getPage().setNewHcpParticipantRole(arrSelectParticipant[0]);
                getPage().getSelectInteractionProductTopic().isLoaded();
                if(!("Teleconference_AZ".equalsIgnoreCase(TestDataFieldNames.interactionType)))
                    getPage().getSelectInteractionProductTopic().click();
            }
            getPage().getNewHcpPageSearch().click();
            getPage().getAddParticipantAndClose().isLoaded();

            getCommonFunctions().performTableOperation("Select", Integer.parseInt(arrRoles[0]));
            getPage().getAddParticipantAndClose().click();

            getPage().getInteractionEdit().isLoaded();

            if(arrSelectParticipant.length>1) {
                getPage().getParticipantTab().click();
                getPage().getNewEmployeeButton().click();
                getPage().setNewHcpParticipantRole(arrSelectParticipant[1]);
                getPage().getNewHcpPageSearch().click();
                getCommonFunctions().performTableOperation("Select", Integer.parseInt(arrRoles[1]));
                getPage().getAddParticipantAndClose().click();
                getPage().getNewLocationTab().isLoaded();
            }
        } catch (Exception e) {
            LOGGER.error("Failed to create new participant " + e );
            throw new FrameworkExceptions("Failed in createNewParticipant() method of ParticipantPage class");
        }
    }

    public void verifyParticipantIsAdded() throws FrameworkExceptions {
        try{
            Services.waitForPageLoadJS();
            getPage().getParticipantTab().isLoaded();
            String strLinkText = getPage().getParticipantTab().getText();
            strLinkText = strLinkText.substring(strLinkText.indexOf("[") + 1, strLinkText.indexOf("]")).trim();
            if (strLinkText.contains("+")) {
                strLinkText = strLinkText.split(Pattern.quote("+"))[0];
            }
            if (Integer.parseInt(strLinkText) > 1)
            {
                LOGGER.info("Participant is added successfully. No. of Participant added "+strLinkText);
            }
            else
            {
                LOGGER.error("No Participant is added to interaction");
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add Location " + e );
            throw new FrameworkExceptions("Failed in verifyParticipantIsAdded() method of ParticipantPage class");
        }
    }

    public void verifyNoProductTopicIsDisplayed() throws FrameworkExceptions
    {
        try{
            getPage().getParticipantTab().click();
            getPage().getNewHcpTab().isLoaded();
            getPage().getNewHcpTab().click();
            getPage().getNewHcpParticipantRole().isLoaded();
            getCommonFunctions().validateErrorMessage(TestDataFieldNames.participantValidateErrorMessage);
        }
        catch (Exception e) {
            LOGGER.error("Failed in verifyNoProductTopicIsDisplayed " + e);
            throw new FrameworkExceptions("Failed in verifyNoProductTopicIsDisplayed() method of ParticipantPage class");
        }
    }
}



