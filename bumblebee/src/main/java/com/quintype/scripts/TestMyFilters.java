package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;
import com.quintype.pom.WorkspaceFilters;


public class TestMyFilters extends OpenBrowser
{   
	@BeforeClass(groups = {"functest", "smoketest"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	
	
	// Test Case: Filters_TC_02 => Verifying user is able to save a filter.
    @Test(groups = { "functest", "smoketest" }, priority = 1) 
    public void testUserAbleToSaveFilter()
    {
    	log.info("Execution of TestMyFilters Started");
    	String filterName = "TC 02";
        WorkspacePage workspacePage = new WorkspacePage(driver);  
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();           
        workspaceFilters.selectStoryCheckbox();     
        workspaceFilters.selectPhotoCheckbox();     
        workspaceFilters.clickApply();              
        workspaceFilters.clickFilterSaveButton();	
        workspaceFilters.enterFilterName(filterName);	
        workspaceFilters.clickFilterSaveButton();	
        workspaceFilters.clickFilterListTab();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), filterName), "Saved Filter not displaying in My Filter List of Filter List");
    
    }		
    
    // Test Case: Filters_TC_03 => Verifying user is able to save a filter & then clear it
    @Test(groups = { "functest", "smoketest" }, priority = 2) 
    public void testUserAbleToClearFilterAfterSaving()
    {    	
    	String filterName = "TC 03";
        WorkspacePage workspacePage = new WorkspacePage(driver);  
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();             
        workspaceFilters.selectStoryCheckbox();     
        workspaceFilters.selectVideoCheckbox();     
        workspaceFilters.clickApply();              
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.enterFilterName(filterName);
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.clickClearButtonAfterSave();
        workspacePage.clickPublishedLink();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getStoryTypeList(), "Photo"), "Clearing the filter functionality is not working properly");
    
    }		
    		
    // Test Case: Filters_TC_04 => Verifying user is able to save a filter & then update the same
    @Test(groups = { "functest", "smoketest" }, priority = 3)  
    public void testUserAbleToUpdateFilter()
    {
    	String filterName = "TC 04";
        WorkspacePage workspacePage = new WorkspacePage(driver);  
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();      
        workspaceFilters.selectStoryCheckbox();     
        workspaceFilters.selectPhotoCheckbox();     
        workspaceFilters.clickApply();              
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.enterFilterName(filterName);
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.selectVideoCheckbox();
        workspaceFilters.clickApply();
        workspaceFilters.clickUpdateButton();
        workspaceFilters.clickClearButton();
        workspaceFilters.clickFilterListTab();
        workspaceFilters.clickMyCreatedFilter(filterName);
        workspaceFilters.clickFilterOptionsTab();
        Assert.assertTrue(workspaceFilters.verifyCheckboxSelected("Video"), "Update filter functionality is broken, not updating the saved filter");

    }

    // Test Case: Filters_TC_05 => Verifying user is able to save as a saved filter
    @Test(groups = { "functest", "smoketest" }, priority = 4) 
    public void testUserAbleToSaveASFilter()
    {
    	String filterName = "TC 05";
    	String updatedFilterName = "Updated Filter";
        WorkspacePage workspacePage = new WorkspacePage(driver);  
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();      // Click on filter bar of workspace page
        workspaceFilters.selectStoryCheckbox();     // Select story check box
        workspaceFilters.selectListicleCheckbox();     // Select photo story check box
        workspaceFilters.clickApply();              // Click apply button
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.enterFilterName(filterName);
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.clickSaveAsButton();
        workspaceFilters.enterFilterName(updatedFilterName);
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.clickFilterListTab();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), updatedFilterName), "Save as filter is not listing or displaying under My Filter list.");

    }

    // Test Case: Filters_TC_06 => Verifying user is able to use already saved filter
    @Test(groups = { "functest", "smoketest" }, dependsOnMethods = {"testUserAbleToSaveFilter"}, priority = 5)  
    public void testUserAbleToUseAlreadySavedFilter()
    {
    	// For this Test we are using Filter which we created in testUserAbleToSaveFilter method. Thats why using the same filterName.
    	String filterName = "TC 02";	
        WorkspacePage workspacePage = new WorkspacePage(driver);  
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();             // Click on filter bar of workspace page
        workspaceFilters.clickFilterListTab();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), filterName), "Saved filter is not displaying in My Filter list.");
        workspaceFilters.clickMyCreatedFilter(filterName);
        workspaceFilters.clickFilterOptionsTab();        
        workspaceFilters.clickApply();
        workspacePage.clickPublishedLink();
        Assert.assertTrue(Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2, "Photo"));

    }    
			
    // Test Case: Filters_TC_07 => Verifying user is able to use already saved filter & clear after using it
    @Test(groups = { "functest", "smoketest" }, dependsOnMethods = {"testUserAbleToSaveFilter"}, priority = 6)  
    public void testUserAbleToClearAlreadySavedFilter()
    {
    	// For this Test we are using Filter which we created in testUserAbleToSaveFilter method. Thats why using the same filterName.
    	String filterName = "TC 02";
        WorkspacePage workspacePage = new WorkspacePage(driver);  
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();
        workspaceFilters.clickFilterListTab();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), filterName),"Saved filter is not showing in My Filter list.");
        workspaceFilters.clickMyCreatedFilter(filterName);
        workspaceFilters.clickFilterOptionsTab();        
        workspaceFilters.clickApply();
        workspacePage.clickPublishedLink();
        Assert.assertTrue(Verification.verifyAllListElementsAreSame(workspaceFilters.getStoryTypeList(), 2, "Photo"));
        workspaceFilters.clickClearButton();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getStoryTypeList(), "Text"));
        
    }
    			
    //	Test Case: Filters_TC_08 => Verifying user is able to share a saved filter
