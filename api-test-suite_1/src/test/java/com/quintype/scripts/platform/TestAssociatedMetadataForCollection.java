package com.quintype.scripts.platform;
import java.util.ArrayList;
import java.util.Map;

import com.quintype.config.AceConfigFile;
import com.quintype.endpoints.platform.StoryCollectionEndPoint;
import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.Story;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.quintype.config.ConfigFile;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;


public class TestAssociatedMetadataForCollection extends SetUp
{
     ConfigFile configObject;
	  @SuppressWarnings({ "rawtypes", "unchecked" })
      @Test(priority = 1)

      public void createAssociatedMetadataForCollectionAndVerifyOnSketches() {
        configObject = SetUp.getConfigObject();

        String storyTitle1 = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle1);
		Map storyResponse1 = Story.createStory(jsonBody, configObject.basicAuth);
        String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();
    	System.out.println(storyContentID1);

		String collectionTitle = "Test Collection: "+DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject collectionJsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/CollectionWithOneStory.json");
        collectionJsonBody.put("name", collectionTitle);
	    ((JSONArray) collectionJsonBody.get("story-content-ids")).set(0, storyContentID1);
	    int arraySize = ((JSONArray) collectionJsonBody.get("items")).size();
	    String ids[] = {storyContentID1};

		for (int i = 0; i < arraySize; i++) {
			((JSONObject) ((JSONArray) collectionJsonBody.get("items")).get(i)).put("id", ids[i]);
		}

        Map createCollectionAPIResponse = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,collectionJsonBody.toString());
        Double d = (Double) createCollectionAPIResponse.get("id");
        int collectionID1 = d.intValue();

        Map collectionResponse1 = StoryCollectionEndPoint.verifyCreatedCollectionOnSketches(configObject.basicAuth, 200);
    	String collectionID2 = collectionResponse1.get("id").toString().split("\\.")[0];


        String collectionTitle2 = "Test Collection1: "+ DataAndTimeUtilities.getCurrentDateAndTime();

        JSONObject collectionJsonBody2 = JSONUtilities.getJSONFileObject("./src/test/resources/CollectionWithAnotherCollection.json");
        collectionJsonBody2.put("name", collectionTitle2);
        
        ((JSONArray) collectionJsonBody2.get("story-content-ids")).set(0, storyContentID1);
	    ((JSONArray) collectionJsonBody2.get("story-content-ids")).set(1, collectionID1);
        
	    String ids1[] = {storyContentID1, collectionID2};
	    String type[] = {"story", "collection", "story", "collection"};
	    
		int arraySize1 = ((JSONArray) collectionJsonBody2.get("items")).size();

		for (int i = 0; i < arraySize1; i++) {
			((JSONObject) ((JSONArray) collectionJsonBody2.get("items")).get(i)).put("id", ids1[i]);
			((JSONObject) ((JSONArray) collectionJsonBody2.get("items")).get(i)).put("type", type[i]);
		}
		
		Map collectionResponse = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,collectionJsonBody2.toString());
        Double d2 = (Double)collectionResponse.get("id");
		int collectionIDOnResponse = d2.intValue();
        ArrayList<Map> itemsArray = JSONUtilities.getArray(collectionJsonBody, "items");
        Map collectionMapInsideItems = JSONUtilities.getInnerJSON(itemsArray.get(0), "associated-metadata");
        
        Map collectionResponseOnSketches = StoryCollectionEndPoint.verifyCreatedCollectionOnSketches(configObject.basicAuth, 200);
        Double d3 = (Double)collectionResponseOnSketches.get("id");
        int responseCollectionID1 = d3.intValue();
        ArrayList<Map> itemsArrayOnSketches = JSONUtilities.getArray(collectionResponseOnSketches, "items");
        Map collectionMapInsideItemsOnSketches = JSONUtilities.getInnerJSON(itemsArrayOnSketches.get(0), "associated-metadata");
        
    	Assert.assertEquals(responseCollectionID1,collectionIDOnResponse);
    	System.out.println("Validated collection ID");
    	Assert.assertEquals(collectionMapInsideItems,collectionMapInsideItemsOnSketches);
    	System.out.println("Validated Associated metadata");
		
    	System.out.println("Verified Collection with Associated metadata");

    }
}

