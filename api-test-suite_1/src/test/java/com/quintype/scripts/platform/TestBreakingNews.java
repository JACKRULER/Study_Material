package com.quintype.scripts.platform;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.BreakingNewsEndPoints;
import com.quintype.utils.Story;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;

public class TestBreakingNews extends SetUp {
	Logger logger = LogManager.getLogger();


	@SuppressWarnings({ "rawtypes" })
	@Test(priority = 2)
    public void createBreakingNewsAndVerifyListOfBKOnSketches() 
    {
    	ConfigFile configObject = SetUp.getConfigObject();
    	String itsmanAPI = "breaking-news/new";
		String sketchesAPI = "v1/breaking-news?limit=5";
    	String title = "BK: "+DataAndTimeUtilities.getCurrentDateAndTime();
    	Map breakingNews = BreakingNewsEndPoints.createBreakingNewsWithoutLinkedStory(configObject.basicAuth, 201, title, itsmanAPI);
    	
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "headline"), title);
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "author-name"), configObject.displayName);
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "assignee-id"), Integer.toString(configObject.authorID));
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "publisher-id"), Integer.toString(configObject.publisherID));
//    	Assert.assertNull(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "breaking-news-linked-story-id"));
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "content-type"), "breaking-news");
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "story-template"), "breaking-news");
    	String contentID = JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "story-content-id");
    	Assert.assertNotNull(contentID);
    	Assert.assertNotNull(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "story-version-id"));
    	
    	Map skectchesResponse = BreakingNewsEndPoints.verifyBreakingNewsOnSketches(configObject.basicAuth, 200, sketchesAPI);
//    	Assert.assertEquals(JSONUtilities.getArrayValue(skectchesResponse, "stories", 0, "headline"), title);
//    	Assert.assertEquals(JSONUtilities.getArrayValue(skectchesResponse, "stories", 0, "author-name"), configObject.username);
//    	Assert.assertEquals(JSONUtilities.getArrayValue(skectchesResponse, "stories", 0, "id"), contentID);
//    	Assert.assertEquals(JSONUtilities.getArrayValue(skectchesResponse, "stories", 0, "author-id"), Integer.toString(configObject.authorID));
    	
    	System.out.println("Published Breaking News Without Linked Story & Verified.");
    }			
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(priority = 1)
    public void createBreakingNewsAndVerifyOnSketches() 
    {
    	ConfigFile configObject = SetUp.getConfigObject();
    	String itsmanAPI = "breaking-news/new";
    	String sketchesAPI = "v1/breaking-news?limit=5";
    	String title = "Story to be link";
    	String storyTitle = title+": "+DataAndTimeUtilities.getCurrentDateAndTime();
    	JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");
    	jsonBody.put("headline", storyTitle);
    	
    	Map storyResponse = Story.createStory(jsonBody, configObject.basicAuth);
    	String storyTitle1 = (String) ((Map) storyResponse.get("story")).get("headline");
    	String storyContentID = ((Map) storyResponse.get("story-version")).get("story-content-id").toString();
    	
    	String bkTitle = "BK: "+DataAndTimeUtilities.getCurrentDateAndTime();
    	Map breakingNews = BreakingNewsEndPoints.createBreakingNewsWithLinkedStory(configObject.basicAuth, 201, bkTitle, storyTitle1, storyContentID, itsmanAPI);
    	
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "headline"), bkTitle);
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "author-name"), configObject.displayName);
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "assignee-id"), Integer.toString(configObject.authorID));
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "publisher-id"), Integer.toString(configObject.publisherID));
//    	Assert.assertNull(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "breaking-news-linked-story-id"));
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "content-type"), "breaking-news");
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "story-template"), "breaking-news");
    	String contentID = JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "story-content-id");
    	Assert.assertNotNull(contentID);
    	Assert.assertNotNull(JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(breakingNews, "story"), "story-version-id"));
    	
    	Map linkedStory = JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(breakingNews, "story"), "metadata"), "linked-story");
    	
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(linkedStory, "headline"), storyTitle1);
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(linkedStory, "story-content-id"), storyContentID);
    	Assert.assertEquals(JSONUtilities.getValueFromResponse(linkedStory, "id"), storyContentID);
    
    	Map skectchesResponse = BreakingNewsEndPoints.verifyBreakingNewsOnSketches(configObject.basicAuth, 200, sketchesAPI);
