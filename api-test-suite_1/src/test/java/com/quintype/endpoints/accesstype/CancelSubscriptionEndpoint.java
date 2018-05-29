package com.quintype.endpoints.accesstype;

import com.quintype.utils.DataAndTimeUtilities;

import static com.jayway.restassured.RestAssured.given;

public class CancelSubscriptionEndpoint {

    static String identity = "testing+"+DataAndTimeUtilities.getCurrentDateAndTime() +"@gmail.com";
    @SuppressWarnings("rawtypes")
    public static void cancelSubscription(String accesstypeURL, String subscriptionID, String accesstypeProvider, String token1, int responseCode)
    {

        String endPoint = accesstypeURL+"/api/v1/subscribers/"+accesstypeProvider+"/"+identity+"/subscriptions/"+subscriptionID+"/cancel.json";
        given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-SUBAUTH", token1).accept("application/json")
                .when().put(endPoint)
                .then().statusCode(responseCode);

    }



}