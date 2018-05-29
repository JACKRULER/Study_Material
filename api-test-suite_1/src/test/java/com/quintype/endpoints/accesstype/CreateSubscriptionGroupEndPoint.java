package com.quintype.endpoints.accesstype;
import com.quintype.config.AccessTypeConfigFile;
import com.quintype.utils.JSONUtilities;
import static com.jayway.restassured.RestAssured.given;
import java.util.Map;

public class CreateSubscriptionGroupEndPoint {
    private static String subscriptionGroupId;

	@SuppressWarnings({ "rawtypes"})
    public  static Map createSubscriptionGroupOnAccesstype(AccessTypeConfigFile configObject, int responseCode, String jsonBody)
    {
		

		        String endPoint = configObject.accesstypeURL+"/api/v1/management/subscription_groups.json/";
		        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
		                .header("X-SUBAUTH", configObject.token1).accept("application/json").body(jsonBody)
		                .when().post(endPoint)
		                .then().statusCode(responseCode).extract().as(Map.class);

		         subscriptionGroupId = (JSONUtilities.getInnerJSON(response, "subscription_group").get("id").toString().split("\\.")[0]);
		        return response;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map verifyCreatedSubscriptionGroup(AccessTypeConfigFile configObject, int responseCode)
	{
        String endPoint= configObject.accesstypeURL+"/api/v1/subscription_groups/"+subscriptionGroupId +".json";
        Map response = given().header("Content-Type", "application/json")
                .header("X-SUBAUTH", configObject.token1).accept("application/json")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        
         return response;
       
    }
	@SuppressWarnings("rawtypes")
	public  static Map createSubscriptionPlanOnAccesstype(AccessTypeConfigFile configObject,int responseCode, String jsonBody)
    {
		
		        String endPoint = configObject.accesstypeURL+"/api/v1/management/subscription_groups/"+subscriptionGroupId + "/subscription_plans.json";
		        Map response = given().log().ifValidationFails().header("Content-Type", "application/json")
		                .header("X-SUBAUTH", configObject.token1).accept("application/json").body(jsonBody)
		                .when().post(endPoint)
		                .then().statusCode(responseCode).extract().as(Map.class);
		        return response;
	}
}
