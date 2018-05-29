package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class SearchEndPoints {

	// Get Author
	@SuppressWarnings("rawtypes")
	public static Map searchbyAuthorID(int authorID, int responseCode)
	{
    	ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.sketchesURL+"/api/v1/search/?author-id="+authorID+"&limit=100";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
						.accept("application/json")
						.when().get(endPoint)
						.then().assertThat().statusCode(responseCode).extract().as(Map.class);
        
		return response;
	}

	// Search Stories by headline
	@SuppressWarnings("rawtypes")
	public static Map searchbyStoryHeadline(String headline, String fields, int responseCode)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.sketchesURL+"/api/v1/search/?q="+headline+"&fields="+fields;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.accept("application/json")
				.when().get(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(Map.class);

		return response;
	}

}