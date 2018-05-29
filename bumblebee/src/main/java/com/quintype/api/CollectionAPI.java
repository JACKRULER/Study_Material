package com.quintype.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.quintype.util.CurrentDate;
import com.quintype.util.OpenBrowser;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class CollectionAPI extends OpenBrowser
{
	public static Response response = null;
    private static String collectionTitle;
    private static String decodedURL;
	
	public static String createCollection(WebDriver driver)
	{
	//	String collectionAPI = responseData.get(0)+"/api/breaking-news/new";
		String collectionAPI = "https://foodie.staging.quintype.com/api/story-collection";
		collectionTitle = "Collection: "+CurrentDate.getCurrentDateAndTime();
		String url = "";
		
		try{
			url = driver.manage().getCookieNamed("session-cookie").getValue();
	    	decodedURL = java.net.URLDecoder.decode(url, "UTF-8");
		}catch(Exception e){
		}

		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(20, TimeUnit.SECONDS);
		client.setReadTimeout(20, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"id\":null,\"name\":\""+collectionTitle+"\",\"summary\":\"\",\"type\":\"content\",\"story-content-ids\":[],\"metadata\":{\"cover-image\":{\"cover-image-url\":\"https://quintype-dropbox-accelerated.s3-accelerate.amazonaws.com/foodie.staging.quintype.com/2017-05-31/121/logo-tester.jpg\",\"cover-image-metadata\":{\"width\":802,\"height\":602,\"mime-type\":\"image/jpeg\"},\"temp-image-key\":\"foodie.staging.quintype.com/2017-05-31/121/logo-tester.jpg\",\"caption\":\"\"}},\"access\":null,\"price-amount\":null,\"price-currency\":null,\"collection-date\":null,\"items\":[],\"rules\":{},\"template\":\"default\",\"automated\":false}");
		Request request = new Request.Builder()
		  .url(collectionAPI)
		  .post(body)
		  .addHeader("x-qt-auth", decodedURL)
		  .addHeader("content-type", "application/json")
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
			Reporter.log("Response of Collection API is not CORRECT !!", true);
			Reporter.log("Response Status Code : "+response.code()+"", true);
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			e.printStackTrace();
		}

		return collectionTitle;
	}
}