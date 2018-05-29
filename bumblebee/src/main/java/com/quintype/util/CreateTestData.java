package com.quintype.util;

import org.testng.Assert;

import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;

public class CreateTestData extends OpenBrowser {

	public void createPhotoStory(String tagName)
	{
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);		
		NewStoryPage newStoryPage = new NewStoryPage(driver);
	
		workspacePage.clickNewStoryButton();					// Click on New Story Button
		storyTypePage.clickPhotoStoryButton();					// Click on Photo Story Button
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Photo_Title"));	// Enter the Title field data
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(tagName);			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name1"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");		
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
	}
}
