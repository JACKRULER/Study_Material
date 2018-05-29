package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.ScrollPage;
import com.quintype.util.Verification;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.NewStoryPage;
import org.testng.Assert;
import com.quintype.util.SetContentsToClipBoard;


public class TestBlankStory extends OpenBrowser
{
	@BeforeClass(groups = {"functest", "smoketest", "platform", "PlatformReg2"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	// Add 5 new test cases with 5 story elements each where text will always be present, validate elements by editing the story and story will be in different states
	// Test Case Id: Storytype_TC_005 - Verifying the creation of Blank Story with summary, text & image element
	@Test(groups = {"functest", "smoketest", "platform", "PlatformReg2"}, priority = 1)
	public void testBlankStoryWithSummaryTextImage() 
	{
    	log.info("Execution of TestBlankStory Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();												// Click on New Story Button
		storyTypePage.clickBlankStoryButton();												// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Text story with Summary, Text & Image");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data					
//		newStoryPage.clickNewCardAddButton();
//		newStoryPage.enterBrightCoveElementData(dataPropertyFile.getProperty("BrightCove_Data"));
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.addImageGallery(3, dataPropertyFile.getProperty("Image_Name_For_ImageGallery"));
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

 	// Test Case Id: Storytype_TC_005 - Verifying the creation of Blank Story with JS Embed, Map & Social Share element
 	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 2)
 	public void testBlankStoryWithJSEmbedMapSocialShares() 
 	{
 		WorkspacePage workspacePage = new WorkspacePage(driver);
 		StoryTypePage storyTypePage = new StoryTypePage(driver);
 		NewStoryPage newStoryPage = new NewStoryPage(driver);

 		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
 		workspacePage.clickNewStoryButton();		// Click on New Story Button
 		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
 		newStoryPage.enterTitleFieldData("Text story with JS, Map & Social Shares");	// Enter the Title field data
 		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
 		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));				// Select Hero Image with Image Caption		
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.addMap(dataPropertyFile.getProperty("Location_To_Search"));						// Add Map location
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterJSEmbedFieldData(dataPropertyFile.getProperty("JSEmbed"));		// Enter JS Embed field data
		
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterYoutubeFieldData(dataPropertyFile.getProperty("Youtube_URL"));
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterSoundCloudFieldData(dataPropertyFile.getProperty("SoundCloud_URL"));
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterInstaFieldData(dataPropertyFile.getProperty("Instagram_URL"));
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterTwitterFieldData(dataPropertyFile.getProperty("Twitter_URL"));
		
 		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
 		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
 		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
 		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
 		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL
 		newStoryPage.clickSaveButton();				// Click Save button
 		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
 		newStoryPage.clickApproveButton();			// Click Approve button
 		newStoryPage.clickPublishButton();			// Click Publish button		
 		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Blank Story");
 		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
		
 	}

 	// Test Case Id: Storytype_TC_005 - Verifying the creation of Blank Story with Quote, Blurb & Q & A
 	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 3)
 	public void testBlankStoryWithQuoteBlurbQAndA() 
 	{
 		WorkspacePage workspacePage = new WorkspacePage(driver);
 		StoryTypePage storyTypePage = new StoryTypePage(driver);
 		NewStoryPage newStoryPage = new NewStoryPage(driver);

 		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
 		workspacePage.clickNewStoryButton();		// Click on New Story Button
 		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
 		newStoryPage.enterTitleFieldData("Text story with Quote, Blurb & Q&A");			// Enter the Title field data
 		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
 		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));				// Select Hero Image with Image Caption		
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterQuoteFieldData(dataPropertyFile.getProperty("Quote_Field"), dataPropertyFile.getProperty("Quote_Attribution"));			// Enter Quote field data
 //		newStoryPage.clickNewCardAddButton();
 //		newStoryPage.enterBlockquoteFieldData();	// Enter BlockQuote field data
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterBlurbFieldData(dataPropertyFile.getProperty("Blurb"));			// Enter Blurb field data
 //		newStoryPage.clickNewCardAddButton();
 // 	newStoryPage.enterBigFactFieldData();		// Enter BigFact field data
 		newStoryPage.clickNewCardAddButton();
 		newStoryPage.enterQAFieldData(dataPropertyFile.getProperty("Question"), dataPropertyFile.getProperty("Answer"));			// Enter Q & A field data	
 		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
 		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
 		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
 		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
 		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL
 		newStoryPage.clickSaveButton();				// Click Save button
 		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
 		newStoryPage.clickApproveButton();			// Click Approve button
 		newStoryPage.clickPublishButton();			// Click Publish button
 		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Blank Story");
 		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));

 	}		

	// TC ID: 
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 4)
	public void testBlankStoryWithAllElementsInSameCardAddingAtTop()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickBlankStoryButton();
		newStoryPage.enterTitleFieldData("Text story with All Elements");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button		
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Blank Story");
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));
	}		
	
	// Test Case Id:  - Verify the story element swap on cards. 
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 5)
	public void testStoryElementsSwappingOnSameCard() 
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Verify story element swapping on card level");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
		newStoryPage.clickDownAddButtonOfCard();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data					

		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		newStoryPage.clickContentLink();
		
		ScrollPage.scrollDown(driver);
		Assert.assertTrue(Verification.verify(newStoryPage.getCardFirstElementData(), dataPropertyFile.getProperty("Summary_Data")));
		ScrollPage.scrollDown(driver);
		newStoryPage.clickDownButtonOfFirstCardElement();
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(Verification.verify(newStoryPage.getCardFirstElementData(), dataPropertyFile.getProperty("Text_Data")));

		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button		
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Blank Story");
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));	
	}
	
	// Test Case Id:  - Verify the story card swap on story canvas. 
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 6)
	public void testStoryCardSwappingOnSameCard() 
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();												// Click on New Story Button
		storyTypePage.clickBlankStoryButton();												// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Verify cards swapping");							// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data					

		newStoryPage.clickMetadataLink();													// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();														// Click Save button
		newStoryPage.clickContentLink();
		
		Assert.assertTrue(Verification.verify(newStoryPage.getCardFirstElementData(), dataPropertyFile.getProperty("Summary_Data")));
		newStoryPage.clickDownButtonOfFirstCard();
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(Verification.verify(newStoryPage.getCardFirstElementData(), dataPropertyFile.getProperty("Text_Data")));

		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button		
		Assert.assertTrue(newStoryPage.clickPublishLink(), "One of the card element might left empty while publishing a Blank Story");
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));	
	}	

	@Test(groups = {"functest", "platform", "platformReg2"}, priority = 7)
	public void verifyCopyPaste() throws InterruptedException
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();						
		storyTypePage.clickBlankStoryButton();												
		newStoryPage.enterTitleFieldData("Text story with Summary, Text & Image");
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));
		SetContentsToClipBoard.setText("Hi, there");		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.pastePlainTextToTextField();
		Assert.assertTrue(Verification.verify(newStoryPage.getTextElementData(), "Hi, there"));
	}
	
}
