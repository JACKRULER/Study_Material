package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class LoginPage 
{
	private WebDriver driver;

	@FindBy(id = "username")
	private WebElement usernameField;
	
	@FindBy(id = "password")
	private WebElement passwordField;
	
	@FindBy(id = "submit")
	private WebElement loginButton;
	
	@FindBy(css = "a.icon.button.social-icon.facebook")
	private WebElement facebookButton;
	
	@FindBy(css = "a.icon.button.social-icon.twitter")
	private WebElement twitterButton;
	
	@FindBy(css = "a.icon.button.social-icon.google")
	private WebElement gogglePlusButton;
	
	@FindBy(css = "a.icon.button.social-icon.linkedin")
	private WebElement linkedInButton;
	
	@FindBy(css = "p.error-inverse")
	private WebElement errorMessage;
	
	@FindBy(id = "identifierId")
	private WebElement emailFieldOfGmail;

	@FindBy(id = "identifierNext")
	private WebElement nextButton;
	
	@FindBy(name = "password")
	private WebElement passwordFieldOfGmail;

	@FindBy(id = "passwordNext")
	private WebElement passwordNext;

	@FindBy(css = ".forgot-password-link")
	private WebElement forgotPasswordLink;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String username)							
	{
		WaitForElement.waitForElementToBeVisible(driver, usernameField);
		usernameField.clear();
		usernameField.sendKeys(username);
	}
	public void enterPassword(String password)
	{
		WaitForElement.waitForElementToBeVisible(driver, passwordField);
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	public void clickLoginButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, loginButton);
		loginButton.submit();
	}
	public void loginToApplication()
	{
		enterUserName("tester");
		enterPassword("tester");
		clickLoginButton();
	}
	public void authorloginToApplication(String userName, String password)
	{
		enterUserName(userName);
		enterPassword(password);
		clickLoginButton();
	}
	public void clickFacebookButton()
	{
		
	}
	public void clickTwitterButton()
	{
		
	}
	public void clickGooglePlusButton()		// Method to login to itsman through google plus
	{
		WaitForElement.waitForElementToBeVisible(driver, gogglePlusButton);
		gogglePlusButton.click();
	}
	public void linkedInButton()
	{
		
	}
	public void enterEmail(String email)
	{
		WaitForElement.waitForElementToBeVisible(driver, emailFieldOfGmail);
		emailFieldOfGmail.sendKeys(email);
	}
	public void clickNextButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, nextButton);
		nextButton.click();
	}
	public void enterEmailPassword(String emailPassword)
	{
		WaitForElement.waitForElementToBeVisible(driver, passwordFieldOfGmail);
		passwordFieldOfGmail.sendKeys(emailPassword);
	}
	public void clickSignInButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, passwordNext);
		passwordNext.click();
	}

	public String verifyErrorMessage()
	{
		WaitForElement.waitForElementToBeVisible(driver, errorMessage);
		return errorMessage.getText();
	}
	public void clickForgotPasswordLink()
	{
		WaitForElement.waitForElementToBeVisible(driver, forgotPasswordLink);
		forgotPasswordLink.click();
	}
	public void loginToAddAuthor()
	{
		enterUserName("Tester01");
		enterPassword("Tester01");
		clickLoginButton();
	}
}
