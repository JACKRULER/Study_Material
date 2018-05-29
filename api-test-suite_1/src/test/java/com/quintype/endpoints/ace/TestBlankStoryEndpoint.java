package com.quintype.endpoints.ace;

import com.quintype.config.AceConfigFile;
import com.quintype.utils.SetUp;
import org.json.simple.JSONObject;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class TestBlankStoryEndpoint {


    public static Map getblankstory(String basicAuth, int responseCode, JSONObject jsonBody)
    {
        AceConfigFile configObject = SetUp.getAceConfigObject();
        String endPoint = configObject.itsmanURL+"/api/story/new/story-version";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).header("Origin",configObject.itsmanURL).accept("application/json").body(jsonBody)
                .when().post(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        return response;
    }
}