//    @Test(groups = { "functest", "smoketest" }, priority = 7)  
//    public void testUserAbleToShareFilter()
//    {
//    	String filterName = "TC 08";
//        WorkspacePage workspacePage = new WorkspacePage(driver);  
//        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
//        
//		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
//        workspaceFilters.clickFiltersDrawer();
//        workspaceFilters.selectStoryCheckbox();     // Select story check box
//        workspaceFilters.selectPhotoCheckbox();     // Select photo story check box
//        workspaceFilters.clickApply();              // Click apply button
//        workspaceFilters.clickFilterSaveButton();
//        workspaceFilters.enterFilterName(filterName);
//        workspaceFilters.clickFilterSaveButton();
//        workspaceFilters.clickFilterListTab();
//        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), filterName));
//        workspaceFilters.hoverOnMyFilterOptions(filterName);
//        workspaceFilters.clickShareLink(filterName);
//        Assert.assertTrue(workspaceFilters.verifyMyFilterShared(filterName), "Global symbol not displaying next to shatrred filter.");
//        
//    }    
    		
    //	Test Case: Filters_TC_09 => Verifying user is able to unshare a shared saved filter
    @Test(groups = { "functest", "smoketest" }, priority = 8)  
    public void testUserAbleToUnshareFilter()
    {
    	String filterName = "TC 09";
        WorkspacePage workspacePage = new WorkspacePage(driver);  
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();
        workspaceFilters.selectStoryCheckbox();     // Select story check box
        workspaceFilters.selectLiveBlogCheckbox();     // Select photo story check box
        workspaceFilters.clickApply();              // Click apply button
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.enterFilterName(filterName);
        workspaceFilters.clickFilterSaveButton();
        workspaceFilters.clickFilterListTab();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), filterName), "Saved filter not displaying in My Filter list.");
        workspaceFilters.hoverOnMyFilterOptions(filterName);
        workspaceFilters.clickShareLink(filterName);
        Assert.assertTrue(workspaceFilters.verifyMyFilterShared(filterName), "Filter is not getting shared");
        workspaceFilters.clickFilterOptionsTab();
        workspaceFilters.clickFilterListTab();
        workspaceFilters.hoverOnMyFilterOptions(filterName);
        workspaceFilters.clickUnshareLink(filterName);
        Assert.assertFalse(workspaceFilters.verifyMyFilterShared(filterName), "Filter is not getting unshared");
        
    }    
    		
    // Test Case: Filters_TC_010 => Verify whether user is able to delete a saved filter
    @Test( groups = {"functest", "smoketest"}, priority = 9)
    public void testUserAbleToDeleteFilter()
    {
    	String filterName = "TC 010";
    	WorkspacePage workspacePage = new WorkspacePage(driver);
    	WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
    	workspaceFilters.clickFiltersDrawer();
    	workspaceFilters.selectStoryCheckbox();
    	workspaceFilters.selectTextCheckbox();
    	workspaceFilters.clickApply();
    	workspaceFilters.clickFilterSaveButton();
    	workspaceFilters.enterFilterName(filterName);
    	workspaceFilters.clickFilterSaveButton();
    	workspaceFilters.clickFilterListTab();
        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), filterName),"Created Filter ["+filterName+"] not displaying in Filter List");
    	workspaceFilters.hoverOnMyFilterOptions(filterName);
    	workspaceFilters.clickDeleteLink(filterName);
    	Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(workspaceFilters.getSavedFiltersList(), filterName));
   
    }
    
    // Test Case: Filters_TC_011 => Verify whether the user is able to set the filter as default on "Filter list" tab. 
