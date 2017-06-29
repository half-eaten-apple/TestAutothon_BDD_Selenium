package com.epam.scenarios;

import java.util.Map;

import org.testng.annotations.Test;

import com.epam.driver.BaseTest;
import com.epam.pages.AllPages;
import com.epam.pages.DisclosureAgreementPage;
import com.epam.pages.InteractionPage;
import com.epam.pages.LocationPage;
import com.epam.pages.LoginPage;
import com.epam.pages.LogisticsPage;
import com.epam.pages.NewEstimatePage;
import com.epam.pages.NewExpensePage;
import com.epam.pages.ParticipantPage;
import com.epam.pages.ProductTopicPage;
import com.epam.utils.TestDataReader;

/**
 * Created by vsharma on 17-11-2016.
 */
public class EmployeeEducationScenario extends BaseTest{

    @SuppressWarnings({ "unused", "static-access" })
	@Test(enabled = false)
    public void employeeEducationProgram() {
        try {
            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting EmployeeEducation Scenario", "Starting EmployeeEducation Scenario");
            startingMethodPreReq("EmployeeEducation");
            TestDataReader.readScenarioData("EmployeeEducation");
            Map<String, String> treeMap = dataReader.getEntityData("EmployeeEducation", "Data", TestDataReader.currentIterationRow);

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

            logInfo("Ending Employee Education Scenario", "Ending Employee Education Scenario");

        } catch (Exception e) {
            LOGGER.error("Failed in EmployeeEducation scenario"+e);
        }
    }
}
