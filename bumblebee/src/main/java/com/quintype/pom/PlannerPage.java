package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

import java.util.List;

public class PlannerPage 
{
	private WebDriver driver;

	@FindBy(css = "a.add-button")
	private List <WebElement> addNewStory;

	@FindBy(css = "div.planner-container a[role='tab']")
	private List<WebElement> storiesTab;

	@FindBy(css = "#planner input[type='search']")
	private WebElement plannerSearchBox;

	@FindBy(xpath = "//h2[text()='Ideas']/..//span[@class='count']")
	private WebElement ideasTabCount;

	@FindBy(xpath = "//h2[text()='Ideas']/../..//li")
	private List <WebElement> ideasTabStories;

	@FindBy(xpath = "//h2[text()='Not Started']/..//span[@class='count']")
	private WebElement notStartedTabCount;

	@FindBy(xpath = "//h2[text()='Not Started']/../..//li")
	private List <WebElement> notStartedTabStories;

	@FindBy(xpath = "//h2[text()='Open']/..//span[@class='count']")
	private WebElement openTabCount;

	@FindBy(xpath = "//h2[text()='Open']/../..//li")
	private List <WebElement> openTabStories;

	@FindBy(xpath = "//h2[text()='Needs Approval']/..//span[@class='count']")
	private WebElement needsApprovalTabCount;

	@FindBy(xpath = "//h2[text()='Needs Approval']/../..//li")
	private List <WebElement> needsApprovalTabStories;

	@FindBy(xpath = "//h2[text()='Approved']/..//span[@class='count']")
	private WebElement approvedTabCount;

	@FindBy(xpath = "//h2[text()='Approved']/../..//li")
	private List <WebElement> approvedTabStories;

	@FindBy(xpath = "//h2[text()='Published']/..//span[@class='count']")
	private WebElement publishedTabCount;

	@FindBy(xpath = "//h2[text()='Published']/../..//li")
	private List <WebElement> publishedTabStories;

	@FindBy(xpath = "//h2[text()='Rejected']/..//span[@class='count']")
	private WebElement rejectedTabCount;

	@FindBy(xpath = "//h2[text()='Rejected']/../..//li")
	private List <WebElement> rejectedTabStories;

	@FindBy(xpath = "//h2[text()='Scheduled']/..//span[@class='count']")
	private WebElement scheduledTabCount;

	@FindBy(xpath = "//h2[text()='Scheduled']/../..//li")
	private List <WebElement> scheduledTabStories;
	

	public PlannerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickAddNewStory()
	{
		WaitForElement.waitForElementToBeVisible(driver, publishedTabCount);
		addNewStory.get(1).click();
	}
	public boolean verifyStoriesLinkStatus()
	{
		boolean flag = false;
		for (int i = 0; i < storiesTab.size(); i++) {
			WaitForElement.waitForElementToBeVisible(driver, storiesTab.get(i));
			try{
				flag = storiesTab.get(i).isDisplayed();
			}catch(Exception e)
			{
				
				flag = false;
				break;
			}
		}
		return flag;
	}
	public int checkTabCount(String tabName)
	{
		int flag = 1;
		WaitForElement.waitForElementToBeVisible(driver, publishedTabCount);
		switch(tabName)
		{
			case "ideas":		if((ideasTabCount.getText()).equals(ideasTabStories.size()))
									flag = 1;
								else
									flag = 0;
								break;
			case "notstarted":	if((notStartedTabCount.getText()).equals(notStartedTabStories.size()))
									flag = 1;
								else
									flag = 0;
								break;
			case "open":	//	try{Thread.sleep(10000);}catch(Exception e){}
								System.out.println(openTabCount.getText());
								System.out.println(openTabStories.size());
								if((openTabCount.getText()).equals(openTabStories.size()))
									flag = 1;
								else
									flag = 0;
								break;
			case "needsApproval":	if((needsApprovalTabCount.getText()).equals(needsApprovalTabStories.size()))
									flag = 1;
								else
									flag = 0;
								break;
			case "approved":		if((approvedTabCount.getText()).equals(approvedTabStories.size()))
									flag = 1;
								else
									flag = 0;
								break;
			case "published":		if((publishedTabCount.getText()).equals(publishedTabStories.size()))
									flag = 1;
								else
									flag = 0;
								break;
			case "rejected":		if((rejectedTabCount.getText()).equals(rejectedTabStories.size()))
									flag = 1;
								else
									flag = 0;
								break;
			case "scheduled":		if((scheduledTabCount.getText()).equals(scheduledTabStories.size()))
									flag = 1;
								else
									flag = 0;
								break;
		}
		return flag;
	}
	public void verifyPlannerSearch()
	{
		plannerSearchBox.sendKeys("Test Photo Story");
		try{
			Thread.sleep(5000);
			WaitForElement.waitForElementToBeVisible(driver, publishedTabStories.get(0));
			System.out.println(publishedTabStories.get(0));
		}catch(Exception e)
		{

		}

	}

}
