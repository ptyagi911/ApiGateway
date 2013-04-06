package com.example.net;

public class Util {

	public static void dispatch(NetResponse response, 
			ResponseCallbacks callbacks) {
		
		if (response.isSuccess()) {
			try {
				callbacks.onSuccess(response);
			} catch (Exception e) {
				callbacks.onFailure(response);
			}
		} else {
			callbacks.onFailure(response);
		}
	}
}
