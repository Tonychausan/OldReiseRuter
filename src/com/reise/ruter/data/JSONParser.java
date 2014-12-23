package com.reise.ruter.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
public class JSONParser {
	String json = "";
	JSONObject jObj;
	
	// constructor
	public JSONParser() {
	}
	public JSONObject getJSONFromUrl(String url) {
		InputStream content = null;
		try {	
			HttpGet httpGet = new HttpGet(url);
			HttpClient httpclient = new DefaultHttpClient();
			// Execute HTTP Get Request
			HttpResponse response = httpclient.execute(httpGet);
			content = response.getEntity().getContent();
			json = getStringFromInputStream(content);
		} catch (ClientProtocolException e) {
		  // TODO Auto-generated catch block
		} catch (IOException e) {
		  // TODO Auto-generated catch block
		}
	
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		// return JSON String
		return jObj;  
	}
	
	public JSONArray getJSONArrayFromUrl(String url) {
		InputStream content = null;
		JSONArray jArray = null;
		try {	
			HttpGet httpGet = new HttpGet(url);
			HttpClient httpclient = new DefaultHttpClient();
			// Execute HTTP Get Request
			HttpResponse response = httpclient.execute(httpGet);
			content = response.getEntity().getContent();
			json = getStringFromInputStream(content);
		} catch (ClientProtocolException e) {
		  // TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
		  // TODO Auto-generated catch block
			return null;
		}
	
		try {
			jArray = new JSONArray(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		// return JSON String
		return jArray;  
	}
  
	private String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();	
				} catch (IOException e) {
					e.printStackTrace();
				
				}	
			}
		}
		return sb.toString();
	}
}