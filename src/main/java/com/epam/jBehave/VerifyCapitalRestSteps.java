package com.epam.jBehave;
import java.io.FileInputStream;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.epam.driver.BaseTest;
import com.epam.utils.*;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.jbehave.core.annotations.*;

/**
 * Created by malkhama on 24-11-2016.
 */
public class VerifyCapitalRestSteps extends BaseTest {
	private CloseableHttpResponse response = null;
	WebServices webServices = new WebServices();
	private static String baseURL = "";
	WebTarget webTarget = null;
	String TargetURL = "";
	Response RestResponse =null;
	String ServiceRequest;
	@Given("initialize test data for REST scenario <scenario>")
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
	@When("user gets Rest connection")
	public void getRestConnection() throws FrameworkExceptions {
        try{
        	Client client = ClientBuilder.newClient();
                   if(Config.getConfig().getConfigProperty(Constants.RESTSERVICESAUTHENTICATION).trim().equalsIgnoreCase("yes")){
                       HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(Config.getConfig().getConfigProperty(Constants.RESTSERVICESUSER),
                               Config.getConfig().getConfigProperty(Constants.RESTSERVICESPASSWORD));
                       client.register(feature);
                   }

                   TargetURL = baseURL + TestDataFieldNames.EndPointURL;

                   webTarget = client.target(TargetURL);
                   LOGGER.info("Rest Client is connected.");
               }catch (Exception e)
               {
                   LOGGER.error("Failed to get Rest connection " + e );
                   throw new FrameworkExceptions("Failed in getRestConnection() method"+e);
               }
    }

	@When("post Rest request")
	 public void sendRestRequest() throws FrameworkExceptions {
	    try{
	        if(TestDataFieldNames.RequestType.equalsIgnoreCase("GET")) {
	            switch (TestDataFieldNames.ResponseType.toUpperCase()) {
	                case "JSON":
	                    RestResponse = webTarget
	                            .request(MediaType.APPLICATION_JSON)
	                            .get();
	                    break;
	                case "XML":
	                    RestResponse = webTarget
	                            .request(MediaType.APPLICATION_XML)
	                            .get();
	                    break;
	            }
	        }
	        if(RestResponse.getStatus() == 200){
	            LOGGER.info("Rest Request: "+TargetURL+" successfully posted.");}
	        else {
	            LOGGER.error("Failed to post Rest Request: "+TargetURL+". Error code "+RestResponse.getStatus()+", "+RestResponse.getStatusInfo());
	        }

	    }catch (Exception e)
	    {
	        LOGGER.error("Failed to post Rest Request " + e );
	        throw new FrameworkExceptions("Failed in sendRestRequest() method"+e);
	    }
	}

	@Then("get response from the Rest service and validate the result")
	public void getRestResponseAndValidate() throws FrameworkExceptions{

        String restResponse = RestResponse.readEntity(String.class);
        try{
            if(TestDataFieldNames.ResponseType.toUpperCase().equalsIgnoreCase("JSON")) {
                LOGGER.info("JSON response from the rest:\n"+ restResponse);
                webServices.validateJSONResponse(restResponse,TestDataFieldNames.ValidateResponse);
            }
            else
            {
                LOGGER.info("The output of the request is:" + response);
                webServices.validateXMLResponse(restResponse,TestDataFieldNames.ValidateResponse);
            }
        }
        catch (Exception e){
            LOGGER.error("Failed to validate Rest Response " + e );
            throw new FrameworkExceptions("Failed in getRestResponseAndValidate() method"+e);
        }
    }


	@Then("close Rest connection")
	public void closeConnectionForRest() throws FrameworkExceptions{
        try{
        	webServices.closeRestConnection();
        }catch (Exception e)
        {
            LOGGER.error("Failed to close Rest connection " + e );
            throw new FrameworkExceptions("Failed in closeRestConnection() method"+e);
        }
    }
}

