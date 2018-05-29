package com.quintype.endpoints.platform;

import static com.jayway.restassured.RestAssured.given;

import java.util.Map;
import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

public class BreakingNewsEndPoints {
	
	// 
	@SuppressWarnings("rawtypes")
	public static Map createBreakingNewsWithoutLinkedStory(String basicAuth, int responseCode, String title, String api) 
	{	
    	ConfigFile configObject = SetUp.getConfigObject();
		String jsonBody = "{\"metadata\":{},\"story-content-id\":\"new\",\"headline\":\""+title+"\",\"content-id\":\"new\"}";
		String endPoint = configObject.itsmanURL+"/api/"+api;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
						.header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
						.when().post(endPoint)
						.then().assertThat().statusCode(responseCode).extract().as(Map.class);	        
	    return response;
	}
	
	// 
	@SuppressWarnings("rawtypes")
	public static Map createBreakingNewsWithLinkedStory(String basicAuth, int responseCode, String bkTitle, String storyTitle, String storyContentID, String api) 
	{	
		ConfigFile configObject = SetUp.getConfigObject();
		String jsonBody = "{\"metadata\":{\"linked-story\":{\"headline\":\""+storyTitle+"\",\"story-content-id\":\""+storyContentID+"\",\"id\":\""+storyContentID+"\"}},\"story-content-id\":\"new\",\"headline\":\""+bkTitle+"\",\"breaking-news-linked-story-id\":\""+storyContentID+"\",\"content-id\":\"new\"}";
		String endPoint = configObject.itsmanURL+"/api/"+api;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
						.header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
						.when().post(endPoint)
						.then().assertThat().statusCode(responseCode).extract().as(Map.class);	        
		return response;
	}
	
	
	// 
	@SuppressWarnings("rawtypes")
	public static Map verifyBreakingNewsOnSketches(String basicAuth, int responseCode, String api)
	{
		ConfigFile configObject = new ConfigFile();
        String endPoint= configObject.sketchesURL+"/api/"+api;
        Map response = given().header("Content-Type", "application/json")
        		.header("X-QT-Auth", basicAuth).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
         return response;
       
    }

	public static Map createBreakingNewsDefaultConfig(String basicAuth, int responseCode, String jsonBody)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/associated-story-defaults";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
				.when().post(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(Map.class);
		return response;
	}

	public static Map createBreakingNewsWithAssociatedStory(String basicAuth, int responseCode, String jsonBody)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/breaking-news/new";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
				.when().post(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(Map.class);
		return response;
	}
}
