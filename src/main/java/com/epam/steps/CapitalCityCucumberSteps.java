package com.epam.steps;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.epam.driver.BaseTest;
import com.epam.utils.*;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.apache.http.client.methods.CloseableHttpResponse;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

/**
 * Created by malkhama on 24-11-2016.
 */
public class CapitalCityCucumberSteps extends BaseTest {
	private CloseableHttpResponse response = null;
    WebServices webServices = new WebServices();
    private static String baseURL = "";
	 WebTarget webTarget = null;
	 String TargetURL = "";
	 Response RestResponse =null;
    String ServiceRequest;
    Client client = ClientBuilder.newClient();

        @When("^user gets Rest connection$")
        public void getRestConnection() throws FrameworkExceptions {
            try{
            	
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

    @And("^post Rest request$")
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
    @Then("^get response from the Rest service and validate the result$")
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

    @Then("^close Rest connection$")
    public void closeConnectionForRest() throws FrameworkExceptions{
        try{
        	client.close();
			LOGGER.info("Rest connection is closed.");
        }catch (Exception e)
        {
            LOGGER.error("Failed to close Rest connection " + e );
            throw new FrameworkExceptions("Failed in closeRestConnection() method"+e);
        }
    }
}
