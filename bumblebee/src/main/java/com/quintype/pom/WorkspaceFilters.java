package com.quintype.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.quintype.util.WaitForElement;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class WorkspaceFilters
{
    public String actualSectionName;
    public String appendedSectionName;
    private WebDriver driver;

    @FindBy(css = "div.filter-bar")
  	private WebElement filtersDrawer;
    
    @FindBy(css = "#filters .filter-control > h5")
    private WebElement closeFiltersDrawer;

//    @FindBy(css = "#stories input[type='search']")
//    private WebElement workspaceSearchField;

    @FindBy(css = "#filters label[for='story-filter']")
    private WebElement storyCheckBox;

    @FindBy(css = "#filters label[for='Photo']")
    private WebElement photoCheckBox;

    @FindBy(css = "#filters label[for='Video']")
    private WebElement videoCheckBox;

    @FindBy(css = "#filters label[for='Listicle']")
    private WebElement listicleCheckBox;

    @FindBy(css = "#filters label[for='Live Blog']")
    private WebElement liveBlogCheckBox;

    @FindBy(css = "#filters label[for='Text']")
    private WebElement textCheckBox;

    @FindBy(css = "#filters label[for='push-notification']")
    private WebElement pushNotificationCheckBox;

    @FindBy(css = "#filters label[for='breaking-news']")
    private WebElement breakingNewsCheckBox;

    @FindBy(css = "#filters label[for='collection']")
    private WebElement collectionCheckBox;

    @FindBy(css = "#filters label[for='today-date']")
    private WebElement todayDate;

    @FindBy(css = "#filters label[for='this-week']")
    private WebElement thisWeek;

    @FindBy(css = "#filters label[for='this-month']")
    private WebElement thisMonth;

    @FindBy(css = "#filters label[for='custom-range']")
    private WebElement customRange;

    @FindBy(css = "#filters .Select-control")
  	private List<WebElement> sectionField;
    
    @FindBy(css = ".Select-menu-outer .Select-option")
    private WebElement sectionName;

    @FindBy(css = "#filters .author > div")
    private WebElement authorField;

    @FindBy(css = "#filters .author > div")
    private WebElement authorBox;

    @FindBy(css = ".selected-filters-container")
  	private WebElement selectedFilterName;

    @FindBy(css = "#filters-selected .apply-filter")
  	private WebElement applyButton;

    @FindBy(css = "#filters-selected .clear-all")
    private WebElement clearButton;

    @FindBy(css = "#stories .is-active span")
    private WebElement stateTabCount;

    @FindBy(css = "#stories li[class='loading ']")
    private WebElement loadStories;

    @FindBy(css = "#stories span.story-template")
    private List <WebElement> listOfStoryType;

    @FindBy(css = "#stories span.clock")
    private List <WebElement> listOfDate;

    @FindBy(css = "#stories span.author")
    private List <WebElement> listOfAuthor;

    @FindBy(css = "#stories span.tag")
    private List <WebElement> listOfCollectionTemplate;

    @FindBy(css = "#stories .notification-details")
    private List<WebElement> listOfPushNotification; 

    @FindBy(css = "#sections span[class='Select-item-label']")
    private List<WebElement> selectSectionField;

    @FindBy(xpath = "(//div[@class='date-picker']//div)[1]")
    private WebElement fromDateField;

    @FindBy(css = "a.react-datepicker__navigation--previous")
    private WebElement previousNavigationLink;

    @FindBy(css = "div.react-datepicker__month div[aria-label='day-20']")
    private WebElement selectDateFromDatePicker;

    @FindBy(css = "#filters-selected li a")
    private WebElement selectedDate;

    @FindBy(css = "a.load-more")
    private WebElement loadMoreButton;

    @FindBy(css = "#stories li > h3")
    public List <WebElement> listOfTitle;

    @FindBy(css = "#filters-selected .filter-save-btn")
    private WebElement filterSaveButton;

    @FindBy(id = "filterName")
    private WebElement filterNameField;

    @FindBy(css = ".filter-tabs li")
    private List<WebElement> filterTabs;

    @FindBy(css = ".my-filters .selected-filter-name")
    private List<WebElement> selectedMyFiltersName;

    @FindBy(css = "div.show-save-options .filter-control-btn")
    private List<WebElement> filterControlButtons;
    
    @FindBy(css = ".spinning")
    private List<WebElement> spanningLoader;
    
    @FindBy(css = ".spinner")
    private List<WebElement> spinner;
    
    @FindBy(css = ".alert-message.is-active")
    private List<WebElement> alertSavedMessage;
    
    @FindBy(css = ".Select-menu-outer .Select-option.is-focused")
    private WebElement focusedElementInList;

    public WorkspaceFilters(WebDriver driver)
  	{
  		this.driver = driver;
  		PageFactory.initElements(driver, this);
  	}
    
    public void clickFiltersDrawer()
    {
        WaitForElement.waitForElementToBeClickable(driver, filtersDrawer);
        filtersDrawer.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".filter-bar.is-hidden")));       
    }
    public void closeFilterDrawer()
    {
    	WaitForElement.waitForElementToBeClickable(driver, closeFiltersDrawer);
    	closeFiltersDrawer.click();
    }    
    public int getActiveTabCount()
    {
        WaitForElement.waitForElementToBeVisible(driver , stateTabCount);
        return Integer.parseInt(stateTabCount.getText());
    }

