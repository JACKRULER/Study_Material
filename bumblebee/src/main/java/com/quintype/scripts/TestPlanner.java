package com.quintype.scripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.pom.PlannerPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.NewStoryPage;

/*public class TestPlanner extends OpenBrowser
{	
	@BeforeClass(groups = {"functest", "smoketest"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	
	@Test(groups = { "functest" }, priority = 1)
	public void verifyPlannerCountWithStoryList() 	
	{		
		log.info("Execution of TestPlanner Started");
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		PlannerPage plannerPage = new PlannerPage(driver);
		//StoryTypePage storyTypePage = new StoryTypePage(driver);

        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
		loginPage.loginToApplication();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickPlannerLink();

//		plannerPage.verifyPlannerSearch();
		
		Assert.assertEquals(1 , plannerPage.checkTabCount("open"), "Verify Count Failed");
		

		// workspacePage.clickProfileButton();
		// Assert.assertEquals("Login" , workspacePage.clickLogoutLink());
	}

	@Test(groups = { "functest", "smoketest" }, priority = 1)
	public void createPhotoStoryFromPlanner() throws InterruptedException,
	{		
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		PlannerPage plannerPage = new PlannerPage(driver,);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);

		loginPage.loginToApplication();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickPlannerLink();
		plannerPage.clickAddNewStory();
		storyTypePage.clickPhotoStoryButton();

		String parent_Window = driver.getWindowHandle();

		for (String child_Window : driver.getWindowHandles())
     	{  
     		driver.switchTo().window(child_Window);

     		driver.findElement(By.cssSelector("div#story-title div[role='textbox']")).sendKeys("Title Field Data");

		newStoryPage.enterTitleFieldData();			// Enter the Title field data
		
		newStoryPage.selectHeroImage();				// Select Hero Image with Image Caption		
					
		newStoryPage.selectImage();					// Select Image & add Image Caption
				
		newStoryPage.clickMetadataLink();			// Change the tab to Metadata
	
		newStoryPage.enterSocialShareFieldData();	// Enter Social Share Data 
		
		newStoryPage.enterTagFieldData();			// Enter the Tag Data
		
		newStoryPage.enterSectionFieldData();		// Enter the Section Data
		
		newStoryPage.enterCustomURLFieldData();		// Enter Custom URL
	
		newStoryPage.clickSaveButton();
		
	//	newStoryPage.checkPreview();
		
		newStoryPage.clickPublishButton();					// Publish the Story
		

		}
     //Switching back to Parent Window  
     	driver.switchTo().window(parent_Window);  


		// workspacePage.clickProfileButton();
		// Assert.assertEquals("Login" , workspacePage.clickLogoutLink());
	}	

	
}*/
