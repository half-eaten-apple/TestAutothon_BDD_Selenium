package com.epam.utils;

import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import org.w3c.dom.Document;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



/**
 * The Class WebServices. 
 */
public class WebServices {

	/** The web services. */
	private static WebServices webServices = null;

	/** The Constant LOGGER. */
	public static Logger LOGGER = LoggerFactory.getLogger(WebServices.class);

	/** The httpclient. */
	private CloseableHttpClient httpclient = null;

	/** The response. */
	private CloseableHttpResponse response = null;

	/** The ecm util. */
	private static WebServiceUtil webserviceUtil = null;

	/**  all headers *. */
	private static Header[] authenticationHeaders = null;

	/** Authentication URL *. */
	private static String authenticationURL = "";

	/** Base url*. */
	private static String baseURL = "";

	private Client client = null;
	WebTarget webTarget = null;
	String TargetURL = "";
	Response RestResponse =null;

	/**
	 * Instantiates a new ecm.
	 */
	public WebServices() {
		webserviceUtil = new WebServiceUtil();
	}

	/**
	 * Gets the single instance of ECM.
	 * 
	 * @return single instance of ECM
	 */
	public static WebServices getInstance() {
		if (null == webServices) {
			webServices = new WebServices();
		}
		return webServices;
	}



	/**
	 * Sets the authentication url.
	 *
	 * @param url the new authentication url
	 */
	public void setAuthenticationURL(String url){
		this.authenticationURL = url;
	}

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 * @throws FrameworkExceptions
	 */
	public CloseableHttpClient getConnection() throws FrameworkExceptions {
        try {
		LOGGER.info("Executing getConnection()");
		if (null == httpclient) {
			String hostname = Config.getConfig().getConfigProperty(Constants.WEBSERVICEHOSTNAME);
			String port = Config.getConfig().getConfigProperty(Constants.WEBSERVICEPORT);
			if(Config.getConfig().getConfigProperty(Constants.WEBSERVICEAUTHENTICATION).trim().equalsIgnoreCase("yes")){
				String userKey = Config.getConfig().getConfigProperty(Constants.WEBSERVICEUSERKEY);
				String passwordKey = Config.getConfig().getConfigProperty(Constants.WEBSERVICEPASSWORDKEY);
				String userName = Config.getConfig().getConfigProperty(Constants.WEBSERVICEUSER);
				String password = Config.getConfig().getConfigProperty(Constants.WEBSERVICEPASSWORD);
				authenticationURL = baseURL+authenticationURL;
				LOGGER.info("Creating the object to the http client with the connection url:"+authenticationURL);
				httpclient = getHttpClient(authenticationURL, userKey, passwordKey, userName, password);
			}else{
				httpclient=HttpClients.createDefault();
			}
		}
        LOGGER.info("httpclient object created to connect to the connection url:"+authenticationURL);
        } catch (Exception e) {
            LOGGER.error("Failed in getConnection() method" + e);
            throw new FrameworkExceptions("Failed in getConnection() method"+e);
        }
		return httpclient;
	}

	/**
	 * Gets the http client.
	 *
	 * @param url the url
	 * @param userkey the userkey
	 * @param pwdkey the pwdkey
	 * @param uname the uname
	 * @param pwd the pwd
	 * @return the http client
	 * @throws FrameworkExceptions
	 */
	private CloseableHttpClient getHttpClient(String url, String userkey, String pwdkey, String uname, String pwd) throws FrameworkExceptions {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			LOGGER.info("Executing getHttpClient()");

			HttpPost post = new HttpPost(url);
			post.addHeader("content-type", "application/x-www-form-urlencoded");
			List<NameValuePair> userPwdValues = new ArrayList<NameValuePair>();
			userPwdValues.add(new BasicNameValuePair(userkey, uname));
			userPwdValues.add(new BasicNameValuePair(pwdkey, pwd));
			post.setEntity(new UrlEncodedFormEntity(userPwdValues, "UTF-8"));
			response = client.execute(post);
			authenticationHeaders = response.getAllHeaders();
			webserviceUtil.setResponse(response);
			LOGGER.info("Post request created succesfully.");
			LOGGER.info("The response status is:" + response.getStatusLine());

		} catch (Exception e) {
			LOGGER.warn("Exception occurred while getting the connection. Exception message is:"
					+ e.getMessage());
			throw new FrameworkExceptions(
					"Exception occurred while getting the connection. Exception message is:"
							+ e);

		}

