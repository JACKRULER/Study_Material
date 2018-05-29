package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Workspace;
import com.quintype.pom.NewStoryPage;
import com.quintype.pom.WorkspaceFilters;

import org.testng.Reporter;

public class TestDateFilter extends OpenBrowser
{   
	@BeforeClass(groups = {"functest", "platform"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	
   // Verifying the Date Related filters on workspace page
 //  @Test(groups = {"functest", "platform"}, priority = 1)
   public void testDateFilter()
   {
	   log.info("Execution of TestDateFilter Started => Date Filter");
       WorkspacePage workspacePage = new WorkspacePage(driver);
       Workspace workspace = new Workspace(driver);          
       WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);


       Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
       workspaceFilters.clickFiltersDrawer();
       workspaceFilters.selectTodayCheckbox();
       workspaceFilters.clickApply();
       
       workspace.verifyFilters("today");

       workspaceFilters.clickClearButton();
       workspaceFilters.selectThisWeekCheckbox();
       workspaceFilters.clickApply();

       workspace.verifyFilters("week");
       
       workspaceFilters.clickClearButton();
       workspaceFilters.selectThisMonthCheckbox();
       workspaceFilters.clickApply();
   
       workspace.verifyFilters("month");

       workspaceFilters.clickClearButton();
       workspaceFilters.selectCustomRangeCheckbox();
       workspaceFilters.selectFromDate();
       workspaceFilters.clickApply();

       workspace.verifyFilters("customRange");
       
   }
                   
    // Verifying the Author filter on workspace page                   
    @Test(groups = {"functest", "platform"}, priority = 2)
    public void testAuthorFilter()
    {
 	   	log.info("Execution of TestDateFilter Started => Author Filter");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);         
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);


		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();
        workspaceFilters.selectAuthor(dataPropertyFile.getProperty("AuthorName"));
        workspaceFilters.clickApply();
        workspace.verifyFilters("author");
        
    }       
    		
    // Method to verify the Section filter on workspace page (Verifying only one story in published tab)
    @Test(groups = {"functest", "platform"}, priority = 3)
    public void testSectionsFilter()
    {
 	   	log.info("Execution of TestDateFilter Started => Section Filter");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        NewStoryPage newStoryPage = new NewStoryPage(driver);        


		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();
        workspaceFilters.selectSectionName(dataPropertyFile.getProperty("Section_Name"));
        workspaceFilters.getSectionName();
        workspaceFilters.clickApply();
        workspacePage.clickPublishedLink();
        Assert.assertTrue(workspacePage.checkStoriesPresentInTab(), "Stories not found in Published tab");
        workspacePage.clickFirstStory();
        newStoryPage.clickMetadataLink();
        Assert.assertTrue(workspaceFilters.compareSectionName(), "Section Filter Functionality is Broken");
        
    }  

}
