package com.quintype.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class AnalyticsPage
{
	private WebDriver driver;

    @FindBy(css = ".analytics-filters .Select-placeholder")
    private List<WebElement> selectionDropDown;
    
    @FindBy(css = ".analytics-container .spinner")
    private List<WebElement> analyticsSpinner;

	public AnalyticsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    public boolean verifyAnalyticsFilterDisplayed()
    {
        try{
        	WaitForElement.waitForElementToInvisible(driver, analyticsSpinner);
        	selectionDropDown.get(0).click();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
   
}
