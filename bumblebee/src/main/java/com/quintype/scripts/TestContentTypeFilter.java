package com.quintype.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Workspace;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspaceFilters;


public class TestContentTypeFilter extends OpenBrowser
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

    @Test(groups = {"functest", "platform"}, priority = 1)
    public void testStoryTypeFiltersPhoto()
    {
        log.info("Execution of TestContentTypeFilter Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();      // Click on filter bar of workspace page
     //   workspaceFilters.clickClearButton();
        workspaceFilters.selectStoryCheckbox();     // Select story check box
        workspaceFilters.selectPhotoCheckbox();     // Select photo story check box
        workspaceFilters.clickApply();              // Click apply button
        workspace.verifyFilters("photo");
    }

    @Test(groups = {"functest", "platform"}, priority = 2)
    public void testStoryTypeFiltersVideo() {
        log.info("Execution of TestContentTypeFilter Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();
      //  workspaceFilters.clickClearButton();        // Click on clear button to set new filter
        workspaceFilters.selectStoryCheckbox();
        workspaceFilters.selectVideoCheckbox();
        workspaceFilters.clickApply();
        workspace.verifyFilters("video");
    }

    @Test(groups = {"functest", "platform"}, priority = 3)
    public void testStoryTypeFiltersListicle() {
        log.info("Execution of TestContentTypeFilter Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();
       // workspaceFilters.clickClearButton();
        workspaceFilters.selectStoryCheckbox();
        workspaceFilters.selectListicleCheckbox();
        workspaceFilters.clickApply();
        workspace.verifyFilters("listicle");
    }


    @Test(groups = {"functest", "platform"}, priority = 4)
    public void testStoryTypeFiltersLiveBlog() {
        log.info("Execution of TestContentTypeFilter Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();
      //  workspaceFilters.clickClearButton();
        workspaceFilters.selectStoryCheckbox();
        workspaceFilters.selectLiveBlogCheckbox();
        workspaceFilters.clickApply();

        workspace.verifyFilters("live blog");
    }


    @Test(groups = {"functest", "platform"}, priority = 5)
    public void testStoryTypeFiltersText() {
        log.info("Execution of TestContentTypeFilter Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspaceFilters.clickFiltersDrawer();
      //  workspaceFilters.clickClearButton();
        workspaceFilters.selectStoryCheckbox();
        workspaceFilters.selectTextCheckbox();
        workspaceFilters.clickApply();

        workspace.verifyFilters("text");
    }

//    @Test(groups = {"functest", "platform"}, priority = 6)
//    public void testPushNotificationFilter()
//    {
//        String[] storyStatus = {"published"};
//        LoginPage loginPage = new LoginPage(driver);
//        WorkspacePage workspacePage = new WorkspacePage(driver);
//        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);
//        Workspace workspace = new Workspace(driver);
//
//        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
//
//        workspaceFilters.clickFiltersDrawer();
//        workspaceFilters.selectPushNotificationCheckBox();
//        workspaceFilters.clickApply();
//
//        workspace.verifyFilters("push notification");
//
//    }


    //Verifying the Breaking News filter on workspace page
    @Test(groups = {"functest", "platform"}, priority = 7)
    public void testBreakingNewsFilter()
    {
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");

        workspaceFilters.clickFiltersDrawer();
        workspaceFilters.selectBreakingNewsCheckBox();
        workspaceFilters.clickApply();
        workspace.verifyFilters("breaking news");

    }

     //Verifying the Collection filter on workspace page
    @Test(groups = {"functest", "platform"}, priority = 8)
    public void testCollectionFilter()
    {
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        Workspace workspace = new Workspace(driver);
        WorkspaceFilters workspaceFilters = new WorkspaceFilters(driver);

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        log.debug("Test Collection Filter =====>");

        workspaceFilters.clickFiltersDrawer();
        workspaceFilters.selectCollectionCheckBox();
        workspaceFilters.clickApply();

        workspace.verifyFilters("collection");
    }

}

    