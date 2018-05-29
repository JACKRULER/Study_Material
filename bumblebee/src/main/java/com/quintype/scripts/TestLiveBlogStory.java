package com.quintype.scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.CheckAlert;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;

public class TestLiveBlogStory extends OpenBrowser 
{
	@BeforeClass(groups = {"functest", "platform", "platformReg4"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	
	@Test(groups = {"functest", "platform", "platformReg4"}, priority = 1)
	public void testLiveBlogStoryWithMandatoryElement()
	{
		log.info("Execution of TestLiveBlogStory Started");
		int liveBlogCardCount = 3;
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickLiveBlogStoryButton();
        newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Live_Blog_Title"));
        newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
        newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));

        for (int i = 1; i <= liveBlogCardCount; i++) {
            newStoryPage.clickNewCardAddButton();
            newStoryPage.selectKeyEventCheckboxLiveBlog();
            newStoryPage.enterTitleFieldDataLiveBlog(dataPropertyFile.getProperty("Title_Live_Blog")+" "+i);			
		}
        newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");		
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		
	}

	@Test(groups = {"functest", "platform", "platformReg4"}, priority = 2, enabled = false)
	public void testLiveBlogStoryCloseFunctionalityWithAcceptPopUp()
	{
		log.info("Execution of TestLiveBlogStory Started");
		int liveBlogCardCount = 3;
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickLiveBlogStoryButton();
        newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Live_Blog_Title"));
        newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
        newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));

        for (int i = 1; i <= liveBlogCardCount; i++) {
            newStoryPage.clickNewCardAddButton();
            newStoryPage.selectKeyEventCheckboxLiveBlog();
            newStoryPage.enterTitleFieldDataLiveBlog(dataPropertyFile.getProperty("Title_Live_Blog")+" "+i);			
		}
        newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");		
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		
		workspacePage.clickFirstStory();
		newStoryPage.clickCloseButton();
		Assert.assertFalse(CheckAlert.verifyAcceptAlert(driver));
		
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickPublishedLink();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		workspacePage.clickFirstStory();
		Assert.assertFalse(newStoryPage.isCloseButtonDisplayed());
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();

	}
	
	
	
	@Test(groups = {"functest", "platform", "platformReg4"}, priority = 3, enabled = false)
	public void testLiveBlogStoryCloseFunctionalityWithDismissPopUp()
	{
		log.info("Execution of TestLiveBlogStory Started");
		int liveBlogCardCount = 3;
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickNewStoryButton();
        storyTypePage.clickLiveBlogStoryButton();
        newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Live_Blog_Title"));
        newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
        newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));

        for (int i = 1; i <= liveBlogCardCount; i++) {
            newStoryPage.clickNewCardAddButton();
            newStoryPage.selectKeyEventCheckboxLiveBlog();
            newStoryPage.enterTitleFieldDataLiveBlog(dataPropertyFile.getProperty("Title_Live_Blog")+" "+i);			
		}
        newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");		
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		
		workspacePage.clickFirstStory();
		newStoryPage.clickCloseButton();
		Assert.assertFalse(CheckAlert.verifyDismissAlert(driver));
		
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickPublishedLink();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		workspacePage.clickFirstStory();
		Assert.assertTrue(newStoryPage.isCloseButtonDisplayed());
		
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();

	}
	

	//
	@Test(groups = {"functest", "platform", "platformReg4"}, priority = 4, enabled = false)
	public void verifyOrderOfLiveBlogCards()
	{
		int liveBlogCardCount = 3;
		String titleOfTheLastAddedCard = "This is the last added card";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickLiveBlogStoryButton();
		newStoryPage.enterTitleFieldData("LiveBlog: Verify Card Order");
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));

		for (int i = 1; i <= liveBlogCardCount; i++) {
			newStoryPage.clickNewCardAddButton();
			newStoryPage.selectKeyEventCheckboxLiveBlog();
			newStoryPage.enterTitleFieldDataLiveBlog(dataPropertyFile.getProperty("Title_Live_Blog")+" "+i);
		}
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectKeyEventCheckboxLiveBlog();
		newStoryPage.enterTitleFieldDataLiveBlog(titleOfTheLastAddedCard);

		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL

		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		workspacePage.clickFirstStory();
		Assert.assertTrue(Verification.verify(newStoryPage.getTitleFieldOfLiveBlog().get(0).getAttribute("value"), titleOfTheLastAddedCard));
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();

	}


}
