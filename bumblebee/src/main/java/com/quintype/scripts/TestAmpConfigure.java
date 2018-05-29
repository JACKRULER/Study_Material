package com.quintype.scripts;

import com.quintype.pom.ConfigurePage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAmpConfigure extends OpenBrowser {
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
    public void testAmpConfigure(){
        log.info("Execution of TestAuthorProfile Started");
        WorkspacePage workspacePage = new WorkspacePage(driver);
        ConfigurePage configurePage = new ConfigurePage(driver);
        Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickAdminLink();
        workspacePage.clickConfigureLink();
        String successMessage = "Successfully updated AMP config";

        configurePage.clickAMPTab();
        configurePage.enterPrimaryColor(dataPropertyFile.getProperty("Primary_Color"));
        configurePage.enterSecondaryColor(dataPropertyFile.getProperty("Secondary_Color"));
        configurePage.enterHeaderBGColor(dataPropertyFile.getProperty("Header_Bg_Color"));
        configurePage.enterFooterBGColor(dataPropertyFile.getProperty("Footer_Bg_Color"));
        configurePage.enterFooterTextColor(dataPropertyFile.getProperty("Footer_Text_Color"));
        configurePage.enterSectionTitleColor(dataPropertyFile.getProperty("Section_Title_Color"));
        configurePage.enterBodyAdUnitPath(dataPropertyFile.getProperty("Body_Ad_Unit_Path"));
        configurePage.enterBodyAdWidth(dataPropertyFile.getProperty("Body_Ad_Width"));
        configurePage.enterBodyAdHeight(dataPropertyFile.getProperty("Body_Ad_Height"));
        configurePage.enterTopAdUnitPath(dataPropertyFile.getProperty("Top_Ad_Unit_Path"));
        configurePage.enterTopAdWidth(dataPropertyFile.getProperty("Top_Ad_Width"));
        configurePage.enterTopAdHeight(dataPropertyFile.getProperty("Top_Ad_Height"));
        configurePage.enterBottomAdUnitPath(dataPropertyFile.getProperty("Bottom_Ad_Unit_Path"));
        configurePage.enterBottomAdWidth(dataPropertyFile.getProperty("Bottom_Ad_Width"));
        configurePage.enterBottomAdHeight(dataPropertyFile.getProperty("Bottom_Ad_Height"));
        configurePage.enterFontFamilyPrimary(dataPropertyFile.getProperty("Primary_Font_Family"));
        configurePage.enterFontCSSPrimary(dataPropertyFile.getProperty("Primary_Font_CSS"));
        configurePage.enterFontFamilySecondary(dataPropertyFile.getProperty("Secondary_Font_Family"));
        configurePage.enterFontCSSSecondary(dataPropertyFile.getProperty("Secondary_Font_CSS"));
        //configurePage.enterTrackingIdGa(dataPropertyFile.getProperty("Ga_Tracking_ID"));
        //configurePage.enterGtmId(dataPropertyFile.getProperty("Gtm_ID"));
        //configurePage.enterComscore1(dataPropertyFile.getProperty("Comscore_C1"));
        //configurePage.enterComscore2(dataPropertyFile.getProperty("Comscore_C2"));
        configurePage.enterURLLogo(dataPropertyFile.getProperty("Logo_URL"));
        configurePage.clickAMPSaveButton();
        Assert.assertEquals(configurePage.getSuccessMessage(),successMessage);
    }
}
