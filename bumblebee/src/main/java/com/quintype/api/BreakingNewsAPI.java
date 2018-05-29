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

public class BreakingNewsAPI extends OpenBrowser
{
	private static Response response = null;
	
	public void createBreakingNews(int count)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String breakingNewsAPI = publisherAPI.getEditorURL()+"/api/breaking-news/new";
		String breakingNewsTitle = "Title of Breaking News: "+count;
		
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(20, TimeUnit.SECONDS);
		client.setReadTimeout(20, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"metadata\":{},\"story-content-id\":\"new\",\"headline\":\""+breakingNewsTitle+"\",\"content-id\":\"new\"}");
		Request request = new Request.Builder()
  		.url(breakingNewsAPI)
  		.post(body)
  		.addHeader("x-qt-auth", publisherAPI.getXQTAuth())
  		.addHeader("content-type", "application/json")
  		.build();
  		try{
  			response = client.newCall(request).execute();	
		}catch(IOException e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		if(response.code()>=200 && response.code()<400)
		{
		
		}else
		{
			Reporter.log("Response of Breaking News API is not CORRECT !!", true);
			Reporter.log("Response Status Code : "+response.code()+"", true);
		//	System.exit(0);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){

		}		
	}

}