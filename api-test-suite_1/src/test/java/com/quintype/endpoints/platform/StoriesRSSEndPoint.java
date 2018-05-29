package com.quintype.endpoints.platform;

import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.xml.element.NodeChildren;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;
import com.quintype.config.ConfigFile;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.registerParser;

public class StoriesRSSEndPoint {

    public static NodeChildren storiesRss(int responseCode)
    {
        //String endPoint = "/stories.rss";
        ConfigFile configObject = new ConfigFile();
        String endPoint= configObject.sketchesURL+"/stories.rss?tag=test";
        registerParser("application/xml", Parser.XML);

        ExtractableResponse<Response> response = given().log().ifValidationFails().header("Content-Type", "application/rss+xml")
                .when().get(endPoint)
                .then().statusCode(responseCode).extract();

        // This will return all the stories in the RSS feed - first 7 nodes are RSS feed's metadata
        return response.response().xmlPath().get().children().get(0).children();

    }

    public static String getNodeTitle(NodeChildren node, int nodeIndex){
    	
        return String.valueOf(node.get(nodeIndex+7).children().get(0));
    }


}
