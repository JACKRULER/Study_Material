package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class TagManagerEndPoints {

    //Create Tag
    public static int createTag(String basicAuth, String jsonBody, int responseCode, String itsmanURL)
    {
        String endPoint = itsmanURL+"/api/tag";
        int responseID = given().log().ifValidationFails().header("Content-Type", "application/json").body(jsonBody)
                .header("X-QT-Auth", basicAuth)
                .accept("application/json")
                .when().post(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(int.class);

        return responseID;
    }

    //Check Created by and Created at
    public static Map validateCreatedAtandCreatedBy(String basicAuth,int responseCode,String tagName, String itsmanURL)
    {
        String endPoint = itsmanURL+"/api/tag?name="+tagName;
        Map tagResponse = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth)
                .accept("application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(Map.class);

        return tagResponse;
    }

    //Check story count
    public static Map storyCountForTag(String basicAuth,int responseCode,int tagId, String itsmanURL)
    {
        String endPoint = itsmanURL+"/api/search?tag-id="+tagId;
        Map responseStoryCount = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth)
                .accept("application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(Map.class);

        return responseStoryCount;
    }
}
