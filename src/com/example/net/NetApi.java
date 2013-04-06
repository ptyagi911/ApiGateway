package com.example.net;

import java.io.IOException;

import com.example.main.UICallbacks;

public class NetApi implements ResponseCallbacks {

	String responseString = null;
	UICallbacks uiCallbacks;
	
	public void setUICallbacks(UICallbacks callbacks) {
		this.uiCallbacks = callbacks;
	}
	
	public void fetch(NetRequest request) {
		makeCall(request, this);
	}
	
	public void makeCall(NetRequest request,
			final ResponseCallbacks responseCallbacks) {
		Task task = new Task();
		task.setResponseCallbacks(responseCallbacks);
		task.execute(request);
	}

	@Override
	public void onSuccess(NetResponse response) throws IOException {
		System.out.println(response);
		if (response.isSuccess()) {
			responseString = "Request Fetched Successfully";
			uiCallbacks.onSuccess(responseString);
		} else {
			responseString = "Failed to fetch request";
			uiCallbacks.onFailure(responseString);
		}
		
		System.out.println("Response: " + responseString);
	}

	@Override
	public void onFailure(NetResponse response) {
		System.out.println(response);	
	}

	public String getResponseString() {
		return responseString;
	}
}