//    	Assert.assertEquals(JSONUtilities.getArrayValue(skectchesResponse, "stories", 0, "headline"), bkTitle);
//    	Assert.assertEquals(JSONUtilities.getArrayValue(skectchesResponse, "stories", 0, "author-name"), configObject.username);
//    	Assert.assertEquals(JSONUtilities.getArrayValue(skectchesResponse, "stories", 0, "id"), contentID);
//    	Assert.assertEquals(JSONUtilities.getArrayValue(skectchesResponse, "stories", 0, "author-id"), Integer.toString(configObject.authorID));
    	
//    	ArrayList<Map> storiesArray = JSONUtilities.getArray(skectchesResponse, "stories");
//    	Map skektchesLinkedStory = JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(storiesArray.get(0), "metadata"), "linked-story");
//    	Assert.assertEquals(skektchesLinkedStory.get("headline"), storyTitle1);
//    	Assert.assertEquals(skektchesLinkedStory.get("story-content-id"), storyContentID);
//    	Assert.assertEquals(skektchesLinkedStory.get("id"), storyContentID);
    	
    	System.out.println("Published Breaking News With Linked Story & Verified.");
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(priority = 3)
	public void createBreakingNewsDefaultTemplate() {
		logger.info("Starting creating breakingNews Default template test");
		ConfigFile configObject = SetUp.getConfigObject();
		String sketchesAPI = "v1/breaking-news?limit=5";

		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BreakingNewsDefaults.json");
		Map defaults = JSONUtilities.getInnerJSON(jsonBody,"defaults");
		String text = defaults.get("text").toString().replaceAll("\\<.*?>","");
		Map storyResponse = BreakingNewsEndPoints.createBreakingNewsDefaultConfig(configObject.basicAuth, 201, jsonBody.toString());
		logger.info("created breakingNews Default template test");

		logger.info("Starting creating breakingNews with associated story");
		String title = "Breaking news: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/BreakingNewsWithAssociatedStory.json");
		jsonBody1.put("headline", title);
		Map associatedStory = JSONUtilities.getInnerJSON(jsonBody1,"associated-story");
		String responseHeadline = associatedStory.get("headline").toString();
		jsonBody1.put(responseHeadline,text);

		Map storyResponse1 = BreakingNewsEndPoints.createBreakingNewsWithAssociatedStory(configObject.basicAuth,201,jsonBody1.toString());
		Map sketchesResponse = BreakingNewsEndPoints.verifyBreakingNewsOnSketches(configObject.basicAuth, 200, sketchesAPI);
		ArrayList<Map> storiesArray = JSONUtilities.getArray(sketchesResponse, "stories");
		Map sketchesLinkedStory = JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(storiesArray.get(0), "metadata"), "linked-story");

		String title1 = "Breaking news: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody2 = JSONUtilities.getJSONFileObject("./src/test/resources/BreakingNewsWithAssociatedStory.json");
		jsonBody1.put("headline", title1);
		Map associatedStory1 = JSONUtilities.getInnerJSON(jsonBody2,"associated-story");
		String responseHeadline1 = associatedStory.get("headline").toString();
		jsonBody1.put(responseHeadline1,text);

		String title2 = "Breaking news: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody3 = JSONUtilities.getJSONFileObject("./src/test/resources/BreakingNewsWithAssociatedStory.json");
		jsonBody1.put("headline", title2);
		Map associatedStory2 = JSONUtilities.getInnerJSON(jsonBody3,"associated-story");
		String responseHeadline2 = associatedStory.get("headline").toString();
		jsonBody1.put(responseHeadline2,text);

		logger.info("created breakingNews with associated story");
	}

}
