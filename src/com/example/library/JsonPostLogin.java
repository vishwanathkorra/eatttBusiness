package com.example.library;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonPostLogin {

	public static JSONObject getJSONfromURL(String url,String username,String password) {
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;
		// Download JSON data from URL
		try {
			
			
			  // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";
            
            // 3. build jsonObject
            
            

            // 4. convert JSONObject to JSON to String
            //json = table.toString();
            JSONObject jsonobject = new JSONObject();
            jsonobject.accumulate("username", username);
            jsonobject.accumulate("password", password);
            json=jsonobject.toString();
            System.out.println("final json is"+json);
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person); 

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content   
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
			
			
			HttpEntity entity = httpResponse.getEntity();
			is = entity.getContent();
			System.out.println("result"+is);
			

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// Convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		try {

			jArray = new JSONObject(result);
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		System.out.println("JSon Array is "+jArray);

		return jArray;
	}
}
