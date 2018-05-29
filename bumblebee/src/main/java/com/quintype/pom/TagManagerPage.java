package com.quintype.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

import java.util.List;

public class TagManagerPage
{
	private WebDriver driver;
    private Actions actions;

    @FindBy(css = "#tags-manager .tag-manager__title")
    private WebElement tagsLabel;
    
    @FindBy(css = "#tag-manager input[name='tag-search']")
    private WebElement tagSearchField;
    
    @FindBy(css = "#tag-manager .tag-manager-row")
    private List<WebElement> listOfTagRows;
    
    @FindBy(css = "#tag-manager .tag-manager-row__name")
    private List<WebElement> listOfTagNames;

    @FindBy(css = "#tag-manager .tag-manager-row__slug")
    private List<WebElement> listOfTagSlug;
    
    @FindBy(css = "#tag-manager .tag-manager-row__metadata")
    private List<WebElement> listOfTagMetadata;
    
    @FindBy(css = "#tag-manager .tag-manager-row__count")
    private List<WebElement> listOfTagsCount;
    
    @FindBy(css = "#tag-manager .tag-manager-row__created-on")
    private List<WebElement> listOfCreatedOnDate;
    
    @FindBy(css = "#tag-manager .tag-manager-row__created-by")
    private List<WebElement> listOfCreatedByAuthor;
    
    @FindBy(css = "#tag-manager .pagination>li>a")
    private List<WebElement> paginationLinksList;
    
    @FindBy(id = "tag-meta-description")
    private WebElement metaDescriptionField;

    @FindBy(css = ".input-group input")
    private WebElement tagSlugField;
    
    @FindBy(css = "#tag-manager .submit-tag")
    private WebElement editTagButton;
    
    @FindBy(css = "#tag-manager .close")
    private WebElement closeButton;

    @FindBy(css = "#tag-manager .add-new-tags > a")
    private WebElement addNewTagLink;
    
    @FindBys({
    	@FindBy(css = "#tag-manager .pagination .is-active"),
    	@FindBy(linkText = "2")
    })
    private WebElement activePaginationLink;

    @FindBy(css = ".has-error .text-error:nth-of-type(1)")
    private WebElement tagSaveErrorMessage;

    public TagManagerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    public boolean verifyTagsLabel()
    {
        try{
            return tagsLabel.isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
    public boolean isAddNewTagLinkDisplayed()
    {
    	try{
    		WaitForElement.waitForElementToBeClickable(driver, addNewTagLink);
    		return addNewTagLink.isDisplayed();
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
    public void clickAddNewTagLink()
    {
    	WaitForElement.waitForElementToBeClickable(driver, addNewTagLink);
		addNewTagLink.click();
    }
    public void enterDataInTagSearchField(String tagName)
    {
    	WaitForElement.waitForElementToBeVisible(driver, tagSearchField);
    	tagSearchField.clear();
    	tagSearchField.sendKeys(tagName);
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    /*public List<WebElement> getListOfTagRows()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfTagRows);
    	return listOfTagRows;
    }*/
    public List<WebElement> getListOfTagNames()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfTagNames);
    	return listOfTagNames;
    }
    public List<WebElement> getListOfTagSlug()
    {
        WaitForElement.waitForElementToBeVisible(driver, listOfTagSlug);
        return listOfTagSlug;
    }
    public List<WebElement> getListOfTagsMetadata()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfTagMetadata.get(0));
    	return listOfTagMetadata;
    }
    /*public List<WebElement> getListOfTagsCount()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfTagsCount.get(0));
    	return listOfTagsCount;
    }
    public List<WebElement> getListOfCreatedOnDate()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfCreatedOnDate.get(0));
    	return listOfCreatedOnDate;
    }
    public List<WebElement> getListOfCreatedByAuthor()
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfCreatedByAuthor.get(0));
    	return listOfCreatedByAuthor;
    }
    public List<WebElement> getPaginationLinksList()
    {
    	WaitForElement.waitForElementToBeVisible(driver, paginationLinksList);
    	return paginationLinksList;
    }*/
    public void clickTagEditButton(String tagName)
    {
    	WebElement tagEditButton = driver.findElement(By.xpath("//span[text()='"+tagName+"']/..//a"));
    	WaitForElement.waitForElementToBeClickable(driver, tagEditButton);
    	tagEditButton.click();
    }
    public void enterMetaDescription(String metaDescription)
    {
    	WaitForElement.waitForElementToBeVisible(driver, metaDescriptionField);
    	metaDescriptionField.click();
    	metaDescriptionField.clear();
    	metaDescriptionField.sendKeys(metaDescription);
    }
    public void enterTagSlug(String tagSlug)
    {
        WaitForElement.waitForElementToBeVisible(driver, tagSlugField);
        tagSlugField.click();
        tagSlugField.clear();
        tagSlugField.sendKeys(tagSlug);
    }
    public String validateTagSlug()
    {
        WaitForElement.waitForElementToBeVisible(driver, tagSlugField);
        String tagSlug = tagSlugField.getAttribute("value");
        return tagSlug;
    }
    public void removeTagSlug()
    {
        actions = new Actions(driver);
        WaitForElement.waitForElementToBeVisible(driver, tagSlugField);
        //tagSlugField.click();
        actions.moveToElement(tagSlugField).click().sendKeys(Keys.CONTROL, "a").sendKeys(Keys.DELETE).keyUp(Keys.LEFT_CONTROL).build().perform();
        //tagSlugField.clear();
        System.out.println(tagSlugField.getAttribute("value"));
    }
    public String tagSaveError()
    {
        WaitForElement.waitForElementToBeVisible(driver, tagSaveErrorMessage);
        String saveErrorMessage = tagSaveErrorMessage.getText();
        return saveErrorMessage;
    }
    public void clickEditTagButton()
    {
    	WaitForElement.waitForElementToBeClickable(driver, editTagButton);
    	editTagButton.click();
    	WaitForElement.waitForElementToBeVisible(driver, tagSearchField);
    }
    public void clickCloseButton()
    {
    	WaitForElement.waitForElementToBeClickable(driver, closeButton);
    	closeButton.click();
    }
    /*public boolean isPaginationLinkActive()
    {
    	return activePaginationLink.isDisplayed();
    }*/
}
