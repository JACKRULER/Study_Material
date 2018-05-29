package com.quintype.scripts.accesstype;

import java.util.Map;

import com.quintype.config.AccessTypeConfigFile;
import com.quintype.endpoints.accesstype.CreateSubscriptionEndpoint;
import com.quintype.endpoints.accesstype.CreateSubscriptionGroupEndPoint;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.quintype.utils.JSONUtilities;


public class TestCreateSubscription extends SetUp {

    Logger logger = LogManager.getLogger();
    AccessTypeConfigFile accessTypeConfigFile;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test(priority = 3)
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


           
   		   Map subscriptionResponse = CreateSubscriptionEndpoint.verifyCreatedSubscription(accessTypeConfigFile, 200);
           Assert.assertEquals((JSONUtilities.getArray(subscriptionResponse, "subscriptions").get(0).get("subscriber_id").toString()),subscriptionId);
           Assert.assertEquals((JSONUtilities.getArray(subscriptionResponse, "subscriptions").get(0).get("subscription_plan_id").toString()),subPlanId1);
           Assert.assertEquals((JSONUtilities.getArray(subscriptionResponse, "subscriptions").get(0).get("created_at").toString()),createdDate);
           Assert.assertEquals((JSONUtilities.getArray(subscriptionResponse, "subscriptions").get(0).get("updated_at").toString()),updatedDate);
           Assert.assertEquals((JSONUtilities.getArray(subscriptionResponse, "subscriptions").get(0).get("subscription_group_id").toString()),sunGrpId);
           Assert.assertEquals((JSONUtilities.getArray(subscriptionResponse, "subscriptions").get(0).get("start_timestamp").toString()),startTime);
           Assert.assertEquals((JSONUtilities.getArray(subscriptionResponse, "subscriptions").get(0).get("end_timestamp").toString()),endTime);


           logger.info("Created subscription & Verified.");
           logger.info("createSubscriptionOnAccesstype test completed");
       }       
}
