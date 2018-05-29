package com.quintype.scripts.platform;

import com.google.gson.JsonObject;
import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.AttributeCreationEndPoints;
import com.quintype.endpoints.platform.AuthorEndPoints;
import com.quintype.endpoints.platform.ConfigEndpoints;
import com.quintype.endpoints.platform.CustomURLEndpoint;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.io.*;


public class TestAdminCustomUrls extends SetUp{
    Logger logger = LogManager.getLogger();

    @Test(priority = 1)
    public void createCustomUrlAndVerifyOnSketches() {
        logger.info("Starting Custom-url creation");
        ConfigFile configObject = SetUp.getConfigObject();
        String sourcePath = "/new/slug: "+DataAndTimeUtilities.getCurrentDateAndTime();
        String encodedSourcePath = URLEncoder.encode(sourcePath);


        JSONObject json = JSONUtilities.getJSONFileObject("./src/test/resources/CustomUrls.json");
        json.put("source-path",sourcePath);
        String type=json.get("type").toString();
        long statusCode = new Long((Long) json.get("status-code"));
        String destinationPath = json.get("destination-path").toString();
        CustomURLEndpoint.createCustomUrl(configObject.basicAuth,201,json);
        Map sketchesResponse = CustomURLEndpoint.verifycreateCustomUrlOnSketches(configObject.sketchesURL,configObject.basicAuth,200,encodedSourcePath);
        String expectedStatuscode1 =JSONUtilities.getInnerJSON(sketchesResponse,"page").get("status-code").toString();
        Long d = (Long) json.get("status-code");
        int expectedStatuscode = d.intValue();
        Assert.assertEquals(JSONUtilities.getInnerJSON(sketchesResponse,"page").get("type"),type);
        Assert.assertEquals(expectedStatuscode,statusCode);
        Assert.assertEquals(JSONUtilities.getInnerJSON(sketchesResponse,"page").get("destination-path"),destinationPath);
        logger.info("created custom-url and verified on sketches");

    }

    @Test(priority = 2)
    public void deleteCustomUrlAndVerifyOnSketches() {
        logger.info("Starting Custom-url deletion");
        ConfigFile configObject = SetUp.getConfigObject();
        String sourcePath = "/new/slug: "+DataAndTimeUtilities.getCurrentDateAndTime();
        String encodedSourcePath = URLEncoder.encode(sourcePath);


        JSONObject json = JSONUtilities.getJSONFileObject("./src/test/resources/CustomUrls.json");
        json.put("source-path",sourcePath);
        Map itsmanResponse = CustomURLEndpoint.createCustomUrl(configObject.basicAuth,201,json);
        //String id = itsmanResponse.get("id").toString();
        Double d = (Double) itsmanResponse.get("id");
        int id = d.intValue();
        logger.info(id);
        CustomURLEndpoint.deleteCustomUrl(configObject.basicAuth,204,id);
        Map sketchesResponse = CustomURLEndpoint.verifycreateCustomUrlOnSketches(configObject.sketchesURL,configObject.basicAuth,200,encodedSourcePath);
        Assert.assertEquals(sketchesResponse.get("page"),null);
        Double d1 = (Double) sketchesResponse.get("status-code");
        int statusCode = d1.intValue();
        Assert.assertEquals(statusCode,404);
        logger.info("deleted custom-url and verified on sketches");
    }
    @Test(priority = 3)
    public void verifyMandatoryFields() throws InterruptedException
    {
        logger.info("Starting Custom-url creation");
        ConfigFile configObject = SetUp.getConfigObject();

        JSONObject json = JSONUtilities.getJSONFileObject("./src/test/resources/CustomUrls.json");
        json.remove("source-path");

        CustomURLEndpoint.createCustomUrl(configObject.basicAuth,422,json);
        logger.info("Validated mandatory fields");

    }
    @Test(priority = 4)
    public void editCustomUrlAndVerifyOnSketches() {
        logger.info("Starting Custom-url editing");
        ConfigFile configObject = SetUp.getConfigObject();
        String sourcePath = "/new/slug: "+DataAndTimeUtilities.getCurrentDateAndTime();
        String sourcePath1 = "/edit/slug: "+DataAndTimeUtilities.getCurrentDateAndTime();
        String encodedSourcePath = URLEncoder.encode(sourcePath);
        String destinationPath1 ="/destination/slug"+DataAndTimeUtilities.getCurrentDateAndTime();


        JSONObject json = JSONUtilities.getJSONFileObject("./src/test/resources/CustomUrls.json");
        json.put("source-path",sourcePath);
        Map itsmanResponse = CustomURLEndpoint.createCustomUrl(configObject.basicAuth,201,json);
        Double d = (Double) itsmanResponse.get("id");
        int id = d.intValue();
        String destinationPath = json.get("destination-path").toString();
        Map sketchesResponse = CustomURLEndpoint.verifycreateCustomUrlOnSketches(configObject.sketchesURL,configObject.basicAuth,200,encodedSourcePath);
        Assert.assertEquals(JSONUtilities.getInnerJSON(sketchesResponse,"page").get("destination-path"),destinationPath);

        json.put("source-path",sourcePath1);
        json.put("destination-path",destinationPath1);
        logger.info(json);
        String encodedSourcePath1 = URLEncoder.encode(sourcePath1);
        CustomURLEndpoint.editCustomUrl(configObject.basicAuth,200,id,json);
        Map sketchesResponse1 = CustomURLEndpoint.verifycreateCustomUrlOnSketches(configObject.sketchesURL,configObject.basicAuth,200,encodedSourcePath1);
        Assert.assertEquals(JSONUtilities.getInnerJSON(sketchesResponse1,"page").get("destination-path"),destinationPath1);

        logger.info("Custom-url edited and verified on sketches");

        CustomURLEndpoint.createCustomUrl(configObject.basicAuth,422,json);
        logger.info("Validated for duplicate custom-urls");

    }


    }
