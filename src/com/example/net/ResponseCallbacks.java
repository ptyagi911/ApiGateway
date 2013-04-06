package com.example.net;

import java.io.IOException;

public interface ResponseCallbacks<T> {
	public void onFailure(NetResponse response);
	void onSuccess(NetResponse response) throws IOException;
}
