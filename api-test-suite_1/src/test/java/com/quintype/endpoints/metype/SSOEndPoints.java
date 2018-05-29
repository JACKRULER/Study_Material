package com.quintype.endpoints.metype;

import com.quintype.config.MeTypeConfigFile;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class SSOEndPoints {

    
	// Create a Comment in Metype:
	@SuppressWarnings("rawtypes")
	public static Map getCurrentUser(String metypeURL,String publisherAccessToken, int responseCode)
	{		
		String endPoint = metypeURL+"/api/v1/current_user.json?access_token="+publisherAccessToken;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
						.when().get(endPoint)
						.then().statusCode(responseCode).extract().as(Map.class);

		return response;

	}

	public static Map getCurrentUserwithSession(String metypeURL,String talktype_session,String publisherAccessToken, int responseCode)
	{
		String endPoint = metypeURL+"/api/v1/current_user.json?access_token="+publisherAccessToken;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("Cookie","_talktype_session="+talktype_session)
				.when().get(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);

		return response;

	}




}