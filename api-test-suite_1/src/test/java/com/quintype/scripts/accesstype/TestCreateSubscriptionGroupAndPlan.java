package com.quintype.scripts.accesstype;
import com.quintype.config.AccessTypeConfigFile;

import com.quintype.endpoints.accesstype.CreateSubscriptionGroupEndPoint;

import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import java.util.Map;

public class TestCreateSubscriptionGroupAndPlan  extends SetUp {

    Logger logger = LogManager.getLogger();
    AccessTypeConfigFile accessTypeConfigFile;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test(priority = 1)
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
   @SuppressWarnings({"rawtypes","unchecked"})
   @Test(priority = 2)
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
}