//    public void enterDataInWorkspaceSearchField(String searchTitle)
//    {
//        WaitForElement.waitForElementToBeVisible(driver , workspaceSearchField);
//        workspaceSearchField.sendKeys(searchTitle);
//    }
//    public WebElement getSearchBoxElement()
//    {
//    	return workspaceSearchField;
//    }
    public List<WebElement> getStoryList()
    {
        WaitForElement.waitForElementToBeVisible(driver, listOfTitle);
        return listOfTitle;
    }

    public void clickLoadMoreButton()
    {
        while(true)
        {
            try{
            	WaitForElement.waitForElementToBeClickable(driver, loadMoreButton);
                loadMoreButton.click();
//                ScrollPage.scrollDown(driver);
                Thread.sleep(1000);
            }catch(Exception e){
                break;
            }   
        }
    } 

    public boolean loadMoreButtonDisplayed()
    {
        return loadMoreButton.isDisplayed();
    }
    public List<WebElement> getStoryTypeList()
    {
        return listOfStoryType;
    }
    public List<WebElement> getCollectionList()
    {
        return listOfCollectionTemplate;        
    }
    public List<WebElement> getDateList()
    {
        return listOfDate;        
    }
    public WebElement getSelectedDate()
    {
        return selectedDate;
    }
    public List<WebElement> getAuthorList()
    {
        return listOfAuthor;
    }
    public List<WebElement> getSavedFiltersList()
    {
        WaitForElement.waitForElementToBeVisible(driver , selectedMyFiltersName);
    	return selectedMyFiltersName;
    }
    public void selectStoryCheckbox()         // To select today radio button
    {
        WaitForElement.waitForElementToBeClickable(driver , storyCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", storyCheckBox); 
        //storyCheckBox.click();
    }
    public void selectPhotoCheckbox()         // To select today radio button
    {
        WaitForElement.waitForElementToBeClickable(driver , photoCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", photoCheckBox);
       // photoCheckBox.click();
    }
    public void selectVideoCheckbox()         // To select today radio button
    {
        WaitForElement.waitForElementToBeClickable(driver , videoCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", videoCheckBox);
      //  videoCheckBox.click();
    }
    public void selectListicleCheckbox()         // To select today radio button
    {
        WaitForElement.waitForElementToBeClickable(driver , listicleCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", listicleCheckBox);
       // listicleCheckBox.click();
    }
    public void selectLiveBlogCheckbox()         // To select today radio button
    {
        WaitForElement.waitForElementToBeClickable(driver , liveBlogCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", liveBlogCheckBox);
       // liveBlogCheckBox.click();
    }
    public void selectTextCheckbox()         // To select today radio button
    {
        WaitForElement.waitForElementToBeClickable(driver , textCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", textCheckBox);
       // textCheckBox.click();
    }
    public void selectPushNotificationCheckBox()
    {
        WaitForElement.waitForElementToBeClickable(driver , pushNotificationCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", pushNotificationCheckBox);
       // pushNotificationCheckBox.click();
    }
    public void selectBreakingNewsCheckBox()
    {
        WaitForElement.waitForElementToBeClickable(driver , breakingNewsCheckBox);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", breakingNewsCheckBox);
       // breakingNewsCheckBox.click();
    }    
    public void selectCollectionCheckBox()
    {
        WaitForElement.waitForElementToBeClickable(driver , collectionCheckBox);
        JavascriptExecutor executor =((JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", collectionCheckBox);
      //  collectionCheckBox.click();
    }
    public void clickApply()
    {
        WaitForElement.waitForElementToBeClickable(driver , applyButton);
        JavascriptExecutor executor =((JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", applyButton);
        WaitForElement.waitForElementToInvisible(driver, spinner);
    }
    public void clickFilterSaveButton()
    {
    	 WaitForElement.waitForElementToBeVisible(driver , filterSaveButton);
         filterSaveButton.click();
         // Sometimes Saved label not get disappears. So it failed few times.
//         WaitForElement.waitForElementToInvisible(driver, alertSavedMessage);
    }
    public void enterFilterName(String filterName)
    {
    	WaitForElement.waitForElementToBeVisible(driver, filterNameField);
    	filterNameField.sendKeys(filterName);
    }
    public void clickFilterOptionsTab()
    {
    	WaitForElement.waitForElementToBeClickable(driver, filterTabs.get(0));
    	filterTabs.get(0).click();
        WaitForElement.waitForElementToInvisible(driver, spinner);
    }
    public void clickFilterListTab()
    {
    	WaitForElement.waitForElementToBeClickable(driver, filterTabs.get(1));
    	filterTabs.get(1).click();
    	WaitForElement.waitForElementToInvisible(driver, spinner);
    }
    
    public void clickUpdateButton()
    {
        WaitForElement.waitForElementToBeClickable(driver , filterControlButtons.get(0));
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", filterControlButtons.get(0));
        WaitForElement.waitForElementToInvisible(driver, alertSavedMessage);
    }
    public void clickSaveAsButton()
    {
        WaitForElement.waitForElementToBeClickable(driver , filterControlButtons.get(1));
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", filterControlButtons.get(1));        
    }
    public void clickMyCreatedFilter(String myCreatedFilter)
    {
    	WebElement myFilter = driver.findElement(By.xpath("//span[@class='selected-filter-name'][text()='"+myCreatedFilter+"']"));
        WaitForElement.waitForElementToBeClickable(driver , myFilter);
        myFilter.click();
    }
    public void hoverOnMyFilterOptions(String myFilterName)
    {
    	WebElement myFilter = driver.findElement(By.xpath("//span[text()='"+myFilterName+"']/..//div[@class='options']"));
        WaitForElement.waitForElementToBeClickable(driver , myFilter);
        Actions actions = new Actions(driver);
        actions.moveToElement(myFilter).perform();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void clickShareLink(String myFilterName)
    {
    	WebElement shareLink = driver.findElement(By.xpath("//span[text()='"+myFilterName+"']/..//li[@class='filter-share']"));
    	WaitForElement.waitForElementToBeClickable(driver , shareLink);
    	shareLink.click();
    }
    public void clickDeleteLink(String myFilterName)
    {
    	WebElement deleteLink = driver.findElement(By.xpath("//span[text()='"+myFilterName+"']/..//li[@class='filter-delete']"));
    	WaitForElement.waitForElementToBeClickable(driver , deleteLink);
    	deleteLink.click();  
        WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
    	try {					// Without this wait its considering the deleted filter too in list.
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void clickSetAsDefaultFilterLink(String myFilterName)
    {
    	WebElement setAsDefaultFilter = driver.findElement(By.xpath("//span[text()='"+myFilterName+"']/..//li[@class='filter-set-default']"));
    	WaitForElement.waitForElementToBeClickable(driver , setAsDefaultFilter);
    	setAsDefaultFilter.click();    	
    }
    public void clickUnshareLink(String myFilterName)
    {
    	WebElement unshareLink = driver.findElement(By.xpath("//span[text()='"+myFilterName+"']/..//li[@class='filter-unshare']"));
    	WaitForElement.waitForElementToBeClickable(driver , unshareLink);
    	unshareLink.click();
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void clickRemoveDefaultFilter(String myFilterName)
    {
    	WebElement removeDefaultFilterLink = driver.findElement(By.xpath("//span[text()='"+myFilterName+"']/..//li[@class='filter-remove-default']"));
    	WaitForElement.waitForElementToBeClickable(driver , removeDefaultFilterLink);
    	removeDefaultFilterLink.click();
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public boolean verifyMyFilterShared(String myFilterName)
    {
    	try{
    		WebElement sharedIcon = driver.findElement(By.xpath("//span[text()='"+myFilterName+"']/..//span[@class='sharable']"));
    		WaitForElement.waitForElementToBeClickable(driver, sharedIcon);
    		sharedIcon.click();
        	return true;    		
    	}catch(Exception e)
    	{
        	return false;
    	}
    }
    public boolean verifyDefaultFilterSet(String myFilterName)
    {
    	try{
    		WebElement defaultFilter = driver.findElement(By.xpath("//span[text()='"+myFilterName+"']/..//span[@class='show-default']")); 
    		WaitForElement.waitForElementToBeClickable(driver, defaultFilter);
    		defaultFilter.click();
        	return true;    		
    	}catch(Exception e)
    	{
        	return false;
    	}
    }
    public  boolean verifySharedFilterVisibleToAnotherUser(String sharedFilterName)
    {
    	try{
    		driver.findElement(By.xpath("//ul[@class='shared-filters']//span[text()='"+sharedFilterName+"']")).click();
        	return true;    		
    	}catch(Exception e)
    	{
        	return false;
    	}
    }
    public boolean verifyCheckboxSelected(String storyType)
    {
    	WaitForElement.waitForElementToBeClickable(driver , clearButton);
    	try{
    		driver.findElement(By.xpath("//label[@for='"+storyType+"'] [@class='checkbox is-selected']"));
    		return true;
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
    public void clickClearButton()
    {	
//    	WaitForElement.waitForElementToBeVisible(driver, filterControlButtons.get(0));
        WaitForElement.waitForElementToBeClickable(driver, clearButton);
        clearButton.click();
        WaitForElement.waitForElementToInvisible(driver, spinner);        
    }
    public void clickClearButtonAfterSave()
    {	
    	WaitForElement.waitForElementToBeVisible(driver, filterControlButtons.get(0));
//        WaitForElement.waitForElementToBeClickable(driver, clearButton);
        clearButton.click();
        WaitForElement.waitForElementToInvisible(driver, spinner);        
    }

    public void selectTodayCheckbox()         // To select today radio button
    {
        WaitForElement.waitForElementToBeVisible(driver , todayDate);
        JavascriptExecutor Executor =((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", todayDate);    
    //    todayDate.click();
    }
    public void selectThisWeekCheckbox()      // To select this week radio button
    {
        WaitForElement.waitForElementToBeVisible(driver , thisWeek);
        thisWeek.click();   
    }
    public void selectThisMonthCheckbox()     // To select this month radio button
    {
        WaitForElement.waitForElementToBeVisible(driver , thisMonth);
        thisMonth.click();
    }
    public void selectCustomRangeCheckbox()   // To select custom range radio button
    {
        WaitForElement.waitForElementToBeVisible(driver , customRange);
        customRange.click();
    }
    public void selectFromDate()              // To select from date from date picker
    {
        fromDateField.click();
        previousNavigationLink.click();     // For clicking on left arrow
        previousNavigationLink.click();     // For clicking on left arrow
        selectDateFromDatePicker.click();   // For selecting the date. By default will select 20th of last to last month from current date
    }

    public void selectAuthor(String authorName)
    {
        WaitForElement.waitForElementToBeVisible(driver, authorField);
        Actions a = new Actions(driver);
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));
        a.moveToElement(authorField).click().sendKeys(authorName).perform();
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));        
        
        WaitForElement.waitForElementToBeVisible(driver , authorField);
        a.moveToElement(authorField).sendKeys(Keys.ENTER).perform();
    }

    public void selectSectionName(String section)
    {
        WaitForElement.waitForElementToBeClickable(driver , sectionField.get(0));
        Actions actions = new Actions(driver);
        actions.moveToElement(sectionField.get(0)).click().sendKeys(section).build().perform();        
        WaitForElement.waitForElementToBeVisible(driver, focusedElementInList);
        focusedElementInList.click();
    }

    public void getSectionName()
    {
        appendedSectionName = selectedFilterName.getText();
        actualSectionName = appendedSectionName.substring(9, appendedSectionName.length()-1);
    }

    public boolean compareSectionName()
    {
    	boolean flag = false;
        WaitForElement.waitForElementToBeVisible(driver , selectSectionField);

        for (int i = 0; i < selectSectionField.size(); i++) {
			if(selectSectionField.get(i).getText().contains(actualSectionName))
			{
				flag = true;
				break;
			}
		}
        return flag;
    }

}
