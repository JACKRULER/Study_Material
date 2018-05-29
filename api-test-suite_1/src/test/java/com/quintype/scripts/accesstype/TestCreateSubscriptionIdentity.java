package com.quintype.scripts.accesstype;

import com.quintype.config.AccessTypeConfigFile;
import com.quintype.endpoints.accesstype.CreateIdentitySubscriptionEndpoint;
import com.quintype.endpoints.accesstype.CreateSubscriptionEndpoint;
import com.quintype.endpoints.accesstype.CreateSubscriptionGroupEndPoint;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.Map;


public class TestCreateSubscriptionIdentity extends SetUp {

    Logger logger = LogManager.getLogger();
    AccessTypeConfigFile accessTypeConfigFile;

    //@SuppressWarnings({ "rawtypes", "unchecked" })
    //@Test (priority = 1)

    public void createSubscriptionGroupOnAccesstype()
    {
        logger.info("Starting createSubscriptionGroupOnAccesstype test ");
        accessTypeConfigFile = SetUp.getAccessTypeConfigFile();
        String subscriptionGroupName = "Sub_Group: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/accesstype/createSubscriptionGroup.json");

        JSONUtilities.getInnerJSON(jsonBody, "subscription_group").put("name", subscriptionGroupName).toString();
        String subGroupName =  JSONUtilities.getInnerJSON(jsonBody, "subscription_group").get("name").toString();


        Map subscriptionGroupCreateAPIResponse = CreateSubscriptionGroupEndPoint.createSubscriptionGroupOnAccesstype(accessTypeConfigFile, 201, jsonBody.toString());
        String subGroupId = JSONUtilities.getInnerJSON(subscriptionGroupCreateAPIResponse, "subscription_group").get("id").toString();

        Map subscriptionGroupResponse = CreateSubscriptionGroupEndPoint.verifyCreatedSubscriptionGroup(accessTypeConfigFile, 200);
        Assert.assertEquals((JSONUtilities.getInnerJSON(subscriptionGroupResponse, "subscription_group").get("id").toString()),subGroupId);
        Assert.assertEquals((JSONUtilities.getInnerJSON(subscriptionGroupResponse, "subscription_group").get("name").toString()),subGroupName);

