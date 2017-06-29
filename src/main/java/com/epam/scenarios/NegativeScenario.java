package com.epam.scenarios;

import com.epam.driver.BaseTest;
import com.epam.pages.*;
import com.epam.utils.Constants;
import com.epam.utils.TestDataReader;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by vsharma on 17-11-2016.
 */
public class NegativeScenario extends BaseTest {

    Map<String, String> treeMap;
    @Test(priority = 1, enabled = false)
    public void employeeEducationMandatoryFields() {
        try {
            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting EmployeeEducationMandatoryFields Scenarios", "Starting EmployeeEducationMandatoryFields Scenarios");
            startingMethodPreReq(Constants.NEGATIVESCENARIO);
            dataReader.readScenarioData(Constants.NEGATIVESCENARIO);
            treeMap = dataReader.getEntityData(Constants.NEGATIVESCENARIO, "Data", TestDataReader.currentIterationRow);

            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into successfully", "Login successfully");

            AllPages.getPage(InteractionPage.class).createInteractionWithMissingDataAndVerifyErrMessage();
            logInfo("createInteractionWithMissingDataAndVerifyErrMessage!!!!","createInteractionWithMissingDataAndVerifyErrMessage!!!!");

            logInfo("Ending EmployeeEducationMandatoryFields Scenario", "Ending  EmployeeEducationMandatoryFields Scenario");
        }
        catch (Exception e) {
            LOGGER.error("Failed in employeeEducationMandatoryFields"+e);
        }
    }

    @Test(priority = 2, enabled = false)
    public void multipleProductTopicScenario() {
        try {
            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting MultipleInteractionProductTopicsTestScenario Scenarios", "Starting MultipleInteractionProductTopicsTestScenario Scenarios");
            startingMethodPreReq(Constants.NEGATIVESCENARIO);
            dataReader.readScenarioData(Constants.NEGATIVESCENARIO);
            treeMap = dataReader.getEntityData(Constants.NEGATIVESCENARIO, "Data", TestDataReader.currentIterationRow);

            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into Sales Force successfully", "Logged into Sales Force successfully");

            AllPages.getPage(InteractionPage.class).createNewInteraction();
            logInfo("New interaction created", "Created new interaction");

            AllPages.getPage(ProductTopicPage.class).verifyErrorMessageForInteractionProductTopics();
            logInfo("VerifyErrorMessageForInteractionProductTopics", "VerifyErrorMessageForInteractionProductTopics");

            logInfo("Ending  MultipleInteractionProductTopicsTestScenario Scenario", "Ending  MultipleInteractionProductTopicsTestScenario Scenario");

        }
        catch (Exception e) {
            LOGGER.error("Failed in multipleProductTopicScenario"+e);
        }
    }

    @Test(priority = 3, enabled = false)
    public void noProductTopicScenario() {
        try {
            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting NoProductTopicTestScenario Scenarios", "Starting NoProductTopicTestScenario Scenarios");
            startingMethodPreReq(Constants.NEGATIVESCENARIO);
            dataReader.readScenarioData(Constants.NEGATIVESCENARIO);
            treeMap = dataReader.getEntityData(Constants.NEGATIVESCENARIO, "Data", TestDataReader.currentIterationRow);

            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into successfully", "Login successfully");

            AllPages.getPage(InteractionPage.class).createNewInteraction();
            logInfo("New interaction created", "Created new interaction");

            AllPages.getPage(ParticipantPage.class).verifyNoProductTopicIsDisplayed();
            logInfo("verifyNoProductTopicIsDisplayed Created!!!!","verifyNoProductTopicIsDisplayed Created!!!!");

            logInfo("Ending  NoProductTopicTestScenario Scenario", "Ending  NoProductTopicTestScenario Scenario");

        }
        catch (Exception e) {
            LOGGER.error("Failed in noProductTopicScenario"+e);
        }
    }
}
