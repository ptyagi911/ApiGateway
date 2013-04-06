package com.example.main;

import java.io.IOException;

public interface UICallbacks<T> {
	public void onFailure(String response);
	void onSuccess(String response) throws IOException;
}
