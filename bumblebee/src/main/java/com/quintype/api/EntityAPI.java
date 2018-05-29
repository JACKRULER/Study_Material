package com.quintype.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class EntityAPI 
{
	private static Response response = null;	
	
	public static void createEntityWithPhoto(String entityTitle)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String entityAPI = publisherAPI.getEditorURL()+"/api/entity/";
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"entity\":{\"type\":\"person1\",\"name\":\""+entityTitle+"\",\"email\":\"test1@test.com\",\"photo\":[{\"key\":\"testing-b7d32e45-7968-445c-be11-1c5a7d0edea6.editor.fun.quinpress.com/2017-08-04/1336/5.jpg\",\"url\":\"https://quintype-dropbox-accelerated.s3-accelerate.amazonaws.com/testing-b7d32e45-7968-445c-be11-1c5a7d0edea6.editor.fun.quinpress.com/2017-08-04/1336/5.jpg\",\"metadata\":{\"width\":636,\"height\":477,\"mime-type\":\"image/jpeg\"},\"caption\":\"\",\"attribution\":\"\",\"temp-key\":\"testing-b7d32e45-7968-445c-be11-1c5a7d0edea6.editor.fun.quinpress.com/2017-08-04/1336/5.jpg\"}]}}");
		Request request = new Request.Builder()
		  .url(entityAPI)
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
		if(!(response.code()>=200 && response.code()<400))
		{
			Reporter.log("Response Status Code of Entity API is : "+response.code()+"", true);
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	public static void createEntityWithoutPhoto(String entityTitle)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String entityAPI = publisherAPI.getEditorURL()+"/api/entity/";
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"entity\":{\"type\":\"person1\",\"name\":\""+entityTitle+"\",\"email\":\"Test\",\"photo\":[]}}");
		Request request = new Request.Builder()
		  .url(entityAPI)
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
		if(!(response.code()>=200 && response.code()<400))
		{
			Reporter.log("Response Status Code of Entity API is : "+response.code()+"", true);
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			e.printStackTrace();
		}		

	}
	
/*	public static void createRioEntity(String name)
	{
		String entityName = name;
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"entity\":{\"type\":\"rio-person\",\"name\":\""+entityName+"\",\"email\":\"asd@asd.com\",\"photo\":[{\"key\":\"rio-demo.staging.quintype.com/2017-07-21/1325/A2.jpg\",\"url\":\"https://quintype-dropbox-accelerated.s3-accelerate.amazonaws.com/rio-demo.staging.quintype.com/2017-07-21/1325/A2.jpg\",\"metadata\":{\"width\":1600,\"height\":1200,\"mime-type\":\"image/jpeg\"},\"caption\":\"\",\"attribution\":\"\",\"temp-key\":\"rio-demo.staging.quintype.com/2017-07-21/1325/A2.jpg\"}]}}");
		Request request = new Request.Builder()
		  .url("https://rio-demo.staging.quintype.com/api/entity/")
		  .post(body)
		  .addHeader("x-qt-auth", "I1M4GLZba79Tgjctr4FZHvTjykeicR5x3LsSGeSgrouq4ePOWNtKDGXiP/vdPT+t0/U+5vqunNc47m2VeOieLisPWewmaqoa1KzMTGPx+K0fyAqpkTftZAGwLK6nAonpn6UoooYH7A6s7PvddiOKwxGD1bxCA5Pthpuf+la5cqn9Sshne7fCfxz7m8NbUKeLu+LYb49qujStSFIOBA0qy5HP3q5TCqcd9eQ/HCsslm8a2Zohdr8q7KW3dn5rLkEm3Op1lhHWJ7sNEgdVUV1GAXLef/B9GkJxFzi+vxrlpBDyyySakcbehW7PF2QFFpvhK29ZCY6f2v70/p8/sGdTn334U3StCLNL1jchz/Or6wOp48gNKLTP0cOpn4CapJWqYAGZBMTsHrikkUxbS9SHBoqhLRObY/Xzb/ozJB3AfVQn1x+RGUaweXdyhIXyGXbkpozRpiYhgbow0tFRXxgu7inOIBMh+e08hKTVqN7NfRCK3JZefUdrdAWVK+AMquj7/33/1uC0feG8stMSJS99ZlporxBTfLV46qlaFmL8+7j9fnmtXkI05tb0WYOTJHB2W0kK+hC8AE+Q6KA2agaQfPE6NnsMqr6u9hcWWQl7MjapLYPRa3kymn9C4SMLz/Hs/DEzoUOovsqil86+jstfFGyuX6J+079vqaFx4728FPj/AV13JKIBdNZimYXwPAHQv9j1LYxtpMSM5cwhb2gM2zU6HQ0/8hRFqFLKgWZbexw=--EpC1Tbp/c4Usny3Zv2X7l6VtQVd4EpUlCrFwkYqV6iA=")
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "9bf53d72-e4e6-a0fc-5d80-f45056f3c1c6")
		  .build();

		try{
  			response = client.newCall(request).execute();	
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		if(response.code()>=200 && response.code()<400)
		{
		
		}else
		{
			Reporter.log("Response of Entity API is not CORRECT !!", true);
			Reporter.log("Response Status Code : "+response.code()+"", true);
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){

		}		
	}		*/
}
