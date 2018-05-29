package com.quintype.scripts.platform;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.ConfigEndpoints;
import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import com.quintype.utils.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

public class TestRelatedStories extends SetUp{
    ConfigFile configObject;
    Logger logger = LogManager.getLogger();
    @SuppressWarnings({ "rawtypes", "unchecked" })
@Test
    public void testRelatedStoriesUsingSectionId() {

        logger.info("Starting testRelatedStoriesUsingSections test ");
        configObject = SetUp.getConfigObject();
        // The below logic needs to be changed like when a particular section is not exists then create as prerequisite
        String sectionId="6689";
        String storyTitle1 = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");
        jsonBody1.put("headline", storyTitle1);
        ArrayList<Map> sectionArray = JSONUtilities.getArray(jsonBody1, "sections");
        Map sectionJson = sectionArray.get(0);
        sectionJson.put("id",Integer.parseInt(sectionId));
        Map storyResponse1 = Story.createStory(jsonBody1, configObject.basicAuth);
        String storyContentId = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();
        logger.info("Created first story "+storyContentId+ " under section with id " + sectionId);

        Map relatedStoryResponse = StoryCreationEndPoints.getRelatedStoriesbySectionId(storyContentId, Integer.parseInt(sectionId), 200, configObject);
        ArrayList<Map> relatedstoriesArray = JSONUtilities.getArray(relatedStoryResponse, "related-stories");
        int size = relatedstoriesArray.size();
        logger.info("There are "+size + " stories in the related story API response");
        for (int i=0;i<size;i++) {
            sectionArray = JSONUtilities.getArray(relatedstoriesArray.get(i),"sections");
            logger.info("Verifying story id : "+ relatedstoriesArray.get(i).get("id"));
            String responseSectionId = sectionArray.get(0).get("id").toString();
            Assert.assertEquals(Integer.parseInt(sectionId),(int)Double.parseDouble(responseSectionId));


        }


        logger.info("testRelatedStoriesUsingSectionId test Completed ");
    }


    }
