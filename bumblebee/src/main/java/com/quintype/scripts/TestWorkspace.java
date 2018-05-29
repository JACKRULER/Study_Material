package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.CurrentDate;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;
import com.quintype.util.Workspace;
import com.quintype.pom.WorkspaceFilters;
import java.util.List;

import com.quintype.api.BreakingNewsAPI;
import com.quintype.api.PublisherAPI;
import com.quintype.api.StoryAPI;
import com.quintype.api.TestData;
import org.testng.Reporter;

public class TestWorkspace extends OpenBrowser
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

	//Verifying theWorkspace search functionality
	@Test(groups = {"functest", "platform"}, priority = 1)
	public void testWorkspaceSearch()
	{
		log.info("Execution of TestWorkspace Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		StoryAPI storyAPI = new StoryAPI();
		String firstStoryTitle = "Test Workspace Search "+CurrentDate.getCurrentDateAndTime();

		List<String> firstStoryVersion = storyAPI.createPhotoStoryInOpenState(firstStoryTitle, 0);
		storyAPI.changePhotoStoryStatus("story-submit", firstStoryVersion);
		storyAPI.changePhotoStoryStatus("story-approve", firstStoryVersion);
		storyAPI.changePhotoStoryStatus("story-publish", firstStoryVersion);

		String secondStoryTitle = "Test Workspace Search "+CurrentDate.getCurrentDateAndTime();

		List<String> secondStoryVersion = storyAPI.createPhotoStoryInOpenState(secondStoryTitle, 1);
		storyAPI.changePhotoStoryStatus("story-submit", secondStoryVersion);
		storyAPI.changePhotoStoryStatus("story-approve", secondStoryVersion);
		storyAPI.changePhotoStoryStatus("story-publish", secondStoryVersion);

		workspacePage.clickPublishedLink();
		workspacePage.enterDataInWorkspaceSearchField(firstStoryTitle);
		Assert.assertTrue(workspacePage.checkStoriesPresentInTab(), "Stories not found in Published tab");
		Reporter.log("Expected searched story title: "+firstStoryTitle);
		Reporter.log("Actual searched stoty title: "+workspacePage.getListOfStoryTitle(firstStoryTitle).get(0).getText());
		Assert.assertTrue(Verification.verify(firstStoryTitle, workspacePage.getListOfStoryTitle(firstStoryTitle).get(0).getText()), "Workspace Search Functionality is Broken, Searched story not found on 1st position");

	}
	//Verifying whether the load more button is present or not in both case

	@Test(groups = {"functest", "smoketest", "platform"}, priority = 2)
    public void testLoadMore()
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);  

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickOpenTab();
        Assert.assertTrue(workspace.verifyLoadMoreButton(), "Workspace Loadmore Functionality is Broken");
        workspacePage.clickPublishedLink();
        Assert.assertTrue(workspace.verifyLoadMoreButton(), "Workspace Loadmore Functionality is Broken");
        
    }       

    // Cross verifying the tab count with number of stories listed in same tab
    @Test(groups = {"functest", "smoketest", "platform"}, priority = 3)   
    public void testTabCountWithNoOfStories()
    {
        String[] storyState = {"open", "approval", "approved", "published", "rejected", "scheduled"};
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);        
        
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
       //The below assertion needs to be revisited
		// Assert.assertTrue(workspace.clickTab(storyState[3], "checkStoriesCountWithStories"), "Workspace tab count is not matching with the no. of Stories");
        
    }
    
    // Verifying the Workspace Menu Links
    @Test(groups = {"functest", "smoketest", "platform"}, priority = 4)   
    public void testWorkspaceMenu()
    {
    	WorkspacePage workspacePage = new WorkspacePage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickWorkspaceMenuButton();
        Assert.assertTrue(Verification.compareTwoListOfString(workspacePage.getListOfWorkspaceMenuLinks(), getMenuList()));
        workspacePage.clickWorkspaceMenuButton();
        
    }

    public static String[] getMenuList()
    {
    	if(System.getProperty("domain").equalsIgnoreCase("platform"))
		{
    		String expectedMenuLinks[] = {"Workspace", "Planner", "Pages", "Sorters", "Analytics", "Tag Manager", "Comment Manager", "Admin", "Entities", "API Manager", "Media Library", "My Profile", "Logout"};
    		return expectedMenuLinks;
		}
		else
		{
			String expectedMenuLinks[] = {"Workspace", "Planner", "Pages", "Sorters", "Analytics", "Tag Manager", "Admin", "API Manager", "Media Library", "My Profile", "Logout"};
			return expectedMenuLinks;
		}
    }
    			
    // TC: 
    @Test(groups = {"functest", "platform"}, priority = 5)
    public void testDeleteStoryFunctionality()
    {
    	log.info("Execution of TestWorkspace Started");
    	WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);		
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();					
		storyTypePage.clickPhotoStoryButton();					
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Photo_Title"));	
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.selectImage(dataPropertyFile.getProperty("Image_Name"));				// Select Image & add Image Caption
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();				// Click Save button
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		Assert.assertTrue(Verification.verifyStoryOnWorkspace("Open", newStoryPage.getEnteredStoryTitle()));
		workspacePage.deleteStory(newStoryPage.getEnteredStoryTitle());
		Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(workspacePage.getUpdatedListOfStoryTitle(newStoryPage.getEnteredStoryTitle()), newStoryPage.getEnteredStoryTitle()));
		
    }
    
    // TC: 
    @Test(groups = {"functest", "platform"}, priority = 6)
    public void testQuickLinkVisible()
    {
 		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();		// Click on New Story Button
		storyTypePage.clickBlankStoryButton();		// Click on Blank Story Button
		newStoryPage.enterTitleFieldData("Check Quick Link Visibility");			// Enter the Title field data
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));		// Select Hero Image with Image Caption		
		newStoryPage.clickNewCardAddButton();
		newStoryPage.enterSummaryFieldData(dataPropertyFile.getProperty("Summary_Data"));	// Enter the Summary field data
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
		Assert.assertTrue(workspacePage.verifyQuickLinkButton(newStoryPage.getEnteredStoryTitle()));
      
    }
    			

	//TC:			Check workspace stories count after deleting few Stories.
	@Test(groups = {"functest", "platform"}, priority = 7)
	public void testStoriesCountOnWorkspaceAfterDeletingStories()
	{
		int storiesCountOnWorkspace = 20;
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);

		//		Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
		//		loginPage.loginToApplication();
		workspacePage.clickPublishedLink();
		if(workspacePage.getListOfStoryTitle().size()==storiesCountOnWorkspace)
		{
			workspacePage.deleteStory(0);
			workspacePage.deleteStory(1);
			driver.navigate().refresh();
			Assert.assertEquals(workspacePage.getListOfStoryTitle().size(), storiesCountOnWorkspace, "After deleting Stories from Published tab 20 stories are not getting loaded");
		}

		workspacePage.clickProfileButton();			// Click on Logout button
		Assert.assertEquals(workspacePage.clickLogoutLink(), "Login", "User is not able to logout from Itsman");
	}

	// TC:			Check workspace stories count after deleting few Breaking News.
    @Test(groups = {"functest", "platform"}, priority = 8)
	public void testStoriesCountOnWorkspaceAfterDeletingBreakingNews()
	{
		int storiesCountOnWorkspace = 20;
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		// Creating Breaking News to Delete.
		BreakingNewsAPI breakingNewsAPI = new BreakingNewsAPI();
		for(int i = 1; i <= 3; i++)
			{breakingNewsAPI.createBreakingNews(i);
			Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
			workspacePage.clickPublishedLink();
			if(workspacePage.getListOfStoryTitle().size()==storiesCountOnWorkspace)
			{
				workspacePage.deleteStory(0);
				workspacePage.deleteStory(1);
				driver.navigate().refresh();
				Assert.assertEquals(workspacePage.getListOfStoryTitle().size(), storiesCountOnWorkspace, "After deleting Breaking News from Published tab 20 stories are not getting loaded");
			}
		}
	}
}
