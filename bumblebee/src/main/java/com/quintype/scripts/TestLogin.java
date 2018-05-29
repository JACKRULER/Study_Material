package com.quintype.scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.api.GeneralAPI;
import com.quintype.api.PublisherAPI;
import org.testng.Reporter;
import com.quintype.pom.ForgotPasswordPage;

public class TestLogin extends OpenBrowser 
{
	@BeforeClass(groups = {"functest", "platform","PlatformReg1"})
	public void validation()
	{
		WorkspacePage workspacePage = new WorkspacePage(driver);
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is in Login page");
		}
		else {
			workspacePage.clickWorkspaceMenuButton();
			workspacePage.clickWorkspaceLink();
			workspacePage.clickProfileButton();
			workspacePage.clickLogoutLink();
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is in login page");
		}
	}

	// Test Case ID: Login_TC_001 & Login_TC_009
	@Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 1)
	public void testValidLoginUNPassword() 
	{		  
		log.info("Execution of TestLogin Started");
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);

		Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");		// Click logout link & verify user is logged out using tab title
	
		loginPage.enterUserName(dataPropertyFile.getProperty("Username"));	// Enter valid username
		loginPage.enterPassword(dataPropertyFile.getProperty("Password"));	// Enter valid password
		loginPage.clickLoginButton();		// Click on Login button

		Assert.assertEquals("Workspace" , driver.getTitle(), "User is not able to login to the Itsman with valid credentials");	// Verify user is able to login by tab title
		workspacePage.clickProfileButton();		// Click Profile Button
		
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
	}
				
	// Verifying the Google+ login of the Itsman	
	//@Test(groups = {"platform","PlatformReg1"}, priority = 2)
	public void testValidLoginGooglePlus()
	{
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);

		Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");

		loginPage.clickGooglePlusButton();
		loginPage.enterEmail(dataPropertyFile.getProperty("Email"));
		loginPage.clickNextButton();
		loginPage.enterEmailPassword(dataPropertyFile.getProperty("EmailPassword"));
		loginPage.clickSignInButton();

		workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");

	}

	
	//Test Case ID: Login_TC_004	
	@Test(groups = {"functest", "platform","PlatformReg1"}, priority = 3)
	public void testForgotPassword()
	{
		LoginPage loginPage = new LoginPage(driver);
		ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

		Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");

		loginPage.clickForgotPasswordLink();

		forgotPasswordPage.enterEmail(dataPropertyFile.getProperty("Email"));
		forgotPasswordPage.clickSendButton();
		
		Assert.assertEquals(true , forgotPasswordPage.verifyMessage().contains("Reset password email has been sent to"), "Forgot Password Functionality is Broken");
		driver.get(dataPropertyFile.getProperty("itsmanURL")+"/logout");		// In order to navigate to Login Page, so next scripts will not Interrupt
		driver.navigate().refresh();				// Refresh the page to reset the Login page
	}			

	// Invalid data to verify login functionality
	@DataProvider(name = "invalidLoginCredential")
	public Object[][] createData() 
	{
		return new Object[][] { {"tester" , "ASDASDASD"}, { "ASDASDASD", "tester"}, {"ASDASDASD", "ASDASDSD"},};
	}
	// Test Case ID: Login_TC_003
	@Test(groups = {"functest", "platform","PlatformReg1"}, priority = 4, dataProvider = "invalidLoginCredential")
	public void testInvalidLogin(String username, String password)
	{
		log.info("Execution of TestLogin Started");
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals("Login", driver.getTitle(), "Itsman is down or not reachable at this moment");
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
	
		Assert.assertEquals("Authentication failed", loginPage.verifyErrorMessage());
	}		
	
	// Test Case ID: Login_TC_002: Verify a member having no roles assigned should able to login. 
	// There should be member with username & password tester123 in Itsman.
	@Test(groups = {"functest", "platform","PlatformReg1"}, priority = 5)
	public void testErrorMsgIfNoRoleAssign()
	{
		String expectedMessage = "No roles assigned. Contact an admin!";
		LoginPage loginPage = new LoginPage(driver);
		
		Assert.assertEquals("Login", driver.getTitle(), "Itsman is down or not reachable at this moment");
		loginPage.enterUserName("tester123");
		loginPage.enterPassword("tester123");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.verifyErrorMessage(), expectedMessage);
	}

	@Test(groups = {"functest", "platform","PlatformReg1"}, priority = 6)
	public void testAddAuthorLogin()
	{
		log.info("Execution of TestLogin Started");
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);

		Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");		// Click logout link & verify user is logged out using tab title

		loginPage.enterUserName(dataPropertyFile.getProperty("Username2"));	// Enter valid username
		loginPage.enterPassword(dataPropertyFile.getProperty("Password2"));	// Enter valid password
		loginPage.clickLoginButton();		// Click on Login button

		Assert.assertEquals("Workspace" , driver.getTitle(), "User is not able to login to the Itsman with valid credentials");	// Verify user is able to login by tab title
		workspacePage.clickProfileButton();		// Click Profile Button

		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
	}
			
}
