package com.quintype.utils;

import com.quintype.endpoints.platform.StoryCollectionEndPoint;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collection
{

    @SuppressWarnings("rawtypes")
	public static Map createCollection(JSONObject collectionJsonBody, String basicAuth) {


        Map createCollectionAPIResponse = StoryCollectionEndPoint.createCollection(basicAuth, 201,collectionJsonBody.toString());
        Double d = (Double) createCollectionAPIResponse.get("id");
        int collectionID1 = d.intValue();

        Map collectionResponse = StoryCollectionEndPoint.verifyCreatedCollectionOnSketches(basicAuth, 200);
        Double d1 = (Double) collectionResponse.get("id");
        int responseCollectionID = d1.intValue();
        Assert.assertEquals(collectionID1,responseCollectionID);

        return createCollectionAPIResponse;

        }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONObject setStoryIDInCollectionJsonObject( List<String> storyContentID, JSONObject collectionJsonBody) {
        List<String> storyList = new ArrayList<String>();
        for(int i =0;i<storyContentID.size();i++)
            storyList.add(storyContentID.get(i));
        collectionJsonBody.put("story-content-ids",storyList);

        ArrayList<Map> itemsArray = JSONUtilities.getArray(collectionJsonBody, "items");
        ArrayList<Map> itemsArray1 = new ArrayList<Map>();

        for(int i=0;i<storyContentID.size();i++)
        {
            Map singleItem = new HashMap(itemsArray.get(0));
            singleItem.put("id",storyContentID.get(i));

            Map storyMapInsideItems = new HashMap(JSONUtilities.getInnerJSON(singleItem, "story"));

            storyMapInsideItems.put("id",storyContentID.get(i));
            singleItem.put("story",storyMapInsideItems);

            itemsArray1.add(i,singleItem);

        }

        collectionJsonBody.put("items",itemsArray);
        return collectionJsonBody;
    }
      
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONObject setCollectionIDInCollectionJsonObject(String collectionTitle1, int collectionID1, JSONObject collectionJsonBody) {
        List<Integer> storyList = new ArrayList<Integer>();
        storyList.add(collectionID1);
        collectionJsonBody.put("story-content-ids",storyList);
        ArrayList<Map> itemsArray = JSONUtilities.getArray(collectionJsonBody, "items");
        itemsArray.get(0).put("id",collectionID1);
		Map collectionMapInsideItems = JSONUtilities.getInnerJSON(itemsArray.get(0), "collection");
        collectionMapInsideItems.put("id",collectionID1);
        collectionMapInsideItems.put("name",collectionTitle1);
        itemsArray.get(0).put("collection",collectionMapInsideItems);
        collectionJsonBody.put("items",itemsArray);
        return collectionJsonBody;
    }
}
