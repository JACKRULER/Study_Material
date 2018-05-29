package com.quintype.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.Reporter;

public class CheckAlert extends OpenBrowser {
	public static boolean verifyAcceptAlert(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			Reporter.log("Alert Message : " + alert.getText());
			alert.accept();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public static boolean verifyAcceptAlert(WebDriver driver, String alertMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String actualAlert = alert.getText();
			Assert.assertTrue(alertMessage.contentEquals(actualAlert), "Expected and Actual alert are different");
			alert.accept();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public static boolean verifyDismissAlert(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			Reporter.log("Alert Message : " + alert.getText());
			alert.dismiss();
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}
