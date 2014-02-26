package com.plantplaces.dao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Low level network functions that can be called by any higher-level DAO.
 * @author Brandan
 *
 */
public class NetworkDAO {

	/**
	 * Invoke a URI (URL), and pass back the String result we get when we invoke that URI.
	 * @param uri the address (typically starting with http) that we want to call.
	 * @return the data we get when we invoke this address.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String request(String uri) throws ClientProtocolException, IOException {
		// HttpGet represents the request that we are making
		HttpGet httpGet = new HttpGet(uri);
		
		// ResponseHandler handles the response from this request.
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		
		// HttpClient glues together the request and the response.
		HttpClient httpClient = new DefaultHttpClient();
		
		// make the request and response happen!
		String response = httpClient.execute(httpGet, responseHandler);
		
		// return the response.
		return response;
		
	}
	
}
