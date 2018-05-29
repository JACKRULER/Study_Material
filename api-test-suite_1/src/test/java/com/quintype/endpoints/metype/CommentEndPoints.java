package com.quintype.endpoints.metype;

import com.jayway.restassured.http.ContentType;
import com.quintype.config.ConfigFile;
import com.quintype.config.MeTypeConfigFile;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class CommentEndPoints {
    private static String commentID = "";
    private static String commentRealmID = "";
    
	// Create a Comment in Metype:
	@SuppressWarnings("rawtypes")
	public static String createComment(MeTypeConfigFile configFile, int responseCode, String jsonBody)
	{		
		String endPoint = configFile.metypeURL+"/api/v1/accounts/"+configFile.metypeAccountId+"/pages/"+configFile.metypeFEURL+"/comments.json";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
						.header("Cookie","_talktype_session="+configFile.talktypeAuthUser1)
						//.header("Referer","https://staging.metype.com/iframe?account_id=2&primary_color=IzRkMDg2YQ==&bg_color=I2ZlZmVmZQ==&font_color=IzRhNGE0YQ==&page_url=https://staging.metype.com/iframe-test-widget&windowHeight=1006&screenWidth=1920")
						.body(jsonBody)
						.when().post(endPoint)
						.then().statusCode(responseCode).extract().as(Map.class);

		return ((Map)response.get("comment")).get("id").toString();

	}

	public static Map listComments(MeTypeConfigFile configFile, int responseCode)
	{
		String endPoint = configFile.metypeURL+"/api/v1/accounts/"+configFile.metypeAccountId+"/pages/"+configFile.metypeFEURL+"/comments.json?parent_comments_limit=1&sort_order=desc";
		Map response = given().log().ifValidationFails().header("Content-Type", "application/json")

				.when().get(endPoint)
				.then().statusCode(responseCode).extract().as(Map.class);



		return response;
	}



}