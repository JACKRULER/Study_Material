package com.quintype.scripts.platform;
import com.google.common.collect.ObjectArrays;
import com.google.gson.JsonArray;
import com.google.gson.internal.LinkedTreeMap;
import com.jayway.restassured.response.ValidatableResponse;
import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.ConfigEndpoints;
import com.quintype.endpoints.platform.StoryCollectionEndPoint;
import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAdminSections extends SetUp
{
    Logger logger = LogManager.getLogger();

    //	create a new section and replace the name given here in the section.json
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test(priority = 1)
    public void createSectionAndVerifyOnSketches() throws InterruptedException
    {
    	ConfigFile configObject = SetUp.getConfigObject();

    	String SectionTitle = "Test Section: "+DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/section.json");
        Map sectionMap = JSONUtilities.getInnerJSON(jsonBody, "section");
        sectionMap.put("name",SectionTitle);
        sectionMap.put("slug",SectionTitle);
        sectionMap.put("parent-id",6563);
        jsonBody.put("section", sectionMap);

        //Take the response of the created section and assert whether the section-id is null or not
        Map createSectionResponse = ConfigEndpoints.createSection(configObject.basicAuth, 201, jsonBody.toString());
        String SectionID = ((Map) createSectionResponse.get("section")).get("id").toString();
        Assert.assertNotNull(SectionID, "Section ID is null");

        //compare section name and slug from Itsman API

        List sectionResponse =  ConfigEndpoints.getSectionOnItsman(configObject.basicAuth,200);
        boolean flag=false;
        logger.info("looking for :"+ SectionID);
        for(int i=0;i<sectionResponse.size();i++){

            Map firstResponseItem = (Map) sectionResponse.get(i);
        	if((firstResponseItem.get("name").equals(SectionTitle)) &&(firstResponseItem.get("slug").equals(SectionTitle)) ){
                Double d  = (Double)firstResponseItem.get("collection-id");
                int collectionIDfromResponse = d.intValue();
        		flag = true;
        	}

        }
       Assert.assertEquals(true,flag);

        // Removing sketches validation - looks like the API response is not consistent. Neet to analyse.
//        //compare the created section details and the sketches response of the section details.
//        //logger.info("Waiting 30 seconds before the sections reflects on sketches: "+ DataAndTimeUtilities.getCurrentDateAndTime());
//        Thread.sleep(60000);
////        logger.info("Resuming : "+DataAndTimeUtilities.getCurrentDateAndTime());
//        Map sectionAPIResponse = ConfigEndpoints.getconfig(200);
//        ArrayList<Map> sectionsArray = JSONUtilities.getArray(sectionAPIResponse, "sections");
//
//        //  Assert.assertEquals(sectionsArray.size(),sectionCountBeforeNewSectionCreation+1);
//        boolean flag=false;
//        logger.info("looking for :"+ SectionID);
//
//        for(int i=0;i<sectionsArray.size();i++){
//            logger.info(sectionsArray.get(i).get("id").toString());
//        	if((sectionsArray.get(i).get("id").toString().equals(SectionID)) &&(sectionsArray.get(i).get("slug").toString().equals(SectionTitle)) ){
//        	    if (sectionsArray.get(i).get("collection").toString().contains(SectionTitle))
//        		flag = true;
//        		logger.info("### Matched: "+ sectionsArray.get(i).get("id"));
//        	}
//
//        }
//
        logger.info("Completed: Created a section and retrived the same with itsman API");

    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test(priority = 2)

    public void createNonEnglishSectionAndVerifyOnSketches() throws InterruptedException
    {
        ConfigFile configObject = SetUp.getConfigObject();

        String SectionTitle = "फायदे का सौदा: "+DataAndTimeUtilities.getCurrentDateAndTime();
        String SectionSlug = "Hindi-slug:"+DataAndTimeUtilities.getCurrentDateAndTime();

        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/section.json");
        Map sectionMap = JSONUtilities.getInnerJSON(jsonBody, "section");
        sectionMap.put("name",SectionTitle);
        sectionMap.put("slug",SectionSlug);
        jsonBody.put("section", sectionMap);

        Map createSectionResponse = ConfigEndpoints.createSection(configObject.basicAuth, 201, jsonBody.toString());
        String SectionID = ((Map) createSectionResponse.get("section")).get("id").toString();
        Assert.assertNotNull(SectionID, "Section ID is null");

        List sectionResponse =  ConfigEndpoints.getSectionOnItsman(configObject.basicAuth,200);
        boolean flag=false;
        logger.info("looking for :"+ SectionID);
        for(int i=0;i<sectionResponse.size();i++){

            Map firstResponseItem = (Map) sectionResponse.get(i);
            if((firstResponseItem.get("name").equals(SectionTitle)) &&(firstResponseItem.get("slug").equals(SectionSlug)) ){
                Double d  = (Double)firstResponseItem.get("collection-id");
                int collectionIDfromResponse = d.intValue();
                flag = true;
                Map collectionResponse = StoryCollectionEndPoint.verifyCollectionsonsketcheswithURL(configObject.sketchesURL, configObject.basicAuth, 200, collectionIDfromResponse);
                Assert.assertTrue(collectionResponse.get("name").equals(SectionTitle));
            }

        }
        Assert.assertEquals(true,flag);

        /*System.out.println("Waiting 30 second before the sections reflects on sketches: "+ DataAndTimeUtilities.getCurrentDateAndTime());
        Thread.sleep(60000);
        System.out.println("Resuming : "+DataAndTimeUtilities.getCurrentDateAndTime());
        Map sectionAPIResponse = ConfigEndpoints.getconfig(200);

        ArrayList<Map> sectionsArray = JSONUtilities.getArray(sectionAPIResponse, "sections");

        //  Assert.assertEquals(sectionsArray.size(),sectionCountBeforeNewSectionCreation+1);
        boolean flag=false;

        for(int i=0;i<sectionsArray.size();i++){

            if((sectionsArray.get(i).get("id").toString().equals(SectionID)) &&(sectionsArray.get(i).get("slug").equals(SectionSlug)) ){
                if (sectionsArray.get(i).get("collection").toString().contains(SectionTitle))
                    flag = true;
                System.out.println("### Matched: "+ sectionsArray.get(i).get("id"));
            }

        }
        Assert.assertEquals(true,flag);*/
        logger.info("Completed: Created a non english section and retrived the same with itsman API");

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test(priority = 3)
    public void createSectionWithoutSlug() throws InterruptedException
    {
        ConfigFile configObject = SetUp.getConfigObject();

        String SectionTitle = "Section: "+DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/section.json");
        Map sectionMap = JSONUtilities.getInnerJSON(jsonBody, "section");
        sectionMap.put("name",SectionTitle);
        sectionMap.remove("slug");
        jsonBody.put("section", sectionMap);

        ConfigEndpoints.createSection(configObject.basicAuth, 422, jsonBody.toString());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })    
    @Test(priority = 4)
    public void verifyDuplicateSectionErrorMessage()
    {
        ConfigFile configObject = SetUp.getConfigObject();
        String duplicateErrorMessage = "Can't create a section because there is an existing collection with the same name";


        List sectionResponse =  ConfigEndpoints.getSectionOnItsman(configObject.basicAuth,200);
        Map firstResponseItem = (Map) sectionResponse.get(0);
    	
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/section.json");
        Map sectionMap = JSONUtilities.getInnerJSON(jsonBody, "section");
    
        sectionMap.put("name", firstResponseItem.get("name"));
        sectionMap.put("slug", firstResponseItem.get("slug"));
        sectionMap.put("parent-id",6563);
        
        jsonBody.put("section", sectionMap);
        

        Map createSectionResponse = ConfigEndpoints.createSection(configObject.basicAuth, 422, jsonBody.toString());
        System.out.println("createSectionResponse"+createSectionResponse);
        String errMsg = ((Map) createSectionResponse.get("error")).get("message").toString();
        System.out.println(errMsg);
        Assert.assertEquals(errMsg, duplicateErrorMessage);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })        
    @Test(priority = 5)
	public void verifyCreatedSectionAppearingOnStoryPage() throws InterruptedException, JSONException {
		ConfigFile configObject = SetUp.getConfigObject();

    	String sectionTitle = "Test Section: "+DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/section.json");
        Map sectionMap = JSONUtilities.getInnerJSON(jsonBody, "section");
        
        sectionMap.put("name",sectionTitle);
        sectionMap.put("slug",sectionTitle);
        sectionMap.put("parent-id",6563);
        jsonBody.put("section", sectionMap);

        //Take the response of the created section and assert whether the section-id is null or not
        Map createSectionResponse = ConfigEndpoints.createSection(configObject.basicAuth, 201, jsonBody.toString());
        String sectionID = ((Map) createSectionResponse.get("section")).get("id").toString();        
        double sId = Double.parseDouble(sectionID);
        int sId1 = (int) sId; 
        
        String storyTitle = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");
        JSONArray sectionJson = JSONUtilities.getInnerJSONAtrrayData(jsonBody1, "sections");
        Map sectionMap1 = JSONUtilities.getJSONArrayToMap(sectionJson, 0);
        sectionMap1.put("name", sectionTitle);
        sectionMap1.put("slug", sectionTitle);
        sectionMap1.put("id", sId1);
        org.json.JSONArray jsonArr = JSONUtilities.mapToJSONArray(sectionMap1); 
        
        jsonBody1.put("sections", jsonArr);         
		jsonBody1.put("headline", storyTitle);
        
        
		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, sId1, configObject.publisherID, 201, jsonBody1.toString());
        String storyVersionID = ((Map) storyCreateAPIResponse.get("story-version")).get("id").toString();
		String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();
        
		Assert.assertNotNull(storyVersionID, "Story Version ID is null");
        Assert.assertNotNull(storyContentID, "Story Content ID is null");
        
        StoryCreationEndPoints.submitStory(configObject.basicAuth, 201);
		StoryCreationEndPoints.approveStory(configObject.basicAuth, 201);
		StoryCreationEndPoints.publishStory(configObject.basicAuth, 201);
		Map sketchesAPIResponse = StoryCreationEndPoints.verifyCreatedStoryOnSketches(configObject.basicAuth, 200);
		Assert.assertEquals(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story").get("story-content-id"), storyContentID);
		Assert.assertEquals(((Map) sketchesAPIResponse.get("story")).get("headline"), storyTitle);

		String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionJson, 0, "name");
		String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
		Assert.assertEquals(actualSectionName, expectedSectionName);
		logger.info("Completed verifyCreatedSectionAppearingOnStoryPage") ;
    }
}



