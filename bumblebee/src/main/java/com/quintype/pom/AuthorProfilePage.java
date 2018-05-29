package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.UploadImage;
import com.quintype.util.WaitForElement;

public class AuthorProfilePage 
{
    private WebDriver driver; 
    private Actions actions;

    @FindBy(css ="#author-profile input[name='name']")
    private WebElement profileNameField;
    
    @FindBy(name="twitter-handle")
    private WebElement twitterHandleField;

    @FindBy(name="communication-email")
    private WebElement communicationEmailField;

    @FindBy(css= "div.bio-field")
    private WebElement bioField;

    @FindBy(css=".image-upload input[type='file']")
    private WebElement uploadAvatarLink;

    @FindBy(css="input.author-submit")
    private WebElement saveButton;

    @FindBy(css=".remove-image")
    private WebElement removeImage;

    public AuthorProfilePage(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getMyProfileName()
    {
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        WaitForElement.waitForElementToBeVisible(driver, profileNameField);
        return profileNameField;
    }
    public void enterAuthorName(String name)
    {
    	WaitForElement.waitForElementToBeVisible(driver, profileNameField);
    	profileNameField.clear();
    	profileNameField.sendKeys(name);
    }
    public void enterTwitterHandleFieldData(String twitterHandle)
    {
        WaitForElement.waitForElementToBeVisible(driver, twitterHandleField);
        twitterHandleField.sendKeys(Keys.BACK_SPACE);
        //twitterHandleField.clear();
        twitterHandleField.sendKeys(twitterHandle);        
    }
    public void enterCommunicationEmailFieldData(String communicationEmailID)
    {
        WaitForElement.waitForElementToBeVisible(driver, communicationEmailField);
        communicationEmailField.clear();
        communicationEmailField.sendKeys(communicationEmailID);        
    }
    public WebElement getCommunicationEmailID()
    {
        WaitForElement.waitForElementToBeVisible(driver, communicationEmailField);
        communicationEmailField.clear();
        return communicationEmailField;
    }
    public void enterBioFieldData(String bioData)
    {
        WaitForElement.waitForElementToBeVisible(driver, bioField);
        bioField.clear();
        bioField.click();
        bioField.sendKeys(bioData);
    }
    public void clickRemoveImageButton()
    {
		WaitForElement.waitForElementToBeClickable(driver, removeImage);
    	removeImage.click();
    }
    public boolean getImageStatus()
    {
    	try{
        	return removeImage.isDisplayed();
    	}catch(Exception e)
    	{
    		return false;
    	}   	
    }
    public void uploadProfileImage(String imageName)
    {
        UploadImage.uploadImage(uploadAvatarLink, imageName);
        WaitForElement.waitForElementToBeVisible(driver, removeImage);  
    }
    public void clickSaveButton()
    {
        WaitForElement.waitForElementToBeVisible(driver, saveButton);
        saveButton.click();
    }

    public void clearAuthorName()
    {
    	WaitForElement.waitForElementToBeVisible(driver, profileNameField);
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        profileNameField.clear();
//    	profileNameField.sendKeys(Keys.CONTROL + "a");
//      profileNameField.sendKeys(Keys.chord(Keys.DELETE));
    }
    
}