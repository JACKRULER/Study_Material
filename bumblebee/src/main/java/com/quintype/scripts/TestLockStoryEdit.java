package com.quintype.scripts;

import java.awt.Toolkit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;
import com.quintype.util.WaitForElement;

import org.openqa.selenium.By;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class TestLockStoryEdit extends OpenBrowser 
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
	
	// Verify story is lock for 2nd user if 1st user is editing it.
	@Test(groups = {"functest", "smoketest", "platform"}, priority = 1)
	public void testLockStoryEditFunctionality() 
	{		  
    	log.info("Execution of Lock Story Edit Started");
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
		newStoryPage.clickMetadataLink();													// Change the tab to Metadata
		newStoryPage.enterSocialShareFieldData(dataPropertyFile.getProperty("Social_Share"));	// Enter Social Share Data 
		newStoryPage.enterTagFieldData(dataPropertyFile.getProperty("Tag_Name"));			// Enter the Tag Data
		newStoryPage.enterSectionFieldData(dataPropertyFile.getProperty("Section_Name"));	// Enter the Section Data
		newStoryPage.enterCustomURLFieldData(dataPropertyFile.getProperty("Custom_URL"));	// Enter Custom URL
		newStoryPage.clickSaveButton();														// Click Save button
		
		ChromeDriverManager.getInstance().setup();	// Getting an instance of the Chrome Browser				
		ChromeDriver driver1 = new ChromeDriver();
//		System.out.println("Driver Value => "+driver);
 		driver1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		// Applied implicitwait to wait for 5 sec
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point position = new Point(0, 0);
		driver1.manage().window().setPosition(position);
		Dimension maximizedScreenSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		driver1.manage().window().setSize(maximizedScreenSize);
		
		driver1.get(dataPropertyFile.getProperty("itsmanURL"));
		driver1.findElement(By.id("username")).sendKeys("Agent_tester");
		driver1.findElement(By.id("password")).sendKeys("Agent_tester");
		driver1.findElement(By.id("submit")).click();
		Assert.assertEquals(driver1.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		WebElement openTab = driver1.findElement(By.cssSelector("#stories a[href='/workspace#open'] span"));
		WaitForElement.waitForElementToBeVisible(driver1, openTab);
		openTab.click();
		List<WebElement> listOfStories = driver1.findElements(By.cssSelector("#stories .stack-list  h3 > a"));
		WaitForElement.waitForElementToBeVisible(driver1, listOfStories.get(0));
		listOfStories.get(0).click();
		boolean flag = false;
		try{
			driver1.findElement(By.cssSelector(".toggle-checkbox input[disabled]")).isDisplayed();
			flag = true;
		}catch (Exception e) {
			flag = false;
		}
		System.out.println("Result => "+flag);
		// Wait for authors icon to be refect on front end. Ably taking some time to reflect.
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(flag, "Toggle is not disabled");
		Assert.assertEquals(driver1.findElements(By.cssSelector("li.current-editors__editor")).size(), 4);
		
		driver1.quit();
		
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		
	}
}
