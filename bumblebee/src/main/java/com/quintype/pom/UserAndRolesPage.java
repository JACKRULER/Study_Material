package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.ScrollPage;
import com.quintype.util.WaitForElement;

import org.openqa.selenium.interactions.Actions;

import java.util.List;

import org.openqa.selenium.Keys;

public class UserAndRolesPage 
{
	private WebDriver driver;
	
	@FindBy(css = "#admin-page [data-tab='Members']")
	private WebElement memberTabLink;
	
	@FindBy(css = "#admin-page .is-active[data-tab='Members']")
	private WebElement memberActiveTabLink;
	
	@FindBy(css = "span.add-role__error")
	private List<WebElement> mandatoryFieldErrorMessages;
	
	@FindBy(css = ".Select-noresults")
	private WebElement memberNotFound;
	
	@FindBy(css = ".Select-menu-outer .Select-option")
	private WebElement searchedMember;
	
	@FindBy(css = "#admin-page li[data-tab] >a")
	private List<WebElement> listOfTabs;
	
	@FindBy(id = "member-name-input")
	private WebElement memberNameField;
	
	@FindBy(id = "member-email-input")
	private WebElement memberEmailField;

	@FindBy(id = "member-communication-email-input")
	private WebElement memberCommunicationEmailIDField;
	
	@FindBy(id = "member-username-input")
	private WebElement memberUserNameField;
	
	@FindBy(id= "member-password-input")
	private WebElement memberPasswordField;
	
	@FindBy(id = "member-adder-submit")
	private WebElement addMemberButton;

    @FindBy(css = "#members > div")
	private WebElement memberSearchField;

	@FindBy(id = "member-role-role-name-select")
	private WebElement addRoleToMemberField;

	@FindBy(id = "member-role-adder-submit")
	private WebElement addRoleToMemberButton;

	@FindBy(xpath = "//input[contains(@id, 'Tech Support')]")
	private WebElement techSupportCheckbox;

	@FindBy(id = "member-role-deleter-submit")
	private WebElement deleteMemberRoleButton;

	@FindBy(css = ".users-container > h5")
	private WebElement memberTitle;
	public UserAndRolesPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickMembersTab()
	{
		WaitForElement.waitForElementToBeVisible(driver, memberTabLink);
		memberTabLink.click();	
	}
	public boolean isMemberTabActive()
	{
		WaitForElement.waitForElementToBeClickable(driver, memberActiveTabLink);
		return memberActiveTabLink.isDisplayed();
	}
	public List<WebElement> getListOfMandatoryFieldsErrorMessage()
	{
		WaitForElement.waitForElementToBeVisible(driver, mandatoryFieldErrorMessages);
		return mandatoryFieldErrorMessages;
	}
	public boolean isMemberNotFound()
	{
		WaitForElement.waitForElementToBeVisible(driver, memberNotFound);
		return memberNotFound.isDisplayed();
	}
	public boolean isMemberFound()
	{
		WaitForElement.waitForElementToBeVisible(driver, searchedMember);		
		return searchedMember.isDisplayed();
	}
	public List<WebElement> getListOfTabs()
	{
		WaitForElement.waitForElementToBeVisible(driver, listOfTabs);
		return listOfTabs;
	}
	public void enterMemberName(String memberName)
	{
		WaitForElement.waitForElementToBeVisible(driver, memberNameField);
		memberNameField.sendKeys(memberName);	
	}
	public void enterMemberEmail(String memberEmail)
	{
		WaitForElement.waitForElementToBeVisible(driver, memberEmailField);
		memberEmailField.sendKeys(memberEmail);	
	}
	public void enterCommunicationEmailID(String memberCommEmail)
	{
		WaitForElement.waitForElementToBeVisible(driver, memberCommunicationEmailIDField);
		memberCommunicationEmailIDField.sendKeys(memberCommEmail);	
	}
	public void enterMemberUserName(String memberUsername)
	{
		WaitForElement.waitForElementToBeVisible(driver, memberUserNameField);
		memberUserNameField.sendKeys(memberUsername);	
	}
	public void enterMemberPassword(String memberPassword)
	{
		WaitForElement.waitForElementToBeVisible(driver, memberPasswordField);
		memberPasswordField.sendKeys(memberPassword);	
	}
	public void clickAddMemberButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, addMemberButton);
		addMemberButton.click();	
	}
	public void searchForMember(String memberName)
	{
		ScrollPage.scrollUP(driver);		
		try{
			Thread.sleep(3000);
		}catch(Exception e){}

		Actions a = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, memberSearchField);
		a.moveToElement(memberSearchField).click().sendKeys(memberName).build().perform();
	}
	public void selectMember(String memberName)
	{
		ScrollPage.scrollUP(driver);		
		try{
			Thread.sleep(3000);
		}catch(Exception e){}
		Actions a = new Actions(driver);
//		WaitForElement.waitForElementToBeVisible(driver, memberSearchField);
//		a.moveToElement(memberSearchField).click().sendKeys(memberName).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, memberSearchField);
		a.moveToElement(memberSearchField).click().sendKeys(memberName).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, searchedMember);
		a.moveToElement(memberSearchField).sendKeys(Keys.ENTER).build().perform();
	}
	public void selectRole(String memberRole)
	{
		Actions a = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, addRoleToMemberField);
		a.moveToElement(addRoleToMemberField).click().sendKeys(memberRole).sendKeys(Keys.ENTER).build().perform();
	}
	public void clickAddRoleToMemberButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, addRoleToMemberButton);		
		addRoleToMemberButton.click();
	}
	public void clickOnRoleCheckBox()
	{
		WaitForElement.waitForElementToBeVisible(driver, techSupportCheckbox);
		techSupportCheckbox.click();
	}
	public void clickDeleteMemberRole()
	{
		WaitForElement.waitForElementToBeVisible(driver, deleteMemberRoleButton);
		deleteMemberRoleButton.click();
	}
	public boolean verifyRoleIsSelected()
	{
		try{
			return techSupportCheckbox.isDisplayed();			
		}catch(Exception e)
		{
			return false;
		}			
	}
	public WebElement getMemberPageTitle()
	{
		WaitForElement.waitForElementToBeClickable(driver, memberTitle);
		return memberTitle;
	}
}