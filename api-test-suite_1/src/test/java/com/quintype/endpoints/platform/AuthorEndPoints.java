package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class AuthorEndPoints {

	// Get Author
	@SuppressWarnings("rawtypes")
	public static Map getAuthorbyId(int authorID, int responseCode)
	{
    	ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.sketchesURL+"/api/v1/authors/"+authorID;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
						.accept("application/json")
						.when().get(endPoint)
						.then().assertThat().statusCode(responseCode).extract().as(Map.class);
        
		return response;
	}

	//Add Author
	public static Map createAuthor(String basicAuth,String jsonBody,int responseCode)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/member";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json").body(jsonBody)
				.header("X-QT-Auth", basicAuth)
				.accept("application/json")
				.when().post(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(Map.class);

		return response;
	}

	public static Map createAuthorProfile(String basicAuth,String jsonBody,int responseCode)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL+"/api/author/181594/profile";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json").body(jsonBody)
				.header("X-QT-Auth", basicAuth)
				.accept("application/json")
				.when().post(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(Map.class);

		return response;
	}

	public static Map getStorybyId(String storyContentID, int responseCode)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.sketchesURL+"/api/v1/stories/"+storyContentID;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.accept("application/json")
				.when().get(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(Map.class);

		return response;
	}

	public static Map getStorybySlug(String storySlug, int responseCode)
	{
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.sketchesURL+"/api/v1/stories-by-slug?slug="+storySlug;
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.accept("application/json")
				.when().get(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(Map.class);

		return response;
	}

	public static ArrayList getMemberbyNamefromItsman(String itsmanURL, String basicAuth, String authorName, int responseCode)
	{
		String endPoint = itsmanURL+"/api/member/search/?name="+authorName;
		ArrayList response = given().log().ifValidationFails().header("Content-Type", "application/json")
				.accept("application/json")
				.header("X-QT-Auth", basicAuth)
				.when().get(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(ArrayList.class);

		return response;
	}

}