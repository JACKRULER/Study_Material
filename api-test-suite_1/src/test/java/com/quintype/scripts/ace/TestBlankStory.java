package com.quintype.scripts.ace;

import com.quintype.config.AceConfigFile;
import com.quintype.endpoints.ace.TestBlankStoryEndpoint;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.Map;

;

public class TestBlankStory extends SetUp {
    Logger logger = LogManager.getLogger();
    //@SuppressWarnings({"rawtypes", "unchecked"})
    public void getblankstory() {
        System.out.println("Start: creatingablankstory");
        AceConfigFile aceConfigFile = SetUp.getAceConfigObject();
        String storyTitle = "Blank Story: " + DataAndTimeUtilities.getCurrentDateAndTime();
        JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/BlankStory.json");
        jsonBody.put("headline", storyTitle);
        System.out.println(storyTitle);
        Map StoryResponse = TestBlankStoryEndpoint.getblankstory(aceConfigFile.basicAuth,200,jsonBody);
        System.out.println(StoryResponse);


    }

}
