package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class ConfigEndpoints {
    @SuppressWarnings("rawtypes")
	public static Map createSection(String basicAuth, int responseCode, String jsonBody) {
        ConfigFile configObject = SetUp.getConfigObject();
        String endPoint = configObject.itsmanURL + "/api/section";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .body(jsonBody)
                .when().post(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(Map.class);

        return response;
    }

    @SuppressWarnings("rawtypes")
	public static Map getconfig(int responseCode) {

        ConfigFile configObject = SetUp.getConfigObject();
        String endPoint = configObject.sketchesURL + "/api/v1/config";//+ DataAndTimeUtilities.getCurrentDateAndTime();
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(Map.class);

        return response;
    }

    public static List<String> getSectionOnItsman(String basicAuth , int responseCode) {

        ConfigFile configObject = SetUp.getConfigObject();
        String endPoint = configObject.itsmanURL + "/api/section";
        ArrayList response;
        response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth)
                .when().get(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(ArrayList.class);

        return response;
    }
}


