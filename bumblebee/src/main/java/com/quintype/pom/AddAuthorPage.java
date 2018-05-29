package com.quintype.pom;

import com.quintype.util.ScrollPage;
import com.quintype.util.WaitForElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.List;

public class AddAuthorPage {
    private WebDriver driver;
    private Actions actions;

    @FindBy(id = "author-name-input")
    private WebElement authorName;

    @FindBy(id = "author-email-input")
    private WebElement authorEmail;

    @FindBy(id = "author-communication-email-input")
    private WebElement authorCommunicationEmail;

    @FindBy(id = "author-bio-input")
    private WebElement authorBio;

    @FindBy(id = "author-adder-submit")
    private WebElement addAuthorButton;

    @FindBy(css = "#members > div")
    private WebElement memberSearchField;

    @FindBy(css = ".Select-menu-outer .Select-option")
    private WebElement searchedMember;

    @FindBy(css = ".header-indicate.is-success")
    private WebElement successBannerMessage;

    public AddAuthorPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterAuthorName(String memberName)
    {
        WaitForElement.waitForElementToBeVisible(driver, authorName);
        authorName.sendKeys(memberName);
    }

    public void enterAuthorEmail(String memberEmail)
    {
        WaitForElement.waitForElementToBeVisible(driver, authorEmail);
        authorEmail.sendKeys(memberEmail);
    }

    public void enterAuthorCommunicationEmail(String memberCommEmail)
    {
        WaitForElement.waitForElementToBeVisible(driver, authorCommunicationEmail);
        authorCommunicationEmail.sendKeys(memberCommEmail);
    }

    public void enterAuthorBio(String bioData)
    {
        WaitForElement.waitForElementToBeVisible(driver, authorBio);
        authorBio.click();
        authorBio.sendKeys(bioData);
    }

    public void clickAddAuthorButton()
    {
        WaitForElement.waitForElementToBeVisible(driver, addAuthorButton);
        addAuthorButton.click();
    }

    public String getSuccessMessage()
    {
        WaitForElement.waitForElementToBeVisible(driver, successBannerMessage);
        String successMessage = successBannerMessage.getText();
        return successMessage;
    }

    public void searchForAuthor(String memberName)
    {
        ScrollPage.scrollUP(driver);
        try{
            Thread.sleep(3000);
        }catch(Exception e){}

        Actions a = new Actions(driver);
        WaitForElement.waitForElementToBeVisible(driver, memberSearchField);
        a.moveToElement(memberSearchField).click().sendKeys(memberName).build().perform();
    }

    public void selectAuthor(String memberName)
    {
        ScrollPage.scrollUP(driver);
        try{
            Thread.sleep(3000);
        }catch(Exception e){}
        Actions a = new Actions(driver);
//		WaitForElement.waitForElementToBeVisible(driver, memberSearchField);
//		a.moveToElement(memberSearchField).click().sendKeys(memberName).build().perform();
        WaitForElement.waitForElementToBeVisible(driver, memberSearchField);
        a.moveToElement(memberSearchField).click().sendKeys(memberName).build().perform();
        WaitForElement.waitForElementToBeVisible(driver, searchedMember);
        a.moveToElement(memberSearchField).sendKeys(Keys.ENTER).build().perform();
    }

    public boolean isAuthorFound()
    {
        WaitForElement.waitForElementToBeVisible(driver, searchedMember);
        return searchedMember.isDisplayed();
    }
}
