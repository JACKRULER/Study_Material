package com.quintype.pom;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.quintype.util.WaitForElement;

public class PagesPage {

	private WebDriver driver;

	@FindBy(css=".list-items a")
	private List<WebElement> collectionList;

	public PagesPage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public String getNameOfFirstCollection() {


		WaitForElement.fluentWaitForElement(driver, collectionList.get(0));
		
		WaitForElement.waitForElementToBeVisible(driver, collectionList);
		//  The collection page is having a defect. The list loads with certain order intially and sort after 2-3 seconds.
		// Adding an explicit wait until this defect is fixed.
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return collectionList.get(0).getText();
	}

	public void modifyLatestCollectionPage(){
		WaitForElement.waitForElementToBeVisible(driver, collectionList);
		collectionList.get(0).click();

	}


}
