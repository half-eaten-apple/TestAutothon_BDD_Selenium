package com.epam.jBehave;
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
import org.jbehave.core.annotations.*;
import com.epam.pages.AllPages;

/**
 * Created by malkhama on 24-11-2016.
 */
public class CalculatorServiceSteps extends BaseTest {
    WebServices webServices = new WebServices();
    String ServiceRequest;
    @Given("initialize test data for webservice scenario <scenario>")
    public void initializeTestData(@Named("scenario") String scenarioName) throws FrameworkExceptions {
        try {
            TestDataReader dataReader = new TestDataReader();
            logInfo(scenarioName, scenarioName);
            TestDataReader.readScenarioData(scenarioName);
            Map<String, String> treeMap = TestDataReader.getEntityData(scenarioName, "Data", TestDataReader.currentIterationRow);
            TestDataFieldNames.readVariables();
        } catch (Exception FrameworkExceptions) {
            LOGGER.error("Failed to initialize the test data"+FrameworkExceptions);
            throw new FrameworkExceptions(FrameworkExceptions);
        }
    }
    @When("user gets webservice connection")
    public void getWebServiceConnection() throws FrameworkExceptions {
        try{
            String RequestFilePath = Config.getConfig().getConfigProperty("RequestFolderPath")+ TestDataFieldNames.RequestFileName;
            FileInputStream inputFile = new FileInputStream(RequestFilePath);

            ServiceRequest = IOUtils.toString(inputFile);
            inputFile.close();
            webServices.getConnection();
            System.out.println("Rest Services");
        } catch (Exception frameworkExceptions) {
            LOGGER.error("Failed to establish webservice connection"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @When("post webservice request file")
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

    @Then("get response file and validate the result")
    public void getResponseAndValidate() throws FrameworkExceptions {
        try{
            String ServiceResponse = webServices.getResponse();

            webServices.validateXMLResponse(ServiceResponse, TestDataFieldNames.ValidateResponse);
        } catch (Exception frameworkExceptions) {
            LOGGER.error("Failed to establish webservice connection"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("close webservice connection")
    public void closeConnection() throws FrameworkExceptions {
        try{
            webServices.closeConnection();
        } catch (Exception frameworkExceptions) {
            LOGGER.error("Failed to establish webservice connection"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }
}
