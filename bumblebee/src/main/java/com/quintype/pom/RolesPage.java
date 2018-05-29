package com.quintype.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class RolesPage {

	private WebDriver driver;
	
	@FindBy(css = "#admin-page [data-tab='Roles']")
	private WebElement rolesLink;
	
	@FindBy(id = "add-new-role")
	private List<WebElement> addNewRoleButton;
	
	@FindBy(css = "#roles .role > h3")
	private List<WebElement> listOfRolesName;
	
	@FindBy(id = "add-role__name")
	private WebElement roleNameField;
	
	@FindBy(css = "div.add-role__parent__select > div")
	private WebElement parentRoleDropDown;
	
	@FindBy(css = "div.add-role__author-story > .radio")
	private WebElement roleAuthorAStoryCheckBox;
	
	@FindBy(css = ".add-role__access__actions label[for='add-role__action-1']")
	private List<WebElement> listOfRolesCheckbox;
	
	@FindBy(css = ".add-role__access__actions label[for='add-role__action-1'].is-selected")
	private List<WebElement> listOfSelectedRolesCheckbox;
	
	@FindBy(css = ".add-role__save")
	private WebElement saveRoleButton;
	
	@FindBy(css = "span[class='add-role__error']")
	private List<WebElement> listOfMandatoryErrorMsg;
	
	@FindBy(css = ".add-role__access__category")
	private List<WebElement> listOfRoleAccessCategory;
	
	@FindBy(css = ".add-role__parent__select div[class='Select-placeholder']")
	private WebElement selectedParentRole;
	
	@FindBy(css = ".close")
	private WebElement closeButton;
	
	
	public RolesPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickRolesTab()
	{
		WaitForElement.waitForElementToBeClickable(driver, rolesLink);
		rolesLink.click();
	}
	public void clickAddNewRoleButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, addNewRoleButton.get(0));
		addNewRoleButton.get(0).click();
	}
	public List<WebElement> getListOfAddNewRoleButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, addNewRoleButton.get(0));
		return addNewRoleButton;
	}
	public List<WebElement> getListOfRolesName(String textToBeMatch)
	{
		WaitForElement.waitForElementToBeMatch(driver, listOfRolesName, textToBeMatch);
		return listOfRolesName;
	}
	public List<WebElement> getListOfRolesName()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfRolesName);
		return listOfRolesName;
	}
	public void enterRoleName(String roleName)
	{
		WaitForElement.waitForElementToBeVisible(driver, roleNameField);
		roleNameField.sendKeys(roleName);
	}
	public void selectParentRole(String parentRole)
	{
		Actions action = new Actions(driver);
		WaitForElement.waitForElementToBeClickable(driver, parentRoleDropDown);
		action.moveToElement(parentRoleDropDown).click().sendKeys(parentRole).sendKeys(Keys.ENTER).perform();;
	}
	public List<WebElement> getListOfRoles()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfRolesCheckbox);
		return listOfRolesCheckbox;
	}
	public List<WebElement> getListOfSelectedRolesCheckbox()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfSelectedRolesCheckbox);
		return listOfSelectedRolesCheckbox;
	}
	public void clickSaveRoleButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, saveRoleButton);
		saveRoleButton.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public List<WebElement> getListOfMandatoryErrorMsg()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfMandatoryErrorMsg);
		return listOfMandatoryErrorMsg;
	}
	public void clickContentTab()
	{
		WaitForElement.waitForElementToBeClickable(driver, listOfRoleAccessCategory.get(0));
		listOfRoleAccessCategory.get(0).click();
	}
	public void clickAnalyticsTab()
	{
		WaitForElement.waitForElementToBeClickable(driver, listOfRoleAccessCategory.get(1));
		listOfRoleAccessCategory.get(1).click();		
	}
	public void clickAdminTab()
	{
		WaitForElement.waitForElementToBeClickable(driver, listOfRoleAccessCategory.get(2));
		listOfRoleAccessCategory.get(2).click();		
	}
	public List<WebElement> getListOfRoleAccessCategory()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfRoleAccessCategory);
		return listOfRoleAccessCategory;
	}
	public void clickCloseButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, closeButton);
		closeButton.click();
	}
	public void clickEditButton(String roleName)
	{
		WebElement editButton = driver.findElement(By.xpath("//h3[text()='"+roleName+"']/../..//a[@class='small icon button edit gray']"));
		WaitForElement.waitForElementToBeClickable(driver, editButton);
		editButton.click();
	}
	public void clickDeleteButton(String roleName)
	{
		WebElement editButton = driver.findElement(By.xpath("//h3[text()='"+roleName+"']/../..//a[@class='small icon button trash']"));
		WaitForElement.waitForElementToBeClickable(driver, editButton);
		Actions action = new Actions(driver);
		action.moveToElement(editButton).click().perform();
		//editButton.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public List<WebElement> getListOfSelectedActions(String roleName)
	{
		List<WebElement> listOfSelectedActions = driver.findElements(By.xpath("//h3[text()='"+roleName+"']/..//li[@class='role__permission']"));
		WaitForElement.waitForElementToBeVisible(driver, listOfSelectedActions);
		return listOfSelectedActions;
	}
	
	public WebElement getSelectedParentRoleName()
	{
		WaitForElement.waitForElementToBeClickable(driver, selectedParentRole);
		return selectedParentRole;
		
	}
}
