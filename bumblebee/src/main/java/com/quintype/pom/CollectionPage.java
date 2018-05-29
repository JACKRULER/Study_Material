package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.quintype.util.CurrentDate;
import com.quintype.util.ScrollPage;
import com.quintype.util.UploadImage;
import com.quintype.util.WaitForElement;

import java.util.List;

public class CollectionPage
{
    private WebDriver driver;
    public static String collectionTitle;

    @FindBy(css = "#story-title div[role='textbox']")
    private WebElement titleField;

    @FindBy(css = "#story-summary div[role='textbox']")
    private WebElement summaryField;

    @FindBy(id = "hero-image")
    private WebElement chooseCoverImage;

    @FindBy(css = "div.image-upload  input[type='file']")
    private WebElement uploadHeroImageLink;

  	@FindBy(css = ".uploaded-image")
    private WebElement uploadedImage;

  	@FindBy(xpath = "//a[text()='Sorter']")
    private WebElement sorterTab;

  	@FindBy(css = "span.iconic[title='Add to sorter']")
    private List <WebElement> selectStoryFromLHS;

  	@FindBy(css = ".all-stories .sort-title.left-align")
    private List <WebElement> storyListTitle;

    @FindBy(css = "div.story-sort-list .sort-title")
    private List <WebElement> storyTitleRHS;

    @FindBy(css = ".sorter-set-property-button")
    private List <WebElement> manageButtonList;

    @FindBy(css = ".top-twenty.story-sort-list .sort-items .sorter-set-property-button")
    private WebElement firstManageButton;

  	@FindBy(css = ".save")
    private WebElement saveButton;

  	@FindBy(css = "div.headline-search-form > input")
    private WebElement searchField;

  	@FindBy(css = "div.advanced-search")
    private WebElement advancedSearchButton;

  	@FindBy(css = "div.filters .radio")
    private List <WebElement> selectRadioButton;

  	@FindBy(css = "div.control-buttons .apply")
    private WebElement applyFilterButton;

  	@FindBy(css = "div.control-buttons .reset")
    private WebElement resetAllButton;

    @FindBy(css = ".rodal-zoom-enter .text-editor [role='textbox']")
    private List <WebElement> feildsOnPopup;

    @FindBy(css = "button.save-button")
    private WebElement saveForMetadataButton;

    @FindBy(css=".story-ollection-details .radio")
    private List<WebElement> collectionTypeRadioButtons;

    @FindBy(css = ".iconic[title='Add to sorter']")
    private List<WebElement> addToSorterStarIcon;

    @FindBy(css=".top-twenty.story-sort-list .sort-items .sort-title.left-align")
    //@FindBy(css=".top-twenty.story-sort-list .sort-items")
    private List<WebElement> selectedStories;

    @FindBy(css = "li[data-tab='stories'] > a")
    private WebElement storiesLink;

    @FindBy(css = "li[data-tab='collections'] > a")
    private WebElement collectionsLink;


  	public CollectionPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

  	public void enterStoryCollectionTitle(String title)
    {
		    WaitForElement.waitForElementToBeVisible(driver, titleField);
            titleField.sendKeys(title);
  	}

  	public void enterStoryCollectionSummary(String collectionSummary)
  	{
        WaitForElement.waitForElementToBeVisible(driver, summaryField);
        summaryField.sendKeys(collectionSummary);
  	}

  	public void uploadCoverImage(String imageName)
  	{
  		Actions action = new Actions(driver);
        WaitForElement.waitForElementToBeVisible(driver, chooseCoverImage);
		action.moveToElement(chooseCoverImage).click().build().perform();
        UploadImage.uploadImage(uploadHeroImageLink, imageName); // Method to upload the images
    	WaitForElement.waitForElementToBeVisible(driver, uploadedImage);
  	}
  	public void clickSorterTab()
  	{
  		ScrollPage.scrollUP(driver);
        WaitForElement.waitForElementToBeVisible(driver, sorterTab);
    	sorterTab.click();
  	}

  	public void clickAdvancedSearch()
  	{
        WaitForElement.waitForElementToBeVisible(driver, advancedSearchButton);
        advancedSearchButton.click();
  	}

  	public void selectStoryRadioButton()
  	{
        WaitForElement.waitForElementToBeVisible(driver, selectRadioButton.get(0));
        selectRadioButton.get(0).click();
  	}

  	public void selectAutoCollectionTypeRadio(){

  		WaitForElement.waitForElementToBeClickable(driver, collectionTypeRadioButtons.get(1));
  		collectionTypeRadioButtons.get(1).click();
  	}

    public void selectRecoCollectionTypeRadio(){

  		WaitForElement.waitForElementToBeClickable(driver, collectionTypeRadioButtons.get(2));
  		collectionTypeRadioButtons.get(2).click();
  	}

  	public void selectCollectionRadioButton()
  	{
        WaitForElement.waitForElementToBeVisible(driver, selectRadioButton.get(1));
        selectRadioButton.get(1).click();
  	}

  	public void clickApplyFilterButton()
  	{
      	WaitForElement.waitForElementToBeVisible(driver, applyFilterButton);
        applyFilterButton.click();
    }

    public void clickManageButton()
    {
      WaitForElement.waitForElementToBeVisible(driver, firstManageButton);
      firstManageButton.click();
    }

    public boolean verifyManageButtonIsDisplayed(){
    	WaitForElement.waitForElementToBeVisible(driver, firstManageButton);
    	return firstManageButton.isDisplayed();
    }
    public void clickSaveButtonForMetadata()
    {
      WaitForElement.waitForElementToBeVisible(driver, saveForMetadataButton);
        saveForMetadataButton.click();
    }

  	public void clickSaveButton(){
        WaitForElement.waitForElementToBeVisible(driver, saveButton);
        saveButton.click();
  	}

    public String getFirstSearchedCollection()
    {
        WaitForElement.waitForElementToBeVisible(driver, storyListTitle.get(0));
        return storyListTitle.get(0).getText();
    }

    public void addToSorter( int numberOfStories){
    	WaitForElement.waitForPageToLoad(driver);
//      for (WebElement element : selectedStories) {
//        Assert.assertTrue(element.isDisplayed());
//      }
//      
//      WaitForElement.waitForElementToBeVisible(driver, addToSorterStarIcon.get(0));
    	WaitForElement.fluentWaitForElement(driver, addToSorterStarIcon.get(0));
      
    	for(int i = 0; i< numberOfStories; i++) {
    		addToSorterStarIcon.get(0).click();
    		//WaitForElement.waitForElementToBeVisible(driver, selectedStories);
    		//Assert.assertEquals(storyListTitle.get(1).getText(), selectedStories.get(0).getText());

    	}
    }

    public void clickStoriesLink()
    {
    	WaitForElement.waitForElementToBeVisible(driver, storiesLink);
    	storiesLink.click();
    }
    public void clickCollectionsLink()
    {
    	WaitForElement.waitForElementToBeVisible(driver, collectionsLink);
    	collectionsLink.click();
    }
    public boolean isStoriesLinkDisplayed()
    {
    	WaitForElement.waitForElementToBeVisible(driver, storiesLink);
    	return storiesLink.isDisplayed();
    }
    public boolean isCollectionsLinkDisplayed()
    {
    	WaitForElement.waitForElementToBeVisible(driver, collectionsLink);
    	return collectionsLink.isDisplayed();
    }

}
