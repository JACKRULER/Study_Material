package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.pom.LoginPage;
import com.quintype.pom.MediaLibraryPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.pom.PlannerPage;
import com.quintype.pom.CollectionPagesPage;
import com.quintype.pom.ConfigurePage;
import com.quintype.pom.EntitiesManagerPage;
import com.quintype.pom.TagManagerPage;
import com.quintype.pom.APIManagerPage;
import com.quintype.pom.AdminSettingsAttributesPage;
import com.quintype.pom.AdminSettingsMenu;
import com.quintype.pom.AdminSettingsPage;
import com.quintype.pom.AdminSettingsSectionsPage;
import com.quintype.pom.AdminSettingsStacksPage;
import com.quintype.pom.AnalyticsPage;
import com.quintype.pom.SEOMetadataPage;
import com.quintype.pom.SortersPage;
import org.testng.Assert;
import org.testng.Reporter;

public class TestPagesLoaded extends OpenBrowser
{					
	@BeforeClass(groups = {"functest", "smoketest", "platform","PlatformReg1"})
	public void loginToItsman()
	{
        WorkspacePage workspacePage = new WorkspacePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
            loginPage.loginToApplication();
        }
		else
        {
            workspacePage.clickWorkspaceMenuButton();
            workspacePage.clickWorkspaceLink();
            workspacePage.clickProfileButton();
            workspacePage.clickLogoutLink();
            Assert.assertEquals(driver.getTitle(), "Login", "Itsman is in login page");
            loginPage.loginToApplication();
        }
	}	
	
	// TC ID: 
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 2) // Verifying all the workspace menu pages are loaded
    public void testPlannerPageLoaded() 
    {
    	log.info("Execution of TestPagesLoaded Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        PlannerPage plannerPage = new PlannerPage(driver);

		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickPlannerLink();
        Assert.assertTrue(plannerPage.verifyStoriesLinkStatus(), "Planner page is not loading ");
        
    }
    
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 3) // Verifying all the workspace menu pages are loaded
    public void testCollectionPagesPageLoaded() 
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        CollectionPagesPage collectionPagesPage = new CollectionPagesPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickPagesLink();
        Assert.assertTrue(collectionPagesPage.verifySearchField(), "Collection Pages page is not loading");
        
    }			
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 4) // Verifying all the workspace menu pages are loaded
    public void testSortersPageLoaded() 
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        SortersPage sortersPage = new SortersPage(driver);
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickSortersLink();
        workspacePage.clickTopStoriesLink();
        Assert.assertTrue(sortersPage.verifySearchField(), "Top Stories Sorters page is not loading");
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickSortersLink();
        workspacePage.clickBreakingNewsLink();
        Assert.assertTrue(sortersPage.verifySearchField(), "Breaking News Sorters page is not loading");
        
    }	
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 5) // Verifying all the workspace menu pages are loaded
    public void testAnalyticsPageLoaded() 
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        AnalyticsPage analyticsPage = new AnalyticsPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAnalyticsLink();
        workspacePage.clickStoryAnalyticsLink();
        Assert.assertTrue(analyticsPage.verifyAnalyticsFilterDisplayed());
        
    }       
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 6) // Verifying all the workspace menu pages are loaded
    public void testTagManagerPageLoaded() 
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        TagManagerPage tagManagerPage = new TagManagerPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickTagManagerLink();
        Assert.assertTrue(tagManagerPage.verifyTagsLabel(), "Tag Manager page is not loading ");
        
    }
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 7) // Verifying all the workspace menu pages are loaded
    public void testAdminSettingsPagesLoaded() 
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        AdminSettingsPage adminSettingsPage  = new AdminSettingsPage(driver);
        AdminSettingsStacksPage adminSettingsStacksPage = new AdminSettingsStacksPage(driver);
        AdminSettingsSectionsPage adminSettingsSectionsPage = new AdminSettingsSectionsPage(driver);
        AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
        AdminSettingsAttributesPage adminSettingsAttributesPage = new AdminSettingsAttributesPage(driver);
    
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickSettingsLink();
       
        adminSettingsStacksPage.clickStacksLink();
        Assert.assertTrue(adminSettingsStacksPage.verifyAddStackButton(), "Admin - Settings - Stack page is not loading ");
   
        adminSettingsPage.clickSettingsLink();
        Assert.assertTrue(adminSettingsPage.verifySettingsSaveButton(), "Admin - Settings - Settings page is not loading ");
        
        adminSettingsSectionsPage.clickSectionsLink();
        Assert.assertTrue(adminSettingsSectionsPage.verifyAddSectionButton(), "Admin - Settings - Sections page is not loading ");
        
        adminSettingsMenu.clickMenuLink();
        Assert.assertTrue(adminSettingsMenu.verifyAddMenuItemButton(), "Admin - Settings - Menu page is not loading ");
        
        adminSettingsPage.clickHTMLSnippetsLink();
        Assert.assertTrue(adminSettingsPage.verifyAddSnippetButton(), "Admin - Settings - HTML Snippets page is not loading ");

        adminSettingsPage.clickSocialLink();
        Assert.assertTrue(adminSettingsPage.verifySocialSaveButton(), "Admin - Settings - Social page is not loading ");

        adminSettingsAttributesPage.clickAttributesLink();
        Assert.assertTrue(adminSettingsAttributesPage.verifyAddAttributeButton(), "Admin - Settings - Attribute page is not loading ");

        adminSettingsPage.clickOtherLink();
        Assert.assertTrue(adminSettingsPage.verifyOtherSaveButton(), "Admin - Settings - Other page is not loading ");
        
    }
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 8) // Verifying all the workspace menu pages are loaded
    public void testAdminSEOMetadataPageLoaded() 
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        SEOMetadataPage seoMetadataPage = new SEOMetadataPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickSEOMetadataLink();
        Assert.assertTrue(seoMetadataPage.verifyFilterMenuField(), "Admin - SEO Metadata page is not loading ");
        
    }
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 9) // Verifying all the workspace menu pages are loaded
    public void testAdminConfigurePageLoaded() 
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        ConfigurePage configurePage = new ConfigurePage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickConfigureLink();
        Assert.assertTrue(configurePage.verifyCardHeaderDisplayed(), "Admin - Configure page is not loading ");
        
    }
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 10)
    public void testEntityPageLoaded()
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        Assert.assertTrue(entitiesManagerPage.isEntitySeachFieldDisplayed(), "Entity Manager page is not loading ");
    }
    
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 11)
    public void testAPIManagerPageLoaded()
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        APIManagerPage apiManagerPage = new APIManagerPage(driver);
        
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAPIManagerLink();
        Assert.assertTrue(apiManagerPage.verifyAddEndPointButton(), "API Manager page is not loading ");
        
    }
    @Test(groups = {"functest", "smoketest", "platform","PlatformReg1"}, priority = 12)
    public void testMediaLibraryPageLoaded()
    {
        WorkspacePage workspacePage = new WorkspacePage(driver);
        MediaLibraryPage mediaLibraryPage = new MediaLibraryPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickMediaLibraryLink();
        Assert.assertTrue(mediaLibraryPage.verifyMediaLibraryTitle(), "Media Library page is not loading ");
        
    }
    
}


