package com.quintype.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.quintype.util.ScrollPage;
import com.quintype.util.WaitForElement;

public class AdminSettingsMenu {

	private WebDriver driver;
	private Actions actions;
	
	@FindBy(css = ".admin-container a[aria-controls='menu']")
	private WebElement menuLink;
	
	@FindBy(css = "#menu button")
	private WebElement addMenuItemButton;
	
	@FindBy(name = "item-title")
	private WebElement nameField;
	
	@FindBy(name = "item-color")
	private WebElement colorField;
	
	@FindBy(css = "#menu label[for='type-section']")
	private WebElement sectionItemType;
	
	@FindBy(css = "#menu label[for='type-tag']")
	private WebElement tagItemType;
	
	@FindBy(css = "#menu label[for='type-link']")
	private WebElement urlItemType;
	
	@FindBy(css = "#menu select")
	private List<WebElement> selectDropDowns;
	
	@FindBy(css = "#menu div[class='Select-control']")
	private WebElement tagDropDown;
	
	@FindBy(name = "item-link")
	private WebElement urlField;
	
	@FindBy(css = "#menu .save-button")
	private WebElement saveItemButton;
	
	@FindBy(css = "#menu a[href='#']")
	private List<WebElement> listOfMenuItems;
	
	@FindBy(css = "#menu .errors li")
	private List<WebElement> listOfErrorMessages;
	
	@FindBy(css = "#menu .close")
	private WebElement closeButton;
	
    @FindBy(css = "#menu .up")
    private List<WebElement> listOfUpArrowLink;
    
    @FindBy(css = "#menu .down")
    private List<WebElement> listOfDownArrowLink;
	
	public AdminSettingsMenu(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickMenuLink()
	{
		ScrollPage.scrollUP(driver);
		WaitForElement.waitForElementToBeClickable(driver, menuLink);
		menuLink.click();
	}
	public void clickAddMenuItemButton()
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeClickable(driver, addMenuItemButton);
		actions.moveToElement(addMenuItemButton, 0, 0).click().build().perform();
	}
    public boolean verifyAddMenuItemButton()
    {
        try{
            return addMenuItemButton.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
	public void enterName(String name)
	{
		WaitForElement.waitForElementToBeVisible(driver, nameField);
		nameField.click();
		nameField.clear();
		nameField.sendKeys(name);
	}
	public void enterColor(String color)
	{
		WaitForElement.waitForElementToBeVisible(driver, colorField);
		colorField.click();
		colorField.clear();
		colorField.sendKeys(color);
	}
	public void clearColorField()
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, colorField);
		actions.moveToElement(colorField).click().sendKeys(Keys.CONTROL, "a").sendKeys(Keys.DELETE).keyUp(Keys.LEFT_CONTROL).build().perform();
	}
	public void selectParentMenuItem(String parentMenu)
	{
		WaitForElement.waitForElementToBeVisible(driver, selectDropDowns.get(0));
		Select selectEntityType = new Select(selectDropDowns.get(0));
		selectEntityType.selectByVisibleText(parentMenu);
	}
	public void selectSectionType(String name)
	{
		WaitForElement.waitForElementToBeVisible(driver, selectDropDowns.get(1));
		Select selectEntityType = new Select(selectDropDowns.get(1));
		selectEntityType.selectByVisibleText(name);
	}
	public void selectTagType(String tagName)
	{
		Actions action = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, tagDropDown);
		action.moveToElement(tagDropDown).click().sendKeys(tagName).sendKeys(Keys.ENTER).build().perform();	
	}
	public void enterURL(String url)
	{
		WaitForElement.waitForElementToBeVisible(driver, urlField);
		urlField.sendKeys(url);
	}
	public void clickSectionItemType()
	{
		WaitForElement.waitForElementToBeClickable(driver, sectionItemType);
		sectionItemType.click();
	}
	public void clickTagItemType()
	{
		WaitForElement.waitForElementToBeClickable(driver, tagItemType);
		tagItemType.click();
	}
	public void clickURLItemType()
	{
		WaitForElement.waitForElementToBeClickable(driver, urlItemType);
		urlItemType.click();
	}
	public void clickSaveItemButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, saveItemButton);
		saveItemButton.click();
	}
	public List<WebElement> getListOfMenuItems(String menuName)
	{
		WaitForElement.waitForElementToBeMatch(driver, listOfMenuItems, menuName);
		return listOfMenuItems;
	}
	public List<WebElement> getListOfMenuItems()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfMenuItems);
		return listOfMenuItems;
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
	public WebElement getTypeOfMenu(String menuName)
	{
		WebElement typeOfMenu = driver.findElement(By.linkText(menuName)).findElement(By.xpath("../../..")).findElement(By.tagName("p"));
		return typeOfMenu;
	}
	public void clickDeleteButton(String menuName)
	{
		WebElement deleteButton = driver.findElement(By.linkText(menuName)).findElement(By.xpath("../../..")).findElement(By.cssSelector(".disable"));
		WaitForElement.waitForElementToBeClickable(driver, deleteButton);
		deleteButton.click();
	}
	public void clickMenuItem(String menuName)
	{
		WebElement menuItemLink = driver.findElement(By.linkText(menuName));
		WaitForElement.waitForElementToBeClickable(driver, menuItemLink);
		menuItemLink.click();
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
