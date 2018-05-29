package com.quintype.scripts.platform;

import com.quintype.config.ConfigFile;

import com.quintype.endpoints.platform.ConfigEndpoints;
import com.quintype.endpoints.platform.ThemeUploadHelper;

import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestThemeUpload extends SetUp
{
    Logger logger = LogManager.getLogger();

    @Test(priority = 1)
    public void testUploadThemeAPI()
    {
    	ConfigFile configObject = SetUp.getConfigObject();

        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/theme.json");
        Map themeResponse;
        Map jsondata = JSONUtilities.getInnerJSON(jsonBody, "theme-attributes");
         themeResponse = ThemeUploadHelper.uploadtheam(configObject.itsmanURL,configObject.basicAuth,200,jsonBody.toString());
        Map configResponse = ConfigEndpoints.getconfig(200);
        Map configthemeResponse = JSONUtilities.getInnerJSON(configResponse, "theme-attributes");

        Assert.assertEquals(configthemeResponse.get("primary_color"),jsondata.get("primary_color"));
        Assert.assertEquals(configthemeResponse.get("secondary_color"),jsondata.get("secondary_color"));
        Assert.assertEquals(configthemeResponse.get("logo"),jsondata.get("logo"));
        Assert.assertEquals(configthemeResponse.get("monogram"),jsondata.get("monogram"));
        Assert.assertEquals(configthemeResponse.get("header_text_color"),jsondata.get("header_text_color"));
        Assert.assertEquals(configthemeResponse.get("header_background_color"),jsondata.get("header_background_color"));
        Assert.assertEquals(configthemeResponse.get("header_theme"),jsondata.get("header_theme"));
        Assert.assertEquals(configthemeResponse.get("footer_text_color"),jsondata.get("footer_text_color"));
        Assert.assertEquals(configthemeResponse.get("footer_background_color"),jsondata.get("footer_background_color"));


    }

}



