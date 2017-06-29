package com.epam.steps;

import com.epam.driver.BaseTest;
import com.epam.pages.*;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.TestDataFieldNames;
import com.epam.utils.TestDataReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.Map;

/**
 * Created by vsharma on 02-11-2016.
 */
public class SpeakerProgramSteps extends BaseTest {

	TestDataReader dataReader = new TestDataReader();
	Map<String, String> treeMap;

    @Given("^initialize test data (.*)$")
    public void initialize_test_data(String scenarioName) throws FrameworkExceptions {
        try {
            String logText = "";
            if("AddTwoIntegersWebServiceScenario".equalsIgnoreCase(scenarioName)||"SubtractTwoIntegersWebServiceScenario".equalsIgnoreCase(scenarioName)||"VerifyCapitalCityScenarioofAssam".equalsIgnoreCase(scenarioName)){
                logText = "Starting Web service Scenario";
            }else{
                startingMethodPreReq(scenarioName);
                if("SpeakerProgram_AZ".equalsIgnoreCase(scenarioName)) {
                    logText = "Starting Speaker Program Scenario";
                } else if ("OffsiteEveningSpeakerProgramAZ".equalsIgnoreCase(scenarioName)) {
                    logText = "Starting Speaker Program OffSite Evening Scenario";
                } else if ("TeleconferenceAZ".equalsIgnoreCase(scenarioName)) {
                    logText = "Starting Speaker Program TeleConference Scenario";
                } else if ("RegionalExhibit".equalsIgnoreCase(scenarioName)) {
                    logText = "Starting Regional Exhibit Scenario";
                } else if ("EmployeeEducation".equalsIgnoreCase(scenarioName)) {
                    logText = "Starting Employee Education Scenario";
                }
            }
            logInfo(logText, logText);

            TestDataReader.readScenarioData(scenarioName);
            treeMap = TestDataReader.getEntityData(scenarioName, "Data", TestDataReader.currentIterationRow);
            TestDataFieldNames.readVariables();
        } catch (Exception FrameworkExceptions) {
            LOGGER.error("Failed to initialize the test data"+FrameworkExceptions);
            throw new FrameworkExceptions(FrameworkExceptions);
        }
    }

