package com.quintype.scripts.platform;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.ConfigPageEndPoints;
import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.quintype.utils.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPromotionalMessagePage extends SetUp
{
	Logger logger = LogManager.getLogger();

	@SuppressWarnings("rawtypes")
	@Test(priority = 1)
    public void testDefaultPromotionalMessageAndVerifyOnSketches()
    {
		ConfigFile configObject = SetUp.getConfigObject();
		String expectedSlug = "promotional-message";
		String cardID ="00000000-0000-0000-0000-000000000000";

    	List<String> expectedText = ConfigPageEndPoints.verifyPromotionalMessages(expectedSlug, 200);
    	String defaultMessage=expectedText.get(0).toString();

		String storyTitle1 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle1);
		Map storyResponse1 = Story.createStory(jsonBody, configObject.basicAuth);
		String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();
		Map sketchesResponse = StoryCreationEndPoints.verifyCreatedStoryOnSketchesWithContrntID(configObject.basicAuth,200,configObject.sketchesURL,storyContentID1);
		ArrayList<Map> cardArray = (ArrayList<Map>) JSONUtilities.getArrayValueToMap(JSONUtilities.getInnerJSON(sketchesResponse, "story"), "cards", 0, "story-elements");
		int size=cardArray.size();
		Assert.assertEquals(cardArray.get(size-1).get("id"),cardID);
		Assert.assertEquals(cardArray.get(size-1).get("text"),defaultMessage);
    }
	@Test(priority = 2)
	public void testSyndicatedPromotionalMessageAndVerifyOnSketches(){

		ConfigFile configObject = SetUp.getConfigObject();
		String expectedSlug = "promotional-message";
		String cardID ="00000000-0000-0000-0000-000000000000";

		List<String> expectedText = ConfigPageEndPoints.verifyPromotionalMessages(expectedSlug, 200);
		String syndicatedMessage=expectedText.get(2).toString();
		String attributesJson = "{\"story-attributes\":{\"syndicatedfrom\":[\"thequint\"]}}";
		String storyTitle1 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");

		jsonBody.put("headline",storyTitle1);

		jsonBody.put("metadata",attributesJson);

		Map storyResponse1 = Story.createStory(jsonBody, configObject.basicAuth);
		String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();
		Map sketchesResponse = StoryCreationEndPoints.verifyCreatedStoryOnSketchesWithContrntID(configObject.basicAuth,200,configObject.sketchesURL,storyContentID1);
		ArrayList<Map> cardArray = (ArrayList<Map>) JSONUtilities.getArrayValueToMap(JSONUtilities.getInnerJSON(sketchesResponse, "story"), "cards", 0, "story-elements");
		int size=cardArray.size();
		Assert.assertEquals(cardArray.get(size-1).get("id"),cardID);
		Assert.assertEquals(cardArray.get(size-1).get("text"),syndicatedMessage);
	}

	@Test(priority = 3)
	public void testUGCPromotionalMessageAndVerifyOnSketches(){

		ConfigFile configObject = SetUp.getConfigObject();
		String expectedSlug = "promotional-message";
		String cardID ="00000000-0000-0000-0000-000000000000";

		List<String> expectedText = ConfigPageEndPoints.verifyPromotionalMessages(expectedSlug, 200);
		String UGCMessage=expectedText.get(1).toString();
		String attributesJson = "{\"story-attributes\":{\"ugc\":[\"contribution\"],\"syndicatedfrom\":[\"UGC\"]},\"imported-card-id\":\"09374078-4678-4334-9b0c-7b1da5f82bbe\"}";
		String storyTitle1 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle1);
		jsonBody.put("metadata",attributesJson);
		Map storyResponse1 = Story.createStory(jsonBody, configObject.basicAuth);
		String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();
		Map sketchesResponse = StoryCreationEndPoints.verifyCreatedStoryOnSketchesWithContrntID(configObject.basicAuth,200,configObject.sketchesURL,storyContentID1);
		ArrayList<Map> cardArray = (ArrayList<Map>) JSONUtilities.getArrayValueToMap(JSONUtilities.getInnerJSON(sketchesResponse, "story"), "cards", 0, "story-elements");
		int size=cardArray.size();
		logger.info(cardArray);
		Assert.assertEquals(cardArray.get(size-1).get("id"),cardID);
		Assert.assertEquals(cardArray.get(size-1).get("text"),UGCMessage);
	}


}



