package com.quintype.scripts.platform;

import com.google.gson.JsonObject;
import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.*;
import com.quintype.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.io.*;

public class TestAuthorProfile extends SetUp {

    Logger logger = LogManager.getLogger();

    @Test(priority = 1)
    public void createAuthorProfileAndVerifyOnSketchesStoryByID() throws InterruptedException, JSONException {
        logger.info("started creating author profile");
        ConfigFile configObject = SetUp.getConfigObject();
        JSONObject json = JSONUtilities.getJSONFileObject("./src/test/resources/AuthorProfile.json");

        AuthorEndPoints.createAuthorProfile(configObject.basicAuth, json.toString(), 200);
        String authorName = json.get("name").toString();
        String bio = json.get("bio").toString();

        String storyTitle = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");

        JSONArray authorJson = JSONUtilities.getInnerJSONAtrrayData(jsonBody1, "authors");
        Map authorMap = JSONUtilities.getJSONArrayToMap(authorJson, 0);
        authorMap.put("name", authorName);
        authorMap.put("bio", bio);

        org.json.JSONArray jsonArr = JSONUtilities.mapToJSONArray(authorMap);
        jsonBody1.put("authors", jsonArr);
        jsonBody1.put("headline", storyTitle);

        Map storyCreateAPIResponse = Story.createStory(jsonBody1, configObject.basicAuth);
        String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();

        Map sketchesAPIResponse = AuthorEndPoints.getStorybyId(storyContentID, 200);
        Assert.assertEquals(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story").get("story-content-id"), storyContentID);
        Assert.assertEquals(((Map) sketchesAPIResponse.get("story")).get("headline"), storyTitle);
        String actualAuthorName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "authors", 0, "name");
        Assert.assertEquals(actualAuthorName, authorName);
        String actualBio = JSONUtilities.getActualArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "authors", 0, "bio");
        Assert.assertEquals(actualBio, bio);

