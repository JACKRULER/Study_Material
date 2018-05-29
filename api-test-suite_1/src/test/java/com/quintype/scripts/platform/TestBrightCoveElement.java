package com.quintype.scripts.platform;

import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import com.quintype.utils.Story;

public class TestBrightCoveElement {
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Test(priority = 1)
	public void createBlankBrightCoveStoryWithoutVideoId()
	{
		System.out.println("Start: createBlankBrightCoveStory");
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Blank Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BrightcoveElementWithoutVideoId.json");
		jsonBody.put("headline",storyTitle);

		Map storyCreateAPIResponse = Story.createAndSubmitStory(jsonBody,configObject.basicAuth,422);

		System.out.println("Failed to create Blank Story with Brightcove Element Without VideoId");
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Test(priority = 2)
	public void createBlankBrightCoveStoryWithoutAccountId()
	{
		System.out.println("Start: createBlankBrightCoveStory");
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Blank Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BrightcoveElementWithoutAccountId.json");
		jsonBody.put("headline",storyTitle);

		Map storyCreateAPIResponse = Story.createAndSubmitStory(jsonBody,configObject.basicAuth,422);

		System.out.println("Failed to create Blank Story with Brightcove Element Without AccountId");
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Test(priority = 3)
	public void createBlankBrightCoveStoryWithoutPlayerId()
	{
		System.out.println("Start: createBlankBrightCoveStory");
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Blank Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BrightcoveElementWithoutPlayerId.json");
		jsonBody.put("headline",storyTitle);

		Map storyCreateAPIResponse = Story.createAndSubmitStory(jsonBody,configObject.basicAuth,422);

		System.out.println("Failed to create Blank Story with Brightcove Element Without PlayerId");
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Test(priority = 4)
	public void createBlankBrightCoveStoryWithoutPlayerMediaId()
	{
		System.out.println("Start: createBlankBrightCoveStory");
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Blank Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BrightcoveElementWithoutPlayerMedia.json");
		jsonBody.put("headline",storyTitle);

		Map storyCreateAPIResponse = Story.createAndSubmitStory(jsonBody,configObject.basicAuth,422);

		System.out.println("Failed to create Blank Story with Brightcove Element Without PlayerMedia");
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Test(priority = 5)
public void createBlankBrightCoveStory()
	{
		System.out.println("Start: createBlankBrightCoveStory");
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Blank Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BrightcoveElement.json");
		jsonBody.put("headline", storyTitle);

		Map storyCreateAPIResponse = Story.createStory(jsonBody,configObject.basicAuth);

		Map sketchesAPIResponse = StoryCreationEndPoints.verifyCreatedStoryOnSketches(configObject.basicAuth, 200);
		Map sketchesAmpAPIResponse = StoryCreationEndPoints.verifyCreatedStoryAsAMPOnSketches(configObject.basicAuth, 200);

		verifyBlankBrightCoveStory(sketchesAPIResponse);
		verifyBlankBrightCoveStory(sketchesAmpAPIResponse);

		System.out.println("Published Blank Story with Brightcove Element & Verified.");
	}

	@SuppressWarnings("rawtypes")
	public void verifyBlankBrightCoveStory(Map Response) {
		//Extracting the brightcove element
		Map story = JSONUtilities.getInnerJSON(Response, "story");
		ArrayList<Map> cards = JSONUtilities.getArray(story,"cards");
		Map brightcoveElement = JSONUtilities.getArray(cards.get(9), "story-elements").get(0);

		Map metadata = JSONUtilities.getInnerJSON(brightcoveElement, "metadata");

		Assert.assertEquals(brightcoveElement.get("type"), "external-file");
		Assert.assertEquals(brightcoveElement.get("subtype"), "brightcove-video");
		Assert.assertEquals(true, metadata.containsKey("account-id"));
		Assert.assertEquals(true, metadata.containsKey("embed-code"));
		Assert.assertEquals(true, metadata.containsKey("player-id"));
		Assert.assertEquals(true, metadata.containsKey("player-media"));
		Assert.assertEquals(true, metadata.containsKey("video-id"));
		Assert.assertNotNull(brightcoveElement.get("file-type"));

	}

	
}

