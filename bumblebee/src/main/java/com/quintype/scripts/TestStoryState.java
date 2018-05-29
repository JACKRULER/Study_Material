package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quintype.pom.WorkspacePage;
import com.quintype.util.CheckAlert;
import com.quintype.util.CurrentDate;
import com.quintype.util.OpenBrowser;
import com.quintype.util.ScreenShot;
import com.quintype.util.Verification;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import ru.yandex.qatools.ashot.Screenshot;

public class TestStoryState extends OpenBrowser
{
	@BeforeClass(groups = {"functest", "platform" ,"smoketest"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();
		}
	}	
	
	// TC ID: Story_TC_01 => Verify story displaying on workspace page under Open tab after saving.
	@Test(groups = {"functest", "platform","smoketest"}, priority = 1)

	public void testSaveFunctionalityOfStories()
	{
		log.info("Execution of TestStoryState Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickPhotoStoryButton();
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Photo_Title")+": Saved");
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickMetadataLink();													// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));// Enter Social Share Data
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Open", newStoryPage.getEnteredStoryTitle()));

	}
	// TC ID: Story_TC_02 & Story_TC_03 => 02 - Title field should be filled with default auto generated title. 03 - Should display the saved story on workspace page under Open tab with defalt auto generated title.
	@Test(groups = {"functest", "platform","smoketest"}, priority = 2)
	public void testAutoGenerateStoryTitle()
	{
		String expectedTitle = "Untitled";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickPhotoStoryButton();

		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));
		newStoryPage.clickSaveButton();
		Assert.assertTrue(newStoryPage.getTitleFieldData().contains(expectedTitle));
		expectedTitle = newStoryPage.getTitleFieldData();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		Assert.assertTrue(workspacePage.getListOfStoryTitle().get(0).getText().contains(expectedTitle));

	}
	// TC ID: Story_TC_04 => Verify story displaying on workspace page under Need Approval tab after submitting.
	@Test(groups = {"functest", "platform","smoketest"}, priority = 3)
	public void testSubmitFunctionalityOfStories()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();					// Click on New Story Button
		storyTypePage.clickPhotoStoryButton();					// Click on Photo Story Button
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Photo_Title")+": Submitted");	// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickNeedApprovalTab();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Needs Approval", newStoryPage.getEnteredStoryTitle()));

	}
	// TC: Story_TC_06 => Verify story displaying on workspace page under Approved tab after approving.
	@Test(groups = {"functest", "platform","smoketest"}, priority = 4)
	public void testApproveFunctionalityOfStories()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();					// Click on New Story Button
		storyTypePage.clickPhotoStoryButton();					// Click on Photo Story Button
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Photo_Title")+": Approved");	// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickApprovedTab();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Approved", newStoryPage.getEnteredStoryTitle()));

	}

	// TC ID: Story_TC_08 => Verify story displaying on workspace page under Rejected tab after submitting. 
	@Test(groups = { "functest", "platform","smoketest" }, priority = 5)
	public void testRejectFunctionalityOfStories()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickPhotoStoryButton();		// Click on Photo Story Button
		newStoryPage.enterTitleFieldData("Rejected Story");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));				// Select Hero Image with Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));					// Select Image
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		Assert.assertTrue(newStoryPage.clickRejectButton(), "One of the mandatory field is missing before Rejecting the story");
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Rejected", newStoryPage.getEnteredStoryTitle()));

	}

	// TC ID: Story_TC_09 & Story_TC_010 => Verify story displaying on workspace page under Scheduled tab after submitting. And Verify scheduled story moving to Published tab after scheduled time.
	@Test(groups = { "functest", "platform","smoketest" }, priority = 6)
	public void testScheduledStoryFunctionality()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Scheduled Story");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));				// Select Hero Image with Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));		// Enter the Summary field data
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));		// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));		// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		Assert.assertTrue(newStoryPage.clickSubmitButton(), "One of the mandatory field is not filled before Submitting the story");
		newStoryPage.clickApproveButton();			// Click Approve button
		newStoryPage.clickPublishButton();			// Click Publish button
		newStoryPage.clickPublishDropDown();
		newStoryPage.scheduleTime();
		newStoryPage.clickSaveButtonBlue();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Scheduled", newStoryPage.getEnteredStoryTitle()));

//		try {
//			Thread.sleep(90*1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		workspacePage.clickWorkspaceMenuButton();
//		workspacePage.clickWorkspaceLink();
//		workspacePage.clickPublishedLink();
//		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()), "Scheduled story is not moving to Published tab");

		
	}

	// TC ID: Story_TC_011 => Verify story displaying on workspace page under Open tab after retract.
	@Test(groups = {"functest", "platform","smoketest"}, priority = 7)
	public void testRetrackStoryFunctionalityForAcceptPopUp()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Retracted Story");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data
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
		workspacePage.clickFirstStory();
		newStoryPage.clickRetractButton();
		Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertFalse(CheckAlert.verifyAcceptAlert(driver));

		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Open", newStoryPage.getEnteredStoryTitle()));

	}

	// TC ID: Story_TC_011 => Verify story displaying on workspace page under Open tab after retract.
	@Test(groups = {"functest", "platform","smoketest"}, priority = 8)
	public void testRetrackStoryFunctionalityForDismissPopUp()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Retracted Story");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data
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
		workspacePage.clickFirstStory();
		newStoryPage.clickRetractButton();
		Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertFalse(CheckAlert.verifyDismissAlert(driver));

		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickPublishedLink();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Published", newStoryPage.getEnteredStoryTitle()));

	}
	
	@Test(groups = {"functest", "platform","smoketest" }, priority = 9)
	public void verifyAlertWhileSubmittingStory() 

	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Verify Submit Alert");			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));				// Select Hero Image with Image Caption
		newStoryPage.clickSaveButton();				// Click Save button
		newStoryPage.clickSubmitButton();
		Assert.assertTrue(newStoryPage.isHeaderErrorDisplayed());
		Assert.assertTrue(newStoryPage.isMetadataTabErrorDisplayed());
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();

	}

}
