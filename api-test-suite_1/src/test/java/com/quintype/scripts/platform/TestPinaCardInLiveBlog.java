package com.quintype.scripts.platform;

import com.google.gson.JsonObject;
import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.ItsmanToggleEndpoints;
import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestPinaCardInLiveBlog extends SetUp {
	Logger logger = LogManager.getLogger();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(priority = 1)
	public void pinaCardInLiveBlogStoryAndVerifyOnSketches() {
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "LiveBlog Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/LiveBlogStory.json");
		jsonBody.put("headline", storyTitle);

		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID,
				configObject.publisherID, 201, jsonBody.toString());
		String storyVersionID = ((Map) storyCreateAPIResponse.get("story-version")).get("id").toString();
		String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();

		Assert.assertNotNull(storyVersionID, "Story Version ID is null");
		Assert.assertNotNull(storyContentID, "Story Content ID is null");

		StoryCreationEndPoints.submitStory(configObject.basicAuth, 201);
		StoryCreationEndPoints.approveStory(configObject.basicAuth, 201);
		StoryCreationEndPoints.publishStory(configObject.basicAuth, 201);
		Map sketchesAPIResponse = StoryCreationEndPoints.verifyCreatedStoryOnSketches(configObject.basicAuth, 200);
		Map story = JSONUtilities.getInnerJSON(sketchesAPIResponse, "story");
		ArrayList cards = JSONUtilities.getInnerJSON1(story, "cards");
		String fCard = cards.get(0).toString();
		Assert.assertTrue(fCard.contains("is-pinned?=true") && fCard.contains("text=Second Title"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(priority = 1)
	public void tryPinningtwoCardsAndExpect422whenStoryIsSubmit() {
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "LiveBlog Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/LiveBlogStory.json");
		jsonBody.put("headline", storyTitle);
		Map cards = JSONUtilities.getInnerJSON(jsonBody, "cards");

		Set<String> cardKeys = cards.keySet();
		Object[] cardKeysArray = cardKeys.toArray();
		Map firstCard = JSONUtilities.getInnerJSON(cards, cardKeysArray[1].toString());
		JSONObject obj = JSONUtilities.getJSONFileObject("./src/test/resources/pincard.json");
		firstCard.put("metadata", obj);
		cards.put(cardKeysArray[1].toString(), firstCard);
		jsonBody.put("cards", cards);

		Map secondCard = JSONUtilities.getInnerJSON(cards, cardKeysArray[0].toString());
		secondCard.put("metadata", obj);
		cards.put(cardKeysArray[0].toString(), secondCard);
		jsonBody.put("cards", cards);

		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID,
				configObject.publisherID, 201, jsonBody.toString());
		String storyVersionID = ((Map) storyCreateAPIResponse.get("story-version")).get("id").toString();
		String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();

		Assert.assertNotNull(storyVersionID, "Story Version ID is null");
		Assert.assertNotNull(storyContentID, "Story Content ID is null");

		StoryCreationEndPoints.submitStory(configObject.basicAuth, 422);
	}
}
