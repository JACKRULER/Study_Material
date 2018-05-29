package com.quintype.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.CheckAlert;
import com.quintype.util.ScrollPage;
import com.quintype.util.WaitForElement;

public class AdminSettingsSectionsPage {

	private WebDriver driver;
	private Actions actions;
	
	@FindBy(css = "#container a[aria-controls='sections']")
	private WebElement sectionsLink;
	
	@FindBy(css = "#sections button")
	private WebElement addSectionButton;
	
	@FindBy(css = "#sections input[name='section-name']")
	private WebElement nameField;
	
	@FindBy(css = "#sections input[name='section-display-name']")
	private WebElement displayNameField;
	
	@FindBy(css = "#sections select")
	private WebElement selectParentSectionField;
	
	@FindBy(css = "#sections .save-button")
	private WebElement saveButton;
	
	@FindBy(css = "#sections a[href='#']")
	private List<WebElement> listOfSections;
	
	@FindBy(css = "#sections .errors li")
	private List<WebElement> listOfErrorMessages;
	
	@FindBy(css = "#sections .deleted a[href='#']")
	private List<WebElement> listOfDisabledSections;
	
	@FindBy(css = "#sections .close")
	private WebElement closeButton;
	
	
	public AdminSettingsSectionsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickSectionsLink()
	{
		ScrollPage.scrollUP(driver);
		WaitForElement.waitForElementToBeClickable(driver, sectionsLink);
		sectionsLink.click();
	}
	public boolean verifyAddSectionButton()
	{
		try{
			return addSectionButton.isDisplayed();
		}catch(Exception e)
		{
			return false;
		}
	}
	public void clickAddSectionButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, addSectionButton);
		addSectionButton.click();
	}
	public void enterSectionName(String name)
	{
		WaitForElement.waitForElementToBeVisible(driver, nameField);
		nameField.clear();
		nameField.sendKeys(name);
	}
	public void enterDisplayName(String displayName)
	{
		WaitForElement.waitForElementToBeVisible(driver, displayNameField);
		displayNameField.clear();
		displayNameField.sendKeys(displayName);
	}
	public void selectParentSection(String parentSection)
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, selectParentSectionField);
		actions.moveToElement(selectParentSectionField).click().sendKeys(parentSection).sendKeys(Keys.ENTER).build().perform();
	}
	public void clickSaveButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, saveButton);
		saveButton.click();
	}
	public List<WebElement> getListOfSections(String sectionName)
	{
		WaitForElement.waitForElementToBeMatch(driver, listOfSections, sectionName);
//		WaitForElement.waitForElementToBeVisible(driver, listOfSections);
		return listOfSections;
	}
	public List<WebElement> getListOfErrorMessages()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfErrorMessages);
		return listOfErrorMessages;
	}
	public void clickCloseButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, closeButton);
		closeButton.click();
	}
	public void clickDisableButton(String sectionName)
	{
//		actions = new Actions(driver);
//		// Here we are mapping disable button the section name. So using xpath to use text() method.
////		WebElement disableButton = driver.findElement(By.linkText(sectionName)).findElement(By.xpath("../..")).findElement(By.cssSelector(".disable"));
//		WebElement disableButton = driver.findElement(By.xpath("//a[text()='"+sectionName+"']/../..//a[@class='icon small disable']"));
//		WaitForElement.waitForElementToBeClickable(driver, disableButton);
//		actions.moveToElement(disableButton, 0, 0).click().build().perform();
//		CheckAlert.verifyAlert(driver);
		// Here we are mapping disable button with the section name. So using xpath to use text() method.
		WebElement disableButton = driver.findElement(By.xpath("//a[text()='"+sectionName+"']/../..//a[@class='icon small disable']"));
		WaitForElement.waitForElementToBeClickable(driver, disableButton);
		disableButton.click();
		CheckAlert.verifyAcceptAlert(driver);
	}
	public void clickSectionName(String sectionName)
	{
		WebElement section = driver.findElement(By.xpath("//a[text()='"+sectionName+"']"));
		WaitForElement.waitForElementToBeClickable(driver, section);
		section.click();
	}
	public List<WebElement> getListOfDisabledSections()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfDisabledSections);
		return listOfDisabledSections;
	}

	public void createSection(String sectionName)
	{
		clickSectionsLink();
		clickAddSectionButton();
		enterSectionName(sectionName);
		enterDisplayName(sectionName);
		clickSaveButton();
	}

	public void createChildSection(String parentSectionName, String childSectionName)
	{
		clickSectionsLink();		
		clickAddSectionButton();
		enterSectionName(childSectionName);
		enterDisplayName(childSectionName);
		selectParentSection(parentSectionName);
		clickSaveButton();
	}
	
}
