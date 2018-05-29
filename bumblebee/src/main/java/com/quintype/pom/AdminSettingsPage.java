package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.ScrollPage;
import com.quintype.util.WaitForElement;

import org.openqa.selenium.JavascriptExecutor;

public class AdminSettingsPage
{
	private WebDriver driver;

//    @FindBy(css = ".admin-container a[aria-controls='stacks']")
//    private WebElement stacksLink;

    @FindBy(css = ".admin-container a[aria-controls='settings']")
    private WebElement settingsLink;

    @FindBy(css = ".admin-container a[aria-controls='favicon']")
    private WebElement faviconLink;

//    @FindBy(css = ".admin-container a[aria-controls='sections']")
//    private WebElement sectionsLink;

//    @FindBy(css = ".admin-container a[aria-controls='menu']")
//    private WebElement menuLink;

    @FindBy(css = ".admin-container a[aria-controls='html-snippets']")
    private WebElement htmlSnippetsLink;

    @FindBy(css = ".admin-container a[aria-controls='social']")
    private WebElement socialLink;

//    @FindBy(css = ".admin-container a[aria-controls='attributes']")
//    private WebElement attributesLink;

    @FindBy(css = ".admin-container a[aria-controls='other']")
    private WebElement otherLink;  

//    @FindBy(css = "#stacks button")
//    private WebElement addStackButton;  

    @FindBy(css = "#settings input[value='Save']")
    private WebElement settingsSaveButton;                 

//    @FindBy(css = "#sections button")
//    private WebElement addSectionButton;  

//    @FindBy(css = "#menu button")
//    private WebElement addMenuItemButton;      

    @FindBy(css = "#html-snippets button")
    private WebElement addSnippetButton;        

    @FindBy(css = "#social .update-social-links")
    private WebElement socialSaveButton;

//    @FindBy(css = "#attributes button")
//    private WebElement addAttributeButton; 
    
    @FindBy(css = "#other button")   
    private WebElement otherSaveButton;

	public AdminSettingsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//    public void clickStacksLink()
//    {
//        WaitForElement.waitForElementToBeVisible(driver, stacksLink);
//        JavascriptExecutor executor =( (JavascriptExecutor)driver);
//        executor.executeScript("arguments[0].click();", stacksLink);
//    }
    public void clickSettingsLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, settingsLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", settingsLink);
    }
    public void clickFaviconLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, faviconLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", faviconLink);
    }
//    public void clickSectionsLink()
//    {
//        WaitForElement.waitForElementToBeVisible(driver, sectionsLink);
//        JavascriptExecutor executor =( (JavascriptExecutor)driver);
//        executor.executeScript("arguments[0].click();", sectionsLink);
//    }
//    public void clickMenuLink()
//    {
//        WaitForElement.waitForElementToBeVisible(driver, menuLink);
//        JavascriptExecutor executor =( (JavascriptExecutor)driver);
//        executor.executeScript("arguments[0].click();", menuLink);
//    }
    public void clickHTMLSnippetsLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, htmlSnippetsLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", htmlSnippetsLink);
    }
    public void clickSocialLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, socialLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", socialLink);
    }
//    public void clickAttributesLink()
//    {
//        WaitForElement.waitForElementToBeVisible(driver, attributesLink);
//        JavascriptExecutor executor =( (JavascriptExecutor)driver);
//        executor.executeScript("arguments[0].click();", attributesLink);
//    }
    public void clickOtherLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, otherLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", otherLink);
    }
//    public boolean verifyAddStackButton()
//    {
//        try{
//            return addStackButton.isDisplayed();
//        }catch(Exception e)
//        {
//            return false;
//        }
//    }
    public boolean verifySettingsSaveButton()
    {
        try{
            return settingsSaveButton.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
//    public boolean verifyAddSectionButton()
//    {
//        try{
//            return addSectionButton.isDisplayed();
//        }catch(Exception e)
//        {
//            return false;
//        }
//    }
//    public boolean verifyAddMenuItemButton()
//    {
//        try{
//            return addMenuItemButton.isDisplayed();
//        }catch(Exception e)
//        {
//            return false;
//        }
//    }
    public boolean verifyAddSnippetButton()
    {
        try{
            return addSnippetButton.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
    public boolean verifySocialSaveButton()
    {
        try{
            return socialSaveButton.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
//    public boolean verifyAddAttributeButton()
//    {
//        try{
//            return addAttributeButton.isDisplayed();
//        }catch(Exception e)
//        {
//            return false;
//        }
//    }
    public boolean verifyOtherSaveButton()
    {
        ScrollPage.scrollDown(driver);
        try{
            return otherSaveButton.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
   
}
