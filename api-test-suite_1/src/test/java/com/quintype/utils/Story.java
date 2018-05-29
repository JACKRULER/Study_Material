package com.quintype.utils;

import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.StoryCreationEndPoints;

public class Story {
	

	@SuppressWarnings("rawtypes")
	public static Map 	createStory(JSONObject jsonBody, String basicAuth)
	{
    	ConfigFile configObject = SetUp.getConfigObject();
    	
    	Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
    	String storyVersionID = ((Map) storyCreateAPIResponse.get("story-version")).get("id").toString();
        String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();
        
        Assert.assertNotNull(storyVersionID, "Story Version ID is null");
        Assert.assertNotNull(storyContentID, "Story Content ID is null");
        
    	StoryCreationEndPoints.submitStory(configObject.basicAuth, 201);
    	StoryCreationEndPoints.approveStory(configObject.basicAuth, 201);
    	StoryCreationEndPoints.publishStory(configObject.basicAuth, 201);
    	
    	Map sketchesAPIResponse = StoryCreationEndPoints.verifyCreatedStoryOnSketches(configObject.basicAuth, 200);
    	
    	Assert.assertEquals(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story").get("story-content-id"), storyContentID);
    	Assert.assertEquals(((Map) sketchesAPIResponse.get("story")).get("headline"), jsonBody.get("headline"));
    	
//    	JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/BlankStory.json", "sections");
//    	String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
//        String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
//    	Assert.assertEquals(actualSectionName, expectedSectionName);
		
		return storyCreateAPIResponse;
	}

	@SuppressWarnings("rawtypes")
	public static Map createAndSubmitStory(JSONObject jsonBody, String basicAuth, int responseCode)
	{
		ConfigFile configObject = SetUp.getConfigObject();

		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
		String storyVersionID = ((Map) storyCreateAPIResponse.get("story-version")).get("id").toString();
		String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();

		Assert.assertNotNull(storyVersionID, "Story Version ID is null");
		Assert.assertNotNull(storyContentID, "Story Content ID is null");

		StoryCreationEndPoints.submitStory(configObject.basicAuth,responseCode);

		return storyCreateAPIResponse;
	}


}

