package com.quintype.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.CheckAlert;
import com.quintype.util.CurrentDate;
import com.quintype.util.WaitForElement;

import java.util.List;

public class BreakingNewsStoryPage 
{
	private WebDriver driver;           
    private String storyTitle;

    @FindBy(css = "div.story-element .Select-control")                        
    private List<WebElement> newsTypeField;

    @FindBy(css = "div.story-element  div[role='textbox']")                        
    private WebElement titleField;
    
    @FindBy(css = ".story-element.show-clear div[class='Select-control']")
    private WebElement linkedStoryField;
    
    @FindBy(css = ".Select-control > div[class='Select-placeholder']")
    private WebElement selectedLinkedStory;
    
    @FindBy(id = "breaking-news-discard-link")
    private WebElement discardButton;

    @FindBy(css = "a[title='Publish']")                        
    private WebElement publishButton;

    @FindBy(xpath = "//input[@id='associated-story-check']/parent::*")
    private WebElement associatedStoryCheckBox;

    @FindBy(xpath = "//input[@id='associated-story-advance-check']/parent::*")
    private WebElement showAdvancedFieldsCheckBox;

    @FindBy(css = ".public-DraftEditor-content")
    private WebElement contentField;

    public BreakingNewsStoryPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectNewsType(String newsType)                             // code to select News type data
    {
        WaitForElement.waitForElementToBeVisible(driver, newsTypeField.get(0));
        newsTypeField.get(0).click();

        //Select the Value defined in Data property files.
        Actions a = new Actions(driver);
        a.moveToElement(newsTypeField.get(0)).click().sendKeys(newsType).sendKeys(Keys.ENTER).build().perform();
    }
    public void enterBreakingNewsTitle(String title)
    {
    	WaitForElement.waitForElementToBeClickable(driver, titleField);
		storyTitle = (title+" : "+CurrentDate.getCurrentDateAndTime());
		titleField.sendKeys(storyTitle);
    }
    public String getBreakingNewsTitle()
    {
    	return storyTitle;
    }
    public void selectLinkedStory(String linkedStoryTitle)
    {
    	WaitForElement.waitForElementToBeClickable(driver, linkedStoryField);
    	Actions actions = new Actions(driver);
    	actions.moveToElement(linkedStoryField).click().sendKeys(linkedStoryTitle).build().perform();
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	actions.moveToElement(linkedStoryField).sendKeys(Keys.ENTER).build().perform();
    }
    public String getLinkedStoryTitle()
    {
    	WaitForElement.waitForElementToBeClickable(driver, selectedLinkedStory);
    	return selectedLinkedStory.getText();
    }
    public void clickDiscardButton()
    {
    	Actions a = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, discardButton);
		a.moveToElement(discardButton).click().build().perform();
    }
    public boolean clickPublishButton()
	{
		Actions a = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, publishButton);
		a.moveToElement(publishButton).click().build().perform();
		return CheckAlert.verifyAcceptAlert(driver);
	}

    public void clickAssociatedStoryCheckBox()
    {
        //List<WebElement> linklist = driver.findElements(By.cssSelector("input[type=checkbox]"));
        Actions a = new Actions(driver);
        WaitForElement.waitForElementToBeVisible(driver, associatedStoryCheckBox);
        a.moveToElement(associatedStoryCheckBox).click().build().perform();

        /*WaitForElement.waitForElementToBeClickable(driver , associatedStoryCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", associatedStoryCheckBox);*/
        //System.out.println(status);
    }

    public void clickAssociatedFieldsCheckBox()
    {
        WaitForElement.waitForElementToBeClickable(driver , showAdvancedFieldsCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", showAdvancedFieldsCheckBox);
        //WaitForElement.waitForElementToBeVisible(driver, associatedStoryCheckBox);
        //associatedStoryCheckBox.click();*/



    }

    public String getContentData()
    {
        WaitForElement.waitForElementToBeVisible(driver, contentField);
        return contentField.getText();
    }

    public void enterContent(String content)
    {
        WaitForElement.waitForElementToBeClickable(driver,contentField);
        contentField.clear();
        contentField.sendKeys(content);
    }
}