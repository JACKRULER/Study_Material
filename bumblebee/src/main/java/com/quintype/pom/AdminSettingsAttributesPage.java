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
import com.quintype.util.WaitForElement;

public class AdminSettingsAttributesPage {
    
    private WebDriver driver;
    private Actions actions;
    
	@FindBy(css = ".admin-container a[aria-controls='attributes']")
	private WebElement attributesLink;
	
    @FindBy(css = "#attributes button")
    private WebElement addAttributeButton; 
    
    @FindBy(css = "#attributes .radio")
    private List<WebElement> radioButtonList;
    
    @FindBy(css =".radio input[type = 'radio'][value='story']")
    private WebElement storyRadioButton;
    
    @FindBy(css = ".radio input[type = 'radio'][value='card']")
    private WebElement cardRadioButton;
    
    @FindBy(css = "#attributes [type='text']")
    private WebElement attributeNameField;
    
    @FindBy(css="#attributes [class='Select-control']")
    private List <WebElement> typeValuesDropDown;

    @FindBy(css="#attributes [class='Select-item-label']")
    private List <WebElement> attributeValue;

    @FindBy(css= "#attributes a.save-button")
    private WebElement saveAttributeButton;
    
    @FindBy(css = "#attributes a[href='#']")
    private List<WebElement> attributeNames;
    
    @FindBy(css = ".Select-item-icon")
    private WebElement crossIcon;
    
    @FindBy(css = "#attributes .errors > li")
    private List<WebElement> listOfErrorMessages;
    
    @FindBy(css = "#attributes .close")
    private WebElement closeButton;
    
    @FindBy(css = "#attributes .stack-list>li")
    private WebElement attributesStackList;
    
    public AdminSettingsAttributesPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    
    public void clickAttributesLink()
    {
    	WaitForElement.waitForElementToBeClickable(driver, attributesLink);
    	attributesLink.click();
    }
    public boolean verifyAddAttributeButton()
    {
        try{
            return addAttributeButton.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
    public void clickAddAttributeButton()
    {
    	WaitForElement.waitForElementToBeClickable(driver, addAttributeButton);
    	addAttributeButton.click();
    }
    public void selectStoryTypeRadioButton()
    {
    	WaitForElement.waitForElementToBeClickable(driver, radioButtonList.get(0));
    	radioButtonList.get(0).click();
    }
    public void selectCardTypeRadioButton()
    {
    	WaitForElement.waitForElementToBeClickable(driver, radioButtonList.get(1));
    	radioButtonList.get(1).click();
    }
    public void selectSingleLevelOfEntity()
    {
    	WaitForElement.waitForElementToBeClickable(driver, radioButtonList.get(2));
    	radioButtonList.get(2).click();
    }
    public void selectMultiLevelOfEntity()
    {
    	WaitForElement.waitForElementToBeClickable(driver, radioButtonList.get(3));
    	radioButtonList.get(3).click();
    }
    public void enterAttributeName(String attributeName)
    {
        WaitForElement.waitForElementToBeClickable(driver, attributeNameField);
        attributeNameField.clear();
        attributeNameField.sendKeys(attributeName);
    }
    public void selectTypeDropDown(String type)
    {
        actions = new Actions(driver);
        WaitForElement.waitForElementToBeVisible(driver, typeValuesDropDown);
        WebElement typedropDown = typeValuesDropDown.get(0);
        actions.moveToElement(typedropDown).click().sendKeys(type).sendKeys(Keys.ENTER).build().perform();
    }
    public void selectValueDropDown(String values)
    {
        actions = new Actions(driver);
        WaitForElement.waitForElementToBeVisible(driver, typeValuesDropDown);
        WebElement valuesDropDown = typeValuesDropDown.get(1);
        WaitForElement.waitForElementToBeClickable(driver, valuesDropDown);
        actions.moveToElement(valuesDropDown).click().build().perform();
//        try {		// Without this wait script sometimes doesn't fill value in values drop down field.
//			Thread.sleep(700);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        actions.moveToElement(valuesDropDown).sendKeys(values).sendKeys(Keys.ENTER).build().perform();
        WaitForElement.waitForElementToBeVisible(driver, crossIcon);
    }
    public String getValues()
    {
        actions = new Actions(driver);
        WaitForElement.waitForElementToBeVisible(driver, typeValuesDropDown);
        WebElement valuesDropDown = typeValuesDropDown.get(1);
        WaitForElement.waitForElementToBeClickable(driver, valuesDropDown);
        actions.moveToElement(valuesDropDown).click().build().perform();
//        try {		// Without this wait script sometimes doesn't fill value in values drop down field.
//			Thread.sleep(700);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

        WaitForElement.waitForElementToBeVisible(driver, crossIcon);
        return attributeValue.get(0).getText();

    }
    public void removeValuesFromValuesDropDown()
    {
        actions = new Actions(driver);
        WebElement valuesDropDown = typeValuesDropDown.get(1);
        WaitForElement.waitForElementToBeVisible(driver, valuesDropDown);
        actions.moveToElement(valuesDropDown).click().sendKeys(Keys.BACK_SPACE).build().perform();
    }
    public void clickSaveAttributeButton()
    {
        WaitForElement.waitForElementToBeClickable(driver, saveAttributeButton);
        saveAttributeButton.click();
    }
    public List<WebElement> getAttributesName()
    {
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        WaitForElement.waitForElementToBeVisible(driver, attributeNames);
        return attributeNames;
        
    }
    public boolean isAttributesStackListEmpty()
    {
    	try{
    		Thread.sleep(3000);
    		attributesStackList.isDisplayed();
    		return false;
    	}catch (Exception e) {
    		return true;
		}
    }
    public boolean getAttributeTypeStatus(String attributeName)
    {
    	try{
    		driver.findElement(By.linkText(attributeName)).findElement(By.xpath("../..")).findElement(By.cssSelector(".attribute-type"));
    		return true;
    	}catch (Exception e) {
    		return false;
		}
    }
    public String getAttributeValues(String attributeName)
    {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	WebElement attributeValues = driver.findElement(By.linkText(attributeName)).findElement(By.xpath("../../..//section/p"));
    	WaitForElement.waitForElementToBeVisible(driver, attributeValues);
    	return attributeValues.getText();
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
    public void clickAttributeLink(String attributeName)
    {
    	WebElement attributeLink = driver.findElement(By.linkText(attributeName));
    	WaitForElement.waitForElementToBeClickable(driver, attributeLink);
    	attributeLink.click();
    }
    public void clickDeleteAttributeButton(String attributeName)
    {
    	WebElement deleteButton = driver.findElement(By.linkText(attributeName)).findElement(By.xpath("../..")).findElement(By.cssSelector(".trash"));
    	WaitForElement.waitForElementToBeClickable(driver, deleteButton);
    	deleteButton.click();
    	CheckAlert.verifyAcceptAlert(driver);
    }

}