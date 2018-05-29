package com.quintype.scripts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quintype.pom.ConfigurePage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;

public class TestFeatureToggle extends OpenBrowser
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
	
	@Test(groups = {"functest", "platform"}, priority = 1)
	public void testEnableEntities()
	{
		log.info("Execution of TestFeatureToggle Started");
		WorkspacePage workspacePage = new WorkspacePage(driver);
		ConfigurePage configurePage = new ConfigurePage(driver);
		

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickConfigureLink();
        configurePage.clickEntitiesToggleButton();
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}