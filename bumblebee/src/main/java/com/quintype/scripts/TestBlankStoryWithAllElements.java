package com.quintype.scripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;

public class TestBlankStoryWithAllElements extends OpenBrowser{
	
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
	
	@Test(groups = {"functest", "smoketest", "platform"}, priority = 1)
	public void testBlankStoryWithAllElements() 
	{
    	log.info("Execution of TestBlankStory Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();												// Click on New Story Button
		storyTypePage.clickBlankStoryButton();												// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Text story with All Elements");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data					
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.addImageGallery(3, dataPropertyFile.getProperty("Image_Name_For_ImageGallery"));
//		
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterBlurbFieldData(dataPropertyFile.getProperty("Blurb"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterBigFactFieldData(dataPropertyFile.getProperty("BigFact_Field"), dataPropertyFile.getProperty("BigFact_Descrition"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterJSEmbedFieldData(dataPropertyFile.getProperty("JSEmbed"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterQuoteFieldData(dataPropertyFile.getProperty("Quote_Field"), dataPropertyFile.getProperty("Quote_Attribution"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterJWPlayerFieldData(dataPropertyFile.getProperty("JWPlayer"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterTwitterFieldData(dataPropertyFile.getProperty("Twitter_URL"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterSoundCloudFieldData(dataPropertyFile.getProperty("SoundCloud_URL"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.addMap(dataPropertyFile.getProperty("Location_To_Search"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterYoutubeFieldData(dataPropertyFile.getProperty("Youtube_URL"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterBlockquoteFieldData(dataPropertyFile.getProperty("Blockquote_Field"), dataPropertyFile.getProperty("Blockquote_Attribution"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterQAFieldData(dataPropertyFile.getProperty("Question"), dataPropertyFile.getProperty("Answer"));
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterInstaFieldData(dataPropertyFile.getProperty("Instagram_URL"));
		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.uploadAttachment(dataPropertyFile.getProperty("Attachment_Name"));
		newStoryPage.clickNewCardAddButton();
		newStoryPage.uploadCSV(dataPropertyFile.getProperty("CSV_File"));
		
		
		
		
		
		
		newStoryPage.clickMetadataLink();													// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();														// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();													// Click Approve button
		newStoryPage.clickPublishButton();													// Click Publish button		
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Blank Story");
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		
	}

}
