package com.quintype.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.NewStoryPage;
import org.testng.Reporter;
import org.testng.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import java.awt.Toolkit;

public class PublisherAPI extends OpenBrowser
{
	private static Response response = null;
	private static String editorURL;
	private static String sketchesURL;
	private static String webURL;
	private static String x_QT_AUTH;
	private static int publisherID;
	private static int sectionID;
	
	public static void createPublisher() throws JSONException
	{
		String treeStructure = "";
		JSONObject jsonObj = null;
		
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(40, TimeUnit.SECONDS);
		client.setReadTimeout(40, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"users\":[{\"name\":\"Tester\",\"email\":\"testerquint@gmail.com\",\"username\":\"tester\",\"password\":\"tester\",\"role-name\":\"Admin\"},{\"name\":\"Sachin\",\"email\":\"sachinquint@gmail.com\",\"username\":\"sachin\",\"password\":\"sachin\",\"role-name\":\"Admin\"}],\"sections\":[{\"name\":\"News\",\"slug\":\"news\",\"display-name\":\"News\"},{\"name\":\"Travel\",\"slug\":\"travel\",\"display-name\":\"Travel\"},{\"name\":\"Sport\",\"slug\":\"sport\",\"display-name\":\"Sport\"}],\"features\":{\"opinion-poll\":true,\"alterative-headline-and-image\":true,\"enable-story-seo-tags\":true,\"story-collection\":true,\"breaking-news\":true,\"image-gallery\":true},\"collection-templates\":[\"section\"],\"enabled-story-elements\":[\"summary\",\"text\",\"image\",\"image-gallery\",\"social-media\",\"jsembed\",\"location\",\"quote\",\"blockquote\",\"blurb\",\"bigfact\",\"q-and-a\",\"opinion-poll\",\"question\",\"answer\"],\"breaking-news-sorters\":[{\"story-group\":\"news-flash\",\"name\":\"News Flash\",\"headline-limit\":96}]}");
		Request request = new Request.Builder()
		  .url("http://testing.editor.fun.quinpress.com/api/testing/publisher")
		  .post(body)
		  .addHeader("content-type", "application/json")
		  .build();
		try{
			response = client.newCall(request).execute();
			treeStructure = response.body().string();
		}catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		try{
			if(response.code()>=200 && response.code()<400)
			{
				jsonObj = new JSONObject(treeStructure);
				editorURL = jsonObj.getString("editor-url");
				sketchesURL = jsonObj.getString("api-url");
				webURL = jsonObj.getString("web-url");
				x_QT_AUTH = response.header("X-QT-AUTH");
			}else
			{
				Reporter.log("Response Status Code of API use to create Publisher is : "+response.code()+"", true);
				System.exit(1);
			}
		}catch(JSONException json)
		{
			json.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		log.info("Itsman Created using Publisher API");
		System.out.println("\nEditor-URL: "+editorURL+"\n");
		System.out.println("Sketches-URL: "+sketchesURL);
		System.out.println("Web-URL: "+webURL+"\n");
//		System.out.println("X-QT-AUTH: "+x_QT_AUTH);
	}
	public String getEditorURL()
	{
		return editorURL;
	}
	public String getSketchesURL()
	{
		return sketchesURL;
	}
	public String getWebURL()
	{
		return webURL;
	}
	public String getXQTAuth()
	{
		return x_QT_AUTH;
	}
		
//	@Test(groups = { "functest", "smoketest" })
	public static void createTagInItsman()
	{
		ChromeDriverManager.getInstance().setup();
		WebDriver driver = new ChromeDriver();
	  	driver.get(editorURL);
	  	System.out.println("Title of Page => "+driver.getTitle());
//	  	driver.manage().window().maximize();		// Not working on CI with new chromedriver
	  	java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Point position = new Point(0, 0);
	    driver.manage().window().setPosition(position);
	    Dimension maximizedScreenSize =
	    new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
	    driver.manage().window().setSize(maximizedScreenSize);

	  	LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
	  	
		Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
	  	loginPage.loginToApplication();				// Login to Itsman/Editor
	  	Assert.assertEquals("Workspace" , driver.getTitle(), "User is not able to login to the Itsman with valid credentials");	// Verify user is able to login
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData("Social Share Message");
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));
//		newStoryPage.enterTagFieldData("India");
		newStoryPage.clickSaveButton();				// Click Save button	  	
	  	Reporter.log("\n<<=== Tag Created ===>>\n", true);
	  	log.info("Tag Created in Itsman");
	  	driver.quit();
	}
	public int getPublisherID()
	{
//		Response response = null;
		String treeStructure = "";
		JSONObject jsonObj = null;
		String sectionAPI = editorURL+"/api/section";
		
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(40, TimeUnit.SECONDS);
		client.setReadTimeout(40, TimeUnit.SECONDS);
		Request request = new Request.Builder()
		  .url(sectionAPI)
		  .get()
		  .addHeader("x-qt-auth", x_QT_AUTH)
		  .build();
		try{
			response = client.newCall(request).execute();
			treeStructure = response.body().string();
			treeStructure = treeStructure.replace("[", "");
			treeStructure = treeStructure.replace("]", "");
			jsonObj = new JSONObject(treeStructure);
			publisherID = jsonObj.getInt("publisher-id");
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}catch (JSONException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		return publisherID;
	}
	public int getSectionID()
	{
		String treeStructure = "";
		JSONObject jsonObj = null;
		String sectionAPI = editorURL+"/api/section";
		
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(40, TimeUnit.SECONDS);
		client.setReadTimeout(40, TimeUnit.SECONDS);
		Request request = new Request.Builder()
		  .url(sectionAPI)
		  .get()
		  .addHeader("x-qt-auth", x_QT_AUTH)
		  .build();
		try{
			response = client.newCall(request).execute();
			treeStructure = response.body().string();
			treeStructure = treeStructure.replace("[", "");
			treeStructure = treeStructure.replace("]", "");
			jsonObj = new JSONObject(treeStructure);
			sectionID = jsonObj.getInt("id");
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}catch (JSONException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try{				
			response.body().close();	// Closing the response body connection to avoid warning
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		return sectionID;
	}
	
}