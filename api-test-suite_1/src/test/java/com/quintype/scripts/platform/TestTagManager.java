package com.quintype.scripts.platform;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.TagManagerEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.quintype.utils.Story;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TestTagManager extends SetUp {

    Logger logger = LogManager.getLogger();
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test(priority = 1)
    public void addTags() throws InterruptedException {

        ConfigFile configObject = SetUp.getConfigObject();
        int statusCode = 200;
        double storyCount = 2.0;
        String tagName = DataAndTimeUtilities.getCurrentDateForEmail();
        String tagMetaDescription = "Meta Description for "+DataAndTimeUtilities.getCurrentDateForEmail();
        String basicAuth = configObject.basicAuth1;
        String jsonBodyTags = "{\"name\":\""+tagName+"\",\"meta-description\":\""+tagMetaDescription+"\",\"slug\":\""+tagName+"\"}";



        int tagID = TagManagerEndPoints.createTag(basicAuth,jsonBodyTags,statusCode,configObject.itsmanURL);
        logger.info("Successfully created a tag with id: "+ tagID);



        String storyTitleBlankStory = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBodyBlankStory = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");
        jsonBodyBlankStory.put("headline", storyTitleBlankStory);
        JSONUtilities.getArray(jsonBodyBlankStory,"tags").get(0).put("id",tagID);
        JSONUtilities.getArray(jsonBodyBlankStory,"tags").get(0).put("name",tagName);
        JSONUtilities.getArray(jsonBodyBlankStory,"tags").get(0).put("meta-description",tagMetaDescription);


        Map storyCreateAPIResponse = Story.createStory(jsonBodyBlankStory, configObject.basicAuth);
        logger.info("Created a blank story with a story id: "+((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString()+ "with Tag id: "+tagID);


        String storyTitlePhotoStory = "Photo Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBodyPhotoStory = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
        jsonBodyPhotoStory.put("headline", storyTitlePhotoStory);
        JSONUtilities.getArray(jsonBodyPhotoStory,"tags").get(0).put("id",tagID);
        JSONUtilities.getArray(jsonBodyPhotoStory,"tags").get(0).put("name",tagName);
        JSONUtilities.getArray(jsonBodyPhotoStory,"tags").get(0).put("meta-description",tagMetaDescription);

        Map photoStoryCreateAPIResponse = Story.createStory(jsonBodyPhotoStory, configObject.basicAuth);
        logger.info("Created a Photo story with a story id: "+((Map) photoStoryCreateAPIResponse.get("story-version")).get("story-content-id").toString()+ "with Tag id: "+tagID);

        //Validate Created By
        Map tagResponse = TagManagerEndPoints.validateCreatedAtandCreatedBy(basicAuth,statusCode,tagName,configObject.itsmanURL);
        String expectedTagAuthor = configObject.username1;
        Assert.assertEquals(JSONUtilities.getArray(tagResponse,"tags").get(0).get("member-name").toString(),expectedTagAuthor);
        logger.info("Verified Tag Created By: "+expectedTagAuthor+" for tag-id: "+tagID);

        //Validate Story Count, validation is flaky. Needs to be fixed
        Thread.sleep(5000);
        Map storyCountResponse = TagManagerEndPoints.storyCountForTag(basicAuth,statusCode,tagID,configObject.itsmanURL);
        Assert.assertEquals(storyCountResponse.get("hits"),storyCount);
        logger.info("Verified number of stories using Tags: "+ storyCount+" for tag-id: "+tagID);

    }
}
