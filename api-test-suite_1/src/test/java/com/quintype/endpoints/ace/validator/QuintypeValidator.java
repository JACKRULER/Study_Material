package com.quintype.endpoints.ace.validator;

import com.quintype.config.ValidatorConfigFile;
import com.quintype.utils.JSONUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class QuintypeValidator {

    static Logger logger = LogManager.getLogger();


    public static Map getValidatorResponse1(ValidatorConfigFile configObject, JSONObject jsonBody, int responseCode, String strKey) {

        String endPoint = configObject.ValidatorURL + "/api/validate.json";
        Map response = given().log().ifValidationFails()
                .headers("Content-Type", "application/json")
                .body(jsonBody)
                .when().post(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        return (Map) JSONUtilities.getInnerJSON(response, "results").get(strKey);


    }


    public static ArrayList getValidatorResponseforlinks(ValidatorConfigFile configObject, JSONObject jsonBody, int responseCode) {

        String endPoint = configObject.ValidatorURL + "/api/validate.json";
        Map response;
        response = given().log().ifValidationFails()
                .headers("Content-Type", "application/json")
                .body(jsonBody)
                .when().post(endPoint)
                .then().statusCode(responseCode).extract().as(Map.class);
        return (ArrayList) response.get("links");


//        return (Map) JSONUtilities.getInnerJSON(response, "links");


    }


    public static String validateSEO(String pubURL, ValidatorConfigFile validatorConfigFile) {
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/validator.json");
        jsonBody.put("url", pubURL);
        Map seoResponse = QuintypeValidator.getValidatorResponse1(validatorConfigFile, jsonBody, 201, "seo");
        String strStatusseo = seoResponse.get("status").toString();
        if (!strStatusseo.equals("PASS")) {
            if (seoResponse.containsKey("errors")) {
                String errorStatus = seoResponse.get("errors").toString();
                logger.info(errorStatus);
            } else if (seoResponse.containsKey("debug")) {
                String debugStr = seoResponse.get("debug").toString();
                logger.info(debugStr);
            }
            return strStatusseo;

        }

        return strStatusseo;
    }


    public static String validateAMP(String pubURL , ValidatorConfigFile validatorConfigFile) {
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/validator.json");
        jsonBody.put("url", pubURL);
        Map ampResponse = QuintypeValidator.getValidatorResponse1(validatorConfigFile, jsonBody, 201, "amp");
        String strStatusamp = ampResponse.get("status").toString();
        if (!strStatusamp.equals("PASS")) {
            if (ampResponse.containsKey("errors")) {
                String errorStatus = ampResponse.get("errors").toString();
                logger.info(errorStatus);
            } else if (ampResponse.containsKey("debug")) {
                String debugStr = ampResponse.get("debug").toString();
                logger.info(debugStr);
            }
            return strStatusamp;

        }
        return strStatusamp;
    }

    public static String validateRobots(String pubURL, ValidatorConfigFile validatorConfigFile) {
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/validator.json");
        jsonBody.put("url", pubURL);
        Map robotsResponse = QuintypeValidator.getValidatorResponse1(validatorConfigFile, jsonBody, 201, "robots");
        String strStatusRobot = robotsResponse.get("status").toString();

        if (!strStatusRobot.equals("PASS")) {
            if (robotsResponse.containsKey("errors")) {
                String errorStatus = robotsResponse.get("errors").toString();
                logger.info(errorStatus);
            } else if (robotsResponse.containsKey("debug")) {
                String debugStr = robotsResponse.get("debug").toString();
                logger.info(debugStr);
            }
            return strStatusRobot;


        }
        return strStatusRobot;
    }
    public static String validateOG(String pubURL , ValidatorConfigFile validatorConfigFile) {
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/validator.json");
        jsonBody.put("url", pubURL);
        Map ogResponse = QuintypeValidator.getValidatorResponse1(validatorConfigFile, jsonBody, 201, "og");
        String strStatusog = ogResponse.get("status").toString();
        if (!strStatusog.equals("PASS")) {
            if (ogResponse.containsKey("errors")) {
                String errorStatus = ogResponse.get("errors").toString();
                logger.info(errorStatus);
            } else if (ogResponse.containsKey("debug")) {
                String debugStr = ogResponse.get("debug").toString();
                logger.info(debugStr);
            }
            return strStatusog;

        }

        return strStatusog;
    }



    public static String validateHEADERS(String pubURL , ValidatorConfigFile validatorConfigFile) {
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/validator.json");
        jsonBody.put("url", pubURL);
        Map headerResponse = QuintypeValidator.getValidatorResponse1(validatorConfigFile, jsonBody, 201, "headers");
        String strStatusheader = headerResponse.get("status").toString();
        if (!strStatusheader.equals("PASS")) {
            if (headerResponse.containsKey("errors")) {
                String errorStatus = headerResponse.get("errors").toString();
                logger.info(errorStatus);
            } else if (headerResponse.containsKey("debug")) {
                String debugStr = headerResponse.get("debug").toString();
                logger.info(debugStr);
            }
           return strStatusheader;

        }

        return strStatusheader;
    }


    public void validateLinks(String pubURL , ValidatorConfigFile validatorConfigFile){
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/validator.json");
        jsonBody.put("url",pubURL);
        ArrayList linksResponse = QuintypeValidator.getValidatorResponseforlinks(validatorConfigFile,jsonBody,201);
        System.out.println(linksResponse);


//        for(int i=0;i<linksResponse.size();i++){
//                String urls;
//                int statuscode = Integer.parseInt(null);
//                urls = ArrayList(linksResponse(i).get(statuscode));
//                Assert.assertEquals(urls , 200);
//
//            }

    }




}
