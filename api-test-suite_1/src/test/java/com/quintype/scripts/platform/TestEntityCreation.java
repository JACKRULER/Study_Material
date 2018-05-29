/*package com.quintype.scripts;

import java.util.ArrayList;
import java.util.Map;

import com.quintype.endpoints.platform.StoryCreationEndPoints;
import com.quintype.utils.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.EntityEndPoints;

public class TestEntityCreation extends SetUp{
	
//	ConfigFile configObject;
//
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testEntityCreation(){
//		configObject = SetUp.getConfigObject();
//		String entityName = "Entity_"+ DataAndTimeUtilities.getCurrentDateAndTime();
//		EntityEndPoints.createEntity(201, configObject.basicAuth, entityName);
//		System.out.println("New Entity Created: "+entityName);
//		String entityKey = "name";
//		String storyTitle = "Blank Story for Entities: "+DataAndTimeUtilities.getCurrentDateAndTime();
//    	JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/InterviewStoryWithEntity.json");
//		jsonBody.put("headline", storyTitle);
//		jsonBody = setentityValue(entityKey,entityName, jsonBody);
//
//		Story.createStory(jsonBody,configObject.basicAuth);
//		Map storyReponse = StoryCreationEndPoints.verifyCreatedStoryOnSketches(configObject.basicAuth, 200);
//		validateEntityValue(entityKey,entityName, storyReponse);
//
//
//	}
//
//	private void validateEntityValue(String entityKey,String entityName, Map storyReponse) {
//		Map storyMap = JSONUtilities.getInnerJSON(storyReponse,"story");
//		ArrayList<Map> cards = JSONUtilities.getArray(storyMap, "cards");
//
//		for(int i=0;i<cards.size();i++){
//
//			ArrayList<Map> storyElements = JSONUtilities.getArray(cards.get(i), "story-elements");
//			Map metadata = JSONUtilities.getInnerJSON(storyElements.get(0), "metadata");
//
//			String name = JSONUtilities.getInnerJSON(metadata,"interviewer").get(entityKey).toString();
//			Assert.assertEquals(name,entityName);
//		}
//	}
//
//	private JSONObject setentityValue(String entityKey, String entityName, JSONObject jsonBody) {
//		JSONObject cardJsonObject = (JSONObject) jsonBody.get("cards");
//		for(int i=0; i<cardJsonObject.size();i++){
//				Map cardObject = JSONUtilities.getInnerJSON(cardJsonObject, "new-" + i);
//				JSONArray storyElementArray = (JSONArray) JSONUtilities.getArray(cardObject,"story-elements");
//				JSONObject firstStoryElement = (JSONObject) storyElementArray.get(0);
//				Map metadataObject = JSONUtilities.getInnerJSON(firstStoryElement, "metadata");
//				Map interviewerObject = JSONUtilities.getInnerJSON(metadataObject, "interviewer");
//				interviewerObject.put(entityKey,entityName);
//				metadataObject.put("interviewer",interviewerObject);
//
//				firstStoryElement.put("metadata",metadataObject);
//				storyElementArray.set(0,firstStoryElement);
//				cardObject.put("story-elements",storyElementArray);
//				cardJsonObject.put("new-"+i,cardObject);
//
//
//			}
//		jsonBody.put("cards",cardJsonObject);
//		return jsonBody;
//	}
//
//
////		cs.createStory(entityCreateAPIResponse); ;
//		
		
		
	}
	


*/