        logger.info("Completed verifyCreated Author Profile Appearing On Story by ID");


    }
    @Test(priority = 2)
    public void createAuthorProfileAndVerifyOnSketchesStoryBySlug() throws InterruptedException, JSONException {

        logger.info("started creating author profile");
        ConfigFile configObject = SetUp.getConfigObject();
        JSONObject json = JSONUtilities.getJSONFileObject("./src/test/resources/AuthorProfile.json");

        AuthorEndPoints.createAuthorProfile(configObject.basicAuth, json.toString(), 200);
        String authorName = json.get("name").toString();
        String bio = json.get("bio").toString();

        String storyTitle = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");

        JSONArray authorJson = JSONUtilities.getInnerJSONAtrrayData(jsonBody1, "authors");
        Map authorMap = JSONUtilities.getJSONArrayToMap(authorJson, 0);
        authorMap.put("name", authorName);
        authorMap.put("bio", bio);

        org.json.JSONArray jsonArr = JSONUtilities.mapToJSONArray(authorMap);
        jsonBody1.put("authors", jsonArr);
        jsonBody1.put("headline", storyTitle);

        Map storyCreateAPIResponse = Story.createStory(jsonBody1, configObject.basicAuth);
        String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();

        Map sketchesAPIResponse = AuthorEndPoints.getStorybyId(storyContentID, 200);
        String slug = JSONUtilities.getInnerJSON(sketchesAPIResponse,"story").get("slug").toString();

        Map sketchesAPIResponse1=AuthorEndPoints.getStorybySlug(slug,200);

        String actualAuthorName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse1, "story"), "authors", 0, "name");
        Assert.assertEquals(actualAuthorName, authorName);
        String actualBio = JSONUtilities.getActualArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse1, "story"), "authors", 0, "bio");
        Assert.assertEquals(actualBio, bio);

        logger.info("Completed verifyCreated Author Profile Appearing On Story by Slug");

    }
    @Test(priority = 3)
    public void createAuthorProfileAndVerifyOnSketchesCollections() throws InterruptedException, JSONException {

        logger.info("started creating author profile");
        ConfigFile configObject = SetUp.getConfigObject();
        JSONObject json = JSONUtilities.getJSONFileObject("./src/test/resources/AuthorProfile.json");

        AuthorEndPoints.createAuthorProfile(configObject.basicAuth, json.toString(), 200);
        String authorName = json.get("name").toString();
        String bio = json.get("bio").toString();

        String storyTitle = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");

        JSONArray authorJson = JSONUtilities.getInnerJSONAtrrayData(jsonBody1, "authors");
        Map authorMap = JSONUtilities.getJSONArrayToMap(authorJson, 0);
        authorMap.put("name", authorName);
        authorMap.put("bio", bio);

        org.json.JSONArray jsonArr = JSONUtilities.mapToJSONArray(authorMap);
        jsonBody1.put("authors", jsonArr);
        jsonBody1.put("headline", storyTitle);

        Map storyCreateAPIResponse = Story.createStory(jsonBody1, configObject.basicAuth);
        String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();

        String collectionTitle = "Test Collection: "+DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject collectionJsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/manualCollection.json");
        collectionJsonBody.put("name", collectionTitle);

        ((JSONArray) collectionJsonBody.get("story-content-ids")).set(0, storyContentID);
        ((JSONArray) collectionJsonBody.get("story-content-ids")).remove(1);

        ((JSONObject) ((JSONArray) collectionJsonBody.get("items")).get(0)).put("id", storyContentID);

        Map createCollectionAPIResponse = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,collectionJsonBody.toString());
        Double d = (Double) createCollectionAPIResponse.get("id");
        int collectionID = d.intValue();

        Map createCollectionSketchesResponse= StoryCollectionEndPoint.verifyCreatedCollectionOnSketcheswithItemType(configObject.basicAuth,collectionID,200);
        Object storyJson = (Map)JSONUtilities.getArrayValueToMap(createCollectionSketchesResponse,"items",0,"story");
        String actualAuthor = (JSONUtilities.getArrayValue((Map) storyJson,"authors",0,"name").toString());
        Assert.assertEquals(actualAuthor,authorName);

        String actualBio = (JSONUtilities.getActualArrayValue((Map) storyJson,"authors",0,"bio").toString());
        Assert.assertEquals(actualBio,bio);
        logger.info("Completed verifyCreated Author Profile Appearing On collection");

    }
    @Test(priority = 4)
    public void createAuthorProfileAndVerifyOnSketchesCollectionsExcludingStory() throws InterruptedException, JSONException {

        logger.info("started creating author profile");
        ConfigFile configObject = SetUp.getConfigObject();
        JSONObject json = JSONUtilities.getJSONFileObject("./src/test/resources/AuthorProfile.json");

        AuthorEndPoints.createAuthorProfile(configObject.basicAuth, json.toString(), 200);
        String authorName = json.get("name").toString();
        String bio = json.get("bio").toString();

        String storyTitle = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");

        JSONArray authorJson = JSONUtilities.getInnerJSONAtrrayData(jsonBody1, "authors");
        Map authorMap = JSONUtilities.getJSONArrayToMap(authorJson, 0);
        authorMap.put("name", authorName);
        authorMap.put("bio", bio);

        org.json.JSONArray jsonArr = JSONUtilities.mapToJSONArray(authorMap);
        jsonBody1.put("authors", jsonArr);
        jsonBody1.put("headline", storyTitle);

        Map storyCreateAPIResponse = Story.createStory(jsonBody1, configObject.basicAuth);
        String storyContentID1 = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();

        String storyTitle2 = "Photo Story1: "+DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody2 = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");

        JSONArray authorJson1 = JSONUtilities.getInnerJSONAtrrayData(jsonBody2, "authors");
        Map authorMap1 = JSONUtilities.getJSONArrayToMap(authorJson1, 0);
        authorMap1.put("name", authorName);
        authorMap1.put("bio", bio);

        org.json.JSONArray jsonArr1 = JSONUtilities.mapToJSONArray(authorMap1);
        jsonBody2.put("authors", jsonArr1);
        jsonBody2.put("headline", storyTitle2);

        Map storyResponse2 = Story.createStory(jsonBody2, configObject.basicAuth);
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

        Map createCollectionAPIResponse = StoryCollectionEndPoint.createCollection(configObject.basicAuth, 201,collectionJson.toString());
        Double d = (Double) createCollectionAPIResponse.get("id");
        int collectionID = d.intValue();

        Map sketchesResponse= StoryCollectionEndPoint.verifyCreatedCollectionOnSketchesExcludingStoryID(configObject.basicAuth,collectionID,storyContentID1,200);

        Object storyJson = (Map)JSONUtilities.getArrayValueToMap(sketchesResponse,"items",0,"story");
        String actualAuthor = (JSONUtilities.getArrayValue((Map) storyJson,"authors",0,"name").toString());
        Assert.assertEquals(actualAuthor,authorName);

        String actualBio = (JSONUtilities.getActualArrayValue((Map) storyJson,"authors",0,"bio").toString());
        Assert.assertEquals(actualBio,bio);
        logger.info("Completed verifyCreated Author Profile Appearing On collection by excluding story");


    }
    }
