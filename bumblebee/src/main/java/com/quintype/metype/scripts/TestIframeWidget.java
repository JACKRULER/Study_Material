package com.quintype.metype.scripts;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.metype.pom.WidgetPage;
import com.quintype.util.OpenBrowser;

public class TestIframeWidget  extends OpenBrowser  {

	@Test(groups = {"metypesmoketest"}, priority = 1)
	public void test()
	{
		String myComment = "My First Comment";
		WidgetPage widgetPage = new WidgetPage(driver);
		
		widgetPage.switchToIFrame();
		widgetPage.clickLoginButton();
		widgetPage.clickGooglePlusLink();
		widgetPage.switchToNewWindow();
		widgetPage.enterEmailID(dataPropertyFile.getProperty("facebookusername"));
		widgetPage.enterPassword(dataPropertyFile.getProperty("facebookpassword"));	
		widgetPage.clickNextButton();
		
		widgetPage.switchToOldWindow();
		
		widgetPage.enterComment(myComment);
		
		widgetPage.clickEnterButton();
		Assert.assertEquals(widgetPage.getListOfComments().get(0).getText(), myComment);
		
		System.out.println("End !!");
	}
}
