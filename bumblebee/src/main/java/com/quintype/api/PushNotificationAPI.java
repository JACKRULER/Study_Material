package com.quintype.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.quintype.util.OpenBrowser;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.testng.Assert;
import org.testng.Reporter;

public class PushNotificationAPI extends OpenBrowser
{
	public static List <String> responseData;
	public static Response response = null;
	
	public void createPushNotification(int count)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String pushNotificationAPI = publisherAPI.getEditorURL()+"/api/push-notification";
		String pushNotificationTitle = "Push Notification: "+count;

		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(20, TimeUnit.SECONDS);
		client.setReadTimeout(20, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"push-notification\":{\"push-notification\":\""+pushNotificationTitle+"\",\"targets\":{\"web\":true,\"mobile\":false}}}");
		Request request = new Request.Builder()
  		.url(pushNotificationAPI)
  		.post(body)
  		.addHeader("content-type", "application/json")
  		.addHeader("x-qt-auth", publisherAPI.getXQTAuth())
  		.build();

		try{
  			response = client.newCall(request).execute();	
		}catch(IOException e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		if(!(response.code()>=200 && response.code()<400))
		{
			Reporter.log("Response Status Code of Push Notification API is : "+response.code()+"", true);
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}