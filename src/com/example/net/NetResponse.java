package com.example.net;

import java.io.InputStream;

public class NetResponse {
	protected int statusCode;
	private String successResponse;
	private String failureResponse;
	
	public NetResponse(int httpStatus) {
		this.statusCode = httpStatus;
	}
	
	public void processResponse(InputStream responseBody) {
		System.out.println("Processing response: " + responseBody);
		//placeholder
		if (responseBody != null) {
			successResponse = "Success Response";
		} else {
			failureResponse = "Failure Response";
		}
	}
	
	public String getSuccessResponseString() {
		return successResponse;
	}
	
	public String getFailureResponseString() {
		return failureResponse;
	}
	
	public int getResponseCode() {
		return statusCode;
	}
	
	public boolean isSuccess() {
		return statusCode >=200 && statusCode < 300;
	}
	
	public boolean isUnauthorized() {
		return statusCode == 401;
	}
}
