package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class APIManagerPage {

	private WebDriver driver;
	
	@FindBy(css = ".table-button")
	private WebElement addEndPointButton;
	
	public APIManagerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyAddEndPointButton()
	{
//		try{
			WaitForElement.waitForElementToBeVisible(driver, addEndPointButton);
            return addEndPointButton.isDisplayed();
//        }catch(Exception e)
//        {
//            return false;
//        }
	}
}
