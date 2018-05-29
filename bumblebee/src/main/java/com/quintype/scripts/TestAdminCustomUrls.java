package com.quintype.scripts;
import java.util.List;

import com.quintype.pom.*;
import com.quintype.util.CurrentDate;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;

public class TestAdminCustomUrls extends OpenBrowser {
    @BeforeClass(groups = {"functest", "platform","PlatformReg1"})
    public void loginToItsman()
    {
        if(driver.getTitle().equalsIgnoreCase("Login"))
        {
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
            loginPage.loginToApplication();
        }
    }

    @Test(groups = {"functest","platform","PlatformReg1"}, priority = 1)
    public void createCustomURL()
    {
        String sourcepath = "news/modi-speech:"+ CurrentDate.getCurrentDateAndTime();
        log.info("started creating CustomURl");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        CustomUrlPage customUrlPage = new CustomUrlPage(driver);

        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();

        customUrlPage.clickCustomUrlLink();
        customUrlPage.clickAddUrlLink();
        customUrlPage.enterSourceUrl(sourcepath);
        customUrlPage.selectTypeDropdown();
        customUrlPage.selectRedirect();
        customUrlPage.selectRedirectDropdown();
        customUrlPage.selectType();
        customUrlPage.clickSubmitButton();
        log.info("Created Custom-url");
    }
}
