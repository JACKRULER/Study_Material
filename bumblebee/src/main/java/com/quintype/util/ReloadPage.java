package com.quintype.util;

import org.openqa.selenium.WebDriver;

public class ReloadPage extends OpenBrowser
{
    public static void resetToLoginPage(WebDriver driver)	// This method delete the session-cookie so we can navigate to login page
    {
    	driver.manage().deleteCookieNamed("session-cookie");
	  	driver.navigate().refresh();
	  	CheckAlert.verifyAcceptAlert(driver);		// After refresh will handle pop up if any occured
    }

}
