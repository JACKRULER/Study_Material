package com.quintype.endpoints.platform;

import com.quintype.config.ConfigFile;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import static com.jayway.restassured.RestAssured.given;

public class EntityEndPoints extends SetUp {
	
	static String jsonBody;
	@SuppressWarnings("rawtypes")
	static List<Map> allEntity = new ArrayList<Map>();	
	@SuppressWarnings("rawtypes")
	public static void createEntity(int responseCode, String basicAuth, String entityName) {
		ConfigFile configObject = SetUp.getConfigObject();
		String endPoint = configObject.itsmanURL + "/api/entity/";


		jsonBody = "{\"entity\":{\"type\":\"dude\",\"name\":\"" + entityName + "\"}}";
		Map entityCreateAPIResponse = given().log().ifValidationFails().header("Content-Type", "application/json")
				.header("X-QT-Auth", basicAuth).accept("application/json").body(jsonBody)
				.when().post(endPoint)
				.then().assertThat().statusCode(responseCode).extract().as(Map.class);

		String entityNameFromResponse = JSONUtilities.getValueFromResponse(JSONUtilities.getInnerJSON(entityCreateAPIResponse, "entity"), "name");

		Assert.assertEquals(entityName, entityNameFromResponse);


	}


	}

