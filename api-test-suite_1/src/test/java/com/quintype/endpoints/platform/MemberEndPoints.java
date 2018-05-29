package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class MemberEndPoints {

    @SuppressWarnings("rawtypes")
	public static Map getmemberActions(String basicAuth, int responseCode) {

        ConfigFile configObject = SetUp.getConfigObject();
        String endPoint = configObject.itsmanURL + "/api/member/actions";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", basicAuth)
                .when().get(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(Map.class);

        return response;

    }

}






