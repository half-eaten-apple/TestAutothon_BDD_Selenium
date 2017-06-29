package com.epam.scenarios;

import com.epam.driver.BaseTest;
import com.epam.pages.*;
import com.epam.utils.TestDataProvider;
import org.testng.annotations.Test;

/**
 * Created by vsharma on 01-12-2016.
 */
public class SpeakerProgramScenario_DP extends BaseTest {
    @Test(priority = 1, enabled = true, dataProvider = "speakerProgram", dataProviderClass = TestDataProvider.class)
    void speakerProgram(String scenarioName, String userName, String password, String interactionCategory,
                        String interactionType, String locationType, String hostingRegion, String requestingRegion,
                        String startDate, String startTime, String endDate, String endTime, String currency,
                        String company, String status, String ahmProduct, String locationPriority, String participantsRole ,String numberOfRoles,
                        String speakerType) {
        try {
            logInfo("Starting Speaker Program Scenario", "Starting Speaker Program Scenario");
            startingMethodPreReq("SpeakerProgram_AZ");

            boolean pageAssert = AllPages.getPage(LoginPage_DP.class).loginToSalesForce(userName, password);
            hardAssert.assertEquals(pageAssert, true, "Logged into sales force successfully");
            logInfo("Logged into sales force successfully", "Logged into sales force successfully");

            pageAssert = AllPages.getPage(InteractionPage_DP.class).createNewInteraction(interactionCategory, interactionType, locationType, startDate,
                    startTime, endDate);
            hardAssert.assertEquals(pageAssert, true, "New interaction created");
            logInfo("New interaction created", "Created new interaction");

            pageAssert = AllPages.getPage(ProductTopicPage_DP.class).addProductTopic(ahmProduct);
            hardAssert.assertEquals(pageAssert, true, "Added product topic");
            logInfo("Added product topic ", "Added product topic ");

            logInfo("Ending Speaker Program Scenario", "Ending Speaker Program Scenario");
        }   catch (Exception e) {
            System.out.println("Exception in SpeakerProgram: " + e);
            LOGGER.error("Failed in the Speaker Program Scenario"+e);
        }
    }

    @Test(priority = 2, enabled = true, dataProvider = "speakerProgramOffsiteEvening", dataProviderClass = TestDataProvider.class)
    void speakerProgram_OffSiteEvening(String scenarioName, String userName, String password, String interactionCategory,
                                       String interactionType, String locationType, String hostingRegion, String requestingRegion,
                                       String startDate, String startTime, String endDate, String endTime, String currency,
                                       String company, String status, String ahmProduct, String locationPriority, String participantsRole ,String numberOfRoles,
                                       String speakerType) {
        try {
            logInfo("Starting Speaker Program OffSite Evening Scenario", "Starting Speaker Program OffSite Evening Scenario");
            startingMethodPreReq("SpeakerProgram_AZ");

            boolean pageAssert = AllPages.getPage(LoginPage_DP.class).loginToSalesForce(userName, password);
            hardAssert.assertEquals(pageAssert, true, "Logged into sales force successfully");
            logInfo("Logged into sales force successfully", "Logged into sales force successfully");

            pageAssert = AllPages.getPage(InteractionPage_DP.class).createNewInteraction(interactionCategory, interactionType, locationType, startDate,
                    startTime, endDate);
            hardAssert.assertEquals(pageAssert, true, "New interaction created");
            logInfo("New interaction created", "Created new interaction");

            pageAssert = AllPages.getPage(ProductTopicPage_DP.class).addProductTopic(ahmProduct);
            hardAssert.assertEquals(pageAssert, true, "Added product topic");
            logInfo("Added product topic ", "Added product topic ");

            logInfo("Ending Speaker Program OffSite Evening Scenario", "Ending Speaker Program OffSite Evening Scenario");
        }   catch (Exception e) {
            LOGGER.error("Failed in OffsiteEveningSpeakerProgramAZ"+e);
        }
    }
}
