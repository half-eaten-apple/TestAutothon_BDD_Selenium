package com.epam.pages;

import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import com.epam.utils.TestDataFieldNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.locators.LocationPageLocators;
import com.epam.utils.CommonFunctions;

public class LocationPage extends PageClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationPage.class);
    private LocationPageLocators getPage() { return initElements(LocationPageLocators.class); }
    private CommonFunctions getCommonFunctions() { return initElements(CommonFunctions.class); }

    public void addNewLocation() throws FrameworkExceptions{
        try {
            String[] arrSelectLocPriority = TestDataFieldNames.locationPriority.split(";");
            Services.waitForPageLoadJS();
            getPage().getNewLocationTab().isLoaded();
            getPage().getNewLocationTab().click();
            getPage().getNewLocationButton().click();

            Services.waitForPageLoadJS();
            getPage().getPageSearch().isLoaded();
            Services.jsExecutor("arguments[0].click();", getPage().getPageSearch().getElement());
            getPage().setLocationPriority(arrSelectLocPriority[0]);
            getCommonFunctions().performTableOperation("Select", 2);
            getPage().getAddLocationAndContinue().click();

            if(Services.waitForElementInWhile(getPage().getAddLocationAndContinue().getElement(), 5))
                getPage().setLocationPriority(arrSelectLocPriority[1]);
            Thread.sleep(3000);
            getCommonFunctions().performTableOperation("Select", 1);
            if(Services.waitForElementInWhile(getPage().getAddLocationAndContinue().getElement(), 5))
                getPage().getLocationAndClose().click();
            if(!getPage().getNewLocationTab().isLoaded()) {
                LOGGER.error("NewLocationTab is not loaded.");
            }
        } catch(Exception e) {
            LOGGER.error("Failed to add new location " + e );
            throw new FrameworkExceptions("Failed in addNewLocation() method of LocationPage class");
        }
    }

    public void verifyLocationIsAdded() throws FrameworkExceptions {
        try{
            Services.waitForPageLoadJS();
            getPage().getNewLocationTab().isLoaded();
            String strLinkText = getPage().getNewLocationTab().getText();
            strLinkText = strLinkText.substring(strLinkText.indexOf("[") + 1, strLinkText.indexOf("]")).trim();
            if (Integer.parseInt(strLinkText) > 0)
            {
                LOGGER.info("Location is added successfully. No. of Locations added "+strLinkText);
            }
            else
            {
                LOGGER.error("No Location is added to interaction");
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add Location " + e );
            throw new FrameworkExceptions("Failed in verifyLocationIsAdded() method of LocationPage class");
        }
    }

    public void locationConfirm() throws FrameworkExceptions  {
        String[] arrSelectParticipant = TestDataFieldNames.participantRole.split(";");
        try{
            getPage().getNewLocationTab().isLoaded();
            getPage().getNewLocationTab().click();
            getCommonFunctions().performTableActions("Locations", "Location Status", "Pending", "Edit");
            getPage().setLocationStatus(TestDataFieldNames.locationStatus);
            getPage().getLocationEditSave().click();
            getPage().getParticipantTab().click();
            getCommonFunctions().performTableActions("Participants", "Role", arrSelectParticipant[0], "Edit");
            getPage().setParticipantStatus(TestDataFieldNames.locationStatus);
            getPage().getParticipantSave().click();
            getPage().getBackToInteraction().click();
        }
        catch (Exception e) {
            LOGGER.error("Failed to confirm location " + e );
            throw new FrameworkExceptions("Failed in locationConfirm() method of LocationPage class");
        }
    }
}