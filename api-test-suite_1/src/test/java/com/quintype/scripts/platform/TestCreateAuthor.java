package com.quintype.scripts.platform;

import com.quintype.config.ConfigFile;
import com.quintype.endpoints.platform.AuthorEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.SetUp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TestCreateAuthor extends SetUp {
    //To verify user can Add Author when there is a Default Role
    @Test(priority = 1)
    public void addAuthorWithPermission(){
        System.out.println("Starting addAuthorWithPermission test");
        ConfigFile configObject = SetUp.getConfigObject();
        String name = DataAndTimeUtilities.getCurrentDateForEmail();
        String email = DataAndTimeUtilities.getCurrentDateForEmail()+"@gmail.com";
        String jsonBody = "{\"metadata\":{},\"name\":\""+name+"\",\"email\":\""+email+"\",\"communication-email\":\""+email+"\",\"bio\":\"QWERTY\",\"username\":\"Random1@qa.com\",\"password\":\"Quintype_92237\",\"can-login\":false}";
        System.out.println(jsonBody);
        int status = 201;
        String basicAuth = configObject.basicAuth2;
        String responseMessage = "Successfully created author with Guest Contributor role.";
        Map createAuthorResponse = AuthorEndPoints.createAuthor(basicAuth,jsonBody,status);
        Assert.assertEquals(createAuthorResponse.get("success"), responseMessage);
        System.out.println("End of addAuthorWithPermission test");
    }

    //To verify user cannot Add Author when the Author already exist
    @Test(priority = 1)
    public void duplicateAuthorWithPermission() throws InterruptedException {
        System.out.println("Starting duplicateAuthorWithPermission test");
        Thread.sleep(2000);
        ConfigFile configObject = SetUp.getConfigObject();
        String name = DataAndTimeUtilities.getCurrentDateForEmail();
        String email = DataAndTimeUtilities.getCurrentDateForEmail()+"@gmail.com";
        String jsonBody = ("{\"metadata\":{},\"name\":\""+name+"\",\"email\":\""+email+"\",\"communication-email\":\""+email+"\",\"bio\":\"Some Bio about the author\",\"username\":\""+name+"\",\"password\":\"Quintype_84291\",\"can-login\":false}");
        System.out.println(jsonBody);
        int status = 201;
        String basicAuth = configObject.basicAuth2;
        String responseSuccessMessage = "Successfully created author with Guest Contributor role.";
        String responseErrorMessage = "Member already exists.";
        Map createAuthorSuccessResponse = AuthorEndPoints.createAuthor(basicAuth,jsonBody,status);
        Assert.assertEquals(createAuthorSuccessResponse.get("success"), responseSuccessMessage);
        Map createAuthorErrorResponse = AuthorEndPoints.createAuthor(basicAuth,jsonBody,status);
        Assert.assertEquals(createAuthorErrorResponse.get("error"), responseErrorMessage);
        System.out.println("End of duplicateAuthorWithPermission test");
    }
}
