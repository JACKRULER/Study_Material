package com.quintype.scripts;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.quintype.api.StoryAPI;
import com.quintype.pom.AdminSettingsStacksPage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.SortersPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.CheckAlert;
import com.quintype.util.CurrentDate;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;

public class TestAdminSettingsStacks extends OpenBrowser {
	
	@BeforeClass(groups = {"functest", "platform" , "PlatformReg3"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	
	// TC ID: Admin_Settings_Stacks_TC_01 => Verify active tab on Admin -> Settings page.
	@Test(groups = {"functest", "platform" ,"PlatformReg3"}, priority = 1)
	public void verifyStacksLinkIsActive()
	{
		log.info("Execution of TestAdminSettingsStacks Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
	
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		Assert.assertTrue(adminSettingsStacksPage.isStackLinkActive());
		
	}
	
	// TC ID: Admin_Settings_Stacks_TC_02 => Verify creation of Stack which display on all pages.
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 2)
	public void verifyCreationOfStackDisplayOnAllPages()
	{
		String stackName = "Stacks_TC_02";
		String color = "#600006";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor(color);
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		Assert.assertTrue(adminSettingsStacksPage.getStackShowInValue(stackName).contains("All pages"));
		
	}
	
	// TC ID: Admin_Settings_Stacks_TC_03 =>  Verify creation of Stack which display on single page.
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 3)
	public void verifyCreationOfStackDisplayedOnSinglePage()
	{
		String stackName = "Stacks_TC_03";
		String pageName = "Home";
		String color = "#400004";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor(color);
		adminSettingsStacksPage.clickShowOnAllPagesCheckBox();
		adminSettingsStacksPage.selectShowOnlyOnPages(pageName);
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		Assert.assertTrue(adminSettingsStacksPage.getStackShowInValue(stackName).contains(pageName));
		
	}
	
	// TC ID: Admin_Settings_Stacks_TC_04 =>  Verify creation of Stack which display on more than one pages.
	@Test(groups = {"functest", "platform","PlatformReg3"}, priority = 4)
	public void verifyCreationOfStackDisplayedOnMultiplePages()
	{
		String stackName = "Stacks_TC_04";
		String color = "#654000";
		String expectedShowIn = "News, Sport";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor(color);
		adminSettingsStacksPage.clickShowOnAllPagesCheckBox();
		adminSettingsStacksPage.selectShowOnlyOnPages("News");
		adminSettingsStacksPage.selectShowOnlyOnPages("Sport");
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		Assert.assertTrue(adminSettingsStacksPage.getStackShowInValue(stackName).contains(expectedShowIn));
		
	}
	
	// TC ID: Admin_Settings_Stacks_TC_05 => Verify mandatory fields on Stack Form.
	@Test(groups = {"functest", "platform","PlatformReg3"}, priority = 5)
	public void verifyMandatoryFields()
	{
		String errorMessages[] = {"Stack name cannot be empty.", "Background color cannot be empty.", "Max stories is not a valid number."};
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.clearMaxStoriesField();
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.compareTwoListOfString(adminSettingsStacksPage.getListOfErrorMessages(), errorMessages));
		adminSettingsStacksPage.clickCrossButton();
		
	}

