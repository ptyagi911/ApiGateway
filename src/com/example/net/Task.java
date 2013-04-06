package com.example.net;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class Task extends AsyncTask<NetRequest, Void, NetResponse>{
	private ResponseCallbacks responseCallbacks;
	private NetCallDispatcher dispatcher;
	
	public Task() {
		dispatcher = new NetCallDispatcher();
	}
	
	public void setResponseCallbacks(ResponseCallbacks callbacks) {
		this.responseCallbacks = callbacks;
	}
	
	@Override
	protected NetResponse doInBackground(NetRequest... params) {
		NetRequest request = params[0];
		System.out.println("Network Request:" + request);
		InputStream responseBody = null;
		NetResponse netResponse = null;
		
		try {
			NetCallDispatcher.Response response = 
					dispatcher.makeCall(request.getUrl(), 
					request.getHeaders(), null, null); 
					
			netResponse = request.createResponse(response.getStatusCode());
			responseBody = response.getResponseBody();
			netResponse.processResponse(responseBody);
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
			netResponse = request.createResponse(-1);
		} finally {
			if (responseBody != null) {
				try{
					responseBody.close();
				} catch(IOException e) {}
			}
		}
		
		return netResponse;
	}

	protected void onPostExecute(NetResponse response) {
		System.out.println("Netresponse: " + response);
		
		Util.dispatch(response, responseCallbacks);
	}


}
