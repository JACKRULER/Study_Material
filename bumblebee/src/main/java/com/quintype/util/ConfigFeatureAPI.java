package com.quintype.util;

import static com.jayway.restassured.RestAssured.*;
import java.util.Map;
import org.json.simple.JSONArray;
import com.jayway.restassured.RestAssured;


public class ConfigFeatureAPI {
	static String basicAuth = "BODQN44uBRstEAETwzu92kY1PHBlm8ZARdgExd4JRtQUv9NZ3ydknnCKVX8eVLx/6C/PeNoZv2kS6zw/LDLabHaCuUkYDt16edZ9QxzHHvXeXkItJGA9Hqe5HTxPqq1zOX3NouZ6sbrqhb6wjjlhl+NSJ/MyHdL4edigcOt0ZbVKX4NbsHmRQbJI3hC+YS9lkyqWQiKqUw7biuWURtU2aD1+wry31Kl5BaeGYBjPuSfjOvFlO4ydC6nkgdZIoUzyqjIambrI+d73mUw32L/6hGRVnDiwp47EmVcHZXb4XdMXvqAuaBSimU4RK76LpRDBa7QGULq8q76KDDI0XXkKS3f8bu9QNeOstHJBpK4YNGFIwFZTxUOow75s1Eu6R66h95wAacjV+a1UieWuKPIIxWRKsIaZ5E5RWYvCWhQ86ZE=--3Ni5lXhGaPJOmo9KumlG5s97/NroDu6U3KFLF9/kQhM=";
	static String toggleValue;
	@SuppressWarnings("rawtypes")
	public static String callAPI(String toggleFeatureName){
		
		JSONArray response = given().log().ifValidationFails().header("Content-Type", "application/json").header("X-QT-Auth", basicAuth)
				.accept("application/json")
				.when().get("https://qa.staging.quintype.com/api/select-features")
				.then().assertThat().statusCode(200).extract().as(JSONArray.class);
		
		for(int i=0;i<response.size();i++){
			Map map = (Map) response.get(i);
			
			if(map.containsKey("name")){
				if(map.get("name").toString().equalsIgnoreCase(toggleFeatureName)){
					
					toggleValue = map.get("toggle").toString();					
				}
			}			
		}
		return toggleValue;
	}
}
	
	
