package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class ForgotPasswordPage
{
	private WebDriver driver;			

	@FindBy(name = "username")
	private WebElement emailField;

	@FindBy(css = ".submit")
	private WebElement sendButton;

	@FindBy(css = ".notification")
	private WebElement notificationLink;

	public ForgotPasswordPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmail(String email)
	{
		WaitForElement.waitForElementToBeVisible(driver, emailField);
		emailField.sendKeys(email);
	}
	public void clickSendButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, sendButton);
		sendButton.click();
	}
	public String verifyMessage()
	{
		WaitForElement.waitForElementToBeVisible(driver, notificationLink);
		return notificationLink.getText();
	}
}