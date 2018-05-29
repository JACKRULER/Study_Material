package com.quintype.scripts.platform;
import com.google.gson.JsonObject;
import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.AttributeCreationEndPoints;
import com.quintype.endpoints.platform.AuthorEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;
import java.io.*;


public class TestAdminCreateStoryAttributes extends SetUp {

    Logger logger = LogManager.getLogger();

    @Test(priority = 1)
    public void createStoryAttributeAndVerify(){
        logger.info("Starting Story attribute creation");
        ConfigFile configObject = SetUp.getConfigObject();
        String attributeDisplayName = "StoryAttribute:"+DataAndTimeUtilities.getCurrentDateAndTime();


        Map itsmanResponse =  AttributeCreationEndPoints.getStoryAttributesItsmanResponse(configObject.basicAuth,200);
        ArrayList<Map> responseArray = JSONUtilities.getArray(itsmanResponse, "story-attributes");
        JSONArray attributeArrayFromDataFile = JSONUtilities.getInnerJSONArrayData("./src/test/resources/StoryAttributes.json", "story-attributes");
        setDisplayName(attributeDisplayName, attributeArrayFromDataFile);

        responseArray.add((Map) attributeArrayFromDataFile.get(0));
        org.json.JSONObject jsonBodyAppendedToResponse = new org.json.JSONObject(itsmanResponse);
        AttributeCreationEndPoints.createStoryAttributes(configObject.basicAuth,201,jsonBodyAppendedToResponse.toString());
        itsmanResponse =  AttributeCreationEndPoints.getStoryAttributesItsmanResponse(configObject.basicAuth,200);
        responseArray = JSONUtilities.getArray(itsmanResponse, "story-attributes");
        boolean flag = false;
        for(int i=0;i<responseArray.size();i++) {
            if (responseArray.get(i).get("display-name").equals(attributeDisplayName) && responseArray.get(i).get("type").equals("story")) {
                flag = true;
                break;

            }
        }

        Assert.assertTrue(flag);
        logger.info("Created story attribute and verified on Itsman response");

    }

    @Test(priority = 2)
    public void createCardAttributeAndVerify(){
        logger.info("Starting Card attribute creation");
        ConfigFile configObject = SetUp.getConfigObject();
        String attributeDisplayName = "Card Attribute:"+DataAndTimeUtilities.getCurrentDateAndTime();
        String type = "card";

        Map itsmanResponse =  AttributeCreationEndPoints.getStoryAttributesItsmanResponse(configObject.basicAuth,200);
        ArrayList<Map> responseArray = JSONUtilities.getArray(itsmanResponse, "story-attributes");
        JSONArray attributeArrayFromDataFile = JSONUtilities.getInnerJSONArrayData("./src/test/resources/StoryAttributes.json", "story-attributes");
        setDisplayName(attributeDisplayName, attributeArrayFromDataFile);
        setType(type,attributeArrayFromDataFile);

        responseArray.add((Map) attributeArrayFromDataFile.get(0));
        org.json.JSONObject jsonBodyAppendedToResponse = new org.json.JSONObject(itsmanResponse);
        AttributeCreationEndPoints.createStoryAttributes(configObject.basicAuth,201,jsonBodyAppendedToResponse.toString());
        itsmanResponse =  AttributeCreationEndPoints.getStoryAttributesItsmanResponse(configObject.basicAuth,200);
        responseArray = JSONUtilities.getArray(itsmanResponse, "story-attributes");
        boolean flag = false;
        for(int i=0;i<responseArray.size();i++) {
            if (responseArray.get(i).get("display-name").equals(attributeDisplayName) && responseArray.get(i).get("type").equals("card")) {
                flag = true;
                break;

            }
        }

        Assert.assertTrue(flag);
        logger.info("Created card attribute and verified on Itsman response");
    }
    public void editstoryAttributeAndVerify(){
        logger.info("Starting edit Card attribute creation");
        ConfigFile configObject = SetUp.getConfigObject();
        String attributeDisplayName = "Story Attribute:"+DataAndTimeUtilities.getCurrentDateAndTime();

        Map itsmanResponse =  AttributeCreationEndPoints.getStoryAttributesItsmanResponse(configObject.basicAuth,200);
        ArrayList<Map> responseArray = JSONUtilities.getArray(itsmanResponse, "story-attributes");
        JSONArray attributeArrayFromDataFile = JSONUtilities.getInnerJSONArrayData("./src/test/resources/StoryAttributes.json", "story-attributes");
        setDisplayName(attributeDisplayName, attributeArrayFromDataFile);

        responseArray.add((Map) attributeArrayFromDataFile.get(0));
        logger.info(responseArray);
        org.json.JSONObject jsonBodyAppendedToResponse = new org.json.JSONObject(itsmanResponse);
        AttributeCreationEndPoints.createStoryAttributes(configObject.basicAuth,201,jsonBodyAppendedToResponse.toString());
        itsmanResponse =  AttributeCreationEndPoints.getStoryAttributesItsmanResponse(configObject.basicAuth,200);
        responseArray = JSONUtilities.getArray(itsmanResponse, "story-attributes");
        boolean flag = false;
        for(int i=0;i<responseArray.size();i++) {
            if (responseArray.get(i).get("display-name").equals(attributeDisplayName) && responseArray.get(i).get("type").equals("card")) {
                flag = true;
                break;

            }
        }

        Assert.assertTrue(flag);
        logger.info("edited card attribute and verified on Itsman response");
    }

    private void setDisplayName(String attributeDisplayName, JSONArray attributeArrayFromDataFie) {
        Map dataAttribute = (Map) attributeArrayFromDataFie.get(0);
        dataAttribute.put("display-name",attributeDisplayName);
    }

    private void setType(String attributeType, JSONArray attributeArrayFromDataFie) {
        Map dataAttribute = (Map) attributeArrayFromDataFie.get(0);
        dataAttribute.put("type",attributeType);
    }

}
