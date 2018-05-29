package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class MediaLibraryPage {

	private WebDriver driver;
	
	@FindBy(css = ".media-library-title")
	private WebElement mediaLibraryTitle;
	
	public MediaLibraryPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyMediaLibraryTitle()
	{
//		try{
			WaitForElement.waitForElementToBeVisible(driver, mediaLibraryTitle);
            return mediaLibraryTitle.isDisplayed();
//        }catch(Exception e)
//        {
//            return false;
//        }
	}
}
