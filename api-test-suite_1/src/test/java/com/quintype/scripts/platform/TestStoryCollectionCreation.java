package com.quintype.scripts.platform;

import java.util.Map;

import com.quintype.endpoints.platform.StoryCollectionEndPoint;
import com.quintype.utils.*;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.Story;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.quintype.config.ConfigFile;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
public class TestStoryCollectionCreation extends SetUp
{
	ConfigFile configObject;
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Test(priority = 1)
    public void createStoryCollectionAndVerifyOnSketches() {

		configObject = SetUp.getConfigObject();
		
		String storyTitle1 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle1);
		Map storyResponse1 = Story.createStory(jsonBody, configObject.basicAuth);
        String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();

        String storyTitle2 = "Photo Story1: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody1.put("headline",storyTitle2);
		Map storyResponse2 = Story.createStory(jsonBody1, configObject.basicAuth);
		String storyContentID2 = ((Map) storyResponse2.get("story-version")).get("story-content-id").toString();

		String collectionTitle = "Test Collection: "+DataAndTimeUtilities.getCurrentDateAndTime();
    	JSONObject collectionJsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/manualCollection.json");
    	collectionJsonBody.put("name", collectionTitle);  
    	
	    ((JSONArray) collectionJsonBody.get("story-content-ids")).set(0, storyContentID1);
	    ((JSONArray) collectionJsonBody.get("story-content-ids")).set(1, storyContentID2);
	    
	    int arraySize = ((JSONArray) collectionJsonBody.get("items")).size();
	    String ids[] = {storyContentID1, storyContentID2};

		for (int i = 0; i < arraySize; i++) {
			((JSONObject) ((JSONArray) collectionJsonBody.get("items")).get(i)).put("id", ids[i]);
		}

		Map createCollectionAPIResponse = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,collectionJsonBody.toString());
		Double d = (Double) createCollectionAPIResponse.get("id");
        int collectionID1 = d.intValue();
		Map createCollectionSketchesResponse = StoryCollectionEndPoint.verifyCreatedCollectionOnSketches(configObject.basicAuth, 200);		
		Assert.assertEquals(createCollectionAPIResponse.get("name"), collectionTitle);
		Assert.assertEquals(JSONUtilities.getArrayValue(createCollectionSketchesResponse, "items", 0, "id"), storyContentID1);
		Assert.assertEquals(JSONUtilities.getArrayValue(createCollectionSketchesResponse, "items", 1, "id"), storyContentID2);
		Double d1 = (Double) createCollectionSketchesResponse.get("id");
        int responseCollectionID = d1.intValue();
		Assert.assertEquals(collectionID1,responseCollectionID);
		
		System.out.println("Verified manual Collection with Stories");

    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Test(priority = 2)
    public void VerifyCollectionOnSketcheswithlimit() {
		configObject = SetUp.getConfigObject();

		String storyTitle1 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle1);
		Map storyResponse1 = Story.createStory(jsonBody, configObject.basicAuth);
        String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();

        String storyTitle2 = "Photo Story1: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody1.put("headline",storyTitle2);
		Map storyResponse2 = Story.createStory(jsonBody1, configObject.basicAuth);
		String storyContentID2 = ((Map) storyResponse2.get("story-version")).get("story-content-id").toString();    	
    	
		String collectionTitle = "Test Collection2: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject collectionJson = JSONUtilities.getJSONFileObject("./src/test/resources/manualCollection.json");
		collectionJson.put("name", collectionTitle);
		((JSONArray) collectionJson.get("story-content-ids")).set(0, storyContentID1);
		((JSONArray) collectionJson.get("story-content-ids")).set(1, storyContentID2);
		 int arraySize = ((JSONArray) collectionJson.get("items")).size();
		    String ids[] = {storyContentID1, storyContentID2};

			for (int i = 0; i < arraySize; i++) {
				((JSONObject) ((JSONArray) collectionJson.get("items")).get(i)).put("id", ids[i]);
			}

			Map createCollectionAPIResponse = Collection.createCollection(collectionJson, configObject.basicAuth);
 			String collectionID = createCollectionAPIResponse.get("id").toString();
     		String collectionSlug = createCollectionAPIResponse.get("slug").toString();
     		
     		//sketches response of collections
			Map collectionResponse = StoryCollectionEndPoint.verifyCreatedCollectionOnSketcheswithlimit(configObject.basicAuth, 200);
			String responseCollectionID = collectionResponse.get("id").toString();
			String responseCollectionSlug = JSONUtilities.getValueFromResponse(collectionResponse,"slug");
			Assert.assertEquals(collectionID,responseCollectionID);
			System.out.println("Validated collection ID");
			Assert.assertEquals(collectionSlug,responseCollectionSlug);
			System.out.println("Validated collection slug");
						
			Assert.assertEquals(JSONUtilities.getArrayValue(collectionResponse, "items", 0, "id"), storyContentID1);
			Assert.assertEquals(JSONUtilities.getArrayValue(collectionResponse, "items", 1, "id"), storyContentID2);


		}
    }


    		


