package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.jayway.restassured.RestAssured.given;

public class ConfigPageEndPoints 
{
	// Post API call to create Promotional Messages.
    @SuppressWarnings("rawtypes")
	public static Map createPromotionalMessage(String jsonBody, int responseCode)
    {
    	ConfigFile configObject = SetUp.getConfigObject();
   
        String endPoint = configObject.itsmanURL + "/api/promotional-messages";
        Map response = given().log().ifValidationFails()
        		.header("User-Agent", "curl/7.38.0")
        		.header("Content-Type", "application/json")
                .header("X-QT-AUTH", configObject.basicAuth)
                .body(jsonBody)
                .when().post(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
    	
        return response;
    }
    @SuppressWarnings("rawtypes")
	public static List <String> verifyPromotionalMessages(String toggleSlug, int responseCode)
    {
    	List <String> promotionalMessages = new ArrayList<String>();
    	ConfigFile configObject = SetUp.getConfigObject();
        String endPoint = configObject.itsmanURL + "/api/select-features";
        ArrayList response = given().log().ifValidationFails().header("Content-Type", "application/json")
                .header("X-QT-Auth", configObject.basicAuth)
                .when().get(endPoint)
                .then().assertThat().statusCode(responseCode).extract().as(ArrayList.class);
        for (int i = 0; i < response.size(); i++) 
        {
            Map singleToggle = (Map) response.get(i);
            if (singleToggle.get("slug").toString().equals(toggleSlug)) 
            {           	
            	promotionalMessages.add((String) JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(singleToggle, "config"), "default").get("text"));	
            	promotionalMessages.add((String) JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(singleToggle, "config"), "sources"), "ugc").get("text"));
            	promotionalMessages.add((String) JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(singleToggle, "config"), "sources"), "syndicated").get("text"));
            }

        }
        return promotionalMessages;
    }

}


