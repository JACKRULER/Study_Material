package com.quintype.scripts;

import com.quintype.util.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quintype.pom.ConfigurePage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.WorkspaceFilters;
import com.quintype.pom.WorkspacePage;

public class TestConfigPromotionalMessage extends OpenBrowser
{   
  @BeforeClass(groups = {"functest", "platform","PlatformReg1"})
  public void loginToItsman()
  {
      WorkspacePage workspacePage = new WorkspacePage(driver);
      if(driver.getTitle().equalsIgnoreCase("Login"))
    {
      LoginPage loginPage = new LoginPage(driver);
      Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
      loginPage.loginToApplication(); 
    }
    else {
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickWorkspaceLink();
    }
  } 
  
   // update message with time stamp
   @Test(groups = {"functest", "platform","PlatformReg1"}, priority = 1)
   public void testAddingPromotionalMessage()
   {
     String expectedMessage = "Updated successfully!";
       WorkspacePage workspacePage = new WorkspacePage(driver);
       ConfigurePage configurePage = new ConfigurePage(driver);
       
       workspacePage.clickWorkspaceMenuButton();
       workspacePage.clickAdminLink();
       workspacePage.clickConfigureLink();
       configurePage.clickPromotionalMessageTab();
       configurePage.enterDefaultPromotionalMessage((dataPropertyFile.getProperty("Default_Signature"))+ CurrentDate.getCurrentDateAndTime());
       configurePage.enterSyndicatedPromotionalMessage((dataPropertyFile.getProperty("Syndicated_Signature"))+CurrentDate.getCurrentDateAndTime());
       configurePage.enterUGCPromotionalMessage((dataPropertyFile.getProperty("UGC_Signature"))+CurrentDate.getCurrentDateAndTime());
       configurePage.clickUpdateButton();
       Assert.assertFalse(CheckAlert.verifyAcceptAlert(driver));
       Assert.assertTrue(configurePage.getSuccessMessageElement().isDisplayed());
       Assert.assertTrue(Verification.verify(configurePage.getSuccessMessageElement().getText(), expectedMessage));
   }


   @Test(groups = {"functest", "platform","PlatformReg1"}, priority = 2)
   public void testPromotionalMessage()
   {
       WorkspacePage workspacePage = new WorkspacePage(driver);
       ConfigurePage configurePage = new ConfigurePage(driver);
       
       workspacePage.clickWorkspaceMenuButton();
       workspacePage.clickAdminLink();
       workspacePage.clickConfigureLink();
       configurePage.clickPromotionalMessageTab();
       String defaultPromotionalMessage = "Changed: "+(dataPropertyFile.getProperty("Default_Signature"))+CurrentDate.getCurrentDateAndTime();
       String syndicatedPromotionalMessage = "Changed: "+(dataPropertyFile.getProperty("Syndicated_Signature"))+CurrentDate.getCurrentDateAndTime();
       String ugcPromotionalMessage = "Changed: "+(dataPropertyFile.getProperty("UGC_Signature"))+CurrentDate.getCurrentDateAndTime();

       configurePage.enterDefaultPromotionalMessage(defaultPromotionalMessage);
       configurePage.enterSyndicatedPromotionalMessage(syndicatedPromotionalMessage);
       configurePage.enterUGCPromotionalMessage(ugcPromotionalMessage);
       configurePage.clickUpdateButton();
       Assert.assertFalse(CheckAlert.verifyAcceptAlert(driver));
       Assert.assertTrue(configurePage.getSuccessMessageElement().isDisplayed());

       Assert.assertEquals(configurePage.getPromotionalMessageTextFields(0).getText().toString().trim(), defaultPromotionalMessage);
       Assert.assertEquals(configurePage.getPromotionalMessageTextFields(1).getText().toString().trim(), syndicatedPromotionalMessage);
       Assert.assertEquals(configurePage.getPromotionalMessageTextFields(2).getText().toString().trim(), ugcPromotionalMessage);
   }

}
