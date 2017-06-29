package com.epam.scenarios;

import com.epam.driver.BaseTest;
import com.epam.pages.*;
import com.epam.utils.Config;
import com.epam.utils.Constants;
import com.epam.utils.TestDataReader;

import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by vsharma on 17-11-2016.
 */
public class RegionalExhibit extends BaseTest{

    @Test(enabled = false)
    public void regionalExhibit(){
        try {
            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting RegionalExhibit Scenario", "Starting RegionalExhibit Scenario");
            startingMethodPreReq("RegionalExhibit");
            dataReader.readScenarioData("RegionalExhibit");
            Map<String, String> treeMap = dataReader.getEntityData("RegionalExhibit", "Data", TestDataReader.currentIterationRow);

            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into Sales Force successfully", "Logged into Sales Force successfully");

            AllPages.getPage(InteractionPage.class).createNewInteraction();
            logInfo("New interaction created", "Created new interaction");

            AllPages.getPage(ProductTopicPage.class).addProductTopic();
            logInfo("Added product topic", "Added product topic");

            AllPages.getPage(LocationPage.class).addNewLocation();
            logInfo("Added new location", "Added new location");

            AllPages.getPage(ParticipantPage.class).createNewParticipant();
            logInfo("Created new participant", "Created new participant");

            AllPages.getPage(LogisticsPage.class).createNewLogistics();
            logInfo("Created new logistics.", "Created new logistics");

            AllPages.getPage(NewEstimatePage.class).createNewEstimate();
            logInfo("Created new estimate", "New estimate created");

            AllPages.getPage(InteractionPage.class).interactionStatusAccepted();
            logInfo("Updated the interaction status to accepted.", "Updated interaction status to accepted");

            AllPages.getPage(LocationPage.class).locationConfirm();
            logInfo("Location confirmed", "Confirmed the location");

            AllPages.getPage(InteractionPage.class).interactionStatusComplete();
            logInfo("Updated the interaction status to completed", "Updated the interaction status to completed");

            AllPages.getPage(NewExpensePage.class).expensesPaid();
            logInfo("Paid expenses successfully", "Expense paid successfully");

            AllPages.getPage(DisclosureAgreementPage.class).addDisclosureAgreement();
            logInfo("Added disclosure agreement successfully", "Added disclosure agreement successfully");

            logInfo("Ending Regional Exhibit Scenario", "Ending Regional Exhibit Scenario");

        } catch (Exception e) {
            LOGGER.error("Failed in RegionalExhibit"+e);
        }
    }

}
