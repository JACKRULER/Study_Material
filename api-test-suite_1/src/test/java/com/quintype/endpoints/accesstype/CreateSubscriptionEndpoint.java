package com.quintype.endpoints.accesstype;

import com.quintype.config.*;
import com.quintype.utils.DataAndTimeUtilities;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class CreateSubscriptionEndpoint {

    static String identity = "testing+"+DataAndTimeUtilities.getCurrentDateAndTime() +"@gmail.com";
    @SuppressWarnings("rawtypes")
    public static Map createSubscriptionOnAccesstype(AccessTypeConfigFile configObject, int responseCode, String jsonBody)
    {

        String endPoint = configObject.accesstypeURL+"/api/v1/subscribers/"+configObject.accesstypeProvider+"/"+identity+"/subscriptions.json";
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-SUBAUTH", configObject.token1).accept("application/json").body(jsonBody)
                .when().post(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

      
        return response;
    }
    
    @SuppressWarnings("rawtypes")
	public static Map verifyCreatedSubscription(AccessTypeConfigFile configObject, int responseCode)
	{

        String endPoint = configObject.accesstypeURL+"/api/v1/subscribers/"+configObject.accesstypeProvider+"/"+identity+"/subscriptions.json";
        Map response = given().header("Content-Type", "application/json")
                .header("X-SUBAUTH", configObject.token1).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        
         return response;
       
    }
}