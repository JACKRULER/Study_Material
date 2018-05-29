package com.quintype.endpoints.platform;
import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class AttributeCreationEndPoints {

    @SuppressWarnings("rawtypes")
    public static Map createStoryAttributes(String basicAuth, int responseCode, String jsonBody) {
        ConfigFile configObject = SetUp.getConfigObject();
        String endPoint = configObject.itsmanURL + "/api/story-attributes";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .body(jsonBody)
                .when().put(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(Map.class);

        return response;
    }

    @SuppressWarnings("rawtypes")
    public static Map getStoryAttributesItsmanResponse(String basicAuth, int responseCode) {
        ConfigFile configObject = SetUp.getConfigObject();
        String endPoint = configObject.itsmanURL + "/api/story-attributes";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

        return response;
    }

}
