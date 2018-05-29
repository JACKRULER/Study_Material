package com.quintype.endpoints.accesstype;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;

public class CreateIdentitySubscriptionEndpoint {

	static String identity = "testing"+UUID.randomUUID() +"@gmail.com";
	
	@SuppressWarnings("rawtypes")
    public static Map CreateIdentitySubscription(String accesstypeURL,String token1,String accesstypeProvider, int responseCode, String jsonBody)
    {
		Logger logger = LogManager.getLogger();
        String endPoint = accesstypeURL+"/api/v1/subscribers/"+accesstypeProvider+"/"+identity+"/subscriber_identities.json";
        logger.info(endPoint);
        
        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-SUBAUTH", token1).accept("application/json").body(jsonBody)
                .when().post(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);

      
        return response;
    }

    @SuppressWarnings("rawtypes")
	public static Map verifyCreatedSubscriptionIdentity(String accesstypeURL,String token1,String accesstypeProvider, int responseCode)
	{
    	Logger logger = LogManager.getLogger();
        String endPoint =accesstypeURL+"/api/v1/subscribers/"+accesstypeProvider+"/"+identity+"/subscriber_identities.json";
        logger.info(endPoint);
        Map response = given().header("Content-Type", "application/json")
                .header("X-SUBAUTH", token1).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        
         return response;
       
    }


}