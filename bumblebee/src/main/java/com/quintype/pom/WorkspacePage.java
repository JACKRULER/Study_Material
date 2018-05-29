package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.quintype.util.CheckAlert;
import com.quintype.util.WaitForElement;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class WorkspacePage
{
	private WebDriver driver;
	Actions actions;

	@FindBy(id = "new-story-link")
	private WebElement newStoryButton;

    @FindBy(css = "a.menu-button")
    private WebElement workspaceMenuButton;

    @FindBy(css ="nav.drawer > ul >li")
    private List <WebElement> workspaceMenuList;
    
    @FindBy(linkText = "Workspace")
    private WebElement workspaceLink;

    @FindBy(linkText = "Planner")
    private WebElement plannerLink;  

    @FindBy(linkText = "Pages")
    private WebElement pagesLink;

    @FindBy(css = "nav.drawer .sort")
    private WebElement sortersLink;
    
    @FindBy(linkText = "Top Stories")
    private WebElement topStoriesLink;
    
    @FindBy(linkText = "Breaking News")
    private WebElement breakingNewsLink;    

    @FindBy(css = "nav.drawer .analytics")
    private WebElement analyticsLink;
    
    @FindBy(linkText = "Story Analytics")
    private WebElement storyAnalyticsLink;

    @FindBy(linkText = "Tag Manager")
    private WebElement tagManagerLink;                

    @FindBy(css = "span.settings")                        
    private WebElement adminLink;  

    @FindBy(linkText = "Settings")
    private WebElement settingsLink;

    @FindBy(linkText = "SEO Metadata")
    private WebElement seoMetadataLink;

    @FindBy(linkText= "Users and Roles")
    private WebElement userAndRoleLink;
    
    @FindBy(linkText = "Configure")
    private WebElement configureLink;
    
    @FindBy(linkText = "API Manager")
    private WebElement apiManagerLink;
    
    @FindBy(linkText = "Media Library")
    private WebElement mediaLibraryLink;

    @FindBy(css = ".drawer a[href='/entities']")
    private WebElement entitiesLink;
    
    @FindBy(linkText = "My Profile")
    private WebElement myProfileLink;

    @FindBy(linkText = "Add Author")
    private WebElement addAuthorLink;

    @FindBy(css = "#stories input[type='search']")
    private WebElement workspaceSearchField;
    
    @FindBy(css = "#stories a[href='/workspace#open'] span")
    private WebElement openTabLink;

    @FindBy(css = "#stories a[href='/workspace#pending-approval'] span")
    private WebElement needApprovalTabLink;

    @FindBy(css = "#stories a[href='/workspace#approved'] span")
    private WebElement approvedTabLink;

    @FindBy(css = "#stories a[href='/workspace#published'] span")
    private WebElement publishedTabLink;

    @FindBy(css = "#stories a[href='/workspace#rejected'] span")
//    @FindBy(css = "#stories a[href='/workspace#rejected']")
    private WebElement rejectedTabLink;

    @FindBy(css = "#stories a[href='/workspace#scheduled'] span")
//    @FindBy(css = "#stories a[href='/workspace#scheduled']")
    private WebElement scheduledTabLink;
    
    @FindBy(css = "#stories a[href='/workspace#published']")
    private WebElement publishedLink;

	@FindBy(css = "a.header-profile")
	private WebElement profileButton;

	@FindBy(css = "nav.profile-dropdown .logout")
	private WebElement logoutLink;

    @FindBy(css = ".drawer .logout")
    private WebElement menuLogoutLink;

    @FindBy(xpath = "//li[@class='loading ']")
    private static WebElement loadStories;

    @FindBy(css = "span.clear-all")
    private WebElement clearAllFilterButton;

    @FindBy(css = "div.no-stories")
    private WebElement noStoryMessage;

    @FindBy(css = "#stories .stack-list  h3 > a")
    public List <WebElement> listOfTitle;

    @FindBy(css = "#stories .is-active a")
    private WebElement workspaceActiveTab;
    
    @FindBy(css = "#stories .trash")
    private List<WebElement> listOfDeleteLinks;
    
	public WorkspacePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    public void clickNewStoryButton() 		// method to click on newStoryButton
	{
		WaitForElement.waitForElementToBeClickable(driver, newStoryButton);
		newStoryButton.click();
	}
    public void clickWorkspaceMenuButton()
    {
    	actions = new Actions(driver);
        WaitForElement.waitForElementToBeClickable(driver, workspaceMenuButton);
        actions.moveToElement(workspaceMenuButton, 0, 0).click().build().perform();
    }
    public void clickWorkspaceMenuButtonWithJS()
    {
        WaitForElement.waitForElementToBeClickable(driver, workspaceMenuButton);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", workspaceMenuButton);
    }
    public List<WebElement> getListOfWorkspaceMenuLinks()
    {
        WaitForElement.waitForElementToBeVisible(driver, workspaceMenuList.get(1));
        return workspaceMenuList;
    }
    public void clickWorkspaceLink()
    {
    	WaitForElement.waitForElementToBeClickable(driver, workspaceLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", workspaceLink);
        CheckAlert.verifyAcceptAlert(driver);
//        new WebDriverWait(driver, 18).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading.spinner")));
    }
    public void clickPlannerLink()
    {
        WaitForElement.waitForElementToBeClickable(driver, plannerLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", plannerLink);
    }
    public void clickPagesLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, pagesLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", pagesLink);
    }
    public void clickSortersLink()
    {
    	actions = new Actions(driver);
    	try{
    		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(By.cssSelector("nav.drawer .is-open .sort")));
    	}catch(Exception e)
    	{
    		actions.moveToElement(sortersLink).click().build().perform();    		
    	}
    }
    public void clickTopStoriesLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, topStoriesLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", topStoriesLink);
    }
    public void clickBreakingNewsLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, breakingNewsLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", breakingNewsLink);
    }
    public void clickAnalyticsLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, analyticsLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", analyticsLink);
    }
    public void clickStoryAnalyticsLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, storyAnalyticsLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", storyAnalyticsLink);
    }
    public void clickTagManagerLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, tagManagerLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", tagManagerLink);
    } 
    public void clickAdminLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, adminLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", adminLink);
    }
    public void clickSettingsLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, settingsLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", settingsLink);
    }
    public void clickSEOMetadataLink()
    {
        WaitForElement.waitForElementToBeVisible(driver, seoMetadataLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", seoMetadataLink);
    }
    public void clickUserAndRoleLink()  // method to click on userAndRoleField
    {
        WaitForElement.waitForElementToBeVisible(driver, userAndRoleLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", userAndRoleLink);     
    }
    public void clickConfigureLink()
    {
    	WaitForElement.waitForElementToBeVisible(driver, configureLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", configureLink);     
    }
    public void clickAPIManagerLink()
    {
    	WaitForElement.waitForElementToBeVisible(driver, apiManagerLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", apiManagerLink);  
    }
    public void clickMediaLibraryLink()
    {
    	WaitForElement.waitForElementToBeVisible(driver, mediaLibraryLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", mediaLibraryLink);  
    }
    public void clickEntitiesLink()  // method to click on userAndRoleField
    {
        WaitForElement.waitForElementToBeVisible(driver, entitiesLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", entitiesLink);     
    }    
    public String myProfile()
    {
        WaitForElement.waitForElementToBeVisible(driver, myProfileLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", myProfileLink);        
        return driver.getTitle();
    }
    public String addAuthor()
    {
        WaitForElement.waitForElementToBeVisible(driver, addAuthorLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", addAuthorLink);
        return driver.getTitle();
    }
    public String clickWorkspaceMenuLogoutLink()  // method to click on userAndRoleField
    {
        WaitForElement.waitForElementToBeVisible(driver, menuLogoutLink);
        JavascriptExecutor executor =( (JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", menuLogoutLink);     
        return driver.getTitle();        
    }
    public void clickOpenTab()
    {
        WaitForElement.waitForElementToBeVisible(driver, openTabLink);
        openTabLink.click();
        WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
    }
    public void clickNeedApprovalTab()
    {
        WaitForElement.waitForElementToBeVisible(driver, needApprovalTabLink);
        needApprovalTabLink.click();
        WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
    }
    public void clickApprovedTab()
    {
        WaitForElement.waitForElementToBeVisible(driver, approvedTabLink);
        approvedTabLink.click();
        WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
    }
//    public void clickPublishedTab()
//    {
//        WaitForElement.waitForElementToBeVisible(driver, publishedTabLink);
//        publishedTabLink.click();
//        new WebDriverWait(driver, 15).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));
//    }
    public void clickPublishedLink()
    {
    	WaitForElement.waitForElementToBeVisible(driver, publishedLink);
    	publishedLink.click();
        WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
    }
    public void clickRejectedTab()
    {
        WaitForElement.waitForElementToBeVisible(driver, rejectedTabLink);
        rejectedTabLink.click();
        WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
    }
    public void clickScheduledTab()
    {
        WaitForElement.waitForElementToBeVisible(driver, scheduledTabLink);
        scheduledTabLink.click();
        WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
    }
    public void enterDataInWorkspaceSearchField(String searchTitle)
    {
        WaitForElement.waitForElementToBeVisible(driver , workspaceSearchField);
        workspaceSearchField.sendKeys(searchTitle);
    }
	public void clickProfileButton()								// method to click on logout from itsman
  	{	
		WaitForElement.waitForElementToBeClickable(driver, profileButton);
		profileButton.click();
	}
	public String clickLogoutLink()
	{
		WaitForElement.waitForElementToBeClickable(driver, logoutLink);
		logoutLink.click();
		return driver.getTitle();
	}
    public boolean checkStoriesPresentInTab()
    {
    	try{
            noStoryMessage.isDisplayed();
            return false;
    	}catch(Exception e)
    	{
    		return true;
    	}
    }
    public void clickFirstStory()
    { 
        WaitForElement.waitForElementToBeVisible(driver, listOfTitle.get(0));
        listOfTitle.get(0).click();        
    }
    public List<WebElement> getListOfStoryTitle()
    { 
    	WaitForElement.waitForElementToBeVisible(driver, listOfTitle);  
        return listOfTitle;
    }
    public List<WebElement> getListOfStoryTitle(String firstStoryTitle)
    { 
    	WaitForElement.waitForElementToBeMatch(driver, listOfTitle, firstStoryTitle);  
        return listOfTitle;
    }
    public List<WebElement> getUpdatedListOfStoryTitle(String storyTitle)
    {
    	By element = By.xpath("(//a[text()='"+storyTitle+"'])[1]");
		WaitForElement.waitForElementToInvisible(driver, element , storyTitle);
		return listOfTitle;
    }
    public static WebElement waitForStoriesToLoad()
    {
        return loadStories;
    }
	
    public String getActiveTabName()
    {
        WaitForElement.waitForElementToBeVisible(driver, workspaceActiveTab);
        return workspaceActiveTab.getText().replaceAll("\\d", "").trim();
    }
    public void deleteStory(int storyCount)
    {
    	WaitForElement.waitForElementToBeVisible(driver, listOfDeleteLinks);
    	listOfDeleteLinks.get(storyCount).click();
    	CheckAlert.verifyAcceptAlert(driver);
    }
    public void deleteStory(String storyTitle)
    {
    	WebElement deleteButton = driver.findElement(By.xpath("//a[text()='"+storyTitle+"']//../..//a[@class='icon button small trash']"));
    	WaitForElement.waitForElementToBeClickable(driver, deleteButton);
    	deleteButton.click();
    	CheckAlert.verifyAcceptAlert(driver);
    }
    public boolean verifyQuickLinkButton(String storyTitle)
    {
    	return driver.findElement(By.xpath("//a[text()='"+storyTitle+"']//../..//a[@class='icon button small new-window']")).isDisplayed();
    }
    public boolean isStackCreated(String stackName)
    {
    	try{
        	WebElement sorterName = driver.findElement(By.xpath("//a[text()='"+stackName+"']"));
        	WaitForElement.waitForElementToBeClickable(driver, sorterName);
        	return sorterName.isDisplayed();	
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
    public void clickOnCreatedSorter(String stackName)
    {
    	actions = new Actions(driver);
    	WebElement sorter = driver.findElement(By.linkText(stackName));
    	WaitForElement.waitForElementToBeClickable(driver, sorter);
    	actions.moveToElement(sorter,0,0).click().build().perform();
    }
    public List<WebElement> getListOfSorters()
    {
    	List<WebElement> listOfSorters = sortersLink.findElements(By.xpath("..//li"));
    	WaitForElement.waitForElementToBeVisible(driver, listOfSorters);
    	return listOfSorters;
    }
    public void deleteBreakingStory(String storyTitle)
    {
        List<WebElement> deleteButton = driver.findElements(By.xpath("//a[@class='icon button small trash']"));
        WaitForElement.waitForElementToBeClickable(driver, deleteButton.get(0));
        deleteButton.get(0).click();
        CheckAlert.verifyAcceptAlert(driver);
    }

}
