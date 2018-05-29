package com.quintype.scripts.metype;

import com.quintype.config.MeTypeConfigFile;
import com.quintype.endpoints.metype.CommentEndPoints;
import com.quintype.endpoints.metype.SSOEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

public class TestSSO extends SetUp
{
	Logger logger = LogManager.getLogger();
	MeTypeConfigFile meTypeConfigFile;

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Test
	public void getSSOCurrentUserwithValidAccessToken() {
		logger.info("Starting getSSOCurrentUserwithValidAccessToken test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		Map currentUserResponse = SSOEndPoints.getCurrentUser(meTypeConfigFile.metypeURL,meTypeConfigFile.publisherAccessToken,200);
		Assert.assertEquals(meTypeConfigFile.usernameOfAccessToken,JSONUtilities.getInnerJSON(currentUserResponse,"current_user").get("name").toString());
		logger.info("Completed getSSOCurrentUserwithValidAccessToken");

	}
	@Test
	public void getSSOCurrentUserwithInvalidAccessToken() {
		logger.info("Starting getSSOCurrentUserwithInvalidAccessToken test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		Map currentUserResponse = SSOEndPoints.getCurrentUser(meTypeConfigFile.metypeURL,meTypeConfigFile.publisherAccessToken+"invalid",403);
		Assert.assertEquals("false",currentUserResponse.get("signed_in").toString());
		Assert.assertEquals("true",currentUserResponse.get("is_guest").toString());
		logger.info("Completed getSSOCurrentUserwithInvalidAccessToken test");

	}
	public void getSSOCurrentUserwithValidAccessTokenAndSession() {
		logger.info("Starting getSSOCurrentUserwithValidAccessToken test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		Map currentUserResponse = SSOEndPoints.getCurrentUserwithSession(meTypeConfigFile.metypeURL,meTypeConfigFile.talktypeAuthUser1,meTypeConfigFile.publisherAccessToken,200);
		Assert.assertEquals(meTypeConfigFile.usernameOfAccessToken,JSONUtilities.getInnerJSON(currentUserResponse,"current_user").get("name").toString());
		logger.info("Completed getSSOCurrentUserwithValidAccessToken");

	}



	}

			