package com.quintype.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.quintype.util.WaitForElement;

public class AdminSettingsStacksPage {

	private WebDriver driver;
	private Actions actions;
	
	@FindBy(css = ".admin-container a[aria-controls='stacks']")
	private WebElement stacksLink;
	
    @FindBy(css = "#stacks button")
    private WebElement addStackButton;
    
    @FindBy(name = "stack-name")
    private WebElement nameField;
    
    @FindBy(name = "stack-color")
    private WebElement colorField;
    
    @FindBy(name = "stack-max-stories")
    private WebElement maxStoriesField;
    
    @FindBy(css = "#stacks label[for='show-on-all-sections']")
    private WebElement showOnAllPagesCheckBox;
    
    @FindBy(css = "#stacks .save-button")
    private WebElement saveStackButton;
    
    @FindBy(css = "#stacks .Select-control")
    private WebElement showOnlyOnPagesField;
    
    @FindBy(css = "#stacks .stack-list a[href='#']")
    private List<WebElement> listOfStacks;
    
    @FindBy(css = "#stacks .errors li")
    private List<WebElement> listOfErrorMessages;

    @FindBy(css = "#stacks .up")
    private List<WebElement> listOfUpArrowLink;
    
    @FindBy(css = "#stacks .down")
    private List<WebElement> listOfDownArrowLink;
    
    
    @FindBy(css = "#stacks .close")
    private WebElement crossButton;
	
	public AdminSettingsStacksPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public boolean isStackLinkActive()
	{
		WaitForElement.waitForElementToBeClickable(driver, stacksLink);
		return stacksLink.isDisplayed();
	}
	public boolean verifyAddStackButton()
    {
        try{
            return addStackButton.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
	public void clickAddStackButton()
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeClickable(driver, addStackButton);
		actions.moveToElement(addStackButton, 0, 0).click().build().perform();
	}
	public void clickStacksLink()
	{
		WaitForElement.waitForElementToBeClickable(driver, stacksLink);
		stacksLink.click();
	}
	public void enterName(String name)
	{
		WaitForElement.waitForElementToBeVisible(driver, nameField);
		nameField.clear();
		nameField.sendKeys(name);
	}
	public void clearColorField()
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, colorField);
		actions.moveToElement(colorField).click().sendKeys(Keys.CONTROL, "a").sendKeys(Keys.DELETE).keyUp(Keys.LEFT_CONTROL).build().perform();
	}
	public void enterColor(String color)
	{
		WaitForElement.waitForElementToBeVisible(driver, colorField);
		colorField.clear();
		colorField.sendKeys(color);
	}
	public void enterMaxStories(String maxStoriesCount)
	{
		WaitForElement.waitForElementToBeVisible(driver, maxStoriesField);
		maxStoriesField.clear();
		maxStoriesField.sendKeys(maxStoriesCount);
	}
	public void clearMaxStoriesField()
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, maxStoriesField);
		actions.moveToElement(maxStoriesField).click().sendKeys(Keys.CONTROL, "a").sendKeys(Keys.DELETE).keyUp(Keys.LEFT_CONTROL).build().perform();
	}
	public String checkDefaultMaxStories()
	{
		WaitForElement.waitForElementToBeVisible(driver, maxStoriesField);
		return maxStoriesField.getAttribute("value");
	}
	public void clickShowOnAllPagesCheckBox()
	{
		WaitForElement.waitForElementToBeClickable(driver, showOnAllPagesCheckBox);
		showOnAllPagesCheckBox.click();
	}
	public void selectShowOnlyOnPages(String pageName)
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, showOnlyOnPagesField);
		actions.moveToElement(showOnlyOnPagesField).click().sendKeys(pageName).sendKeys(Keys.ENTER).build().perform();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#stacks .Select-item-icon")));
	}
	public void clickSaveStackButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, saveStackButton);
		saveStackButton.click();
	}
	public List<WebElement> getListOfStacks(String createdStackName)
	{
		WaitForElement.waitForElementToBeMatch(driver, listOfStacks, createdStackName);
		return listOfStacks;
	}
	public List<WebElement> getListOfStacks()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfStacks);
		return listOfStacks;
	}
	public List<WebElement> getListOfErrorMessages()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfErrorMessages);
		return listOfErrorMessages;
	}
	public void clickCrossButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, crossButton);
		crossButton.click();
	}
	public void clickStackNameLink(String stackName)
	{
		// Here we are clicking on the stack based on the stack name.
		WebElement stackLink = driver.findElement(By.xpath("//a[text()='"+stackName+"']"));
		WaitForElement.waitForElementToBeClickable(driver, stackLink);
		stackLink.click();
	}
	public void clickDisableButton(String stackName)
	{	
		// Here we are mapping disable button with the stack name. So using xpath to use text() method.
		WebElement disableButton = driver.findElement(By.xpath("//a[text()='"+stackName+"']/../..//a[@class='icon small disable']"));
		WaitForElement.waitForElementToBeClickable(driver, disableButton);
		disableButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public List<WebElement> listOfStacksOnMenu(String containsString)
	{
		List<WebElement> listOfStacks = driver.findElements(By.xpath("//ul[@class='subnav']/..//a[contains(text(),'Stack')]"));
		WaitForElement.waitForElementToBeVisible(driver, listOfStacks);
		return listOfStacks;
	}
	public String getStoriesPerStackValue(String stackName)
	{
//		WebElement link = driver.findElement(By.linkText(linkText));
//		WebElement parentElement = link.findElement(By.xpath("../../.."));
//		List<WebElement> elements = parentElement.findElements(By.cssSelector("section span"));
//		System.out.println("Stories Per Stack: "+elements.get(1).getText());
		List<WebElement> storiesPerStack = driver.findElements(By.xpath("//a[text()='"+stackName+"']/../../..//section//span"));
//		System.out.println(storiesPerStack.get(0).getText());
		return storiesPerStack.get(0).getText();
	}
	public String getStackShowInValue(String stackName)
	{
//		WebElement link = driver.findElement(By.linkText(linkText));
//		WebElement parentElement = link.findElement(By.xpath("../../.."));
//		List<WebElement> elements = parentElement.findElements(By.cssSelector("section span"));
//		System.out.println("Stories Per Stack: "+elements.get(1).getText());
		List<WebElement> storiesPerStack = driver.findElements(By.xpath("//a[text()='"+stackName+"']/../../..//section//span"));
//		System.out.println(storiesPerStack.get(0).getText());
		return storiesPerStack.get(1).getText();
	}
	public List<WebElement> getListOfUpArrowLink()
	{	
		WaitForElement.waitForElementToBeVisible(driver, listOfUpArrowLink);
		return listOfUpArrowLink;
	}
	public void clickUpArrowLink(int index)
	{
		WaitForElement.waitForElementToBeClickable(driver, listOfUpArrowLink.get(index));
		listOfUpArrowLink.get(index).click();
		
		try {		// Waiting for the stack list to be update.
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