//    @Test(groups = {"functest", "smoketest"}, priority = 10)
//    public void testUserAbleToSetDefaultFilter()
//    {
//    	String filterName = "TC 011";
//    	LoginPage logingPage = new LoginPage(driver);
//    	WorkspacePage workspacePage = new WorkspacePage(driver);
//    	WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
//    	
//		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
//    	workspaceFilters.clickFiltersDrawer();
//    	workspaceFilters.selectStoryCheckbox();
//    	workspaceFilters.selectPhotoCheckbox();
//    	workspaceFilters.clickApply();
//    	workspaceFilters.clickFilterSaveButton();
//    	workspaceFilters.enterFilterName(filterName);
//    	workspaceFilters.clickFilterSaveButton();
//    	workspaceFilters.clickFilterListTab();
//    	workspaceFilters.hoverOnMyFilterOptions(filterName);
//    	workspaceFilters.clickSetAsDefaultFilterLink(filterName);
//    	Assert.assertTrue(workspaceFilters.verifyDefaultFilterSet(filterName), "My filter is not getting set as a default");
//    	
//    	workspacePage.clickProfileButton();
//    	Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
//    	
//    	logingPage.loginToApplication();
//    	workspaceFilters.clickFiltersDrawer();
//    	Assert.assertTrue(workspaceFilters.verifyCheckboxSelected("Photo"), "Default filter is not set");
//    	workspaceFilters.closeFilterDrawer();
//    	workspacePage.clickPublishedLink();
//    	Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getStoryTypeList(), "Photo"));
//    	
//    }

    // TC ID: My_Filters_TC_013 => Verify whether the user is able to unset the filter as default on "Filter list" tab.    
    /*@Test(groups = {"functest", "smoketest"}, dependsOnMethods = {"testUserAbleToSetDefaultFilter"}, priority = 12)
    public void testUserAbleToRemoveDefaultFilter()
    {
    	String filterName = "TC 011";
    	WorkspacePage workspacePage = new WorkspacePage(driver);
    	WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
    	
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
    	workspaceFilters.clickFiltersDrawer();
    	Assert.assertTrue(workspaceFilters.verifyCheckboxSelected("Photo"), "Default filter is not set");
    	workspaceFilters.clickFilterListTab();
    	workspaceFilters.hoverOnMyFilterOptions(filterName);
    	workspaceFilters.clickRemoveDefaultFilter(filterName);
    	Assert.assertFalse(workspaceFilters.verifyDefaultFilterSet(filterName), "User not able to remove default filter");
    	driver.navigate().refresh();
    	workspacePage.clickPublishedLink();
    	Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getStoryTypeList(), "Text"));
    	
    }
    */
