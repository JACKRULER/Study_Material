package com.quintype.scripts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.quintype.pom.AdminSettingsMenu;
import com.quintype.pom.AdminSettingsSectionsPage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.CheckAlert;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;

public class TestAdminSettingsMenu extends OpenBrowser{
	
	@BeforeClass(groups = {"functest", "platform", "PlatformReg2"})
	public void loginToItsman()
	{
		if(driver.getTitle().equalsIgnoreCase("Login"))
		{
			LoginPage loginPage = new LoginPage(driver);
			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
			loginPage.loginToApplication();	
		}
	}	

	// TC ID: Admin_Settings_Menu_TC_01 => Creation of Menu without Parent Menu.
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 1)
	public void testCreateMenuWithoutParent()
	{
		String menuName = "Menu_TC_01";
		String sectionName = "News";
		String color = "#800002";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(menuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.selectSectionType(sectionName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(menuName), menuName));
		
	}
	// TC ID: Admin_Settings_Menu_TC_02 => Creation of Menu with Parent Menu.
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 2, dependsOnMethods = "testCreateMenuWithoutParent")
	public void testCreateMenuWithParent()
	{
		String menuName = "Menu_TC_02";
		String parentMenuName = "Menu_TC_01";
		String sectionName = "News";
		String color = "#335599";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(menuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.selectParentMenuItem(parentMenuName);
		adminSettingsMenu.selectSectionType(sectionName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(menuName), menuName));
		
	}
	//TC ID: Admin_Settings_Menu_TC_03 => Verify Mandatory Fields.
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 3)
	public void testMandatoryFields()
	{
		String errorMessages[] = {"Item name cannot be empty.", "Color cannot be empty.", "Please select a section."};
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.compareTwoListOfString(adminSettingsMenu.getListOfErrorMessages(), errorMessages));
		adminSettingsMenu.clickCloseButton();
		
	}	
					
	@DataProvider(name = "colorValidation")
	public Object[][] createData() 
	{
		return new Object[][] { {"$black"}, {"123123"}, { "#qqqwww"}, {"#12345"}, {"#7777777"}, {"#$$$%%%"},};
	}
	//TC ID: Admin_Settings_Menu_TC_04 => Validate Color field.
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 4, dataProvider = "colorValidation", dependsOnMethods = "testCreateMenuWithoutParent")
	public void testColorFieldsValidation(String colors)
	{
		String menuName = "Menu_TC_04";
		String sectionName = "News";
		String errorMessages[] = {"Please enter a valid HTML color."};
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(menuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(colors);
		adminSettingsMenu.selectSectionType(sectionName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.compareTwoListOfString(adminSettingsMenu.getListOfErrorMessages(), errorMessages));
		adminSettingsMenu.clickCloseButton();

	}
	//TC ID: Admin_Settings_Menu_TC_05 => Verify Deletion of Parent Menu Item should not allow. 
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 5)
	public void testDeletionOfParentMenu()
	{
		String parentMenuName = "Parent_Menu_TC_05";
		String childMenuName = "Child_Menu_TC_05";
		String sectionName = "News";
		String color = "#000008";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(parentMenuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.clickSectionItemType();
		adminSettingsMenu.selectSectionType(sectionName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(parentMenuName), parentMenuName));
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(childMenuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.selectParentMenuItem(parentMenuName);
		adminSettingsMenu.clickSectionItemType();
		adminSettingsMenu.selectSectionType(sectionName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(childMenuName), childMenuName));
		adminSettingsMenu.clickDeleteButton(parentMenuName);
		CheckAlert.verifyAcceptAlert(driver);
		Assert.assertFalse(CheckAlert.verifyAcceptAlert(driver));
		
	}			
	
	//TC ID: Admin_Settings_Menu_TC_06 => 
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 6)
	public void testCreateMenuOfTypeSection()
	{
		String menuName = "Menu_TC_06";
		String sectionName = "News";
		String color = "#800000";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(menuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.clickSectionItemType();
		adminSettingsMenu.selectSectionType(sectionName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(menuName), menuName));
		Assert.assertTrue(adminSettingsMenu.getTypeOfMenu(menuName).getText().contains("section"));
		
	}			
	//TC ID: Admin_Settings_Menu_TC_07 => 
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 7)
	public void testCreateMenuOfTypeTag()
	{
		String menuName = "Menu_TC_07";
		String tag = "India";
		String color = "#442200";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(menuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.clickTagItemType();
		adminSettingsMenu.selectTagType(tag);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(menuName), menuName));
		Assert.assertTrue(adminSettingsMenu.getTypeOfMenu(menuName).getText().contains("tag"));
		
	}
	//TC ID: Admin_Settings_Menu_TC_08 =>
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 8)
	public void testCreateMenuOfTypeURL()
	{
		String menuName = "Menu_TC_08";
		String url = "https://www.google.com";
		String color = "#987123";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(menuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.clickURLItemType();
		adminSettingsMenu.enterURL(url);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(menuName), menuName));
		Assert.assertTrue(adminSettingsMenu.getTypeOfMenu(menuName).getText().contains("http"));
		
	}		
	//TC ID: Admin_Settings_Menu_TC_09 => Verify Edit functionality.
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 9)
	public void testMenuEditFunctionality()
	{
		String menuName = "Menu_TC_09";
		String updatedMenuName = "Updated_Menu_TC_09";
		String sectionName = "News";
		String color = "#020202";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(menuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.selectSectionType(sectionName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(menuName), menuName));
		adminSettingsMenu.clickMenuItem(menuName);
		adminSettingsMenu.enterName(updatedMenuName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(updatedMenuName), updatedMenuName));
		
	}			
	
	//TC ID: Admin_Settings_Menu_TC_010 => Verify Delete functionality.
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 10)
	public void testDeleteMenuItem()
	{
		String menuName = "Menu_TC_010";
		String color = "#800008";
		String sectionName = "News";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		adminSettingsMenu.clickAddMenuItemButton();
		adminSettingsMenu.enterName(menuName);
		adminSettingsMenu.clearColorField();
		adminSettingsMenu.enterColor(color);
		adminSettingsMenu.selectSectionType(sectionName);
		adminSettingsMenu.clickSaveItemButton();
		Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems(menuName), menuName));
		adminSettingsMenu.clickDeleteButton(menuName);
		Assert.assertFalse(CheckAlert.verifyAcceptAlert(driver));
		driver.navigate().refresh();
		adminSettingsMenu.clickMenuLink();
		Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(adminSettingsMenu.getListOfMenuItems(), menuName));
		
	}		
	
	//TC ID: Admin_Settings_Menu_TC_011 => Change the order of Menu Items & verify the same.
	@Test(groups = {"functest", "platform", "PlatformReg2"}, priority = 11)
	public void verifyOrderOfMenuAfterChanging()
	{
		String menuName = "Menu_TC_011";
		String sectionName = "News";
		String color ="#00FFFF";
		WorkspacePage workspacePage = new WorkspacePage(driver);
		AdminSettingsMenu adminSettingsMenu = new AdminSettingsMenu(driver);
		
		Assert.assertEquals(driver.getTitle(), "Workspace", "Itsman is down or not reachable at this moment");
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickAdminLink();
		workspacePage.clickSettingsLink();
		adminSettingsMenu.clickMenuLink();
		for (int i = 1; i <= 3; i++) 
		{
			adminSettingsMenu.clickAddMenuItemButton();
			adminSettingsMenu.enterName(menuName+": "+i);
			adminSettingsMenu.clearColorField();
			adminSettingsMenu.enterColor(color);
			adminSettingsMenu.selectSectionType(sectionName);
			adminSettingsMenu.clickSaveItemButton();
			Assert.assertTrue(Verification.verifyExpectedElementPresentInList(adminSettingsMenu.getListOfMenuItems((menuName+": "+i)), (menuName+": "+i)));
		}
		List<String> initialList = getMenuItemsList(adminSettingsMenu.getListOfMenuItems());
		List<WebElement> listOfUpArrow = adminSettingsMenu.getListOfUpArrowLink();
		adminSettingsMenu.clickUpArrowLink(listOfUpArrow.size()-1);
		adminSettingsMenu.clickUpArrowLink(listOfUpArrow.size()-2);
		adminSettingsMenu.clickUpArrowLink(listOfUpArrow.size()-1);
		driver.navigate().refresh();
		adminSettingsMenu.clickMenuLink();
		List<String> changedList = getMenuItemsList(adminSettingsMenu.getListOfMenuItems());
		Assert.assertFalse(initialList.equals(changedList));
		
	}
	
	
	public static List <String> getMenuItemsList(List<WebElement> listOfMenuItems)
	{
		List <String> listOfLast3MenuItems = new ArrayList<String>();
		for (int i = listOfMenuItems.size()-1; i >= listOfMenuItems.size()-3; i--) {
			listOfLast3MenuItems.add(listOfMenuItems.get(i).getText());
		}
		return listOfLast3MenuItems;
	}
	
}
