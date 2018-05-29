package com.quintype.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.quintype.pom.WorkspacePage;
import com.quintype.pom.WorkspaceFilters;
import org.testng.Assert;
import org.testng.Reporter;

public class Workspace extends OpenBrowser
{
	private WebDriver driver;

	public Workspace(WebDriver driver)
  	{
  		this.driver = driver;
  		PageFactory.initElements(driver, this);
  	}
	
	public boolean verifyLoadMoreButton()
    {
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        Reporter.log("No. of Stories in Tab: "+workspaceFilters.getActiveTabCount());
        if(workspaceFilters.getActiveTabCount() >= 21)       // if the count is more than 20 then it will click on load more button
        {
            Reporter.log("Load More Button is Present");   
            return workspaceFilters.loadMoreButtonDisplayed();         
        }else
        {           
            Reporter.log("Load More Button is not Present");
            return true;
        }
    }
    
    public boolean verifyFilters(String type)
    {
        String[] storyState = {"open", "approval", "approved", "rejected", "scheduled", "published"};

        if(type.equalsIgnoreCase("breaking news") || type.equalsIgnoreCase("push notification") || type.equalsIgnoreCase("collection"))
        {
       	    clickTab(storyState[5], "testFilters");

            Assert.assertTrue((verifyFilter(type)), "Filter Functionality is Broken");

        }else
        {
			for(int i = 0; i < storyState.length; i++)  
        	{
        	    clickTab(storyState[i], "testFilters");

                Assert.assertTrue((verifyFilter(type)), "Filter Functionality is Broken");

        	}
        }
        return true;
    }

    public void clickTab(String state, String checkOperation)       // This method will click on Tab & verify the stories count on respective tab
    {   
        boolean flag = false;
        WorkspacePage workspacePage = new WorkspacePage(driver);
        switch(state)
        {
            case "open":      workspacePage.clickOpenTab();
//                              if(checkOperation.equalsIgnoreCase("testFilters"))
//                                flag = verifyFilterCount();    //  Check the count of stories in open tab
//                              else
//                                flag = verifyStoriesCountWithStory();
                              break;
            case "approval":  workspacePage.clickNeedApprovalTab();
//                              if(checkOperation.equalsIgnoreCase("testFilters"))
//                                flag = verifyFilterCount();    //  Check the count of stories in open tab
//                              else
//                                flag = verifyStoriesCountWithStory();
                              break;
            case "approved":  workspacePage.clickApprovedTab();
//                              if(checkOperation.equalsIgnoreCase("testFilters"))
//                                flag = verifyFilterCount();    //  Check the count of stories in open tab
//                              else
//                                flag = verifyStoriesCountWithStory();
                              break;
            
            case "published": workspacePage.clickPublishedLink();
//                              if(checkOperation.equalsIgnoreCase("testFilters"))
//                                flag = verifyFilterCount();    //  Check the count of stories in open tab
//                              else
//                                flag = verifyStoriesCountWithStory();
                              
                              //  else if(checkOperation.equalsIgnoreCase("testNotificationFilters"))
                              //    flag = workspaceFilters.verifyStoriesCountWithStory();
                              break;
            
            case "rejected":  workspacePage.clickRejectedTab();
//                              if(checkOperation.equalsIgnoreCase("testFilters"))
//                                flag = verifyFilterCount();    //  Check the count of stories in open tab
//                              else
//                                flag = verifyStoriesCountWithStory();
                              break;
            case "scheduled": workspacePage.clickScheduledTab();
//                              if(checkOperation.equalsIgnoreCase("testFilters"))
//                                flag = verifyFilterCount();    //  Check the count of stories in open tab
//                              else
//                                flag = verifyStoriesCountWithStory();
                              break;
            default :         break;

        }
        //return flag;
    }
    
