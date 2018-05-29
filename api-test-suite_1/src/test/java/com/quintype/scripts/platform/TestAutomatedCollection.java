package com.quintype.scripts.platform;

import java.util.ArrayList;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.StoryCollectionEndPoint;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import com.quintype.utils.Story;


public class TestAutomatedCollection extends SetUp 
{
	Logger logger = LogManager.getLogger();

	ConfigFile configObject;
	@SuppressWarnings({ "rawtypes", "unchecked" })
    //@Test(priority = 1)
    public void createAutomatedCollectionAndVerifyOnSketches() 
	{   ConfigFile configObject = SetUp.getConfigObject();

		String storyTitle = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle);
		Map storyResponse = Story.createStory(jsonBody, configObject.basicAuth);
		String storyContentID = ((Map) storyResponse.get("story-version")).get("story-content-id").toString();

		String storyTitle1 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody1.put("headline",storyTitle1);
		Map storyResponse1 = Story.createStory(jsonBody1, configObject.basicAuth);
		String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();

		String storyTitle2 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody2 = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody2.put("headline",storyTitle2);
		Map storyResponse2 = Story.createStory(jsonBody2, configObject.basicAuth);
		String storyContentID2 = ((Map) storyResponse2.get("story-version")).get("story-content-id").toString();

		String collectionTitle1 = "Test Collection: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody3 = JSONUtilities.getJSONFileObject("./src/test/resources/manualCollection.json");
		jsonBody3.put("name", collectionTitle1);

