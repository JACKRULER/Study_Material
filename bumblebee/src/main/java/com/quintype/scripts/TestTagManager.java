package com.quintype.scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.TagManagerPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.CreateTestData;
import com.quintype.util.CurrentDate;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;

public class TestTagManager extends OpenBrowser 
{
	@BeforeClass(groups = {"functest", "platform", "PlatformReg2"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();

		}
	}	

	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 1)
	public void testValidTagSearchAndCreate()
	{
		log.info("Execution of TestTagManager testValidTagSearchAndCreate Started");
		CurrentDate currentDate = new CurrentDate();
		String tagName = "Tag"+currentDate.getCurrentDateForEmail();
		String metaDescription = "Meta Description for "+tagName+" Tag";
		String tagSlug = tagName.toLowerCase();
		WorkspacePage workspacePage = new WorkspacePage(driver);
    	TagManagerPage tagManagerPage = new TagManagerPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickTagManagerLink();
        Assert.assertEquals(driver.getTitle(), "Tag Manager", "Itsman is down or not reachable at this moment");
        tagManagerPage.enterDataInTagSearchField(tagName);
        Assert.assertTrue(tagManagerPage.isAddNewTagLinkDisplayed());
        tagManagerPage.clickAddNewTagLink();
		Assert.assertEquals(tagManagerPage.validateTagSlug(), tagSlug);
        tagManagerPage.enterMetaDescription(metaDescription);

        tagManagerPage.clickEditTagButton();
        tagManagerPage.enterDataInTagSearchField(tagName);
        Assert.assertTrue(Verification.verify(tagManagerPage.getListOfTagNames().get(0).getText(), tagName));
        
	}

	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 2)
	public void testNewlyCreatedTagFromStoryDisplayedOnTagManager()
	{
		log.info("Execution of TestTagManager testNewlyCreatedTagFromStoryDisplayedOnTagManager Started");
		CurrentDate currentDate = new CurrentDate();
		String storyTitle = "Story Title"+currentDate.getCurrentDateForEmail();
		String tagName = "ராம்"+currentDate.getCurrentDateForEmail();
		String metaDescription = "Meta Description for "+tagName+" Tag";
		String tagSlugExpected = tagName.toLowerCase();
		String tagSlugUpdated = "tag"+currentDate.getCurrentDateForEmail();
		WorkspacePage workspacePage = new WorkspacePage(driver);
		TagManagerPage tagManagerPage = new TagManagerPage(driver);
    	StoryTypePage storyTypePage = new StoryTypePage(driver);
    	NewStoryPage newStoryPage = new NewStoryPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickWorkspaceLink();
    	workspacePage.clickNewStoryButton();
        storyTypePage.clickBlankStoryButton();
        newStoryPage.enterTitleFieldData(storyTitle);
        newStoryPage.clickMetadataLink();
        newStoryPage.enterTagFieldData(tagName);
        newStoryPage.clickSaveButton();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickTagManagerLink();
		Assert.assertEquals(driver.getTitle(), "Tag Manager", "Itsman is down or not reachable at this moment");
        tagManagerPage.enterDataInTagSearchField(tagName);
        Assert.assertTrue(Verification.verify(tagManagerPage.getListOfTagNames().get(0).getText(), tagName));
		tagManagerPage.clickTagEditButton(tagName);
		Assert.assertNotEquals(tagManagerPage.validateTagSlug(), tagSlugExpected);
		tagManagerPage.enterTagSlug(tagSlugUpdated);
        tagManagerPage.enterMetaDescription(metaDescription);
        tagManagerPage.clickEditTagButton();
        //driver.navigate().refresh();
        tagManagerPage.enterDataInTagSearchField(tagName);
		Assert.assertTrue(Verification.verify(tagManagerPage.getListOfTagSlug().get(0).getText(), tagSlugUpdated));
        Assert.assertTrue(Verification.verify(tagManagerPage.getListOfTagsMetadata().get(0).getText(), metaDescription));
        
	}

	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 3)
	public void testTagSaveWithoutSlug()
	{
		log.info("Execution of TestTagManager testTagSaveWithoutSlug Started");
		CurrentDate currentDate = new CurrentDate();
		String tagName = "Tag"+currentDate.getCurrentDateForEmail();
		String expectedErrorMessage = "Slug cannot be empty";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		TagManagerPage tagManagerPage = new TagManagerPage(driver);

		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickTagManagerLink();
		Assert.assertEquals(driver.getTitle(), "Tag Manager", "Itsman is down or not reachable at this moment");
		tagManagerPage.enterDataInTagSearchField(tagName);
		Assert.assertTrue(tagManagerPage.isAddNewTagLinkDisplayed());
		tagManagerPage.clickAddNewTagLink();
		tagManagerPage.removeTagSlug();

		tagManagerPage.clickEditTagButton();
		Assert.assertEquals(tagManagerPage.tagSaveError(), expectedErrorMessage);

	}
	
}



