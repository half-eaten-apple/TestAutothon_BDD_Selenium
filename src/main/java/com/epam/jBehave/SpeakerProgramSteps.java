package com.epam.jBehave;

import com.epam.driver.BaseTest;
import com.epam.pages.*;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.TestDataReader;
import org.jbehave.core.annotations.*;


import java.util.Map;

/**
 * Created by vsharma on 02-11-2016.
 */
public class SpeakerProgramSteps extends BaseTest {
    String logText = "";

    @Given("initialize test data <scenario>")
    public void initializeTestData(@Named("scenario") String scenarioName) throws FrameworkExceptions {
        try {
            startingMethodPreReq(scenarioName);
            TestDataReader dataReader = new TestDataReader();

            if("SpeakerProgram_AZ".equalsIgnoreCase(scenarioName)) {
                logText = "Starting Speaker Program Scenario";
            } else if ("OffsiteEveningSpeakerProgramAZ".equalsIgnoreCase(scenarioName)) {
                logText = "Starting Speaker Program OffSite Evening Scenario";
            } else if ("TeleconferenceAZ".equalsIgnoreCase(scenarioName)) {
                logText = "Starting Speaker Program TeleConference Scenario";
            }
            logInfo(logText, logText);

            TestDataReader.readScenarioData(scenarioName);
            Map<String, String> treeMap = TestDataReader.getEntityData(scenarioName, "Data", TestDataReader.currentIterationRow);
        } catch (Exception FrameworkExceptions) {
            LOGGER.error("Failed to initialize the test data"+FrameworkExceptions);
            throw new FrameworkExceptions(FrameworkExceptions);
        }
    }

    @When("login salesforce")
    public void loginSalesforce() throws FrameworkExceptions {
        try {
            AllPages.getPage(LoginPage.class).loginToSalesForce();
            logInfo("Logged into successfully", "Login successfully");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to Login to Salesforce"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("user should be successfully logged in")
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

    @Then("create new interaction")
    public void createNewInteraction() throws FrameworkExceptions {
        try {
            AllPages.getPage(InteractionPage.class).createNewInteraction();
            logInfo("New interaction created", "Created new interaction");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to create new interaction"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("check interaction is created")
    public void validateInteractionCreated() throws FrameworkExceptions {
        try {
            AllPages.getPage(InteractionPage.class).validateInteractionCreated();
            logInfo("Validated interaction", "Validated interaction");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to validate the interaction"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("add new product topic")
    public void addProductTopic() throws FrameworkExceptions {
        try {
            AllPages.getPage(ProductTopicPage.class).addProductTopic();
            logInfo("Added product topic", "Added product topic");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to add new product"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }
    @Then("product topics should be added")
    public void verifyProductTopicIsAdded() throws FrameworkExceptions {
        try {
            AllPages.getPage(ProductTopicPage.class).verifyProductTopicIsAdded();
            logInfo("Validated new product", "Validated new product");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify the product"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("add new location")
    public void addNewLocation() throws FrameworkExceptions {
        try {
            AllPages.getPage(LocationPage.class).addNewLocation();
            logInfo("Added new location", "Added new location");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to add the location"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("location should be added")
    public void verifyLocationIsAdded() throws FrameworkExceptions{
        try {
            AllPages.getPage(LocationPage.class).verifyLocationIsAdded();
            logInfo("Validated new location", "Validated new location");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify the location"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("add new participant")
    public void createNewParticipant() throws FrameworkExceptions {
        try {
            AllPages.getPage(ParticipantPage.class).createNewParticipant();
            logInfo("Created new participant", "Created new participant");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to create new participant"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("participant should be added")
    public void verifyParticipantIsAdded() throws FrameworkExceptions{
        try {
            AllPages.getPage(ParticipantPage.class).verifyParticipantIsAdded();
            logInfo("Validated new participant", "Validated new participant");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify that the participant is added"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("add new logistics")
    public void createNewLogistics() throws FrameworkExceptions {
        try {
            AllPages.getPage(LogisticsPage.class).createNewLogistics();
            logInfo("Created new logistics.", "Created new logistics");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to create new logistics"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("logistics should be added")
    public void verifyLogisticIsAdded() throws FrameworkExceptions{
        try {
            AllPages.getPage(LogisticsPage.class).verifyLogisticIsAdded();
            logInfo("Validated new logistics", "Validated new logistics");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to validate the logistics"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }
    @Then("add new estimate")
    public void createNewEstimate() throws FrameworkExceptions {
        try {
            AllPages.getPage(NewEstimatePage.class).createNewEstimate();
            logInfo("Created new estimate", "New estimate created");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to create new estimates"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("estimate should be added")
    public void verifyEstimateIsAdded() throws FrameworkExceptions{
        try {
            AllPages.getPage(NewEstimatePage.class).verifyEstimateIsAdded();
            logInfo("Validated new estimates", "Validated new estimate");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to verify that the estimate is added"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("change interaction status to accepted")
    public void interactionStatusToAccepted() throws FrameworkExceptions {
        try {
            AllPages.getPage(InteractionPage.class).interactionStatusAccepted();
            logInfo("Updated the interaction status to accepted.", "Updated interaction status to accepted");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to update the interaction status to accepted"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("confirm location")
    public void confirmLocation() throws FrameworkExceptions {
        try {
            AllPages.getPage(LocationPage.class).locationConfirm();
            logInfo("Location confirmed", "Confirmed the location");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to confirm the location"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("change interaction status to set-up complete")
    public void interactionStatusToCompleted() throws FrameworkExceptions {
        try {
            AllPages.getPage(InteractionPage.class).interactionStatusComplete();
            logInfo("Updated the interaction status to completed", "Updated the interaction status to completed");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to update the interaction Status to completed"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("interaction status should be close-out required")
    public void validateInteractionIsReadyTobeClosed() throws FrameworkExceptions{
        try {
            AllPages.getPage(InteractionPage.class).validateInteractionIsReadyTobeClosed();
            logInfo("updated interaction status to close-out required", "updated interaction status to close-out required");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to update the interaction status to close-out required"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("pay expenses")
    public void payExpenses() throws FrameworkExceptions {
        try {
            AllPages.getPage(NewExpensePage.class).expensesPaid();
            logInfo("Paid expenses successfully", "Expense paid successfully");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to pay expenses"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("add disclosure agreement")
    public void addDisclosureAgreement() throws FrameworkExceptions {
        try {
            AllPages.getPage(DisclosureAgreementPage.class).addDisclosureAgreement();
            logInfo("Added disclosure agreement successfully", "Added disclosure agreement successfully");
            logInfo("Ending Speaker Program "+ logText +" Scenario", "Ending Speaker Program "+ logText +" Scenario");
        } catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to add disclosure agreement"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("interaction status should be complete")
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