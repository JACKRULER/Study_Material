package com.quintype.scripts.platform;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.MemberEndPoints;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TestMemberActions extends SetUp {
    //To verify user can Add Author when there is a Default Role
    @Test(priority = 1)
    public void getMemberActions(){

        ConfigFile configObject = SetUp.getConfigObject();
        Map memberActionsResponse = MemberEndPoints.getmemberActions(configObject.basicAuth3,200);
        Map memberActionJson = JSONUtilities.getInnerJSON(memberActionsResponse, "actions");
        Assert.assertEquals(memberActionJson.size(),7);
        Assert.assertEquals(memberActionJson.get("story-social-media-check").toString(),"true");
        Assert.assertEquals(memberActionJson.get("story-read").toString(),"true");
        Assert.assertEquals(memberActionJson.get("story-save").toString(),"true");
        Assert.assertEquals(memberActionJson.get("story-submit").toString(),"true");
        Assert.assertEquals(memberActionJson.get("story-approve").toString(),"true");
        Assert.assertEquals(memberActionJson.get("set-assignments").toString(),"true");
        Assert.assertEquals(memberActionJson.get("story-publish").toString(),"true");
        System.out.println("/api/member/actions API returns the expected actions    ");


    }


}
