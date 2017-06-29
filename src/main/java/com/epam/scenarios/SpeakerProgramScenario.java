package com.epam.scenarios;

import com.epam.driver.BaseTest;
import com.epam.pages.*;
import com.epam.utils.Config;
import com.epam.utils.Constants;
import com.epam.utils.TestDataReader;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by vsharma on 16-11-2016.
 */
public class SpeakerProgramScenario extends BaseTest{
    @Test(priority = 1 , enabled = true)
    void speakerProgram() {
        try {
            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting Speaker Program Scenario", "Starting Speaker Program Scenario");
            startingMethodPreReq("SpeakerProgram_AZ");
            dataReader.readScenarioData("SpeakerProgram_AZ");
            Map<String, String> treeMap = dataReader.getEntityData("SpeakerProgram_AZ", "Data", TestDataReader.currentIterationRow);

            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into sales force successfully", "Logged into sales force successfully");

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
            logInfo("Ending Speaker Program Scenario", "Ending Speaker Program Scenario");
        } catch (Exception e) {
            System.out.println("Exception in SpeakerProgram: " + e);
            LOGGER.error("Failed in the Speaker Program Scenario"+e);
        }
    }

    @Test(enabled = false )
    void speakerProgram_OffSiteEvening(){
        try {
            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting Speaker Program OffSite Evening Scenario", "Starting Speaker Program OffSite Evening Scenario");
            startingMethodPreReq("OffsiteEveningSpeakerProgramAZ");
            dataReader.readScenarioData("OffsiteEveningSpeakerProgramAZ");
            Map<String, String> treeMap = dataReader.getEntityData("OffsiteEveningSpeakerProgramAZ", "Data", TestDataReader.currentIterationRow);

            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into successfully", "Login successfully");

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
            logInfo("Ending Speaker Program OffSite Evening Scenario", "Ending Speaker Program OffSite Evening Scenario");


        } catch (Exception e) {
            System.out.println("Exception in OffsiteEveningSpeakerProgramAZ: " + e);
            LOGGER.error("Failed in OffsiteEveningSpeakerProgramAZ"+e);
        }
    }

    @Test(priority = 3, enabled = true)
    void speakerProgram_TeleConference(){
        try {

            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting Speaker Program TeleConference Scenario", "Starting Speaker Program TeleConference Scenario");
            startingMethodPreReq("TeleconferenceAZ");
            dataReader.readScenarioData("TeleconferenceAZ");
            Map<String, String> treeMap = dataReader.getEntityData("TeleconferenceAZ", "Data", TestDataReader.currentIterationRow);

            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into successfully", "Login successfully");

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
            logInfo("Ending Speaker Program TeleConference Scenario", "Ending Speaker Program TeleConference Scenario");

        } catch (Exception e) {
            System.out.println("Exception in TeleconferenceAZ: " + e);
            LOGGER.error("Failed in TeleconferenceAZ"+e);
        }
    }
}