		Map createCollectionAPIResponse1 = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,jsonBody3.toString());
		String collectionID1 = createCollectionAPIResponse1.get("id").toString().split("\\.")[0];

		// Without this wait this test is failing. Getting 500 status code while creating 2 collection.
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String collectionTitle2 = "Test Collection: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody4 = JSONUtilities.getJSONFileObject("./src/test/resources/manualCollection.json");
		jsonBody4.put("name", collectionTitle2);

		Map createCollectionAPIResponse2 = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,jsonBody4.toString());
		String collectionID2 = createCollectionAPIResponse2.get("id").toString().split("\\.")[0];

		String automatedCollectionTitle = "Automated Coll: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject automatedCollectionJSON = JSONUtilities.getJSONFileObject("./src/test/resources/AutomatedCollection.json");

		((JSONArray) automatedCollectionJSON.get("story-content-ids")).set(0, storyContentID1);
		((JSONArray) automatedCollectionJSON.get("story-content-ids")).set(1, collectionID1);
		((JSONArray) automatedCollectionJSON.get("story-content-ids")).set(2, storyContentID2);
		((JSONArray) automatedCollectionJSON.get("story-content-ids")).set(3, collectionID2);

		String ids[] = {storyContentID1, collectionID1, storyContentID2, collectionID2};
		String type[] = {"story", "collection", "story", "collection"};

		int arraySize = ((JSONArray) automatedCollectionJSON.get("items")).size();

		for (int i = 0; i < arraySize; i++) {
			((JSONObject) ((JSONArray) automatedCollectionJSON.get("items")).get(i)).put("id", ids[i]);
			((JSONObject) ((JSONArray) automatedCollectionJSON.get("items")).get(i)).put("type", type[i]);
		}
		automatedCollectionJSON.put("name", automatedCollectionTitle);

		Map automatedCollectionResponse = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,automatedCollectionJSON.toString());
		Assert.assertEquals(automatedCollectionResponse.get("name"), automatedCollectionTitle);

		Map automatedCollectionSketchesResponse = StoryCollectionEndPoint.verifyCreatedCollectionOnSketches(configObject.basicAuth, 200);
		Assert.assertEquals(automatedCollectionResponse.get("name"), automatedCollectionTitle);
		Assert.assertEquals(JSONUtilities.getArrayValue(automatedCollectionSketchesResponse, "items", 0, "id"), storyContentID1);
		Assert.assertEquals(JSONUtilities.getArrayValue(automatedCollectionSketchesResponse, "items", 1, "id"), collectionID1);
		Assert.assertEquals(JSONUtilities.getArrayValue(automatedCollectionSketchesResponse, "items", 2, "id"), storyContentID2);
		Assert.assertEquals(JSONUtilities.getArrayValue(automatedCollectionSketchesResponse, "items", 3, "id"), collectionID2);
		ArrayList<Map> itemsArray = JSONUtilities.getArray(automatedCollectionSketchesResponse, "items");

		Assert.assertTrue(itemsArray.size()>4);

		System.out.println("Verified Automated Collection with Stories & Collection");


	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(priority = 2)
	public void createAutomatedCollectionWithAuthorAndVerifyOnSketches() throws InterruptedException, JSONException {
        ConfigFile configObject = SetUp.getConfigObject();
		logger.info("Starting add Author to collection test");

		String authorArray = ("{\\r\\n\\\"updated-at\\\": 1527544928081,\\r\\n        \\\"email\\\": \\\"28May2018220207@gmail.com\\\",\\r\\n        \\\"last-name\\\": null,\\r\\n        \\\"publisher-id\\\": 107,\\r\\n        \\\"name\\\": \\\"28May2018220207\\\",\\r\\n        \\\"avatar-url\\\": null,\\r\\n        \\\"source\\\": null,\\r\\n        \\\"first-name\\\": null,\\r\\n        \\\"communication-email\\\": \\\"28May2018220207@gmail.com\\\",\\r\\n        \\\"bio\\\": \\\"QWERTY\\\",\\r\\n        \\\"id\\\": 232863,\\r\\n        \\\"avatar-s3-key\\\": null,\\r\\n        \\\"twitter-handle\\\": null,\\r\\n        \\\"created-at\\\": 1527544928081\\r\\n    }");

        int authorID = 232863;
        String authorName = "28May2018220207";
        String storyTitle1 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle1);
		jsonBody.put("author-id",authorID);
        jsonBody.put("author-name",authorName);

        JSONObject authorJson = (JSONObject) JSONUtilities.getInnerJSONAtrrayData(jsonBody, "authors").get(0);
        jsonBody.put(authorJson,authorArray);
        //Map authorMap = JSONUtilities.getJSONArrayToMap(authorJson, 0).putAll(authorArray);
        //authorMap.put("name", authorName);
        //authorMap.put("id", authorID);

        //org.json.JSONArray jsonArr = JSONUtilities.mapToJSONArray(authorMap);
        //jsonBody.put("authors", jsonArr);



        Map storyResponse1 = Story.createStory(jsonBody, configObject.basicAuth);
		String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();

		String storyTitle2 = "Photo Story1: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody1.put("headline",storyTitle2);

        jsonBody1.put("headline",storyTitle2);
        jsonBody1.put("author-id",authorID);
        jsonBody1.put("author-name",authorName);

        JSONObject authorJson1 = (JSONObject) JSONUtilities.getInnerJSONAtrrayData(jsonBody1, "authors").get(0);
        jsonBody.put(authorJson1,authorArray);


        Map storyResponse2 = Story.createStory(jsonBody1, configObject.basicAuth);
        String storyContentID2 = ((Map) storyResponse2.get("story-version")).get("story-content-id").toString();



        String collectionTitle1 = "Test Collection: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody3 = JSONUtilities.getJSONFileObject("./src/test/resources/AutomatedCollection.json");
		JSONArray array =new JSONArray();
		jsonBody3.put("name", collectionTitle1);
		jsonBody3.put("story-content-ids",array);
		jsonBody3.put("items",array);
		JSONUtilities.getInnerJSON(jsonBody3,"rules").put("author-id","232863");
		JSONUtilities.getInnerJSON(jsonBody3,"rules").put("section-id","6648");

		Map automatedCollectionResponse = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,jsonBody3.toString());
		Double d = (Double) automatedCollectionResponse.get("id");
		int collectionID = d.intValue();
		Assert.assertEquals(automatedCollectionResponse.get("name"), collectionTitle1);

		Map automatedCollectionSketchesResponse = StoryCollectionEndPoint.verifyCollectionsonsketcheswithURL(configObject.sketchesURL,configObject.basicAuth, 200,collectionID);
		// Without this wait this test is failing. 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(automatedCollectionSketchesResponse.get("name"), collectionTitle1);
		Assert.assertEquals(JSONUtilities.getArrayValue(automatedCollectionSketchesResponse, "items", 0, "id"), storyContentID2);
		Assert.assertEquals(JSONUtilities.getArrayValue(automatedCollectionSketchesResponse, "items", 1, "id"), storyContentID1);


	}
}
