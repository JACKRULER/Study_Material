package com.quintype.metype.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.quintype.util.WaitForElement;

public class WidgetPage {

	public static WebDriver driver;
	public static String winHandleBefore = "";
	
	@FindBy(css = "a.login-button")
	private WebElement loginButton;
	
	@FindBy(css = ".login-provider .facebook")
	private WebElement googlePlusLink;
	
	@FindBy(id = "email")
	private WebElement emailIDField;
	
	@FindBy(id = "u_0_0")
	private WebElement nextButton;
	
	@FindBy(id = "pass")
	private WebElement passwordField;
	
	@FindBy(css = "div.ql-editor")
	private WebElement commentBox;
	
	@FindBy(id = "Fill-4")
	private WebElement enterButton;
	
	@FindBy(css = "div.comment-body")
	private List<WebElement> commentsList; 

	public WidgetPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public void switchToIFrame()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    WebElement iframe = driver.findElement(By.cssSelector(".metype-iframe"));
		driver.switchTo().frame(iframe);
	}
	public void clickLoginButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, loginButton);
		loginButton.click();
	}
	public void clickGooglePlusLink()
	{
		WaitForElement.waitForElementToBeVisible(driver, googlePlusLink);
		googlePlusLink.click();
	}
	
	public void switchToNewWindow()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Store the current window handle
		winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window

		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	}
	
	public void enterEmailID(String email)
	{
		WaitForElement.waitForElementToBeVisible(driver, emailIDField);
		emailIDField.sendKeys(email);
	}
	
	public void clickNextButton()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WaitForElement.waitForElementToBeVisible(driver, nextButton);
		nextButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enterPassword(String password)
	{
		WaitForElement.waitForElementToBeVisible(driver, passwordField);
		passwordField.sendKeys(password);
	}
	// Switch back to original browser (first window)
	public void switchToOldWindow()
	{
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		driver.switchTo().window(winHandleBefore);
		
		driver.switchTo().frame(0);
	}
	
	public void enterComment(String comment)
	{
		WaitForElement.waitForElementToBeVisible(driver, commentBox);
		commentBox.sendKeys(comment);
	}
	
	 
	public void clickEnterButton()
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 WebElement enterButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("Fill-4")));
		// WaitForElement.waitForElementToBeVisible(driver, enterButton);
		 enterButton.click();	
				
	}
	
	public List<WebElement> getListOfComments()
	{
		/*try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		WaitForElement.waitForElementToBeVisible(driver, commentsList.get(0));
		return commentsList;
	}
	
	
}
