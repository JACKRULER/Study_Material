package com.quintype.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

import com.quintype.util.OpenBrowser;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class StoryAPI extends OpenBrowser
{
//	public static List <String> responseData;
	private Response response = null;
//	private List <String> storyVersion;
	
	public List <String> createPhotoStoryInOpenState(String title, int count)
	{
		List <String> storyVersion;
		String domainName = "";
		PublisherAPI publisherAPI = null;
		if(System.getProperty("domain").equalsIgnoreCase("platform"))
		{
			domainName = envFile.getProperty("URL");
		} if(System.getProperty("domain").equalsIgnoreCase("funenv")) {
			publisherAPI = new PublisherAPI();
			domainName = publisherAPI.getEditorURL();
		}
		String storyCreateAPI= domainName+"/api/story/new/story-version";
		int sectionID = publisherAPI.getSectionID();
		int publisherID = publisherAPI.getPublisherID();
		String treeStructure = "";
		JSONObject jsonObj = null;
		storyVersion = new ArrayList<String>();
		List <String> storyBody = new ArrayList<String>();
		
		String storyTitle = title;
		storyBody.add("{\"tree\":[{\"content-type\":\"card\",\"content-id\":\"new-card-0.4605418667170431\"}],\"story-content-id\":\"new\",\"cards\":{\"new-card-0.4605418667170431\":{\"story-elements\":[{\"type\":\"image\",\"id\":\"new-0.11530359292422898\",\"metadata\":{},\"image-url\":\"https://quintype-dropbox-accelerated.s3-accelerate.amazonaws.com/testing-a7325157-4450-453e-8a7b-1916fed08016.editor.fun.quinpress.com/2017-05-01/1792/A9.jpg\",\"image-metadata\":{\"width\":640,\"height\":360,\"mime-type\":\"image/jpeg\"},\"temp-image-key\":\"testing-a7325157-4450-453e-8a7b-1916fed08016.editor.fun.quinpress.com/2017-05-01/1792/A9.jpg\",\"image-attribution\":\"\",\"title\":\"Purple\"}]}},\"editor-features\":{\"can-skip-hero-image\":false,\"must-have-custom-slug\":false,\"must-have-photo\":true},\"story-template\":\"photo\",\"metadata\":{},\"push-notification-targets\":{\"web\":false,\"mobile\":false},\"headline\":\""+storyTitle+"\",\"slug\":\"custom-url\",\"summary\":\"Social Share Message of Photo Story\",\"tags\":[{\"id\":35,\"name\":\"Tag\",\"meta-description\":null}],\"sections\":[{\"id\":"+sectionID+",\"name\":\"News\",\"publisher-id\":"+publisherID+",\"updated-at\":1493651851484,\"deleted-at\":null,\"slug\":\"News\",\"display-name\":\"News\",\"parent-id\":null,\"collection-id\":null,\"pathString\":\"News\"}],\"custom-slug\":\"custom-url\",\"hero-image-url\":\"http://d9zv4zsfqrm9s.cloudfront.net/rio-demo/2017-04/0fd40df3-1643-4da4-9695-1cce9bdc799e/A.jpg\",\"hero-image-metadata\":{\"width\":1600,\"height\":900,\"mime-type\":\"image/jpeg\"},\"hero-image-s3-key\":\"rio-demo/2017-04/0fd40df3-1643-4da4-9695-1cce9bdc799e/A.jpg\",\"hero-image-caption\":\"Image Caption\",\"updated-cards\":[\"new-card-0.4605418667170431\"]}");
	
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, storyBody.get(0));
		Request request = new Request.Builder()
		  .url(storyCreateAPI)
		  .post(body)
		  .addHeader("x-qt-auth", publisherAPI.getXQTAuth())
		  .addHeader("content-type", "application/json")
		  .build();
		try{
			response = client.newCall(request).execute();
			treeStructure = response.body().string();
			jsonObj = new JSONObject(treeStructure);
			JSONObject jsonObj1 = jsonObj.getJSONObject("story-version");

			storyVersion.add(jsonObj1.getString("story-content-id")) ;
			storyVersion.add(jsonObj1.getString("id"));
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(JSONException json) {
			json.printStackTrace();
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e) {
			System.exit(1);
		}
		return storyVersion;	
	}
	
	public List <String> createVideoStoryInOpenState(String title, int count)
	{
		List <String> storyVersion;
		PublisherAPI publisherAPI = new PublisherAPI();
		
		String storyCreateAPI = publisherAPI.getEditorURL()+"/api/story/new/story-version";
		int sectionID = publisherAPI.getSectionID();
		int publisherID = publisherAPI.getPublisherID();
		String treeStructure = "";
		JSONObject jsonObj = null;
		storyVersion = new ArrayList<String>();
		List <String> storyBody = new ArrayList<String>();
		
		String storyTitle = title+" "+count;
		storyBody.add("{\"tree\":[{\"content-type\":\"card\",\"content-id\":\"new-card-0.5002925301514403\"}],\"story-content-id\":\"new\",\"cards\":{\"new-card-0.5002925301514403\":{\"story-elements\":[{\"type\":\"youtube-video\",\"id\":\"new-0.5563970250829535\",\"metadata\":{},\"url\":\"https://www.youtube.com/watch?v=6qdRDW-kfUM\",\"embed-url\":\"https://www.youtube.com/embed/6qdRDW-kfUM\",\"subtype\":null}]}},\"editor-features\":{\"can-skip-hero-image\":false,\"must-have-custom-slug\":false,\"must-have-video\":true},\"story-template\":\"video\",\"metadata\":{},\"push-notification-targets\":{\"web\":false,\"mobile\":false},\"headline\":\""+storyTitle+"\",\"slug\":\"custom-url-for-video-story\",\"hero-image-url\":\"https://quintype-dropbox-accelerated.s3-accelerate.amazonaws.com/testing-a7325157-4450-453e-8a7b-1916fed08016.editor.fun.quinpress.com/2017-05-01/1668/A3.jpg\",\"hero-image-metadata\":{\"width\":300,\"height\":168,\"mime-type\":\"image/jpeg\",\"focus-point\":[150,66]},\"temporary-hero-image-key\":\"testing-a7325157-4450-453e-8a7b-1916fed08016.editor.fun.quinpress.com/2017-05-01/1668/A3.jpg\",\"hero-image-attribution\":\"\",\"hero-image-caption\":\"Nature\",\"updated-cards\":[\"new-card-0.5002925301514403\"],\"summary\":\"Social Share Message of Video Story\",\"sections\":[{\"id\":"+sectionID+",\"name\":\"News\",\"publisher-id\":"+publisherID+",\"updated-at\":1493651851484,\"deleted-at\":null,\"slug\":\"News\",\"display-name\":\"News\",\"parent-id\":null,\"collection-id\":null,\"pathString\":\"News\"}],\"tags\":[{\"id\":35,\"name\":\"Tag\",\"meta-description\":null}],\"custom-slug\":\"custom-url-for-video-story\"}");
	
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, storyBody.get(0));
		Request request = new Request.Builder()
		  .url(storyCreateAPI)
		  .post(body)
		  .addHeader("x-qt-auth", publisherAPI.getXQTAuth())
		  .addHeader("content-type", "application/json")
		  .build();
		try{
			response = client.newCall(request).execute();
			treeStructure = response.body().string();
			jsonObj = new JSONObject(treeStructure);
			JSONObject jsonObj1 =jsonObj.getJSONObject("story-version");

			storyVersion.add(jsonObj1.getString("story-content-id")) ;
			storyVersion.add(jsonObj1.getString("id"));
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}catch(JSONException json)
		{
			json.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			System.exit(1);
		}
		return storyVersion;	
	}
	
	public List <String> createTextStoryInOpenState(String title, int count)
	{
		List <String> storyVersion;
		PublisherAPI publisherAPI = new PublisherAPI();
		
		String storyCreateAPI = publisherAPI.getEditorURL()+"/api/story/new/story-version";
		int sectionID = publisherAPI.getSectionID();
		int publisherID = publisherAPI.getPublisherID();
		String treeStructure = "";
		JSONObject jsonObj = null;
		storyVersion = new ArrayList<String>();
		List <String> storyBody = new ArrayList<String>();
		
		String storyTitle = title+" "+count;
		storyBody.add("{\"tree\":[{\"content-type\":\"card\",\"content-id\":\"new-card-0.8614914986517854\"}],\"story-content-id\":\"new\",\"cards\":{\"new-card-0.8614914986517854\":{\"story-elements\":[{\"type\":\"text\",\"subtype\":\"summary\",\"id\":\"new-story-element-0.7123813739824485\",\"metadata\":{},\"text\":\"<p>Summary element of the story</p>\"}]}},\"editor-features\":{\"can-skip-hero-image\":false,\"must-have-custom-slug\":false},\"story-template\":null,\"metadata\":{},\"push-notification-targets\":{\"web\":false,\"mobile\":false},\"headline\":\""+storyTitle+"\",\"slug\":\"custom-url-for-the-story\",\"updated-cards\":[\"new-card-0.8614914986517854\"],\"hero-image-url\":\"http://d9zv4zsfqrm9s.cloudfront.net/testing-a7325157-4450-453e-8a7b-1916fed08016/2017-05/1d3aa2db-5252-48ea-9152-7ed4bb79e38e/A2.jpg\",\"hero-image-metadata\":{\"width\":1600,\"height\":1200},\"hero-image-s3-key\":\"testing-a7325157-4450-453e-8a7b-1916fed08016/2017-05/1d3aa2db-5252-48ea-9152-7ed4bb79e38e/A2.jpg\",\"hero-image-caption\":\"Woww\",\"summary\":\"Social Share message of the Story \",\"tags\":[{\"id\":35,\"name\":\"Tag\",\"meta-description\":null}],\"sections\":[{\"id\":"+sectionID+",\"name\":\"News\",\"publisher-id\":"+publisherID+",\"updated-at\":1493651851484,\"deleted-at\":null,\"slug\":\"News\",\"display-name\":\"News\",\"parent-id\":null,\"collection-id\":null,\"pathString\":\"News\"}],\"custom-slug\":\"custom-url-for-the-story\"}");
	
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, storyBody.get(0));
		Request request = new Request.Builder()
		  .url(storyCreateAPI)
		  .post(body)
		  .addHeader("x-qt-auth", publisherAPI.getXQTAuth())
		  .addHeader("content-type", "application/json")
		  .build();
		try{
			response = client.newCall(request).execute();
			treeStructure = response.body().string();
			jsonObj = new JSONObject(treeStructure);
			JSONObject jsonObj1 =jsonObj.getJSONObject("story-version");

			storyVersion.add(jsonObj1.getString("story-content-id")) ;
			storyVersion.add(jsonObj1.getString("id"));
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}catch(JSONException json)
		{
			json.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			System.exit(1);
		}
		return storyVersion;	
	}
	
	public void changeStoryStatus(String storyStatus, List <String> storyVersion)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String statusChangeAPI = publisherAPI.getEditorURL()+"/api/story/"+storyVersion.get(0)+"/status";
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		String submitLink =  "{\"story-version-id\":\""+storyVersion.get(1)+"\",\"action\":\""+storyStatus+"\"}";
		RequestBody body = RequestBody.create(mediaType, submitLink);
		Request request = new Request.Builder()
		  .url(statusChangeAPI)
		  .post(body)
		  .addHeader("x-qt-auth", publisherAPI.getXQTAuth())
		  .addHeader("content-type", "application/json")
		  .build();
		try{
			response = client.newCall(request).execute();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			System.exit(1);
		}
	}
	public void changeBlankStoryStatus(String storyStatus, List <String> storyVersion)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String statusChangeAPI = publisherAPI.getEditorURL()+"/api/story/"+storyVersion.get(0)+"/status";
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		String submitLink =  "{\"story-version-id\":\""+storyVersion.get(1)+"\",\"action\":\""+storyStatus+"\"}";
		RequestBody body = RequestBody.create(mediaType, submitLink);
		Request request = new Request.Builder()
		  .url(statusChangeAPI)
		  .post(body)
		  .addHeader("x-qt-auth", publisherAPI.getXQTAuth())
		  .addHeader("content-type", "application/json")
		  .build();
		try{
			response = client.newCall(request).execute();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			System.exit(1);
		}
	}
	public void changePhotoStoryStatus(String storyStatus, List <String> storyVersion)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String statusChangeAPI = publisherAPI.getEditorURL()+"/api/story/"+storyVersion.get(0)+"/status";
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		String submitLink =  "{\"story-version-id\":\""+storyVersion.get(1)+"\",\"action\":\""+storyStatus+"\"}";
		RequestBody body = RequestBody.create(mediaType, submitLink);
		Request request = new Request.Builder()
		  .url(statusChangeAPI)
		  .post(body)
		  .addHeader("x-qt-auth", publisherAPI.getXQTAuth())
		  .addHeader("content-type", "application/json")
		  .build();
		try{
			response = client.newCall(request).execute();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			System.exit(1);
		}
	}
	public void changeVideoStoryStatus(String storyStatus, List <String> storyVersion)
	{
		PublisherAPI publisherAPI = new PublisherAPI();
		String statusChangeAPI = publisherAPI.getEditorURL()+"/api/story/"+storyVersion.get(0)+"/status";
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		String submitLink =  "{\"story-version-id\":\""+storyVersion.get(1)+"\",\"action\":\""+storyStatus+"\"}";
		RequestBody body = RequestBody.create(mediaType, submitLink);
		Request request = new Request.Builder()
		  .url(statusChangeAPI)
		  .post(body)
		  .addHeader("x-qt-auth", publisherAPI.getXQTAuth())
		  .addHeader("content-type", "application/json")
		  .build();
		try{
			response = client.newCall(request).execute();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			System.exit(1);
		}
	}
}