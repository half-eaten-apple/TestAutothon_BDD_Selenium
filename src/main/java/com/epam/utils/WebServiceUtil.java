package com.epam.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * This class is used to store the get and post request
 * 
 */
public class WebServiceUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceUtil.class);

	// request
	HttpUriRequest request = null;

	// response
	CloseableHttpResponse response = null;

	
	/**
	 * Gets the request.
	 * 
	 * @return the request
	 */
	public HttpUriRequest getRequest() {
		LOGGER.info("getReuqest");
		return this.request;
	}

	/**
	 * Sets the request.
	 * 
	 * @param request
	 *            the new request
	 */
	public void setRequest(HttpUriRequest request) {
		LOGGER.info("setReuqest");
		this.request = request;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public CloseableHttpResponse getResponse() {
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response
	 *            the new response
	 */
	public void setResponse(CloseableHttpResponse response) {
		this.response = response;
	}

}
