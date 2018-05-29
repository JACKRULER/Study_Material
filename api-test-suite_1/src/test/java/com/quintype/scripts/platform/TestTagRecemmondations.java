package com.quintype.scripts.platform;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import com.quintype.utils.Story;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Map;
public class TestTagRecemmondations extends SetUp{
	ConfigFile configObject;
	@SuppressWarnings({"rawtypes", "unchecked"})
	//@Test(priority = 1)
	public void createTagRecommondation(){
		ConfigFile configObject = SetUp.getConfigObject();
		String storyTitle = "Photo Story: "+DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle);
		Map storyResponse = Story.createStory(jsonBody, configObject.basicAuth);
		String storyContentID = ((Map) storyResponse.get("story-version")).get("story-content-id").toString();
		Map recemmondedTagsApiResponse = StoryCreationEndPoints.verifyTagRecemmondation(configObject.basicAuth,storyContentID, 200);	
		ArrayList tagsArray=JSONUtilities.getInnerJSON1(recemmondedTagsApiResponse, "primary");
		String listOfTags=JSONUtilities.getArrayValue(recemmondedTagsApiResponse, "primary", 0, "name").toString();
		Assert.assertNotNull(listOfTags);
		
		String storyTitle1 = "Modi visits Rajasthan:" +DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody1 = JSONUtilities.getJSONFileObject("./src/test/resources/PhotoStory.json");
		jsonBody.put("headline",storyTitle1);
		Map storyResponse1 = Story.createStory(jsonBody1, configObject.basicAuth);
		String storyContentID1 = ((Map) storyResponse1.get("story-version")).get("story-content-id").toString();
		Map recemmondedTagsApiResponse1 = StoryCreationEndPoints.verifyTagRecemmondation(configObject.basicAuth,storyContentID1, 200);
		ArrayList tagsArray2=JSONUtilities.getInnerJSON1(recemmondedTagsApiResponse1, "primary");

		Assert.assertNotEquals(tagsArray, tagsArray2);
	}
	
	/*@SuppressWarnings({"unused", "rawtypes"})
	@Test(priority = 2)
	public void createTagRecommondationForNonExistingStory(){
		ConfigFile configObject = SetUp.getConfigObject();

		String storyContentID = "123457765";
		Map recemmondedTagsApiResponse = StoryCreationEndPoints.verifyTagRecemmondation(configObject.basicAuth,storyContentID, 403);		
	}
	
	@SuppressWarnings({"unused", "rawtypes"})
	@Test(priority = 3)
	public void createTagRecommondationForEmptyStoryID(){
		ConfigFile configObject = SetUp.getConfigObject();

		String storyContentID = "";
		Map recemmondedTagsApiResponse = StoryCreationEndPoints.verifyTagRecemmondation(configObject.basicAuth,storyContentID, 404);		
	}*/

}
