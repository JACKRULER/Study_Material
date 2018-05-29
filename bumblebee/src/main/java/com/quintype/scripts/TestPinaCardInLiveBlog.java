package com.quintype.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.CheckAlert;
import com.quintype.util.OpenBrowser;
import com.quintype.util.ScrollPage;
import com.quintype.util.Verification;

public class TestPinaCardInLiveBlog extends OpenBrowser {
	@BeforeClass(groups = { "functest", "platform", "platformReg4" })
	public void loginToItsman() {
		if (driver.getTitle().equalsIgnoreCase("Login")) {
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();
		}
	}

	@Test(groups = { "functest", "platform", "platformReg4" }, priority = 1)
	public void testpinACradInLiveBlogAndVerifyCardIsPinned() throws InterruptedException {
		log.info("Execution of testpinACradInLiveBlogAndVerifyCardIsPinned Started");
		int liveBlogCardCount = 3;
		int cardToBePinned = 2;
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickLiveBlogStoryButton();
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Live_Blog_Title"));
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));

		for (int i = 1; i <= liveBlogCardCount; i++) {
			newStoryPage.clickNewCardAddButton();
			newStoryPage.selectKeyEventCheckboxLiveBlog();
			newStoryPage.enterTitleFieldDataLiveBlog(dataPropertyFile.getProperty("Title_Live_Blog") + " " + i);
			newStoryPage.clickSaveButton();
		}
		newStoryPage.pinCard(cardToBePinned);
		Assert.assertTrue(Verification.verifyCardpinned(cardToBePinned), "Something went wrong and card is not pinned");
		newStoryPage.unPinCard(cardToBePinned);
		Assert.assertFalse(Verification.verifyCardpinned(cardToBePinned),
				"Something went wrong and card is not pinned");
	}

	@Test(groups = { "functest", "platform", "platformReg4" }, priority = 2, dependsOnMethods = {
			"testpinACradInLiveBlogAndVerifyCardIsPinned" })
	public void testpinACardwithoutSaveAndVerifyAlert() throws InterruptedException {
		log.info("Execution of testpinACardwithoutSaveAndVerifyAlert Started");
		int cardToBePinned = 0;
		String alertMessage = "You tried to pin a new card. Please save the story and try again";
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);

		newStoryPage.clickNewCardAddButton();
		newStoryPage.pinCard(cardToBePinned);
		Assert.assertFalse(CheckAlert.verifyAcceptAlert(driver, alertMessage),
				"Something went wrong, Alert did not pop up");
		Assert.assertFalse(Verification.verifyCardpinned(cardToBePinned),
				"Something went wrong and card is not pinned");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
	}

	@Test(groups = { "functest", "platform", "platformReg4" }, priority = 3)
	public void testpinDifferentCardInLiveBlog() throws InterruptedException {
		log.info("Execution of testpinDifferentCardInLiveBlog Started");
		int liveBlogCardCount = 3;
		int cardToBePinned = 2;
		int newCardToBePinned = 1;
		String alertMessage = "Pinning this card will unpin the other card. Do you want to proceed?";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickNewStoryButton();
		storyTypePage.clickLiveBlogStoryButton();
		newStoryPage.enterTitleFieldData(dataPropertyFile.getProperty("Live_Blog_Title"));
		newStoryPage.enterSubTitleFieldData(dataPropertyFile.getProperty("Sub_Title"));
		newStoryPage.selectHeroImage(dataPropertyFile.getProperty("Hero_Image_Name"));

		for (int i = 1; i <= liveBlogCardCount; i++) {
			newStoryPage.clickNewCardAddButton();
			newStoryPage.selectKeyEventCheckboxLiveBlog();
			newStoryPage.enterTitleFieldDataLiveBlog(dataPropertyFile.getProperty("Title_Live_Blog") + " " + i);
			newStoryPage.clickSaveButton();
		}
		newStoryPage.pinCard(cardToBePinned);
		Assert.assertTrue(Verification.verifyCardpinned(cardToBePinned), "Something went wrong and card is not pinned");
		newStoryPage.pinCard(newCardToBePinned);
		Assert.assertFalse(CheckAlert.verifyAcceptAlert(driver, alertMessage),
				"Something went wrong, Alert did not pop up");
		Assert.assertTrue(Verification.verifyCardpinned(newCardToBePinned),
				"Something went wrong and card is not pinned");
		Assert.assertFalse(Verification.verifyCardpinned(cardToBePinned), "Card should have been un-pinned");
	}

}
