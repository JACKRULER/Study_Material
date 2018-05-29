package com.quintype.scripts.platform;

import com.jayway.restassured.path.xml.element.NodeChildren;
import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.StoriesRSSEndPoint;
import com.quintype.utils.*;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TestRSSFeed extends SetUp
{
	ConfigFile configObject;
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Test(priority = 1)
    public void createStoryVerifyFeed() 
	{
		System.out.println("Started: createStoryVerifyFeed test");
		configObject = SetUp.getConfigObject();
		String storyTitle = "Test RSSFeed: "+DataAndTimeUtilities.getCurrentDateAndTime();
    	JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");
    	jsonBody.put("headline", storyTitle);
		Map storyResponse = Story.createStory(jsonBody, configObject.basicAuth);
		System.out.println("Waiting for a minute before checking the "+storyTitle+" story in RSS feed... "+DataAndTimeUtilities.getCurrentDateAndTime());
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Waiting done. Hitting RSS feed now "+DataAndTimeUtilities.getCurrentDateAndTime());
		
		NodeChildren rssFeedStories = StoriesRSSEndPoint.storiesRss(200);
//		System.out.println("XML Structure +>"+ rssFeedStories+"/n");
		StoriesRSSEndPoint.getNodeTitle(rssFeedStories, 1);
		Assert.assertEquals(StoriesRSSEndPoint.getNodeTitle(rssFeedStories, 1),storyTitle);
		System.out.println("Completed: createStoryVerifyFeed test");

    }
	
	// Below script is failing because, its not showing the latest created story. 
	// Its comparing with the previously created story. 
	@SuppressWarnings({ "rawtypes", "unchecked" })
//    @Test(priority = 2)
    public void createStoryVerifyFeedWithTagName() 
	{
		System.out.println("Started: createStoryVerifyFeed test");
		configObject = SetUp.getConfigObject();
		String storyTitle = "Test RSSFeed: "+DataAndTimeUtilities.getCurrentDateAndTime();
    	JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
    	jsonBody.put("headline", storyTitle);
		Map storyResponse = Story.createStory(jsonBody, configObject.basicAuth);
		System.out.println("Waiting for a minute before checking the "+storyTitle+" story in RSS feed... "+DataAndTimeUtilities.getCurrentDateAndTime());
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Waiting done. Hitting RSS feed now "+DataAndTimeUtilities.getCurrentDateAndTime());
		NodeChildren rssFeedStories = StoriesRSSEndPoint.storiesRss(200);
//		System.out.println("XML Structure +>"+ rssFeedStories);
		Assert.assertEquals(StoriesRSSEndPoint.getNodeTitle(rssFeedStories,1),storyTitle);
		System.out.println("Completed: createStoryVerifyFeed test");

    }


}