    public boolean verifyFilterCount()
    {
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);        
        int tabCount = workspaceFilters.getActiveTabCount();
        if(tabCount == 0)
        {
            return false;
        }else
        {
            if(tabCount >= 21)   // if the count is more than 20 then it will click on load more button
            {
                workspaceFilters.clickLoadMoreButton();
                ScrollPage.scrollUP(driver);       // for scrolling the page till top
            }
            return true;
        }
    }

    public boolean verifyStoriesCountWithStory()
    {
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        workspaceFilters.clickLoadMoreButton();
        List<WebElement> ele = workspaceFilters.getStoryList();
        Reporter.log("Tab Count: "+workspaceFilters.getActiveTabCount());
        Reporter.log("Count of Listed Stories: "+ele.size()); 
        
        return Verification.verify(workspaceFilters.getActiveTabCount(), ele.size());
    }

    public boolean verifyFilter(String filterType)   //This method will verify the filter.
    {                                 //Parameter filertype is the type of filter we want to verify
        boolean flag = false;
        String todayDate = CurrentDate.getCurrentDateAndTime().substring(0, 11);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        if(todayDate.charAt(0)=='0')
            todayDate = todayDate.substring(1);
//        System.out.println("Todays Date : "+todayDate);
        switch(filterType)
        {
            case "photo"	: flag = Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2,"photo");
                                break;

            case "video"    : flag = Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2, "video");
                                break;

            case "listicle"		: flag = Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2, "listicle");
                                break;

            case "live blog"    : flag = Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2, "live blog");
                                break;

            case "text"    : flag = Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2, "text");
                                break;

            case "push notification" :  // flag = // write a separate method to handle 2 condition
                                break;

            case "breaking news" :   flag = Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2, "breaking news");
                                break;

            case "collection" :   flag = Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2, "Template: ");
                                break;

            case "today"    : flag = Verification.verifyAllListElementsContainsSame(workspaceFilters.getDateList(), todayDate);
                                break;

            case "week"     : flag = verifyDateRange(7);
                                break;

            case "month"    : flag = verifyDateRange(30);
                                break;

            case "customRange"  :   flag = verifyCustomDate();
                                    break;

            // case "section"  :

            case "author"   : flag = Verification.verifyAllListElementsAreSame(workspaceFilters.getAuthorList(), 2, dataPropertyFile.getProperty("AuthorName"));
                              break;

            default:   break;
        }
        return flag;
    }

    public boolean verifyDateRange(int range)
    {
        boolean flag = false;
        Date startDate = new Date();
        Date endDate = new Date();
        Date actualStoryDate = new Date();
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);  
        List<WebElement> listOfDate = workspaceFilters.getDateList();
        Calendar c = GregorianCalendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
        try{
              startDate = fmt.parse(df.format(c.getTime()));
              c.add(Calendar.DATE, -range);
              endDate = fmt.parse(df.format(c.getTime()));
            }catch(ParseException e)
            {
                e.printStackTrace();
            }
            for(int i = 0; i < listOfDate.size(); i++){
                try{
                      actualStoryDate = fmt.parse(listOfDate.get(i).getText());
            //          System.out.println(actualStoryDate+"  :"+ (k++));
                    }catch(ParseException e)
                    {
                        e.printStackTrace();
                    }
                    if(!(actualStoryDate.after(startDate) || actualStoryDate.before(endDate))){
                        flag = true;
        //                System.out.println("Within Range");
                    }
                    else{
        //                System.out.println("Not Within Range");
                        flag = false;
                        break;
                    }
            }
        return flag;
    }
    
    public boolean verifyCustomDate()
    {
        boolean flag = false;
        Date storyDate = new Date();
        Date startDate = new Date();
        Date endDate = new Date();
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);  
        List<WebElement> listOfDate = workspaceFilters.getDateList();
        SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
        String dateString = workspaceFilters.getSelectedDate().getText().replace("Updated: ", "").replace("x", "");

        dateString = dateString.replaceAll("(?<=[0-9])(?:st|nd|rd|th)", "");
        String[] startEndDate = dateString.split(" - ");
        String[] sDate = startEndDate[0].split(" ");
        String[] eDate = startEndDate[1].split(" ");

        try{
            startDate = fmt.parse(sDate[1]+" "+sDate[0]+" "+sDate[2]);
            endDate = fmt.parse(eDate[1]+" "+eDate[0]+" "+eDate[2]);
        }catch(ParseException e)
        {
            e.printStackTrace();
        }
        for(int i = 0; i < listOfDate.size(); i++)
        {
            try{
                storyDate = fmt.parse(listOfDate.get(i).getText());
        //        System.out.println(storyDate+"  :"+ (k++));
            }catch(ParseException e)
            {
                e.printStackTrace();
            }

            if(!(storyDate.after(endDate) || storyDate.before(startDate))){
    //            System.out.println("Within Range");
                flag = true;
            }
            else{
    //              System.out.println("Not Within Range");
                flag = false;
                break;
            }
        }
        return flag;
    }
			
}