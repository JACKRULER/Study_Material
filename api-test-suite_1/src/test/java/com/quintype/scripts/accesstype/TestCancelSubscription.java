package com.quintype.scripts.accesstype;

import com.quintype.config.AccessTypeConfigFile;
import com.quintype.endpoints.accesstype.CancelSubscriptionEndpoint;
import com.quintype.endpoints.accesstype.CreateSubscriptionEndpoint;
import com.quintype.endpoints.accesstype.CreateSubscriptionGroupEndPoint;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class TestCancelSubscription extends SetUp {

    Logger logger = LogManager.getLogger();
    AccessTypeConfigFile accessTypeConfigFile;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    //@Test(priority = 3)
    public void testCancelSubscription()
       {
   		   logger.info("Starting testCancelSubscription test");
           accessTypeConfigFile = accessTypeConfigFile = SetUp.getAccessTypeConfigFile();
           //CreateSubscriptionEndpoint.createSubscriptionOnAccesstype();
           String subscriptionID ="8958";
           CancelSubscriptionEndpoint.cancelSubscription(accessTypeConfigFile.accesstypeURL,subscriptionID,accessTypeConfigFile.accesstypeProvider,accessTypeConfigFile.token1,204);

           logger.info("testCancelSubscription test completed");
       }       
}
