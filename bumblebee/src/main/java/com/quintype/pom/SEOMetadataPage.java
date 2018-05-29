package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SEOMetadataPage
{
	private WebDriver driver;

    @FindBy(css = ".filter-menu")
    private WebElement filterMenuField;

	public SEOMetadataPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    public boolean verifyFilterMenuField()
    {
        try{
            return filterMenuField.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
   
}
