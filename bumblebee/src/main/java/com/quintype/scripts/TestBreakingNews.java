package com.quintype.scripts;

import com.quintype.pom.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import org.testng.Assert;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;
import com.quintype.api.StoryAPI;

public class TestBreakingNews extends OpenBrowser
{
	@BeforeClass(groups = {"functest", "platform"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	
	// TC ID: Breaking News- Storytype_TC_013 => Verify whether the user is create a Breaking News without Linked Story
    //@Test(groups = {"functest", "platform"}, priority = 1)
    public void createNewBreakingNewsStoryWithoutLinkedStory() 
    {
    	log.info("Execution of TestBreakingNews Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
        BreakingNewsStoryPage breakingNewsStoryPage = new BreakingNewsStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickBreakingNewsButton();
        breakingNewsStoryPage.enterBreakingNewsTitle(dataPropertyFile.getProperty("BreakingNews_Title"));
        breakingNewsStoryPage.clickPublishButton();
        workspacePage.clickPublishedLink();
        Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", breakingNewsStoryPage.getBreakingNewsTitle()));
        
    }

    // TC ID: Breaking News- Storytype_TC_014 => Verify whether the user is create a Breaking News with Linked Story
    //@Test(groups = {"functest", "platform"}, priority = 2)
    public void createNewBreakingNewsStoryWithLinkedStory()
    {
        String linkedStoryTitle = "Test Story";
        WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
        BreakingNewsStoryPage breakingNewsStoryPage = new BreakingNewsStoryPage(driver);

        StoryAPI storyAPI = new StoryAPI();
        List<String> storyVersion = storyAPI.createPhotoStoryInOpenState(linkedStoryTitle, 0);
        storyAPI.changePhotoStoryStatus("story-submit", storyVersion);
        storyAPI.changePhotoStoryStatus("story-approve", storyVersion);
        storyAPI.changePhotoStoryStatus("story-publish", storyVersion);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickBreakingNewsButton();
        breakingNewsStoryPage.enterBreakingNewsTitle(dataPropertyFile.getProperty("BreakingNews_Title"));
        breakingNewsStoryPage.selectLinkedStory(linkedStoryTitle);
        breakingNewsStoryPage.clickPublishButton();
        workspacePage.clickPublishedLink();
        Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", breakingNewsStoryPage.getBreakingNewsTitle()));
        workspacePage.clickFirstStory();
        Assert.assertTrue(Verification.verify(breakingNewsStoryPage.getLinkedStoryTitle(), linkedStoryTitle));

    }
    //TC ID: Breaking News- Storytype_TC_014 => Verify whether the user is able to discard Breaking News which is not linked to a story.

    //@Test(groups = {"functest", "platform"}, priority = 3)
    public void discardNewBreakingNewsStoryWithoutLinkedStory() 
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
        BreakingNewsStoryPage breakingNewsStoryPage = new BreakingNewsStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickBreakingNewsButton();
        breakingNewsStoryPage.enterBreakingNewsTitle(dataPropertyFile.getProperty("BreakingNews_Title"));
        breakingNewsStoryPage.clickDiscardButton();
        workspacePage.clickPublishedLink();
        if(workspacePage.checkStoriesPresentInTab())
        	Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(workspacePage.getListOfStoryTitle(), breakingNewsStoryPage.getBreakingNewsTitle()));
        
    }

    // TC ID: Breaking News- Storytype_TC_014 => Verify whether the user is able to discard Breaking News which is linked to a story.
    //@Test(groups = {"functest", "platform"}, priority = 4)
    public void discardNewBreakingNewsStoryWithLinkedStory()
    {
        String linkedStoryTitle = "Test Story";
        WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
        BreakingNewsStoryPage breakingNewsStoryPage = new BreakingNewsStoryPage(driver);

        StoryAPI storyAPI = new StoryAPI();
        List<String> storyVersion = storyAPI.createPhotoStoryInOpenState(linkedStoryTitle, 1);
        storyAPI.changePhotoStoryStatus("story-submit", storyVersion);
        storyAPI.changePhotoStoryStatus("story-approve", storyVersion);
        storyAPI.changePhotoStoryStatus("story-publish", storyVersion);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickBreakingNewsButton();
        breakingNewsStoryPage.enterBreakingNewsTitle(dataPropertyFile.getProperty("BreakingNews_Title"));
        breakingNewsStoryPage.selectLinkedStory(linkedStoryTitle);
        breakingNewsStoryPage.clickDiscardButton();
        workspacePage.clickPublishedLink();
        if(workspacePage.checkStoriesPresentInTab())
        Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(workspacePage.getListOfStoryTitle(), breakingNewsStoryPage.getBreakingNewsTitle()));

    }
    @Test(groups = {"functest", "platform"}, priority = 5)
    public void testAddingBreakingNewsDefaults()
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        ConfigurePage configurePage = new ConfigurePage(driver);
        NewStoryPage newStoryPage = new NewStoryPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickConfigureLink();
        configurePage.clickBreakingNewsDefaultsTab();
        configurePage.enterContent(dataPropertyFile.getProperty("content"));
        configurePage.selectImage(dataPropertyFile.getProperty("Image_Name"));
        configurePage.clickSaveButton();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickWorkspaceLink();

    }

    @Test(groups = {"functest", "platform"}, priority = 6,dependsOnMethods = "testAddingBreakingNewsDefaults")
    public void newBreakingNewsWithAssociatedStory()
    {
        NewStoryPage newStoryPage = new NewStoryPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
        BreakingNewsStoryPage breakingNewsStoryPage = new BreakingNewsStoryPage(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickBreakingNewsButton();
        breakingNewsStoryPage.enterBreakingNewsTitle(dataPropertyFile.getProperty("BreakingNews_Title"));
        breakingNewsStoryPage.clickAssociatedStoryCheckBox();
        newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
        breakingNewsStoryPage.clickAssociatedFieldsCheckBox();
        Assert.assertEquals(breakingNewsStoryPage.getContentData(),dataPropertyFile.getProperty("content"));
        breakingNewsStoryPage.clickPublishButton();
    }
    @Test(groups = {"functest", "platform"}, priority = 7,dependsOnMethods = "newBreakingNewsWithAssociatedStory")
    public void editBreakingNewsWithAssociatedStory()
    {
        NewStoryPage newStoryPage = new NewStoryPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
        BreakingNewsStoryPage breakingNewsStoryPage = new BreakingNewsStoryPage(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickBreakingNewsButton();
        breakingNewsStoryPage.enterBreakingNewsTitle(dataPropertyFile.getProperty("BreakingNews_Title"));
        breakingNewsStoryPage.clickAssociatedStoryCheckBox();
        newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
        breakingNewsStoryPage.clickAssociatedFieldsCheckBox();
        breakingNewsStoryPage.enterContent(dataPropertyFile.getProperty("content1"));
        breakingNewsStoryPage.clickPublishButton();
    }
    @Test(groups = {"functest", "platform"}, priority = 8,dependsOnMethods = "editBreakingNewsWithAssociatedStory")
    public void deleteBreakingNewsWithAssociatedStory()
    {
        NewStoryPage newStoryPage = new NewStoryPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
        BreakingNewsStoryPage breakingNewsStoryPage = new BreakingNewsStoryPage(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.enterDataInWorkspaceSearchField(dataPropertyFile.getProperty("BreakingNews_Title"));
        workspacePage.clickPublishedLink();
        workspacePage.deleteBreakingStory(dataPropertyFile.getProperty("BreakingNews_Title"));
    }
}