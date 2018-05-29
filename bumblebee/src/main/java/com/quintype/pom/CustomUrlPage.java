package com.quintype.pom;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.CheckAlert;
import com.quintype.util.WaitForElement;
import org.openqa.selenium.support.ui.Select;

public class CustomUrlPage {
    private WebDriver driver;
    private Actions actions;

    @FindBy(linkText = "Custom URLs")
    private WebElement addCustomUrlLink;

    @FindBy(css = "button[class= 'add-url']")
    private WebElement addUrlLink;

    @FindBy(css = "#source-path")
    private WebElement sourcePathField;

    @FindBy(css = ".Select-placeholder")
    private List<WebElement> type;

    @FindBy(css = ".Select-control")
    private List<WebElement> dropdownValue;


    @FindBy(css = ".form-element:nth-child(2)")
    private WebElement redirect;

    @FindBy(css = "input[type='submit']")
    private WebElement submit;

    public CustomUrlPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCustomUrlLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, addCustomUrlLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", addCustomUrlLink);
    }

    public void clickAddUrlLink() 		// method to click on addURL Button
    {
        WaitForElement.waitForElementToBeClickable(driver, addUrlLink);
        addUrlLink.click();
    }

    public void enterSourceUrl(String sourcepath) 		// method to click on addURL Button
    {
        WaitForElement.waitForElementToBeClickable(driver, sourcePathField);
        sourcePathField.click();
        sourcePathField.sendKeys(sourcepath);

    }

    public void selectTypeDropdown() 		// method to select from dropdown
    {
        WaitForElement.waitForElementToBeClickable(driver, type.get(0));
        type.get(0).click();

    }

    public void selectRedirectDropdown() 		// method to select from dropdown
    {
        WaitForElement.waitForElementToBeClickable(driver, type.get(1));
        type.get(1).click();
    }
    public void selectRedirect()
    {
        try {		// Without this wait script sometimes doesn't fill value in values drop down field.
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        actions = new Actions(driver);

        WaitForElement.waitForElementToBeVisible(driver, dropdownValue);
        WebElement firstValue= dropdownValue.get(0);
        actions.moveToElement(firstValue).click(firstValue).build().perform();

        //JavascriptExecutor executor =( (JavascriptExecutor)driver);
        //executor.executeScript("arguments[0].click();", dropdownValue.get(0));
        //dropdownValue.get(0).click();
    }
    public void selectType()
    {
        WaitForElement.waitForElementToBeVisible(driver, dropdownValue);
        WebElement firstValue= dropdownValue.get(0);
        actions.moveToElement(firstValue).click(firstValue).build().perform();
    }


    public void clickSubmitButton() 		// method to click on Submit button
    {
        WaitForElement.waitForElementToBeClickable(driver, submit);
        submit.click();
    }


}
