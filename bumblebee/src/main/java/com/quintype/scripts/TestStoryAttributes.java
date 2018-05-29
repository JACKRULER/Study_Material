package com.quintype.scripts;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.AdminSettingsAttributesPage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.*;

public class TestStoryAttributes extends OpenBrowser {

    private String mutliValueType = "Multi Valued Strings";
    private String entityType = "Entity";

    @BeforeClass(groups = {"functest", "platform", "smoketest","PlatformReg1"})
    public void loginToItsman()
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        if(driver.getTitle().equalsIgnoreCase("Login"))
        {
            Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
            loginPage.loginToApplication();
        }

    }

    // Create an attribute and validate in list,story and delete an attribute and validate in list,story
    @Test(groups = {"functest", "platform", "smoketest","PlatformReg1"}, priority = 1)
    public void verifyAttributeCreationAndDeletionOnStoryPage()
    {
        String attributeName = "Attr_TC_01" + CurrentDate.getCurrentDateAndTime();
        String attributeName1 = "Attr_TC_02" + CurrentDate.getCurrentDateAndTime();

        String values[] = {"A1", "A2", "A3"};

        String expectedAttributeValues = "A1, A2, A3";
        String updatedAttributeValues = "A1";

        WorkspacePage workspacePage = new WorkspacePage(driver);
        AdminSettingsAttributesPage adminSettingsAttributesPage = new AdminSettingsAttributesPage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
        NewStoryPage newStoryPage = new NewStoryPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickSettingsLink();
        adminSettingsAttributesPage.clickAttributesLink();
        adminSettingsAttributesPage.clickAddAttributeButton();
        adminSettingsAttributesPage.selectStoryTypeRadioButton();
        adminSettingsAttributesPage.enterAttributeName(attributeName);
        adminSettingsAttributesPage.selectTypeDropDown(mutliValueType);
        for(String value : values)
            adminSettingsAttributesPage.selectValueDropDown(value);
        adminSettingsAttributesPage.clickSaveAttributeButton();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsAttributesPage.getAttributesName(), attributeName));
        Assert.assertFalse(adminSettingsAttributesPage.getAttributeTypeStatus(attributeName));
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickWorkspaceLink();
        workspacePage.clickNewStoryButton();
        storyTypePage.clickBlankStoryButton();
        newStoryPage.clickStoryAttributesLink();
        Assert.assertTrue(newStoryPage.isAttributeListed(attributeName));

        //Delete Attribute and verify in list page and story page
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickSettingsLink();
        adminSettingsAttributesPage.clickAttributesLink();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsAttributesPage.getAttributesName(), attributeName));
        adminSettingsAttributesPage.clickDeleteAttributeButton(attributeName);
        if(!(adminSettingsAttributesPage.isAttributesStackListEmpty()))
        {
            Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(adminSettingsAttributesPage.getAttributesName(), attributeName));
        }
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickWorkspaceLink();
        workspacePage.clickNewStoryButton();
        storyTypePage.clickBlankStoryButton();
        newStoryPage.clickStoryAttributesLink();
        Assert.assertFalse(newStoryPage.isAttributeListed(attributeName));

    }


    //Verify Mandatory Fields error message on Attribute Page.
    @Test(groups = {"functest", "platform" , "smoketest","PlatformReg1"}, priority = 2)
    public void verifyMandatoryErrorMessages()
    {
        String listOFErrorMessages[] = {"Display name can't be blank", "Values can't be empty"};
        WorkspacePage workspacePage = new WorkspacePage(driver);
        AdminSettingsAttributesPage adminSettingsAttributesPage = new AdminSettingsAttributesPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickSettingsLink();
        adminSettingsAttributesPage.clickAttributesLink();
        adminSettingsAttributesPage.clickAddAttributeButton();
        adminSettingsAttributesPage.clickSaveAttributeButton();
        Assert.assertTrue(Verification.compareTwoListOfString(adminSettingsAttributesPage.getListOfErrorMessages()	, listOFErrorMessages));
        adminSettingsAttributesPage.clickCloseButton();


    }

    //Verify user not able to create duplicate Attribute Values.
    @Test(groups = {"functest", "platform" , "smoketest","PlatformReg1"}, priority = 3)
    public void testUserNotAbleToCreateDuplicateAttributeValues()
    {
        String attributeName = "Attr_TC_2"+CurrentDate.getCurrentDateAndTime();
        String values[] = {"TC21", "TC21", "TC21", "TC21"};
        String expectedValues = "TC21";
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        AdminSettingsAttributesPage adminSettingsAttributesPage = new AdminSettingsAttributesPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickSettingsLink();
        adminSettingsAttributesPage.clickAttributesLink();
        adminSettingsAttributesPage.clickAddAttributeButton();
        adminSettingsAttributesPage.enterAttributeName(attributeName);
        adminSettingsAttributesPage.selectTypeDropDown(mutliValueType);
        for(String value : values)
            adminSettingsAttributesPage.selectValueDropDown(value);
        Assert.assertEquals(adminSettingsAttributesPage.getValues(),expectedValues);
        adminSettingsAttributesPage.clickCloseButton();
    }

}
