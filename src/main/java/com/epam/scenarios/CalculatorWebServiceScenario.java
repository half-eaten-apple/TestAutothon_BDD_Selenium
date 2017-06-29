package com.epam.scenarios;

import java.io.FileInputStream;

import com.epam.driver.BaseTest;
import com.epam.utils.Config;
import com.epam.utils.TestDataFieldNames;
import com.epam.utils.TestDataReader;
import com.epam.utils.WebServices;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;


/**
 * Created by malkhama on 23-11-2016.
 */
public class CalculatorWebServiceScenario extends BaseTest {
    WebServices webServices = new WebServices();
    TestDataFieldNames TestDataFieldNames = new TestDataFieldNames();
    TestDataReader dataReader = new TestDataReader();
    @Test(priority = 1,enabled=true)
    public void AddTwoIntegers(){
        try {
            logInfo("Starting AddTwoIntegersWebServiceScenario", "Starting AddTwoIntegersWebServiceScenario");
            TestDataReader.readScenarioData("AddTwoIntegersWebServiceScenario");
            dataReader.getEntityData("AddTwoIntegersWebServiceScenario", "Data", TestDataReader.currentIterationRow);
            TestDataFieldNames.readVariables();
            FileInputStream inputFile = new FileInputStream(Config.getConfig().getConfigProperty("RequestFolderPath")+TestDataFieldNames.RequestFileName);

            String ServiceRequest = IOUtils.toString(inputFile);
            inputFile.close();
            webServices.getConnection();
            webServices.loadRequest(TestDataFieldNames.EndPointURL, TestDataFieldNames.Appenders, TestDataFieldNames.RequestType,
                    TestDataFieldNames.RequestHeaders, ServiceRequest);
            webServices.sendRequest();
            String ServiceResponse = webServices.getResponse();
            System.out.println(ServiceResponse);

            webServices.validateXMLResponse(ServiceResponse, TestDataFieldNames.ValidateResponse);
            webServices.closeConnection();
        } catch (Exception e) {
            LOGGER.error("Failed in AddTwoIntegersWebServiceScenario "+e);
        }
    }

    @Test(priority = 2,enabled=true)
    public void SubtractIntegers(){
        try {
            logInfo("Starting SubtractTwoIntegersWebServiceScenario", "Starting SubtractTwoIntegersWebServiceScenario");
            TestDataReader.readScenarioData("SubtractTwoIntegersWebServiceScenario");
            dataReader.getEntityData("SubtractTwoIntegersWebServiceScenario", "Data", TestDataReader.currentIterationRow);
            TestDataFieldNames.readVariables();
            FileInputStream inputFile = new FileInputStream(Config.getConfig().getConfigProperty("RequestFolderPath")+TestDataFieldNames.RequestFileName);

            String ServiceRequest = IOUtils.toString(inputFile);
            inputFile.close();
            webServices.getConnection();
            webServices.loadRequest(TestDataFieldNames.EndPointURL, TestDataFieldNames.Appenders, TestDataFieldNames.RequestType,
                    TestDataFieldNames.RequestHeaders, ServiceRequest);
            webServices.sendRequest();
            String ServiceResponse = webServices.getResponse();

            webServices.validateXMLResponse(ServiceResponse, TestDataFieldNames.ValidateResponse);
            webServices.closeConnection();

        } catch (Exception e) {
            LOGGER.error("Failed in SubtractTwoIntegersWebServiceScenario "+e);
        }
    }
}
