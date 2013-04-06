package com.example.main;

import java.io.IOException;

import com.example.apigateway.R;
import com.example.net.NetApi;
import com.example.net.NetRequest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements UICallbacks {
	
	Button button;
	TextView textView;
	NetApi api = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
//		api = new NetApi();
//		api.setUICallbacks(this);
//		
//		button = (Button) findViewById(R.id.button1);
//		textView = (TextView) findViewById(R.id.textView1);
//		button.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				fetchData();
//				
//				String text = api.getResponseString();
//				System.out.println("Text: " + text);
//				textView.setText(text);
//				
//				fetchData();
//				
//				String text3 = api.getResponseString();
//				System.out.println("Text3: " + text3);
//				textView.setText(text);
//			}
//		});
	}

	public void fetchData() {
		NetRequest request = new NetRequest();
		request.setUrl("http://www.disney.com/");
		api.fetch(request);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onFailure(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(String response) throws IOException {
		String text = api.getResponseString();
		System.out.println("Text2: " + text);
		textView.setText(text);
		
	}
}
