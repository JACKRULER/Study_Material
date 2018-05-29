package com.quintype.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;

import com.quintype.util.OpenBrowser;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class GeneralAPI extends OpenBrowser
{
	public static Response response = null;
	
	public static void createMember(String memberName, String memberEmail, String memberUsername, String memberPassword)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String entityAPI = publisherAPI.getEditorURL()+"/api/member";
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(20, TimeUnit.SECONDS);
		client.setReadTimeout(20, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"metadata\":{},\"username\":\""+memberUsername+"\",\"password\":\""+memberPassword+"\",\"name\":\""+memberName+"\",\"email\":\""+memberEmail+"\",\"communication-email\":\""+memberEmail+"\"}");
		Request request = new Request.Builder()
		  .url(entityAPI)
		  .post(body)
		  .addHeader("x-qt-auth", publisherAPI.getXQTAuth())
		  .addHeader("content-type", "application/json")
		  .build();
		try{
  			response = client.newCall(request).execute();	
		}catch(IOException e){
			e.printStackTrace();
			Assert.fail();
		}
		if(!(response.code()>=200 && response.code()<400))
		{
			Reporter.log("Response Status Code of Member API : "+response.code()+"", true);
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
}
