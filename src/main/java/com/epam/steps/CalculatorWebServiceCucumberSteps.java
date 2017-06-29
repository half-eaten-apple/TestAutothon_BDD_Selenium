package com.epam.steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import com.epam.driver.BaseTest;
import com.epam.pages.ExcelSheets;
import com.epam.pages.FeedsWorkBenchQueryPage;
import com.epam.pages.GetOutBoundFileName;
import com.epam.utils.*;

import org.apache.commons.io.IOUtils;

import com.epam.pages.AllPages;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by malkhama on 24-11-2016.
 */
public class CalculatorWebServiceCucumberSteps extends BaseTest {
    WebServices webServices = new WebServices();
    String ServiceRequest;

        @When("^user gets webservice connection$")
        public void getWebServiceConnection() throws FrameworkExceptions {
            try{
                String RequestFilePath = Config.getConfig().getConfigProperty("RequestFolderPath")+ TestDataFieldNames.RequestFileName;
                FileInputStream inputFile = new FileInputStream(RequestFilePath);

                ServiceRequest = IOUtils.toString(inputFile);
                inputFile.close();
                webServices.getConnection();
            } catch (Exception frameworkExceptions) {
                LOGGER.error("Failed to establish webservice connection"+frameworkExceptions);
                throw new FrameworkExceptions(frameworkExceptions);
            }
        }

    @When("^post webservice request file$")
    public void postRequestFileAndValidate() throws FrameworkExceptions {
        try{
            webServices.loadRequest(TestDataFieldNames.EndPointURL, TestDataFieldNames.Appenders, TestDataFieldNames.RequestType,
                    TestDataFieldNames.RequestHeaders, ServiceRequest);
            webServices.sendRequest();
        } catch (Exception frameworkExceptions) {
            LOGGER.error("Failed to establish webservice connection"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^get response file and validate the result$")
    public void getResponseAndValidate() throws FrameworkExceptions {
        try{
            String ServiceResponse = webServices.getResponse();
            webServices.validateXMLResponse(ServiceResponse, TestDataFieldNames.ValidateResponse);
        } catch (Exception frameworkExceptions) {
            LOGGER.error("Failed to establish webservice connection"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("^close webservice connection$")
    public void closeConnection() throws FrameworkExceptions {
        try{
            webServices.closeConnection();
        } catch (Exception frameworkExceptions) {
            LOGGER.error("Failed to establish webservice connection"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }
}