//    // TC ID: My_Filters_TC_014 => Verifying user is able to share a saved filter & other user is able to see & use that shared filter
//    @Test(groups = { "functest", "smoketest" }, priority = 13)  
//    public void testAnotherUserIsAbleToUseShareFilter()
//    {
//    	String filterName = "TC 014";
//        LoginPage loginPage = new LoginPage(driver);
//        WorkspacePage workspacePage = new WorkspacePage(driver);  
//        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
//        
//		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
//        workspaceFilters.clickFiltersDrawer();
//        workspaceFilters.selectStoryCheckbox();     
//        workspaceFilters.selectPhotoCheckbox();     
//        workspaceFilters.clickApply();              
//        workspaceFilters.clickFilterSaveButton();
//        workspaceFilters.enterFilterName(filterName);
//        workspaceFilters.clickFilterSaveButton();
//        workspaceFilters.clickFilterListTab();
//        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), filterName));
//        workspaceFilters.hoverOnMyFilterOptions(filterName);
//        workspaceFilters.clickShareLink(filterName);
//        Assert.assertTrue(workspaceFilters.verifyMyFilterShared(filterName));
//        
//        workspacePage.clickProfileButton();    
//        Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
//        
//		loginPage.enterUserName(dataPropertyFile.getProperty("Username1"));		// Enter valid username
//		loginPage.enterPassword(dataPropertyFile.getProperty("Password1"));		// Enter valid password
//		loginPage.clickLoginButton();											// Click on Login button
//		workspaceFilters.clickFiltersDrawer();
//		workspaceFilters.clickFilterListTab();
//		Assert.assertTrue(workspaceFilters.verifySharedFilterVisibleToAnotherUser(filterName),"Share filter is not visible to the other users");
//        
//    }    
    
//    // TC ID: My_Filters_TC_015 => Verify other user is not able to use shared filter. If not have permission.
//    @Test(groups = { "functest", "smoketest" }, priority = 14)  
//    public void testAnotherUserNotAbleToSeeShareFilterIfDeleted()
//    {
//    	String filterName = "TC 015";
//        LoginPage loginPage = new LoginPage(driver);
//        WorkspacePage workspacePage = new WorkspacePage(driver);  
//        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
//        
//		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
//        workspaceFilters.clickFiltersDrawer();
//        workspaceFilters.selectStoryCheckbox();     // Select story check box
//        workspaceFilters.selectPhotoCheckbox();     // Select photo story check box
//        workspaceFilters.clickApply();              // Click apply button
//        workspaceFilters.clickFilterSaveButton();
//        workspaceFilters.enterFilterName(filterName);
//        workspaceFilters.clickFilterSaveButton();
//        workspaceFilters.clickFilterListTab();
//        Assert.assertTrue(Verification.verifyExpectedElementPresentInList(workspaceFilters.getSavedFiltersList(), filterName));
//        workspaceFilters.hoverOnMyFilterOptions(filterName);
//        workspaceFilters.clickShareLink(filterName);
//        Assert.assertTrue(workspaceFilters.verifyMyFilterShared(filterName));
//        
//        workspacePage.clickProfileButton();    
//        Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
//        
//		loginPage.enterUserName(dataPropertyFile.getProperty("Username1"));		// Enter valid username
//		loginPage.enterPassword(dataPropertyFile.getProperty("Password1"));		// Enter valid password
//		loginPage.clickLoginButton();											// Click on Login button
//		workspaceFilters.clickFiltersDrawer();
//		workspaceFilters.clickFilterListTab();
//		Assert.assertTrue(workspaceFilters.verifySharedFilterVisibleToAnotherUser(filterName),"Share filter is not visible to the other users");
//		
//		workspacePage.clickProfileButton();    
//        Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
//        
//        loginPage.loginToApplication();
//        workspaceFilters.clickFiltersDrawer();
//        workspaceFilters.clickFilterListTab();
//        workspaceFilters.hoverOnMyFilterOptions(filterName);
//        workspaceFilters.clickDeleteLink(filterName);
//        workspaceFilters.clickFilterOptionsTab();
//
//		workspacePage.clickProfileButton();    
//        Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
//        
//		loginPage.enterUserName(dataPropertyFile.getProperty("Username1"));		// Enter valid username
//		loginPage.enterPassword(dataPropertyFile.getProperty("Password1"));		// Enter valid password
//		loginPage.clickLoginButton();											// Click on Login button
//		workspaceFilters.clickFiltersDrawer();
//		workspaceFilters.clickFilterListTab();
//		
//		Assert.assertFalse(workspaceFilters.verifySharedFilterVisibleToAnotherUser(filterName),"Share filter is not visible to the other users");
//        
//    }
    
}