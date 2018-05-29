package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.NewStoryPage;

import org.testng.Assert;
import org.testng.Reporter;

public class TestVideoStory extends OpenBrowser
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
	
	// Test Case ID: Storytype_TC_002
	@Test(groups = {"functest", "platform"}, priority = 1)
	public void testVideoStoryWithMandatory() 
	{
		log.info("Execution of TestVideoStory Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);		
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickVideoStoryButton();		// Click on Photo Story Button
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Video_Title"));			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));			// Select Hero Image with Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterYoutubeFieldData(dataPropertyFile.getProperty("Youtube_URL"));		// Enter youtube link
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");		
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Video Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
	}		
			
}