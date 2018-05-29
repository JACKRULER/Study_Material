package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.json.simple.JSONObject;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class CustomURLEndpoint {
    private static String id;
    private static ConfigFile configObject = SetUp.getConfigObject();

    public static Map createCustomUrl(String basicAuth, int responseCode, JSONObject jsonBody) {
        String endPoint = configObject.itsmanURL+"/api/custom-url";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
                .when().post(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        return response;
    }

    public static Map verifycreateCustomUrlOnSketches(String sketchesURL,String basicAuth, int responseCode,String slug){
        String endPoint= sketchesURL+"/api/v1/custom-urls/"+slug;
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

        return response;
    }

    public static Map deleteCustomUrl(String basicAuth, int responseCode,int id){
        String endPoint= configObject.itsmanURL+"/api/custom-url/"+id;
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .when().delete(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

        return response;
    }

    public static Map editCustomUrl(String basicAuth, int responseCode,int id,JSONObject jsonBody){
        String endPoint= configObject.itsmanURL+"/api/custom-url/"+id;
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .body(jsonBody)
                .when().put(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

        return response;
    }
}
