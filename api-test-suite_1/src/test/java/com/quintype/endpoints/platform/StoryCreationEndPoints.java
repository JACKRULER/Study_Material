package com.quintype.endpoints.platform;

import com.jayway.restassured.http.ContentType;
import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class StoryCreationEndPoints {
    private static String storyVersionID = "";
    private static String storyContentID = "";
    
	// Create Story in Itsman:
	@SuppressWarnings("rawtypes")
	public static  Map createStory(String basicAuth, int sectionID, int publisherID, int responseCode, String jsonBody)
	{		
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/story/new/story-version";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).header("Origin",configObject.itsmanURL).accept("application/json").body(jsonBody)
				.when().post(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);

		storyVersionID = ((Map)response.get("story-version")).get("id").toString();
		storyContentID = ((Map)response.get("story-version")).get("story-content-id").toString();
		return response;
	}

	public static  Map createStorywithURL(String basicAuth, int responseCode, String jsonBody, String itsmanURL) {
		String endPoint = itsmanURL+"/api/story/new/story-version";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
						.header("X-QT-Auth", basicAuth).header("Origin",itsmanURL).accept("application/json").body(jsonBody)
						.when().post(endPoint)
						.then().statusCode(responseCode).extract().as(Map.class);

		storyVersionID = ((Map)response.get("story-version")).get("id").toString();
		storyContentID = ((Map)response.get("story-version")).get("story-content-id").toString();
		return response;
	}

	@SuppressWarnings("rawtypes")
	public static Map createStorywithSourceHeader(String XQTClientHeader,String basicAuth, int sectionID, int publisherID, int responseCode, String jsonBody)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/story/new/story-version";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).header("Origin",configObject.itsmanURL)
				.header("X-QT-CLIENT",XQTClientHeader)
				.accept("application/json").body(jsonBody)
				.when().post(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);


		return response;
	}
	// Submit Story in Itsman:
	@SuppressWarnings("rawtypes")
	public static Map submitStory(String basicAuth, int responseCode) 
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/story/"+storyContentID+"/status";
        String jsonBody = "{\"story-version-id\":\""+storyVersionID+"\",\"action\":\"story-submit\"}";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody).
                        when().post(endPoint).
                        then().statusCode(responseCode).extract().as(Map.class);
        return response;
    }
	
	// Approve Story in Itsman:
	@SuppressWarnings("rawtypes")
	public static Map approveStory(String basicAuth, int responseCode) 
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/story/"+storyContentID+"/status";
		String jsonBody = "{\"story-version-id\":\""+storyVersionID+"\",\"action\":\"story-approve\"}";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
				.when().post(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);
		return response;
	}
	
	// Publish Story in Itsman:
	@SuppressWarnings("rawtypes")
	public static Map publishStory(String basicAuth, int responseCode) 
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/story/"+storyContentID+"/status";
		String jsonBody = "{\"story-version-id\":\""+storyVersionID+"\",\"action\":\"story-publish\"}";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
				.when().post(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);
		return response;
	}

	// Verify created story on sketches
	@SuppressWarnings("rawtypes")
	public static Map verifyCreatedStoryOnSketches(String basicAuth, int responseCode)
	{
    	ConfigFile configObject = SetUp.getConfigObject();
    	return verifyCreatedStoryOnSketchesURL(basicAuth, responseCode, configObject.sketchesURL);

    }
    public static Map verifyCreatedStoryOnSketchesURL(String basicAuth,int responseCode, String sketchesURL ){
        ConfigFile configObject = SetUp.getConfigObject();
		String endPoint= configObject.sketchesURL+"/api/v1/stories/"+storyContentID;
        Map response = given().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

        return response;

    }

	public static Map verifyCreatedStoryOnSketchesWithContrntID(String basicAuth, int responseCode,String sketchesURL,String storyContentID)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint= configObject.sketchesURL+"/api/v1/stories/"+storyContentID;
		Map response = given().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json")
				.when().get(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);

		return response;

	}

	// Verify created story as amp on sketches
	@SuppressWarnings("rawtypes")
	public static Map verifyCreatedStoryAsAMPOnSketches(String basicAuth, int responseCode)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint= configObject.sketchesURL+"/api/v1/amp/story?id="+storyContentID;
		Map response = given().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json")
				.when().get(endPoint)
				.then().contentType(ContentType.JSON).statusCode(responseCode).extract().as(Map.class);

		return response;

	}

	// Verify created story on sketches
	@SuppressWarnings("rawtypes")
	public static Map verifyCreatedStoryOnSketchesbyAuthorId(int authorID,int responseCode)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint= configObject.sketchesURL+"/api/v1/stories?author-id="+authorID;
		Map response = given().header("Content-Type", "application/json")
				.accept("application/json")
				.when().get(endPoint)
				.then().contentType(ContentType.JSON).statusCode(responseCode).extract().as(Map.class);

		return response;

	}
	
	@SuppressWarnings("rawtypes")
	public static Map verifyTagRecemmondation(String basicAuth, String storyContentID,int responseCode)
	{
    	ConfigFile configObject = SetUp.getConfigObject();
        String endPoint= configObject.itsmanURL+"/api/story/"+storyContentID+"/recommended-tags";
        Map response = given().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        
         return response;
       
    }

	public static Map getRelatedStoriesbySectionId(String storyContentID,int sectionID,int responseCode, ConfigFile configObject)
	{

		String endPoint= configObject.sketchesURL+"/api/v1/stories/"+storyContentID+"/related-stories?section-id="+sectionID+"&fields=id,sections";
		Map response = given().header("Content-Type", "application/json")
				.accept("application/json")
				.when().get(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);

		return response;

	}
	public static Map getRelatedStoriesbySectionName(String storyContentID,String sectionName,int responseCode, ConfigFile configObject)
	{

		String endPoint= configObject.sketchesURL+"/api/v1/stories/"+storyContentID+"/related-stories?section="+sectionName+"&fields=id,sections";
		Map response = given().header("Content-Type", "application/json")
				.accept("application/json")
				.when().get(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);

		return response;

	}


}