package com.quintype.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class CollectionPagesPage
{
	private WebDriver driver;

    @FindBy(css = "div.search-container .search")
    private WebElement searchField;

    @FindBy(css = "#story-collection-pages .list-items a")
    private List<WebElement> listOfCollections;
    
	public CollectionPagesPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    public boolean verifySearchField()
    {
        try{
        //    WaitForElement.waitForElementToBeVisible(driver, searchField, className, "Search Field");
            return searchField.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
    public List<WebElement> getListOfCollectios()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfCollections);
    	return listOfCollections;
    }
}