    @Given("^login salesforce$")
    public void login_salesforce() throws FrameworkExceptions {
        try {

            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into successfully", "Login successfully");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to Login to Salesforce"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^user should successfully logged in$")
    public void validate_login() throws FrameworkExceptions{
        try{
            AllPages.getPage(InteractionPage.class).validateUserLogin();
            logInfo("User logged in successfully", "User logged in successfully");
        }
        catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("User failed to login"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^create new interaction$")
    public void create_new_interaction() throws FrameworkExceptions {
        try {
            AllPages.getPage(InteractionPage.class).createNewInteraction();
            logInfo("New interaction created", "Created new interaction");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to create new interaction"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^check interaction is created$")
    public void validateInteractionCreated() throws FrameworkExceptions {
        try {
            AllPages.getPage(InteractionPage.class).validateInteractionCreated();
            logInfo("Validated interaction", "Validated interaction");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to validate the interaction"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^add new product topic$")
    public void add_product() throws FrameworkExceptions {
        try {
            AllPages.getPage(ProductTopicPage.class).addProductTopic();
            logInfo("Added new product", "Added new product");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to add new product"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^product topics should be added$")
    public void verifyProductTopicIsAdded() throws FrameworkExceptions {
        try {
            AllPages.getPage(ProductTopicPage.class).verifyProductTopicIsAdded();
            logInfo("Validated new product", "Validated new product");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify the product"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^add new location$")
    public void add_location() throws FrameworkExceptions{
        try {
            AllPages.getPage(LocationPage.class).addNewLocation();
            logInfo("added new location", "added new location");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to add the location"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^location should be added$")
    public void verifyLocationIsAdded() throws FrameworkExceptions{
        try {
            AllPages.getPage(LocationPage.class).verifyLocationIsAdded();
            logInfo("Validated new location", "Validated new location");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify the location"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^add new participant$")
    public void add_participant() throws FrameworkExceptions{
        try {
            AllPages.getPage(ParticipantPage.class).createNewParticipant();
            logInfo("added new participant", "added new participant");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to create new participant"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^participant should be added$")
    public void verifyParticipantIsAdded() throws FrameworkExceptions{
        try {
            AllPages.getPage(ParticipantPage.class).verifyParticipantIsAdded();
            logInfo("Validated new participant", "Validated new participant");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify that the participant is added"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^add new logistics$")
    public void add_logistic() throws FrameworkExceptions{
        try {
            AllPages.getPage(LogisticsPage.class).createNewLogistics();
            logInfo("added new logistics", "added new logistics");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to create new logistics"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }


    @Then("^logistics should be added$")
    public void verifyLogisticIsAdded() throws FrameworkExceptions{
        try {
            AllPages.getPage(LogisticsPage.class).verifyLogisticIsAdded();
            logInfo("Validated new logistics", "Validated new logistics");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to validate the logistics"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^add new estimate$")
    public void add_estimate() throws FrameworkExceptions{
        try {
            AllPages.getPage(NewEstimatePage.class).createNewEstimate();
            logInfo("added new estimates", "added new estimate");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to create new estimates"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^estimate should be added$")
    public void verifyEstimateIsAdded() throws FrameworkExceptions{
        try {
            AllPages.getPage(NewEstimatePage.class).verifyEstimateIsAdded();
            logInfo("Validated new estimates", "Validated new estimate");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify that the estimate is added"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^change interaction status to accepted$")
    public void change_interaction_status() throws FrameworkExceptions{
        try {
            AllPages.getPage(InteractionPage.class).interactionStatusAccepted();
            logInfo("updated interaction status to accepted", "updated interaction status to accepted");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to update the interaction status to accepted"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^confirm location$")
    public void change_location_status() throws FrameworkExceptions{
        try {
            AllPages.getPage(LocationPage.class).locationConfirm();
            logInfo("updated location status to confirm", "updated location status to confirm");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to confirm the location"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^change interaction status to set-up complete")
    public void change_interaction_status_to_complete() throws FrameworkExceptions{
        try {
            AllPages.getPage(InteractionPage.class).interactionStatusComplete();
            logInfo("updated interaction status to complete ", " updated interaction status to complete");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to update the interaction Status to completed"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^interaction status should be close-out required$")
    public void validateInteractionIsReadyTobeClosed() throws FrameworkExceptions{
        try {
            AllPages.getPage(InteractionPage.class).validateInteractionIsReadyTobeClosed();
            logInfo("updated interaction status to  complete", "updated  interaction status to complete");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to update the interaction status to close-out required"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^pay expenses$")
    public void pay_expenses() throws FrameworkExceptions{
        try {
            AllPages.getPage(NewExpensePage.class).expensesPaid();
            logInfo("new expenses paid", "new expenses paid");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to pay expenses"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Given("^add disclosure agreement$")
    public void add_disclosure() throws FrameworkExceptions{
        try {
            AllPages.getPage(DisclosureAgreementPage.class).addDisclosureAgreement();
            logInfo("added disclosure agreement", "added disclosure agreement");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to add disclosure agreement"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^interaction status should be complete$")
    public void validateInteractionIsComplete() throws FrameworkExceptions{
        try {
            AllPages.getPage(DisclosureAgreementPage.class).validateInteractionIsComplete();
            logInfo("interaction completed", "interaction completed");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify interaction status"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("create interaction & verify error message for missing mandatory fields")
    public void verifyErrorMessageForInteractionProductTopics() throws FrameworkExceptions{
        try {
            AllPages.getPage(InteractionPage.class).createInteractionWithMissingDataAndVerifyErrMessage();
            logInfo("Verify Error Message For Missing Data", "Verify Error Message For Missing Data");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify the Error message for Missing Data"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }
}
