package com.quintype.scripts;

import com.quintype.util.CurrentDate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.CheckAlert;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;
import com.quintype.pom.AuthorProfilePage;

public class TestAuthorProfile extends OpenBrowser
{   
	@BeforeClass(groups = {"functest", "platform","PlatformReg1"})
	public void loginToItsman()
	{
        WorkspacePage workspacePage = new WorkspacePage(driver);
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.authorloginToApplication(dataPropertyFile.getProperty("Username3"), dataPropertyFile.getProperty("Password3"));
		}
		else {
            workspacePage.clickWorkspaceMenuButton();
            workspacePage.clickWorkspaceLink();
            workspacePage.clickProfileButton();
            workspacePage.clickLogoutLink();
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
            loginPage.authorloginToApplication(dataPropertyFile.getProperty("Username3"), dataPropertyFile.getProperty("Password3"));

        }
	}	
	
    @Test(groups = {"functest", "platform","PlatformReg1"}, priority = 1)
    public void testValidProfileSaveWithImage()
    {
        log.info("Execution of TestAuthorProfile Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        AuthorProfilePage authorProfilePage = new AuthorProfilePage(driver);
        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickWorkspaceMenuButton(); 
        Assert.assertEquals("Author Profile" , workspacePage.myProfile());      //Click on Profile link & verify the tab title
        authorProfilePage.enterTwitterHandleFieldData(dataPropertyFile.getProperty("twitter_Handle"));
        authorProfilePage.enterCommunicationEmailFieldData(dataPropertyFile.getProperty("twitter_Handle"));
        authorProfilePage.enterBioFieldData(dataPropertyFile.getProperty("bio_Field"));
        if(authorProfilePage.getImageStatus()==false) {
            authorProfilePage.uploadProfileImage(dataPropertyFile.getProperty("Profile_Pic"));
            authorProfilePage.clickSaveButton();
        }
        else {
            authorProfilePage.clickSaveButton();
        }
        Assert.assertEquals(false , CheckAlert.verifyAcceptAlert(driver), "Alert pop up didn't come when save Profile with new changes");
        Assert.assertTrue(authorProfilePage.getImageStatus());
        
    }
    @Test(groups = {"functest", "platform","PlatformReg1"}, priority = 2)
    public void testMyProfileSaveWithoutName()
    {        
        WorkspacePage workspacePage = new WorkspacePage(driver);
        AuthorProfilePage authorProfilePage = new AuthorProfilePage(driver);
        workspacePage.clickWorkspaceMenuButton();                                          //Click on Workspace Menu
        Assert.assertEquals("Author Profile" , workspacePage.myProfile());      //Click on Profile link & verify the tab title
        authorProfilePage.clearAuthorName();          //Verify Mandatory fields
        authorProfilePage.clickSaveButton();
        Assert.assertEquals(false , CheckAlert.verifyAcceptAlert(driver), "Alert pop up didn't come when save Profile without name");
        
    }       
    
}