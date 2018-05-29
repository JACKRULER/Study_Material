package com.quintype.scripts;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.LoginPage;
import com.quintype.pom.RolesPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;

public class TestRoles extends OpenBrowser 
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
	
	// TC ID: Roles_TC_002 => Verify there is two Add New Role button.
	@Test(groups = {"functest", "platform"}, priority = 1)
	public void testTwoAddNewRoleButton()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		RolesPage rolesPage = new RolesPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		rolesPage.clickRolesTab();
		Assert.assertEquals(2, rolesPage.getListOfAddNewRoleButton().size());
		
	}
	// TC ID: Roles_TC_003 => Verify 3 Access Category tab.
	@Test(groups = {"functest", "platform"}, priority = 2)
	public void testRoleAccessCategory()
	{
		String[] listOfExpectedRoleAccessCategory = {"Content", "Analytics", "Admin"};
		WorkspacePage workspacePage = new WorkspacePage(driver);
		RolesPage rolesPage = new RolesPage(driver);
			
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		rolesPage.clickRolesTab();
		rolesPage.clickAddNewRoleButton();
		Assert.assertTrue(Verification.compareTwoListOfString(rolesPage.getListOfRoleAccessCategory(), listOfExpectedRoleAccessCategory));
		rolesPage.clickCloseButton();
			
	}	
	
	//TC ID: Roles_TC_007 => Verify the mandatory fields on Role Creation form.

	@Test(groups = {"functest", "platform"}, priority = 7)
	public void testMandatoryFieldOnRoles()
	{
		String[] errorMessages = {"Name is required", "No action selected"};
		WorkspacePage workspacePage = new WorkspacePage(driver);
		RolesPage rolesPage = new RolesPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickUserAndRoleLink();
		rolesPage.clickRolesTab();
		rolesPage.clickAddNewRoleButton();
		rolesPage.clickSaveRoleButton();
		Assert.assertTrue(Verification.verify(errorMessages[0], rolesPage.getListOfMandatoryErrorMsg().get(0).getText()));
		Assert.assertTrue(Verification.verify(errorMessages[1], rolesPage.getListOfMandatoryErrorMsg().get(1).getText()));
		rolesPage.clickCloseButton();
		
	}		

}