        logger.info("Created subscription group & Verified.");
        logger.info("createSubscriptionGroupOnAccesstype completed ");

    }

    //@SuppressWarnings({"rawtypes","unchecked"})
    //@Test(priority = 2)
    public void createSubscriptionPlanOnAccesstype()
    {
        logger.info("Starting createSubscriptionPlanOnAccesstype test ");
        accessTypeConfigFile = SetUp.getAccessTypeConfigFile();
        String subscriptionPlanName = "Sub_Plan: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/accesstype/createSubscriptionPlan.json");


        JSONUtilities.getInnerJSON(jsonBody, "subscription_plan").put("title", subscriptionPlanName).toString();
        Map subscriptionGroupResponse = CreateSubscriptionGroupEndPoint.verifyCreatedSubscriptionGroup(accessTypeConfigFile, 200);
        String subGroupId= JSONUtilities.getInnerJSON(subscriptionGroupResponse, "subscription_group").get("id").toString();

        JSONUtilities.getInnerJSON(jsonBody, "subscription_plan").put("subscription_group_id", subGroupId).toString();
        @SuppressWarnings("unused")
        String subPlanName =  JSONUtilities.getInnerJSON(jsonBody, "subscription_plan").get("title").toString();
        String sub_groupid =  JSONUtilities.getInnerJSON(jsonBody, "subscription_plan").get("subscription_group_id").toString();


        Map subscriptionPlanCreateAPIResponse = CreateSubscriptionGroupEndPoint.createSubscriptionPlanOnAccesstype(accessTypeConfigFile, 201, jsonBody.toString());
        String subPlanId = JSONUtilities.getInnerJSON(subscriptionPlanCreateAPIResponse, "subscription_plan").get("id").toString();
        String planDurationLength = JSONUtilities.getInnerJSON(subscriptionPlanCreateAPIResponse, "subscription_plan").get("duration_length").toString();
        String planDuration = JSONUtilities.getInnerJSON(subscriptionPlanCreateAPIResponse, "subscription_plan").get("duration_unit").toString();
        String planCurrency = JSONUtilities.getInnerJSON(subscriptionPlanCreateAPIResponse, "subscription_plan").get("price_currency").toString();
        String planPrice = JSONUtilities.getInnerJSON(subscriptionPlanCreateAPIResponse, "subscription_plan").get("price_cents").toString();
        String isPlanRecurring = JSONUtilities.getInnerJSON(subscriptionPlanCreateAPIResponse, "subscription_plan").get("recurring").toString();
        String planCreatedAt = JSONUtilities.getInnerJSON(subscriptionPlanCreateAPIResponse, "subscription_plan").get("created_at").toString();
        String planUpdatedAt = JSONUtilities.getInnerJSON(subscriptionPlanCreateAPIResponse, "subscription_plan").get("updated_at").toString();

        Map subscriptionGroupResponse1 = CreateSubscriptionGroupEndPoint.verifyCreatedSubscriptionGroup(accessTypeConfigFile, 200);
        Map<String, String> responseJsonBody = JSONUtilities.getInnerJSON(subscriptionGroupResponse1, "subscription_group");
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("id").toString()),subPlanId);
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("subscription_group_id").toString()),sub_groupid);
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("duration_length").toString()),planDurationLength);
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("duration_unit").toString()),planDuration);
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("price_currency").toString()),planCurrency);
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("price_cents").toString()),planPrice);
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("recurring").toString()),isPlanRecurring);
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("created_at").toString()),planCreatedAt);
        Assert.assertEquals((JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("updated_at").toString()),planUpdatedAt);

        logger.info("Created subscription plan & Verified.");
        logger.info("createSubscriptionPlanOnAccesstype test completed");
    }

    //@SuppressWarnings({ "rawtypes", "unchecked" })
    //@Test(priority = 3)
    public void createSubscriptionOnAccesstype()
    {
        logger.info("Starting createSubscriptionOnAccesstype test");
        accessTypeConfigFile = accessTypeConfigFile = SetUp.getAccessTypeConfigFile();
        Map subscriptionGroupResponse = CreateSubscriptionGroupEndPoint.verifyCreatedSubscriptionGroup(accessTypeConfigFile, 200);
        Map responseJsonBody = JSONUtilities.getInnerJSON(subscriptionGroupResponse, "subscription_group");
        String subPlanId =JSONUtilities.getArray(responseJsonBody, "subscription_plans").get(0).get("id").toString();
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/accesstype/createSubscription.json");
        JSONUtilities.getInnerJSON(jsonBody, "subscription").put("subscription_plan_id", subPlanId).toString();
        @SuppressWarnings("unused")
        String subscriptionPlanID = JSONUtilities.getInnerJSON(jsonBody, "subscription").put("subscription_plan_id", subPlanId).toString();
        Map subscriptionCreateAPIResponse = CreateSubscriptionEndpoint.createSubscriptionOnAccesstype(accessTypeConfigFile, 201, jsonBody.toString());
        String subscriptionId =  JSONUtilities.getInnerJSON(subscriptionCreateAPIResponse, "subscription").get("subscriber_id").toString();
        String subPlanId1 =  JSONUtilities.getInnerJSON(subscriptionCreateAPIResponse, "subscription").get("subscription_plan_id").toString();
        String createdDate =  JSONUtilities.getInnerJSON(subscriptionCreateAPIResponse, "subscription").get("created_at").toString();
        String updatedDate =  JSONUtilities.getInnerJSON(subscriptionCreateAPIResponse, "subscription").get("updated_at").toString();
        String sunGrpId =  JSONUtilities.getInnerJSON(subscriptionCreateAPIResponse, "subscription").get("subscription_group_id").toString();
        String startTime =  JSONUtilities.getInnerJSON(subscriptionCreateAPIResponse, "subscription").get("start_timestamp").toString();
        String endTime =  JSONUtilities.getInnerJSON(subscriptionCreateAPIResponse, "subscription").get("end_timestamp").toString();

        logger.info("Created subscription & Verified.");
    }


    public void createSubscriptionIdentityOnAccesstype()
       {
    	   logger.info("Starting createSubscriptionIdentityOnAccesstype test");
           accessTypeConfigFile = SetUp.getAccessTypeConfigFile();
           Map subscriptionResponse = CreateSubscriptionEndpoint.verifyCreatedSubscription(accessTypeConfigFile, 200);
           Object storyJson = (Map)JSONUtilities.getArrayValueToMap(subscriptionResponse,"subscription",0,"story");
           String actualAuthor = (JSONUtilities.getArrayValue((Map) storyJson,"authors",0,"name").toString());

           JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/accesstype/createIdentity.json");
           logger.info(jsonBody);
           String subscribe_identity_value = "testing" + DataAndTimeUtilities.getCurrentDateAndTime()+"test.com";
           JSONUtilities.getInnerJSON(jsonBody, "subscriber_identity").put("value", subscribe_identity_value).toString();
           logger.info(jsonBody);
           
           Map subscriptionIdentityCreateAPIResponse = CreateIdentitySubscriptionEndpoint.CreateIdentitySubscription(accessTypeConfigFile.accesstypeURL,accessTypeConfigFile.token1, accessTypeConfigFile.accesstypeProvider, 201, jsonBody.toString());
           String subscribe_identity_provider =  JSONUtilities.getInnerJSON(subscriptionIdentityCreateAPIResponse, "subscriber_identity").get("provider").toString();
           String subscribe_identity_value1 =  JSONUtilities.getInnerJSON(subscriptionIdentityCreateAPIResponse, "subscriber_identity").get("value").toString();
           logger.info(subscribe_identity_value1);
           logger.info(subscribe_identity_provider);

           
           Map subscriptionIdenityResponse = CreateIdentitySubscriptionEndpoint.verifyCreatedSubscriptionIdentity(accessTypeConfigFile.accesstypeURL,accessTypeConfigFile.token1, accessTypeConfigFile.accesstypeProvider, 200);
           logger.info(subscriptionIdenityResponse);
           //Assert.assertEquals((JSONUtilities.getArray(subscriptionIdenityResponse, "subscriber_identity").get(0).get("value").toString()),subscribe_identity_value1);
           logger.info(JSONUtilities.getArray(subscriptionIdenityResponse, "subscriber_identity").get(0).get("value").toString());
           logger.info("Created subscriptionIdentity & Verified.");
           logger.info("createSubscriptionIdentityOnAccesstype test completed");


		}
 }	