	@DataProvider(name = "colorValidation")
	public Object[][] createData() 
	{
		return new Object[][] { {"$black"}, {"123123"}, { "#qqqwww"}, {"#12345"}, {"#7777777"}, {"#$$$%%%"},};
	}
	// TC ID: Admin_Settings_Stacks_TC_06 => Validate Color field.
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 6, dataProvider = "colorValidation")
	public void verifyColorFieldValidation(String colors)
	{
		String stackName = "Stacks_TC_06";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor(colors);
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertEquals(adminSettingsStacksPage.getListOfErrorMessages().get(0).getText(), "Background color is not a valid HTML color.");
		adminSettingsStacksPage.clickCrossButton();
		
	}
			
	// TC ID: Admin_Settings_Stacks_TC_07  => Verify creating a stack with update max stories count.
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 7)
	public void verifyCreatingStackWithDifferentMaxStoriesCount()
	{
		String stackName = "Stacks_TC_07";
		String maxStories = "8";
		String color = "#000fff";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor(color);
		adminSettingsStacksPage.enterMaxStories(maxStories);
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		Assert.assertEquals(adminSettingsStacksPage.getStoriesPerStackValue(stackName), maxStories);

	}
	
	// TC ID: Admin_Settings_Stacks_TC_08 => Verify created stack is displaying under Sorters link of menu button (Hamburger).
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 8)
	public void verifyCreatedStackDisplayedUnderSorters()
	{
		String stackName = "Stacks_TC_08";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor("#000000");
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSortersLink();
		Assert.assertTrue(workspacePage.isStackCreated(stackName));
		workspacePage.clickWorkspaceMenuButton();
		
	}		
	
	// TC ID: Admin_Settings_Stacks_TC_09 => Verify user able to add stories to the created stack from sorters page.
	@Test(groups = {"functest", "platform","PlatformReg3"}, priority = 9)
	public void verifyAbleToAddStoriesToCreatedStack()
	{
		String stackName = "Stacks_TC_09";
		int storiesToBeAdded = 3;
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		SortersPage sortersPage = new SortersPage(driver);
			
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor("#000000");
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSortersLink();
		Assert.assertTrue(workspacePage.isStackCreated(stackName));
		workspacePage.clickOnCreatedSorter(stackName);
		for (int i = 0; i < storiesToBeAdded; i++) {	// Adding Stories to the RHS of created Sorter.
			sortersPage.clickOnStarIcon(i);
		}
		sortersPage.clickPublishSortOrderButton();
		workspacePage.clickWorkspaceMenuButtonWithJS();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickSortersLink();
		Assert.assertTrue(workspacePage.isStackCreated(stackName));
		workspacePage.clickOnCreatedSorter(stackName);
		Assert.assertEquals(sortersPage.getStarredIconCount(), (storiesToBeAdded));
		
	}
	// TC ID: Admin_Settings_Stacks_TC_010  => Verify duplicate stacks name error message.
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 10)
	public void verifyDuplicateStacksNotAllowed()
	{
		String stackName = "Stacks_TC_10";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
			
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor("#000000");
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor("#000000");
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertEquals(false, CheckAlert.verifyAcceptAlert(driver));
		
	}		
	// TC ID: Admin_Settings_Stacks_TC_011 => Verify Max Stories functionality.
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 11)
	public void verifyMaxStoriesFieldFunctionality()
	{
		String stackName = "Stacks_TC_011";
		int storiesToBeAdded = 7;
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		SortersPage sortersPage = new SortersPage(driver);
			
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor("#000000");
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSortersLink();
		Assert.assertTrue(workspacePage.isStackCreated(stackName));
		workspacePage.clickOnCreatedSorter(stackName);
		for (int i = 0; i < storiesToBeAdded; i++) {	// Adding Stories to the RHS of created Sorter.
			sortersPage.clickOnStarIcon(i);
		}
		sortersPage.clickPublishSortOrderButton();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickSortersLink();
		Assert.assertTrue(workspacePage.isStackCreated(stackName));
		workspacePage.clickOnCreatedSorter(stackName);
		Assert.assertEquals(sortersPage.getStarredIconCount(), (storiesToBeAdded-2));
		
	}
				

	// TC ID: Admin_Settings_Stacks_TC_012  => Verify changing the order of the stories in sorter & saving.
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 12)
	public void verifyChangingOrderOfStoriesInSorter()
	{
		String stackName = "Stack";
		String color = "#600006";
		int storiesToBeAdded = 3;
		ArrayList<String> starredStoriesTitle = new ArrayList<String>();
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		SortersPage sortersPage = new SortersPage(driver);


		StoryAPI storyAPI = new StoryAPI();
		for (int i = 1; i <= storiesToBeAdded; i++) {
			String firstStoryTitle = "Story: "+i;
			List<String> firstStoryVersion = storyAPI.createPhotoStoryInOpenState(firstStoryTitle, 0);
			storyAPI.changePhotoStoryStatus("story-submit", firstStoryVersion);
			storyAPI.changePhotoStoryStatus("story-approve", firstStoryVersion);
			storyAPI.changePhotoStoryStatus("story-publish", firstStoryVersion);
		}
		Assert.assertEquals(driver.getTitle(), dataPropertyFile.getProperty("Login_Page_Title"), "Itsman is down or not reachable at this moment");
		loginPage.loginToApplication();


		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor(color);
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickSortersLink();
		workspacePage.clickOnCreatedSorter(stackName);

		for (int i = 0; i < storiesToBeAdded; i++) {	// Adding Stories to the RHS of created Sorter.
			sortersPage.clickOnStarIcon(i);
		}

		for (int i = 0; i < sortersPage.getListOfStarredStoriesTitle().size(); i++) {
			starredStoriesTitle.add(sortersPage.getListOfStarredStoriesTitle().get(i).getText());
		}
		for (String string : starredStoriesTitle) {
			System.out.println(string);
		}
		/*Robot robot;
		try {
		robot = new Robot();
		robot.mouseMove(sortersPage.getListOfStarredStoriesTitle().get(0).getLocation().getX(), (sortersPage.getListOfStarredStoriesTitle().get(0).getLocation().getY())+160);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(sortersPage.getListOfStarredStoriesTitle().get(2).getLocation().getX(), (sortersPage.getListOfStarredStoriesTitle().get(2).getLocation().getY())+160);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
		e.printStackTrace();
		}
		*/
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sortersPage.clickPublishSortOrderButton();


		//		workspacePage.clickProfileButton();
		//		Assert.assertEquals(workspacePage.clickLogoutLink(), "Login", "User is not able to logout from Itsman");

	}


	// TC ID: Admin_Settings_Stacks_TC_013 => Change the order of stacks & verify under Sorters Link of Menu.
	@Test(groups = {"functest", "platform", "PlatformReg3"}, priority = 13)
	public void verifyChangingOrderOfStacks()
	{
		String stackName = "Stack Order ";
		String color = "#080907";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		for (int i = 1; i <= 3; i++) {
			adminSettingsStacksPage.clickAddStackButton();
			adminSettingsStacksPage.enterName(stackName+": "+i);
			adminSettingsStacksPage.clearColorField();
			adminSettingsStacksPage.enterColor(color);
			adminSettingsStacksPage.clickSaveStackButton();
			Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName+": "+i), stackName+": "+i));
		}
		driver.navigate().refresh();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickSortersLink();
		List<String> initialList = getSorterList(workspacePage.getListOfSorters());
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		List<WebElement> listOfUpArrow = adminSettingsStacksPage.getListOfUpArrowLink();
		adminSettingsStacksPage.clickUpArrowLink(listOfUpArrow.size()-1);
		adminSettingsStacksPage.clickUpArrowLink(listOfUpArrow.size()-2);
		adminSettingsStacksPage.clickUpArrowLink(listOfUpArrow.size()-1);
		driver.navigate().refresh();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickSortersLink();
		List<String> changedList = getSorterList(workspacePage.getListOfSorters());
		Assert.assertFalse(initialList.equals(changedList));
		workspacePage.clickWorkspaceMenuButton();

	}
	 
	// TC ID: Admin_Settings_Stacks_TC_014 => Disable a stack from Admin -> Settings page & verified under Sorters Link of Menu.
	@Test(groups = {"functest", "platform" , "PlatformReg3"}, priority = 14)
	public void verifyStacksDisableFunctionality()
	{
		String stackName = "Stacks_TC_014";
		String sectionColor = "#123456";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor(sectionColor);
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickSortersLink();
		Assert.assertTrue(workspacePage.isStackCreated(stackName));
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickDisableButton(stackName);
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		driver.navigate().refresh();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickSortersLink();
		Assert.assertFalse(workspacePage.isStackCreated(stackName));
		workspacePage.clickWorkspaceLink();
		
	}
					
	// TC ID: Admin_Settings_Stacks_TC_015 => Verify edit functionality.
	@Test(groups = {"functest", "platform" , "PlatformReg3"}, priority = 15)
	public void verifyStacksEditFunctionality()
	{
		String stackName = "Stacks_TC_015";
		String updatedStackName = "Updated_Stacks_TC_015";
		String sectionColor = "#000fff";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsStacksPage.clickAddStackButton();
		adminSettingsStacksPage.enterName(stackName);
		adminSettingsStacksPage.clearColorField();
		adminSettingsStacksPage.enterColor(sectionColor);
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(stackName), stackName));
		List<WebElement> listOfStacks = adminSettingsStacksPage.getListOfStacks(stackName);
		for (int i = 0; i < listOfStacks.size(); i++) {
			if(listOfStacks.get(i).getText().equalsIgnoreCase(stackName))
				listOfStacks.get(i).click();
		}
		adminSettingsStacksPage.enterName(updatedStackName);
		adminSettingsStacksPage.enterColor(sectionColor);
		adminSettingsStacksPage.clickSaveStackButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsStacksPage.getListOfStacks(updatedStackName), updatedStackName));
		
	}	
				
	public static List <String> getSorterList(List<WebElement> listOfSorters)
	{
		List <String> listOfLast3Sorters = new ArrayList<String>();
		for (int i = listOfSorters.size()-1; i >= listOfSorters.size()-3; i--) {
			listOfLast3Sorters.add(listOfSorters.get(i).getText());
		}
		return listOfLast3Sorters;
	}

}
