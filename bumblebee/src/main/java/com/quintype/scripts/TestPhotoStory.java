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

public class TestPhotoStory extends OpenBrowser
{	
	@BeforeClass(groups = {"functest", "smoketest", "platform"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	
	// TC ID: Story_TC_07 => Verify story displaying on workspace page under Published tab after submitting.
	@Test(groups = {"functest", "smoketest", "platform"}, priority = 1) // Verifying the Photo Story
	public void testPhotoStoryWithMandatoryElement() 
	{
		log.info("Execution of TestPhotoStory Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);		
		NewStoryPage newStoryPage = new NewStoryPage(driver);
	
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();					// Click on New Story Button
		storyTypePage.clickPhotoStoryButton();					// Click on Photo Story Button
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Photo_Title"));	// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickAddExistingCardButton();
		newStoryPage.searchForExistingStory(dataPropertyFile.getProperty("Search_Story_Title"));
		newStoryPage.selectExistingStory();
		newStoryPage.selectExistingCard();
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");		
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
				
	}
	
	// TC ID:  => Verify photo story with all possible element in single card is displaying on workspace page under Published tab.
	@Test(groups = {"functest", "platform"}, priority = 2)
	public void testPhotoStoryWithAllElementsInSameCardAddingAtButtom() 
	{
		log.info("Execution of TestPhotoStory Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);		
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickPhotoStoryButton();
		newStoryPage.enterTitleFieldData("Photo Story with all elements");
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage("A1.jpg");	
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterJSEmbedFieldData(dataPropertyFile.getProperty("JSEmbed"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.addMap(dataPropertyFile.getProperty("Location_To_Search"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterQuoteFieldData(dataPropertyFile.getProperty("Question"), dataPropertyFile.getProperty("Answer"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterBlurbFieldData(dataPropertyFile.getProperty("Blurb"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterQAFieldData(dataPropertyFile.getProperty("Quote_Field"), dataPropertyFile.getProperty("Quote_Attribution"));
//		newStoryPage.clickDownAddButtonOfCard();
//		newStoryPage.enterBlockquoteFieldData(dataPropertyFile.getProperty("Blockquote_Field"), dataPropertyFile.getProperty("Blockquote_Attribution"));
//		newStoryPage.clickDownAddButtonOfCard();
//		newStoryPage.enterBigFactFieldData(dataPropertyFile.getProperty("BigFact_Field"), dataPropertyFile.getProperty("BigFact_Descrition"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterYoutubeFieldData(dataPropertyFile.getProperty("Youtube_URL"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterSoundCloudFieldData(dataPropertyFile.getProperty("SoundCloud_URL"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterInstaFieldData(dataPropertyFile.getProperty("Instagram_URL"));
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterTwitterFieldData(dataPropertyFile.getProperty("Twitter_URL"));
		newStoryPage.clickMetadataLink();
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");		
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));	
		
	}
				
	// TC ID:  => Verify at least one image element should present in photo story to be published.
	@Test(groups = {"functest", "platform"}, priority = 3)
	public void testPhotoStoryValidationWithImage() 
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);		
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickPhotoStoryButton();
		newStoryPage.enterTitleFieldData("Verify at least one Image Element on Photo Story");
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage("A1.jpg");	
		newStoryPage.clickNewCardAddButton();
		newStoryPage.deleteCardElementFromCard(0);
		newStoryPage.clickAddStoryElementButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));
		newStoryPage.clickMetadataLink();
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		Assert.assertFalse(newStoryPage.isApproveButtonDisplay(), "Approve button is displaying even though story has error");
		newStoryPage.clickContentLink();
		Assert.assertTrue(newStoryPage.isCardHasError(), "Story error icon is not getting displayed");
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		
	}
	
	// TC ID:  => Verify at least one image gallery element should present in photo story to be published.
	@Test(groups = {"functest", "platform"}, priority = 4)
	public void testPhotoStoryValidationWithImageGallery() 
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);		
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickPhotoStoryButton();
		newStoryPage.enterTitleFieldData("Verify at least one Image Gallery Element on Photo Story");
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage("A1.jpg");	
		newStoryPage.clickNewCardAddButton();
		newStoryPage.deleteCardElementFromCard(0);
		newStoryPage.clickAddStoryElementButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));	
		newStoryPage.clickMetadataLink();
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL	
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		Assert.assertFalse(newStoryPage.isApproveButtonDisplay(), "Approve button is displaying even though story has error");
		newStoryPage.clickContentLink();
		Assert.assertTrue(newStoryPage.isCardHasError(), "Story error icon is not getting displayed");
		newStoryPage.clickNewCardAddButton();
		newStoryPage.deleteCardElementFromCard(1);
		newStoryPage.clickAddStoryElementButton();
		newStoryPage.addImageGallery(2, dataPropertyFile.getProperty("Image_Name_For_ImageGallery"));
		newStoryPage.clickMetadataLink();
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Photo Story");		
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));	
		
	}
	
}