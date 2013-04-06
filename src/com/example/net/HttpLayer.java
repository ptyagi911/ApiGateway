package com.example.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class HttpLayer {
	private DefaultHttpClient httpClient;
	
	public Response makeCall(String url, Map<String, String> headers, String username, String password) throws IOException {

		HttpGet httpGet = new HttpGet(url);
		
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpGet.setHeader(entry.getKey(), entry.getValue());
			}
		}
		
		DefaultHttpClient client = getHttpClient();
		
		HttpResponse response = client.execute(httpGet);
		return new Response(response);
	}
	
	
	private DefaultHttpClient getHttpClient() {
		if (httpClient == null) {
			//httpClient = new DefaultHttpClient();
			HttpParams params = new BasicHttpParams();
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
			httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
		}
		
		return httpClient;
	}
	
	public static class Response {
		private int statusCode;
		private InputStream responseBody;
		
		public Response(HttpResponse httpResponse) throws IOException {
			statusCode = httpResponse.getStatusLine().getStatusCode();
			responseBody = httpResponse.getEntity().getContent();
		}
		
		public Response(InputStream istream, int status) throws IOException {
			statusCode = status;
			responseBody = istream;
		}
		
		public int getStatusCode() {
			return statusCode;
		}
		
		public InputStream getResponseBody() {
			return responseBody;
		}
	}
}
