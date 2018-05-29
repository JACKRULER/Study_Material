package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class ThemeUploadHelper {
    @SuppressWarnings("rawtypes")
	public static Map uploadtheam (String itsmanURL, String basicAuth, int responseCode, String jsonBody) {
        String endPoint = itsmanURL + "/api/theme-attributes";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth).accept("application/json")
                .body(jsonBody)
                .when().post(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(Map.class);

        return response;
    }



}


