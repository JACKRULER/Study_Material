package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class StoryCollectionEndPoint {
	@SuppressWarnings("unused")
	private static String collectionID = "";
	private static String collectionSlug = "";
	
	//Creating manual collection with two stories
	@SuppressWarnings("rawtypes")
	public static Map createCollection(String basicAuth,int responseCode, String jsonBody) {		
    	ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/story-collection";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
						.header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
						.when().post(endPoint)
						.then().statusCode(responseCode).extract().as(Map.class);
        
		collectionID = response.get("id").toString();
    	collectionSlug = response.get("slug").toString();;
      	return response;
	}

    //Creating associated metadata for madrid
    @SuppressWarnings("rawtypes")
    public static Map createCollectionWithURL(String itsmanURL,String basicAuth4,int responseCode, String jsonBody) {
        String endPoint = itsmanURL+"/api/story-collection";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth4).accept("application/json").body(jsonBody)
                .when().post(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

        collectionID = response.get("id").toString();
        collectionSlug = response.get("slug").toString();;
        return response;
    }



    //Verify associated metadata on sketches
	@SuppressWarnings("rawtypes")
	public static Map verifyCollectionsonsketcheswithURL(String sketchesURL,String basicAuth4, int responseCode,int collectionID){
		String endPoint= sketchesURL+"/api/v1/collections/"+collectionID;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
              .header("X-QT-Auth", basicAuth4).accept("application/json")
              .when().get(endPoint)
              .then().statusCode(responseCode).extract().as(Map.class);
      
      return response;
	}

    //Verify craeted colletcion on sketches
    @SuppressWarnings("rawtypes")
    public static Map verifyCreatedCollectionOnSketches(String basicAuth, int responseCode){
        ConfigFile configObject = SetUp.getConfigObject();
        String endPoint= configObject.sketchesURL+"/api/v1/collections/"+collectionSlug;
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

        return response;
    }




	@SuppressWarnings("rawtypes")
	public static Map verifyCreatedCollectionOnSketcheswithlimit(String basicAuth, int responseCode){
		ConfigFile configObject = SetUp.getConfigObject();
        String endPoint= configObject.sketchesURL+"/api/v1/collections/"+collectionSlug+"?limit=2";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        return response;
	}

	@SuppressWarnings("rawtypes")
	public static Map verifyCreatedCollectionOnSketcheswithItemType(String basicAuth,int collectionID, int responseCode){
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint= configObject.sketchesURL+"/api/v1/collections/"+collectionID+"?item-type=story";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json")
				.when().get(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);
		return response;
	}

	@SuppressWarnings("rawtypes")
	public static Map verifyCreatedCollectionOnSketchesExcludingStoryID(String basicAuth,int collectionID,String storyContentID, int responseCode){
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint= configObject.sketchesURL+"/api/v1/collections/"+collectionID+"?exclude-story-id="+storyContentID;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json")
				.when().get(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);
		return response;
	}
}