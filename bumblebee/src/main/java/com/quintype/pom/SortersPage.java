package com.quintype.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class SortersPage {
	
	private WebDriver driver;

    @FindBy(css = "div.headline-search-form input")
    private WebElement storiesSearchField;
    
    @FindBy(css = ".sortable span[data-glyph='star']")
    private List<WebElement> listOfStarIcon;
    
    @FindBy(css = ".top-twenty span[data-glyph='star']")
    private List<WebElement> listOfStarredIcon;
    
    @FindBy(css = ".top-twenty .sort-title")
    private List<WebElement> listOfStarredStoriesTitle;
    
    @FindBy(css = "#story-sorter .plain-text")
    private WebElement publishSortOrderButton;

	public SortersPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    public boolean verifySearchField()
    {
    	WaitForElement.waitForElementToBeVisible(driver, storiesSearchField);
    	return storiesSearchField.isDisplayed();
    }
    public void clickOnStarIcon(int i)
    {
    	WaitForElement.waitForElementToBeVisible(driver, By.cssSelector(".sortable span[data-glyph='star']"));
 //   	WaitForElement.waitForElementToBeVisible(driver, listOfStarIcon);
    	listOfStarIcon.get(i).click();
    }
    public void clickPublishSortOrderButton()
    {
    	WaitForElement.waitForElementToBeClickable(driver, publishSortOrderButton);
    	publishSortOrderButton.click();
    }
    public int getStarredIconCount()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfStarredIcon);
    	return listOfStarredIcon.size();
    }
    public List<WebElement> getListOfStarredStoriesTitle()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfStarredStoriesTitle);
    	return listOfStarredStoriesTitle;
    }
}
