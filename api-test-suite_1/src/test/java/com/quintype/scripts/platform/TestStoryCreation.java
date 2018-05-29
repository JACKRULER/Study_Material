package com.quintype.scripts.platform;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.ItsmanToggleEndpoints;
import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestStoryCreation extends SetUp 
{
	Logger logger = LogManager.getLogger();
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Test(priority = 1)
	public void createBlankStoryAndVerifyOnSketches() {

		logger.info("Starting createBlankStoryAndVerifyOnSketches test");
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");
		jsonBody.put("headline", storyTitle);

		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
		String storyVersionID = ((Map) storyCreateAPIResponse.get("story-version")).get("id").toString();
		String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();

		Assert.assertNotNull(storyVersionID, "Story Version ID is null");
		Assert.assertNotNull(storyContentID, "Story Content ID is null");
		System.out.println("Test"+storyContentID);
		StoryCreationEndPoints.submitStory(configObject.basicAuth, 201);
		StoryCreationEndPoints.approveStory(configObject.basicAuth, 201);
		StoryCreationEndPoints.publishStory(configObject.basicAuth, 201);
		Map sketchesAPIResponse = StoryCreationEndPoints.verifyCreatedStoryOnSketches(configObject.basicAuth, 200);
		Assert.assertEquals(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story").get("story-content-id"), storyContentID);
		Assert.assertEquals(((Map) sketchesAPIResponse.get("story")).get("headline"), storyTitle);

		JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/BlankStory.json", "sections");
		String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
		String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
		Assert.assertEquals(actualSectionName, expectedSectionName);
		logger.info("Completed createBlankStoryAndVerifyOnSketches");

	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Test(priority = 2)
	public void createPhotoStoryAndVerifyOnSketches() {
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Photo Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline", storyTitle);

		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
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

		JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/PhotoStory.json", "sections");
		String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
		String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
		Assert.assertEquals(actualSectionName, expectedSectionName);

		logger.info("Published Photo Story & Verified.");
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Test(priority = 3)
	public void createVideoStoryAndVerifyOnSketches() {
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Video Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/VideoStory.json");
		jsonBody.put("headline", storyTitle);

		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
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

		JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/VideoStory.json", "sections");
		String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
		String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
		Assert.assertEquals(actualSectionName, expectedSectionName);

		logger.info("Published Video Story & Verified.");
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Test(priority = 4)
	public void createListicleStoryAndVerifyOnSketches() {
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Listicle Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/ListicleStory.json");
		jsonBody.put("headline", storyTitle);

		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
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

		JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/ListicleStory.json", "sections");
		String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
		String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
		Assert.assertEquals(actualSectionName, expectedSectionName);

		logger.info("Published Listicle Story & Verified.");
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Test(priority = 5)
	public void createLiveBlogStoryAndVerifyOnSketches() {
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "LiveBlog Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/LiveBlogStory.json");
		jsonBody.put("headline", storyTitle);

		Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
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

		JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/LiveBlogStory.json", "sections");
		String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
		String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
		Assert.assertEquals(actualSectionName, expectedSectionName);

		logger.info("Published Live Blog Story & Verified.");
	}
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(priority = 6)
    public void createInterviewStoryAndVerifyOnSketches() 
    {
    	ConfigFile configObject = SetUp.getConfigObject();
    	String storyTitle = "Interview Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
    	JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/InterviewStory.json");
    	jsonBody.put("headline", storyTitle);
    	
    	Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
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
    	
    	JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/InterviewStory.json", "sections");
    	String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
        String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
    	Assert.assertEquals(actualSectionName, expectedSectionName);

       	logger.info("Published Interview Story & Verified.");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(priority = 7)
    public void createReviewStoryAndVerifyOnSketches() 
    {
    	ConfigFile configObject = SetUp.getConfigObject();
    	String storyTitle = "Review Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
    	JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/ReviewStory.json");
    	jsonBody.put("headline", storyTitle);
    	
    	Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
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
    	
    	JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/ReviewStory.json", "sections");
    	String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
        String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
    	Assert.assertEquals(actualSectionName, expectedSectionName);

       	logger.info("Published Review Story & Verified.");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(priority = 8)
    public void createRecipeStoryAndVerifyOnSketches() 
    {
    	ConfigFile configObject = SetUp.getConfigObject();
    	String storyTitle = "Recipe Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
    	JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/RecipeStory.json");
    	jsonBody.put("headline", storyTitle);
    	
    	Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
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
    	
    	JSONArray sectionsArray = JSONUtilities.getInnerJSONArrayData("./src/test/resources/RecipeStory.json", "sections");
    	String expectedSectionName = JSONUtilities.getJSONArrayValue(sectionsArray, 0, "name");
        String actualSectionName = JSONUtilities.getArrayValue(JSONUtilities.getInnerJSON(sketchesAPIResponse, "story"), "sections", 0, "name");
    	Assert.assertEquals(actualSectionName, expectedSectionName);

       	logger.info("Published Recipe Story & Verified.");
    }

	@SuppressWarnings({"rawtypes", "unchecked", "unused"})
	@Test(priority = 9)
	public void saveStorywithoutClientIDAtTreelevelShouldThrowBadRequest() {

		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Photo Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");

		jsonBody.put("headline", storyTitle);
		ArrayList<Map> treeArray = JSONUtilities.getArray(jsonBody, "tree");
		// The below statement will update the jsonbody obj as well since it is a non primitive type
		treeArray.get(0).put("client-id","");
		Map storyCreateAPIResponse = StoryCreationEndPoints.createStorywithSourceHeader("itsman-ui",configObject.basicAuth, configObject.sectionID, configObject.publisherID, 400, jsonBody.toString());


	}

	@SuppressWarnings({"rawtypes", "unchecked", "unused"})
	@Test(priority = 10)
	public void saveStorywithoutClientIDAtCardlevelShouldThrowBadRequest() {

		ConfigFile configObject = SetUp.getConfigObject();
		if(ItsmanToggleEndpoints.getToggleStatus("non-blocking-story-save",200)) {

			String storyTitle = "Photo Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
			JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/InterviewStoryWithEntity.json");

			jsonBody.put("headline", storyTitle);
			Map cardsjson = JSONUtilities.getInnerJSON(jsonBody, "cards");
			Map firstcard = JSONUtilities.getInnerJSON(cardsjson, "new-1");
			firstcard.put("client-id", "");

			Map storyCreateAPIResponse = StoryCreationEndPoints.createStorywithSourceHeader("itsman-ui", configObject.basicAuth, configObject.sectionID, configObject.publisherID, 400, jsonBody.toString());

		}
		else
			throw new SkipException("Non blocking Auto save feature is turned off");
	}

	@SuppressWarnings({"rawtypes", "unchecked", "unused"})
	@Test(priority = 11)
	public void saveStorywithoutClientIDAtStoryElementShouldThrowBadRequest() 
	{
		if(ItsmanToggleEndpoints.getToggleStatus("non-blocking-story-save",200)) {

				ConfigFile configObject = SetUp.getConfigObject();
				String storyTitle = "Photo Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
				JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/InterviewStoryWithEntity.json");

				jsonBody.put("headline", storyTitle);
				Map cardsjson = JSONUtilities.getInnerJSON(jsonBody, "cards");
				Map firstcard = JSONUtilities.getInnerJSON(cardsjson, "new-1");
				ArrayList<Map> storyElementsArray = JSONUtilities.getArray(firstcard, "story-elements");
				storyElementsArray.get(0).put("client-id", "");


				Map storyCreateAPIResponse = StoryCreationEndPoints.createStorywithSourceHeader("itsman-ui", configObject.basicAuth, configObject.sectionID, configObject.publisherID, 400, jsonBody.toString());
			}
		else
			throw new SkipException("Non blocking Auto save feature is turned off");

	}

	@SuppressWarnings({"rawtypes", "unchecked", "unused"})
	@Test(priority = 12)
	public void saveStorywithoutClientIDAtCompositeStoryElementShouldThrowBadRequest() 
	{
		if(ItsmanToggleEndpoints.getToggleStatus("non-blocking-story-save",200)) {
			ConfigFile configObject = SetUp.getConfigObject();
			String storyTitle = "Photo Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
			JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/InterviewStoryWithEntity.json");

			jsonBody.put("headline", storyTitle);
			Map cardsjson = JSONUtilities.getInnerJSON(jsonBody, "cards");
			Map firstcard = JSONUtilities.getInnerJSON(cardsjson, "new-3");
			ArrayList<Map> storyElementsArray = JSONUtilities.getArray(firstcard, "story-elements");
			ArrayList<Map> compositeElements = JSONUtilities.getArray(storyElementsArray.get(0), "story-elements");
			compositeElements.get(0).put("client-id", "");
			compositeElements.get(1).put("client-id", "");

			Map storyCreateAPIResponse = StoryCreationEndPoints.createStorywithSourceHeader("itsman-ui", configObject.basicAuth, configObject.sectionID, configObject.publisherID, 400, jsonBody.toString());
		}
		else
			throw new SkipException("Non blocking Auto save feature is turned off");

	}

	@SuppressWarnings({"rawtypes", "unchecked", "unused"})
	@Test(priority = 13)
	public void saveStorywithoutClientIDAtTreelevelAndNoXQTSourceHeaderShouldNotThrowBadRequest() 
	{
		if(ItsmanToggleEndpoints.getToggleStatus("non-blocking-story-save",200)) {
			ConfigFile configObject = SetUp.getConfigObject();
			String storyTitle = "Photo Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
			JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");

			jsonBody.put("headline", storyTitle);
			ArrayList<Map> treeArray = JSONUtilities.getArray(jsonBody, "tree");
			// The below statement will update the jsonbody obj as well since it is a non primitive type
			treeArray.get(0).put("client-id", "");
			Map storyCreateAPIResponse = StoryCreationEndPoints.createStory(configObject.basicAuth, configObject.sectionID, configObject.publisherID, 201, jsonBody.toString());
		}
		else
			throw new SkipException("Non blocking Auto save feature is turned off");

	}

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Test(priority = 9)
//	public void createBlankStoryAndVerifyOnSketcheswithAuthorParam() throws InterruptedException {
//    	ConfigFile configObject = SetUp.getConfigObject();
//		int authorId = configObject.authorID1;
//    	Map authorResponse = AuthorEndPoints.getAuthorbyId(authorId, 200);
//		String authorName = JSONUtilities.getInnerJSON(authorResponse,"author").get("name").toString();
//		String slugName = JSONUtilities.getInnerJSON(authorResponse,"author").get("slug").toString();
//		String basicAuth = configObject.basicAuth1;
//		Assert.assertEquals(authorName,"Agent_tester");
//		Assert.assertEquals(slugName,"agent-tester");
//
//		String storyTitle = "Blank Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
//		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");
//		jsonBody.put("headline", storyTitle);
//		Map storyCreateAPIResponse = Story.createStory(jsonBody,basicAuth);
//
//		String storyVersionID = ((Map) storyCreateAPIResponse.get("story-version")).get("id").toString();
//		String storyContentID = ((Map) storyCreateAPIResponse.get("story-version")).get("story-content-id").toString();
//		//Waiting before search indexing is complete
//		Thread.sleep(10000);
//		Map searchResponse = SearchEndPoints.searchbyAuthorID(authorId, 200);
//		Map resultJson = JSONUtilities.getInnerJSON(searchResponse, "results");
//		ArrayList<Map> storyArray = JSONUtilities.getArray(resultJson, "stories");
//		boolean flag=false;
//
//		for(int i=0;i<storyArray.size();i++){
//
//			if(storyArray.get(i).get("story-content-id").equals(storyContentID)) {
//				flag = true;
//				System.out.println("### Matched: "+ storyArray.get(i).get("story-content-id"));
//			}
//
//		}
//		Assert.assertEquals(true,flag);
//
//		System.out.println("Completed: Created a story and retrived the same with search API with Author ID parameter");
//	}

}
			