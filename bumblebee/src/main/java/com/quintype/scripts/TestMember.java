package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.LoginPage;
import com.quintype.pom.RolesPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;
import com.quintype.util.WaitForElement;
import com.quintype.pom.UserAndRolesPage;
import com.quintype.pom.AuthorProfilePage;
import org.testng.Assert;
import org.testng.Reporter;

public class TestMember extends OpenBrowser
{
	@BeforeClass(groups = {"functest", "platform"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	
	// TC: Member_TC_01 => Verify two Tabs on Admin & Roles page.
	@Test(groups = {"functest", "platform"}, priority = 1)
	public void verifyTabCount()
	{
		log.info("Execution of TestMember Started");		
		int countOfExpectedTabs = 2;
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);
		

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();			
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		Assert.assertEquals(userAndRolesPage.getListOfTabs().size(), countOfExpectedTabs);
		
	}
	
	// TC: Member_TC_02 => Verify by default Members tab should be active.
	@Test(groups = {"functest", "platform"}, priority = 2)
	public void verifyIfMemberTabActive()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);
			
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();			
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		Assert.assertTrue(userAndRolesPage.isMemberTabActive());
			
	}
	
	// TC: Member_TC_03 => Verify the mandatory fields on Members tab.
	@Test(groups = {"functest", "platform"}, priority = 3)
	public void verifyMandatoryFields()
	{
		String expectedErrorMessages[] = {"Valid Name is required.", "Valid Email-Id is required", "Username is required", "Password is required"};
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);
		

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		userAndRolesPage.clickAddMemberButton();
		Assert.assertTrue(Verification.compareTwoListOfString(userAndRolesPage.getListOfMandatoryFieldsErrorMessage(), expectedErrorMessages));
		
	}
	
	// TC: Member_TC_04 => Verify the Member search with invalid member.
	@Test(groups = {"functest", "platform"}, priority = 4)
	public void verifyInvalidMemberSearch()
	{
		String invalidMemberName = "Dummy";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();	
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		userAndRolesPage.searchForMember(invalidMemberName);
		Assert.assertTrue(userAndRolesPage.isMemberNotFound());
		
	}
	
	// TC: Member_TC_05 & Member_TC_06 => Verify the Member search with valid member.
	@Test(groups = { "functest", "platform" }, priority = 5)
	public void verifyMemberSearchWithValidMember() 
	{
		String memberName[] = dataPropertyFile.getProperty("Member_Name").split(";");
		String memberEmail[] = dataPropertyFile.getProperty("Member_EmailId").split(";");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();	
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		userAndRolesPage.enterMemberName(memberName[0]);
		userAndRolesPage.enterMemberEmail(memberEmail[0]);
		userAndRolesPage.enterCommunicationEmailID(memberEmail[0]);
		userAndRolesPage.enterMemberUserName(memberName[0]);
		userAndRolesPage.enterMemberPassword(dataPropertyFile.getProperty("Member_Password"));
		userAndRolesPage.clickAddMemberButton();
		userAndRolesPage.searchForMember(memberName[0]);
		Assert.assertTrue(userAndRolesPage.isMemberFound());
		
	}
	
	// TC: Member_TC_7 => Verify Member Name & Email id on Member page.
	@Test(groups = {"functest", "platform"}, priority = 6)
	public void verifyMemberPageTitle()
	{
		String memberName[] = dataPropertyFile.getProperty("Member_Name").split(";");
		String memberEmail[] = dataPropertyFile.getProperty("Member_EmailId").split(";");
		String expectedTitle = "Member - "+memberName[1]+" ("+memberEmail[1]+")";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();	
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		userAndRolesPage.enterMemberName(memberName[1]);
		userAndRolesPage.enterMemberEmail(memberEmail[1]);
		userAndRolesPage.enterCommunicationEmailID(memberEmail[1]);
		userAndRolesPage.enterMemberUserName(memberName[1]);
		userAndRolesPage.enterMemberPassword(dataPropertyFile.getProperty("Member_Password"));
		userAndRolesPage.clickAddMemberButton();
		userAndRolesPage.selectMember(memberName[1]);
		Assert.assertEquals(userAndRolesPage.getMemberPageTitle().getText(), expectedTitle);
		
	}
	// TC: Member_TC_08 => Verify user able to add roles to newly created Member.
	@Test(groups = { "functest", "platform" }, priority = 7)
	public void testCreateMemberWithRole() 
	{
		String memberName[] = dataPropertyFile.getProperty("Member_Name").split(";");
		String memberEmail[] = dataPropertyFile.getProperty("Member_EmailId").split(";");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();	
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		userAndRolesPage.enterMemberName(memberName[2]);
		userAndRolesPage.enterMemberEmail(memberEmail[2]);
		userAndRolesPage.enterCommunicationEmailID(memberEmail[2]);
		userAndRolesPage.enterMemberUserName(memberName[2]);
		userAndRolesPage.enterMemberPassword(dataPropertyFile.getProperty("Member_Password"));
		userAndRolesPage.clickAddMemberButton();
		userAndRolesPage.selectMember(memberName[2]);
		userAndRolesPage.selectRole(dataPropertyFile.getProperty("Tech_Support_Role"));
		userAndRolesPage.clickAddRoleToMemberButton();	
		Assert.assertEquals(true, userAndRolesPage.verifyRoleIsSelected());
		
	}
	// TC: Member_TC_09 => Verify user able to add roles to the newly created Member and able to delete the same roles.
	@Test(groups = { "functest", "platform" }, dependsOnMethods = {"testCreateMemberWithRole"}, priority = 8)
	public void testDeleteMembersRole() 
	{
		String memberName[] = dataPropertyFile.getProperty("Member_Name").split(";");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();			// Click workspace menu button	
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		userAndRolesPage.selectMember(memberName[2]);
		userAndRolesPage.clickOnRoleCheckBox();
		userAndRolesPage.clickDeleteMemberRole();
		Assert.assertEquals(false, userAndRolesPage.verifyRoleIsSelected());
		
	}
	// TC: Member_TC_10 => Verify Member Name & Email id on Member page.
	@Test(groups = {"functest", "platform"}, priority = 9)
	public void verifyNameAndEmailFieldDataOnMemberPage()
	{
		String memberName[] = dataPropertyFile.getProperty("Member_Name").split(";");
		String memberEmail[] = dataPropertyFile.getProperty("Member_EmailId").split(";");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);
		AuthorProfilePage authorProfilePage = new AuthorProfilePage(driver);
			
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();	
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		userAndRolesPage.enterMemberName(memberName[3]);
		userAndRolesPage.enterMemberEmail(memberEmail[3]);
		userAndRolesPage.enterCommunicationEmailID(memberEmail[3]);
		userAndRolesPage.enterMemberUserName(memberName[3]);
		userAndRolesPage.enterMemberPassword(dataPropertyFile.getProperty("Member_Password"));
		userAndRolesPage.clickAddMemberButton();
		WaitForElement.waitForPageToLoad(driver);
		userAndRolesPage.selectMember(memberName[3]);
		driver.navigate().refresh();
		WaitForElement.waitForPageToLoad(driver);
		Assert.assertEquals(authorProfilePage.getMyProfileName().getAttribute("value"), memberName[3]);
		Assert.assertEquals(authorProfilePage.getCommunicationEmailID().getAttribute("value"), memberEmail[3]);
			
	}
				
	// TC: Member_TC_11 => Verify Creating a new Role & Member, verify member able to login in App.
	@Test(groups = {"functest", "platform" }, priority = 10)
	public void verifyNewlyCreatedMemberWithNewRoleIsAbleToLogin() 
	{
		String roleName = "Sample Role";
		String memberName[] = dataPropertyFile.getProperty("Member_Name").split(";");
		String memberEmail[] = dataPropertyFile.getProperty("Member_EmailId").split(";");
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		UserAndRolesPage userAndRolesPage = new UserAndRolesPage(driver);
		RolesPage rolesPage = new RolesPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();	
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		rolesPage.clickRolesTab();
		rolesPage.clickAddNewRoleButton();
		rolesPage.enterRoleName(roleName);
		rolesPage.selectParentRole(dataPropertyFile.getProperty("Admin_Role"));
		rolesPage.clickSaveRoleButton();
		userAndRolesPage.clickMembersTab();
		userAndRolesPage.enterMemberName(memberName[4]);
		userAndRolesPage.enterMemberEmail(memberEmail[4]);
		userAndRolesPage.enterCommunicationEmailID(memberEmail[4]);
		userAndRolesPage.enterMemberUserName(memberName[4]);
		userAndRolesPage.enterMemberPassword(dataPropertyFile.getProperty("Member_Password"));
		userAndRolesPage.clickAddMemberButton();
		userAndRolesPage.selectMember(memberName[4]);
		userAndRolesPage.selectRole(roleName);
		userAndRolesPage.clickAddRoleToMemberButton();	
		
		workspacePage.clickProfileButton();		
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
		
		loginPage.enterUserName(memberName[4]);
		loginPage.enterPassword(dataPropertyFile.getProperty("Member_Password"));
		loginPage.clickLoginButton();
		Assert.assertEquals("Workspace" , driver.getTitle(), "User is not able to login in Itsman");
		
	}		
	
}
