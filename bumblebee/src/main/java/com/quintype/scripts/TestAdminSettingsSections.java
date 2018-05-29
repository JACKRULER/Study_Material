package com.quintype.scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.AdminSettingsSectionsPage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.StoryTypePage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TestAdminSettingsSections extends OpenBrowser {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	@BeforeClass(groups = { "functest", "platform", "platformReg4" })
	public void loginToItsman() {
		if (driver.getTitle().equalsIgnoreCase("Login")) {
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();
		}
	}

	// TC ID: Admin_Settings_Sections_TC_01 => Verify mandatory fields on Section
	// Form.
	@Test(groups = { "functest", "platform", "platformReg4" }, priority = 1, enabled = false)
	public void verifyMandatoryFields() {
		String errorMessages[] = { "Name can't be blank", "Slug can't be blank" };
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsSectionsPage adminSettingsSectionsPage = new AdminSettingsSectionsPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsSectionsPage.clickSectionsLink();
		adminSettingsSectionsPage.clickAddSectionButton();
		adminSettingsSectionsPage.clickSaveButton();
		Assert.assertTrue(
				Verification.compareTwoListOfString(adminSettingsSectionsPage.getListOfErrorMessages(), errorMessages));
		adminSettingsSectionsPage.clickCloseButton();

	}

	// TC ID: Admin_Settings_Sections_TC_02 => Disable parent section, then it
	// should disabled all the child sections.
	@Test(groups = { "functest", "platform", "platformReg4" }, priority = 2)
	public void verifyDisableSectionNotAppearingOnStoryPage_and_verifyParentSectionDisableShouldDisableAllChild() {
		String parentSectionName = "Parent_Section" + dtf.format(now);
		String childSectionName1 = "Child_Section_1" + dtf.format(now);
		String childSectionName2 = "Child_Section_2" + dtf.format(now);

		WorkspacePage workspacePage = new WorkspacePage(driver);
		StoryTypePage storyTypePage = new StoryTypePage(driver);
		NewStoryPage newStoryPage = new NewStoryPage(driver);
		AdminSettingsSectionsPage adminSettingsSectionsPage = new AdminSettingsSectionsPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsSectionsPage.createSection(parentSectionName);
		//Assert.assertTrue(Verification.verifyExpectedElementPresentInList(
		//		adminSettingsSectionsPage.getListOfSections(parentSectionName), parentSectionName));
		adminSettingsSectionsPage.createChildSection(parentSectionName, childSectionName1);
		//Assert.assertTrue(Verification.verifyExpectedElementPresentInList(
		//		adminSettingsSectionsPage.getListOfSections(childSectionName1), childSectionName1));
		adminSettingsSectionsPage.createChildSection(parentSectionName, childSectionName2);
		//Assert.assertTrue(Verification.verifyExpectedElementPresentInList(
		//		adminSettingsSectionsPage.getListOfSections(childSectionName2), childSectionName2));
		adminSettingsSectionsPage.clickDisableButton(parentSectionName);
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		workspacePage.clickNewStoryButton();
		storyTypePage.clickBlankStoryButton();
		newStoryPage.clickMetadataLink();
		//Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(newStoryPage.getListOfSections(),
		//		parentSectionName));

		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		driver.navigate().refresh();
		adminSettingsSectionsPage.clickSectionsLink();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(
				adminSettingsSectionsPage.getListOfDisabledSections(), parentSectionName));
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(
				adminSettingsSectionsPage.getListOfDisabledSections(), childSectionName1));
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(
				adminSettingsSectionsPage.getListOfDisabledSections(), childSectionName2));

	}

	// TC ID: Admin_Settings_Sections_TC_08 => Verify edit functionality.
	@Test(groups = { "functest", "platform", "platformReg4" }, priority = 3, enabled = false)
	public void verifyEditingSection() {
		String sectionName = "Sections_TC_08" + dtf.format(now);
		String updatedSectionName = "Updated_Sections_TC_08" + dtf.format(now);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsSectionsPage adminSettingsSectionsPage = new AdminSettingsSectionsPage(driver);

		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsSectionsPage.clickSectionsLink();
		adminSettingsSectionsPage.clickAddSectionButton();
		adminSettingsSectionsPage.enterSectionName(sectionName);
		adminSettingsSectionsPage.enterDisplayName(sectionName);
		adminSettingsSectionsPage.clickSaveButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(
				adminSettingsSectionsPage.getListOfSections(sectionName), sectionName));
		adminSettingsSectionsPage.clickSectionName(sectionName);
		adminSettingsSectionsPage.enterSectionName(updatedSectionName);
		adminSettingsSectionsPage.enterDisplayName(updatedSectionName);
		adminSettingsSectionsPage.clickSaveButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(
				adminSettingsSectionsPage.getListOfSections(updatedSectionName), updatedSectionName));
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickWorkspaceLink();
	}

}
