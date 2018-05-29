package com.quintype.scripts;

import com.quintype.pom.AddAuthorPage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.CurrentDate;
import com.quintype.util.OpenBrowser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAddAuthor extends OpenBrowser {

    @BeforeClass(groups = {"functest", "platform"})
    public void testAddAuthorLogin()
    {
        if(!driver.getTitle().equalsIgnoreCase("Login"))
        {
            WorkspacePage workspace = new WorkspacePage(driver);
            workspace.clickProfileButton();
            workspace.clickLogoutLink();
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
            loginPage.loginToAddAuthor();
        }
    }

    @Test(groups = {"functest", "platform"}, priority = 1)
    public void testAddAuthor()
    {
        log.info("Execution of TestAddAuthor Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        AddAuthorPage addAuthorPage = new AddAuthorPage(driver);
        CurrentDate currentDate = new CurrentDate();
        String name = currentDate.getCurrentDateForEmail();
        String email = currentDate.getCurrentDateForEmail()+"@gmail.com";
        String successMessage = "Successfully created author with Guest Contributor role.";

        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickWorkspaceMenuButton();
        Assert.assertEquals("Add Author" , workspacePage.addAuthor());
        addAuthorPage.enterAuthorName(name);
        addAuthorPage.enterAuthorEmail(email);
        addAuthorPage.enterAuthorCommunicationEmail(email);
        addAuthorPage.enterAuthorBio(dataPropertyFile.getProperty("bio_Field"));
        addAuthorPage.clickAddAuthorButton();
        Assert.assertEquals(addAuthorPage.getSuccessMessage(),successMessage);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickLogoutLink();
    }
}
