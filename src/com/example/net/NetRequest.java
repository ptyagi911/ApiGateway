package com.example.net;

import java.util.HashMap;
import java.util.Map;

public class NetRequest <T extends NetResponse>{
	
	private String url;
	private HashMap<String, String> queryParams;
	private HashMap<String, String> headers;
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setQueryParams(HashMap<String, String> params) {
		this.queryParams = params;
	}
	
	public Map<String, String> getQueryParams() {
		return queryParams;
	}
	
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
	
	public HashMap<String, String> getHeaders() {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		return this.headers;
	}
	
	public String getUsername() {
		return null;
	}
	
	public String getPassword() {
		return null;
	}
	
	public T createResponse(int statusCode) {
		return (T) new NetResponse(statusCode);
	}
}
