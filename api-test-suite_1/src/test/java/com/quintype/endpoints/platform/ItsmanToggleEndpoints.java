package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class ItsmanToggleEndpoints {

    @SuppressWarnings("rawtypes")
	public static boolean getToggleStatus(String toggleSlug, int responseCode) {

        ConfigFile configObject = SetUp.getConfigObject();
        String endPoint = configObject.itsmanURL + "/api/select-features";
        ArrayList response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", configObject.basicAuth)
                .when().get(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(ArrayList.class);
        boolean flag = false;
        for (int i = 0; i < response.size(); i++) {
            Map singleToggle = (Map) response.get(i);
            if (singleToggle.get("slug").toString().equals(toggleSlug)) {
                if (singleToggle.get("toggle").toString().equals("true")) {
                    flag = true;
                    break;
                }
            }

        }
    return flag;
    }

}






