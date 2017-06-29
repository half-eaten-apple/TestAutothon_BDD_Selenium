package com.epam.scenarios;

import com.epam.driver.BaseTest;
import com.epam.utils.TestDataFieldNames;
import com.epam.utils.TestDataReader;
import com.epam.utils.WebServices;
import org.testng.annotations.Test;

/**
 * Created by malkhama on 28-11-2016.
 */
public class VerifyCapitalCityScenario extends BaseTest {
    WebServices webServices = new WebServices();
    TestDataFieldNames TestDataFieldNames = new TestDataFieldNames();
    TestDataReader dataReader = new TestDataReader();
    @Test(enabled=false)
    public void VerifyCapitalCityScenarioofAssam(){
        try {
            logInfo("Starting VerifyCapitalCityScenarioofAssam", "Starting VerifyCapitalCityScenarioofAssam");
            TestDataReader.readScenarioData("VerifyCapitalCityScenarioofAssam");
            dataReader.getEntityData("VerifyCapitalCityScenarioofAssam", "Data", TestDataReader.currentIterationRow);
            TestDataFieldNames.readVariables();
            webServices.getRestConnection(TestDataFieldNames.EndPointURL);
            webServices.sendRestRequest(TestDataFieldNames.RequestType, TestDataFieldNames.ResponseType);
            webServices.getRestResponseAndValidate(TestDataFieldNames.ResponseType,TestDataFieldNames.ValidateResponse);
            webServices.closeRestConnection();
        } catch (Exception e) {
            LOGGER.error("Failed in VerifyCapitalCityScenarioofAssam "+e);
        }
    }
}
