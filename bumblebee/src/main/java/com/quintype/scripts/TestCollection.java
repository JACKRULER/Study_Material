package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.quintype.pom.ConfigurePage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.PagesPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.ConfigFeatureAPI;
import com.quintype.util.OpenBrowser;
import com.quintype.util.CurrentDate;
import com.quintype.util.WaitForElement;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.CollectionPage;

import org.testng.Reporter;

public class TestCollection extends OpenBrowser
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

	@Test(groups = {"functest","platform"}, priority = 1)
	public void createAutoStoryCollection()
	{
		log.info("Execution of TestCollection Started");
		log.info("Execution of createAutoStoryCollection Started");

		new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
		CollectionPage collectionPage = new CollectionPage(driver);
		PagesPage page = new PagesPage(driver);

		workspacePage.clickNewStoryButton();
		storyTypePage.clickStoryCollectionButton();
		String collectionTitle = dataPropertyFile.getProperty("Collection_Title")+CurrentDate.getCurrentDateAndTime();
		collectionPage.enterStoryCollectionTitle(collectionTitle);
		collectionPage.enterStoryCollectionSummary(dataPropertyFile.getProperty("Collection_Summary"));
		collectionPage.uploadCoverImage(dataPropertyFile.getProperty("Entity_Image"));
		collectionPage.selectAutoCollectionTypeRadio();
		collectionPage.clickSaveButton();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickPagesLink();
		Assert.assertEquals(collectionTitle, page.getNameOfFirstCollection());
		log.info("Execution of createAutoStoryCollection completed");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
  	}

	@Test(groups = { "functest","platform","smoketest"}, priority = 2)
	public void createManualCollection() throws InterruptedException {
		log.info("Execution of createManualCollection Started");
//		launchApplication();
//		loginToItsman();
		WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
		CollectionPage collectionPage = new CollectionPage(driver);
		PagesPage pagesPage = new PagesPage(driver);
		ConfigurePage configPage = new ConfigurePage(driver);

		String toggleStatus = ConfigFeatureAPI.callAPI("Collection Associated Metadata");
		if(toggleStatus.equalsIgnoreCase("false")){
			workspacePage.clickWorkspaceMenuButton();
			workspacePage.clickAdminLink();
			workspacePage.clickConfigureLink();
			configPage.clickCollectionAssociatedMetadataToggle();
			workspacePage.clickWorkspaceMenuButton();
			workspacePage.clickWorkspaceLink();
		}
		workspacePage.clickNewStoryButton();
		storyTypePage.clickStoryCollectionButton();
		String collectionTitle = dataPropertyFile.getProperty("Collection_Title")+CurrentDate.getCurrentDateAndTime();
		collectionPage.enterStoryCollectionTitle(collectionTitle);
		collectionPage.enterStoryCollectionSummary(dataPropertyFile.getProperty("Collection_Summary"));
		collectionPage.uploadCoverImage(dataPropertyFile.getProperty("Entity_Image"));
		collectionPage.clickSorterTab();
		collectionPage.clickAdvancedSearch();
		collectionPage.selectCollectionRadioButton();
		collectionPage.clickApplyFilterButton();
		collectionPage.addToSorter(1);
		collectionPage.clickSaveButton();
		log.info("Execution of createManualCollection completed");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickPagesLink();
		Assert.assertEquals(pagesPage.getNameOfFirstCollection(),collectionTitle);



	}

	@Test(groups = {"platform"}, priority = 3)
	public void createRecommendedStoryCollection()
	{
    	log.info("Execution of TestCollection Started");
		new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
		CollectionPage collectionPage = new CollectionPage(driver);
		PagesPage page = new PagesPage(driver);

		workspacePage.clickNewStoryButton();
		storyTypePage.clickStoryCollectionButton();
		String collectionTitle = dataPropertyFile.getProperty("Collection_Title")+CurrentDate.getCurrentDateAndTime();
		collectionPage.enterStoryCollectionTitle(collectionTitle);
		collectionPage.enterStoryCollectionSummary(dataPropertyFile.getProperty("Collection_Summary"));
		collectionPage.uploadCoverImage(dataPropertyFile.getProperty("Entity_Image"));
		collectionPage.selectRecoCollectionTypeRadio();
		collectionPage.clickSaveButton();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickPagesLink();
		Assert.assertEquals(collectionTitle, page.getNameOfFirstCollection());
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
  	}

	@Test(groups = {"platform"}, priority = 4)
	public void verifyStoriesAndCollectionsTabForAutomatedCollection()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
        StoryTypePage storyTypePage = new StoryTypePage(driver);
		CollectionPage collectionPage = new CollectionPage(driver);

		workspacePage.clickNewStoryButton();
		storyTypePage.clickStoryCollectionButton();
		collectionPage.enterStoryCollectionTitle("Random Title: "+CurrentDate.getCurrentDateAndTime());
		collectionPage.enterStoryCollectionSummary(dataPropertyFile.getProperty("Collection_Summary"));
		collectionPage.uploadCoverImage(dataPropertyFile.getProperty("Entity_Image"));
		collectionPage.selectAutoCollectionTypeRadio();
		collectionPage.clickSorterTab();
		collectionPage.clickStoriesLink();
		Assert.assertTrue(collectionPage.isStoriesLinkDisplayed());
		collectionPage.clickCollectionsLink();
		Assert.assertTrue(collectionPage.isCollectionsLinkDisplayed());
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();

  	}
}
