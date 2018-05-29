/*
 * @author Nikesh
 * This test class /script will verify that story blocking spinner is not getting displayed 
 * on clicking save button if non-blocking auto save feature is toggled on.
 */
package com.quintype.scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.quintype.pom.ConfigurePage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.ConfigFeatureAPI;
import com.quintype.util.CurrentDate;
import com.quintype.util.OpenBrowser;

public class TestNBASFeature extends OpenBrowser{
	
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
		
	@Test(groups = {"functest", "smoketest", "platform"} ,priority = 1)
	public void verifyStorySaveSpinnerDisplay(){
		log.info("Non-blocking story save is started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		
		
		
		workspacePage.clickNewStoryButton();												// Click on New Story Button
		storyTypePage.clickBlankStoryButton();												// Click on Blank Story Button
		String storyTitle =  "Text story for NBAS" + CurrentDate.getCurrentDateAndTime();
		newStoryPage.enterTitleFieldData(storyTitle);			// Enter the Title field data
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterTextFieldData(dataPropertyFile.getProperty("Text_Data"));			// Enter the Text field data					
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickNewCardAddButton();
		newStoryPage.addImageGallery(3, dataPropertyFile.getProperty("Image_Name_For_ImageGallery"));
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterBlurbFieldData(dataPropertyFile.getProperty("Blurb"));
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterBigFactFieldData(dataPropertyFile.getProperty("BigFact_Field"), dataPropertyFile.getProperty("BigFact_Descrition"));
		newStoryPage.clickMetadataLink();													// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		
		String toggleStatus = ConfigFeatureAPI.callAPI("Non blocking story save");
		
		if( toggleStatus.equalsIgnoreCase("true") ){
			newStoryPage.clickSaveButtonforNBAS();
			boolean spinnerDisplayed = newStoryPage.isStorySaveSpinnerDisplayed();
			Assert.assertEquals(spinnerDisplayed, false, "Toggle Is ON but Spinner Displayed" );
		}
		else{
			newStoryPage.clickSaveButtonforNBAS();
			boolean spinnerDisplayed = newStoryPage.isStorySaveSpinnerDisplayed();
			Assert.assertEquals(spinnerDisplayed, true, "Toggle Is OFF but Spinner is not Displayed");
		}
	}


}