		return client;

	}


	/**
	 * Load request.
	 *
	 * @param urlLoad the url load
	 * @param appenders            the appenders
	 * @param requestType            the request type
	 * @throws FrameworkExceptions
	 */

	public void loadRequest(String urlLoad, String appenders, String requestType) throws FrameworkExceptions {
		loadRequest(urlLoad, appenders, requestType, null, null);
	}


	/**
	 * Load request.
	 *
	 * @param urlLoad the url load
	 * @param appenders            the appenders
	 * @param requestType            the request type
	 * @param requestHeaders the request headers
	 * @throws FrameworkExceptions
	 */

	public void loadRequest(String urlLoad, String appenders, String requestType, String requestHeaders) throws FrameworkExceptions {
		loadRequest(urlLoad, appenders, requestType, requestHeaders, null);
	}

	/**
	 * Load request.
	 *
	 * @param urlLoad the url load
	 * @param appenders            the appenders
	 * @param requestType            the request type
	 * @param requestHeaders the request headers
	 * @param requestBody the request body
	 * @throws FrameworkExceptions
	 */

	public void loadRequest(String urlLoad, String appenders, String requestType, String requestHeaders, String requestBody) throws FrameworkExceptions{
		urlLoad=baseURL+urlLoad;
		LOGGER.info("Execuing loadRequest() with the parameters: url: " + urlLoad
				+ "; appenders: " + appenders + "; requestType: " + requestType +"; requestHeaders: " + requestHeaders+"; requestBody: " + requestBody );
		webserviceUtil.setRequest(null);
		webserviceUtil.setResponse(null);
		try {
			String url = URLEncoder.encode(urlLoad, "UTF-8");
			String resultUrl = URLDecoder.decode(url, "UTF-8");
			if(!appenders.isEmpty()){
				String[] params = appenders.split("&");
				for (String param : params) {
					String[] requiredStrings = param.trim().split("=");
					if (resultUrl.contains(requiredStrings[0].trim())) {
						resultUrl = resultUrl.replace(
								requiredStrings[0].trim(),
								requiredStrings[1].trim());
					}
				}
			}
			if (requestType.equalsIgnoreCase("get")) {
				constructGetRequest(resultUrl, requestHeaders);
			} else {
				constructPostRequest(resultUrl, requestHeaders, requestBody);
			}
		} catch (Exception e) {
			LOGGER.warn("Exception occurred while loading the loadRequest(). Exception is:"
					+ e.getMessage());
			throw new FrameworkExceptions("Exception occurred while loading the loadRequest(). Exception is:"
					+ e);
		}
	}


	/**
	 * Construct get request.
	 *
	 * @param url the url
	 * @param requestHeaders the request headers
	 * @throws FrameworkExceptions
	 */
	public void constructGetRequest(String url, String requestHeaders)throws FrameworkExceptions{
		HttpGet get = new HttpGet(url);
		if(null!=authenticationHeaders){
		for(Header h:authenticationHeaders){
			if(h.getName().equalsIgnoreCase("Authorization")){
				get.addHeader(h);
			}
		}
		}
		if(null != requestHeaders){
			String[] allHeaders = requestHeaders.split("\n");
			for (String header : allHeaders) {
				get.addHeader(header.split(":")[0], header.split(":")[1]);
			}
		}
		webserviceUtil.setRequest(get);
	}

	/**
	 * Construct post request.
	 *
	 * @param url the url
	 * @param requestHeaders the request headers
	 * @param requestBody the request body
	 * @throws FrameworkExceptions
	 */
	public void constructPostRequest(String url, String requestHeaders, String requestBody)
                throws FrameworkExceptions{
        try {
            HttpPost post = new HttpPost(url);
            if (null != authenticationHeaders) {
                for (Header h : authenticationHeaders) {
                    if (h.getName().equalsIgnoreCase("Authorization")) {
                        post.addHeader(h);

                    }
                }
            }
            if (null != requestHeaders) {
                String[] allHeaders = requestHeaders.split("\n");
                for (String header : allHeaders) {
                    post.addHeader(header.split(":")[0], header.substring(header.indexOf(":") + 1, header.length()).trim());
                }
            }
            if (null != requestBody) {
                post.setEntity(new StringEntity(requestBody));
            }
            webserviceUtil.setRequest(post);
        }
        catch (Exception e){
            LOGGER.error("Exception occurred while executing the constructPostRequest() method. Exception is:"
                    + e.getMessage());
            throw new FrameworkExceptions("Exception occurred while executing the constructPostRequest() method. Exception is:"
                    + e);
        }
	}



	/**
	 * Send request.
	 * 
	 * @throws FrameworkExceptions
	 */
	public void sendRequest() throws FrameworkExceptions {
		LOGGER.info("Executing the sendRequest()"+webserviceUtil.getRequest().getRequestLine());
		try {
			webserviceUtil.setResponse(getConnection().execute(webserviceUtil.getRequest()));
		} catch (Exception e) {
			LOGGER.warn("Exception occurred while executing the sendRequest(). Exception is:"
					+ e.getMessage());
			throw new FrameworkExceptions(
					"Exception occurred while executing the sendRequest(). Exception is:"
							+ e);
		}
	}

	/**
	 * Gets the reponse.
	 * 
	 * @return the reponse
	 * @throws FrameworkExceptions
	 */
	public String getResponse() throws FrameworkExceptions {
		String response = "";
		try {
			LOGGER.info("Executing getReponse()");
			String status = getResponseStatus();
			response = EntityUtils.toString(webserviceUtil.getResponse()
					.getEntity(), "UTF-8");
			if (!status.contains("200")) {
                LOGGER.error("Error occurred while getting the response: server status message is: "+status+". Response: "+response);
				throw new FrameworkExceptions("Error occurred while getting the response: server status message is: "+status+". Response: "+response);
			} 

			LOGGER.info("The output of the request is:" + response);

		} catch (Exception e) {
			LOGGER.warn("Exception occurred while executing the getReponse(). Exception is:"
					+ e.getMessage());
			throw new FrameworkExceptions(
					"Exception occurred while executing the getReponse(). Exception is:"
							+ e);
		}
		return response;
	}


	/**
	 * Gets the reponse status.
	 * 
	 * @return the reponse status
	 * @throws FrameworkExceptions
	 */
	private String getResponseStatus() throws FrameworkExceptions {
		String responseStatus = "";
		try {
			LOGGER.info("Executing getResponseStatus()");
			responseStatus = webserviceUtil.getResponse().getStatusLine().toString();
			LOGGER.info("The output of the request is:" + responseStatus);
		} catch (Exception e) {
			LOGGER.warn("Exception occurred while exeucting the getResponseStatus(). Exception is:"
					+ e.getMessage()+ " and the response status is "+ webserviceUtil.getResponse().getStatusLine().toString());
			throw new FrameworkExceptions(
					"Exception occurred while exeucting the getResponseStatus(). Exception is:"
							+ e + " and the response status is " + webserviceUtil.getResponse().getStatusLine().toString());
		}
		return responseStatus;
	}

	/**
	 * Close connection.
	 * 
	 * @throws FrameworkExceptions
	 */
	public void closeConnection() throws FrameworkExceptions {
		try {
			LOGGER.info("Executing closeConnection()");
			getConnection().close();
            httpclient = null;
		} catch (Exception e) {
			LOGGER.warn("Exception occurred while closing the HTTP Client");
			throw new FrameworkExceptions(
					"Exception occurred while closing the HTTP Client"+e);
		}
	}



	public void validateXMLResponse(String Response, String xmlValidationExpression) throws FrameworkExceptions{
		try{
			String CurrentValidation, XPath, ExpectedValue;
			DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = newDocumentBuilder.parse(new ByteArrayInputStream(Response.getBytes()));

			XPath xPath =  XPathFactory.newInstance().newXPath();
			for(int noofvalidations=0;noofvalidations<xmlValidationExpression.split(";").length; noofvalidations++)
			{
				CurrentValidation = xmlValidationExpression.split(";")[noofvalidations];
				XPath = CurrentValidation.split("\\|")[0];
				ExpectedValue = CurrentValidation.split("\\|")[1];
				String RetrievedValue = (String)xPath.compile(XPath).evaluate(doc, XPathConstants.STRING);
				if(ExpectedValue.equalsIgnoreCase(RetrievedValue))
				{
					LOGGER.info("Response XML validated successfully. Value "+ExpectedValue+" is present at "+XPath+" in response file.");
				}
				else{
					LOGGER.error("Response XML validation failed. Value "+ExpectedValue+" is not present at "+XPath+" in response file. Retrieved value: "+RetrievedValue);
				}

			}
		}catch(Exception e){
			LOGGER.error("Failed to validate Response XML " + e );
			throw new FrameworkExceptions("Failed in validateXMLResponse() method"+e);
		}
	}

	public void getRestConnection(String targetURL) throws FrameworkExceptions{
		try{

			client = ClientBuilder.newClient();
			if(Config.getConfig().getConfigProperty(Constants.RESTSERVICESAUTHENTICATION).trim().equalsIgnoreCase("yes")){
				HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(Config.getConfig().getConfigProperty(Constants.RESTSERVICESUSER),
						Config.getConfig().getConfigProperty(Constants.RESTSERVICESPASSWORD));
				client.register(feature);
			}

			TargetURL = baseURL + targetURL;

			webTarget = client.target(TargetURL);
			LOGGER.info("Rest Client is connected.");
		}catch (Exception e)
		{
			LOGGER.error("Failed to get Rest connection " + e );
			throw new FrameworkExceptions("Failed in getRestConnection() method"+e);
		}
	}

	public void closeRestConnection() throws FrameworkExceptions{
		try{
			client.close();
			LOGGER.info("Rest connection is closed.");
		}catch (Exception e)
		{
			LOGGER.error("Failed to close Rest connection " + e );
			throw new FrameworkExceptions("Failed in closeRestConnection() method"+e);
		}
	}

	public void sendRestRequest(String RequestType, String ResponseType) throws FrameworkExceptions{
		try{
			if(RequestType.equalsIgnoreCase("GET")) {
				switch (ResponseType.toUpperCase()) {
					case "JSON":
						RestResponse = webTarget.request(MediaType.APPLICATION_JSON).get();
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

	public void getRestResponseAndValidate(String ResponseType, String strXPath) throws FrameworkExceptions{
		String restResponse = RestResponse.readEntity(String.class);
		try{
			if(ResponseType.toUpperCase().equalsIgnoreCase("JSON")) {
				LOGGER.info("JSON response from the rest:\n"+ restResponse);
				validateJSONResponse(restResponse,strXPath);
			}
			else
			{
				LOGGER.info("The output of the request is:" + response);
				validateXMLResponse(restResponse,strXPath);
			}
		}
		catch (Exception e){
			LOGGER.error("Failed to validate Rest Response " + e );
			throw new FrameworkExceptions("Failed in getRestResponseAndValidate() method"+e);
		}
	}


	public void validateJSONResponse(String restResponse,String jsonValidationExpression) throws FrameworkExceptions{
		try{
			String CurrentValidation, XPath, ExpectedValue, RetrievedValue;
			net.minidev.json.JSONArray jsArray;
			DocumentContext jsonContext = JsonPath.parse(restResponse);
			for(int noofvalidations=0;noofvalidations<jsonValidationExpression.split(";").length; noofvalidations++)
			{
				CurrentValidation = jsonValidationExpression.split(";")[noofvalidations];
				XPath = CurrentValidation.split("\\|")[0];
				ExpectedValue = CurrentValidation.split("\\|")[1];
				jsArray = jsonContext.read(XPath);
				Object [] jsObjArray = jsArray.toArray();
				for (int i = 0; i < jsObjArray.length; i++) {
					RetrievedValue = ((String) jsObjArray[i]).trim();
					if (RetrievedValue.equalsIgnoreCase(ExpectedValue)) {
						LOGGER.info("Response JSON validated successfully. Value " + ExpectedValue + " is present at " + XPath + " in response file.");
						break;
					} else if (i == jsObjArray.length - 1) {
						LOGGER.error("Response JSON validation failed. Value " + ExpectedValue + " is not present at " + XPath + " in response file. Retrieved value: " + RetrievedValue);
					}
				}
			}
		}
		catch (Exception e){
			LOGGER.error("Failed to validate JSON Rest Response " + e );
			throw new FrameworkExceptions("Failed in validateJSONResponse() method"+e);
		}
	}
}
