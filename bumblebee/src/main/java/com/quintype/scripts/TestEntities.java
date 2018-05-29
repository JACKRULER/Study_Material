package com.quintype.scripts;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quintype.api.EntityAPI;
import com.quintype.api.PublisherAPI;
import com.quintype.db.ConnectDB;
import com.quintype.pom.EntitiesManagerPage;
import com.quintype.pom.LoginPage;
import com.quintype.pom.WorkspacePage;
import com.quintype.util.OpenBrowser;
import com.quintype.util.Verification;

public class TestEntities extends OpenBrowser 
{
	@BeforeClass(groups = {"functest", "platform", "smoketest"})
	public void doDBSetup()
	{
		ConnectDB.establishDBConnection();
		ConnectDB.deleteEntitiesGraph();
		ConnectDB.deleteEntities();
		ConnectDB.deleteEntitiesType();
 		ConnectDB.createEntitiesType();
 		if(driver.getTitle().equalsIgnoreCase("Login"))
 		{
 			LoginPage loginPage = new LoginPage(driver);
 			Assert.assertEquals(driver.getTitle(), "Login", "Itsman is down or not reachable at this moment");
 			loginPage.loginToApplication();	
 		}
	}
	// Test Case Id : Entity_60 
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 1)
	public void testEntityCreation()
	{
    	log.info("Execution of TestEntities Started");
		String entityName = "Entity_60";
		String entityMailId = "test@gmail.com";
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);

        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.clickEntityTypeField();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField(entityMailId);
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Image"));
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        
        workspacePage.clickProfileButton();
        Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>"); 
	}

	// Test Case Id : Entity_80
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 2)
	public void testEntityEdit()
	{
		String entityName = "Rahul";
		String entityMailId = "test@gmail.com";
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);

        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.clickEntityTypeField();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField(entityMailId);
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Image"));
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        entitiesManagerPage.clickEntityEditButton(entityName);
        entitiesManagerPage.enterNameInField(" Dravid");
        entitiesManagerPage.clickOnSaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList("Rahul Dravid").get(0).getText(), "Rahul Dravid"));        
        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
		Reporter.log("<===== Passed =====>");             
	}
	
	// Test Case Id : Entity_90
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 3)
	public void testDuplicateEntityCreation()
	{
		String entityName = "Entity_90";
		String entityMailId = "test@gmail.com";
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);

        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.clickEntityTypeField();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField(entityMailId);
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Image"));
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.clickEntityTypeField();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField(entityMailId);
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Image"));
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verifyDuplicateElementsPresentInList(entitiesManagerPage.getEntityTableList(), entityName));
        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
		Reporter.log("<===== Passed =====>"); 
	}		
	
	// Test Case Id : Entity_120
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 4)
	public void testFourCharctersSearch()
	{
		String entityName1 = "Mumbai";
		String entityName2 = "Delhi";
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);

        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        EntityAPI.createEntityWithPhoto(entityName1);
        EntityAPI.createEntityWithPhoto(entityName2);
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName2).get(0).getText(), entityName2));
        entitiesManagerPage.enterEntitySearchData("Mumb");
        Assert.assertEquals(true, Verification.verify(entitiesManagerPage.getEntityTableList(entityName1).get(0).getText(), entityName1));

        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
		Reporter.log("<===== Passed =====>"); 
	}		
			
	// Test Case Id : Entity_130
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 5)
	public void testEntitySearchWithSpecialCharcters()
	{
		String firstEntityName = "!@#$%^&";
		String secondEntityName = "Bangalore";
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);

        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        EntityAPI.createEntityWithPhoto(firstEntityName);
        EntityAPI.createEntityWithPhoto(secondEntityName);
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(secondEntityName).get(0).getText(), secondEntityName));
        entitiesManagerPage.enterEntitySearchData(firstEntityName);
        Assert.assertTrue(entitiesManagerPage.getPaginationBarStatus());

        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
		Reporter.log("<===== Passed =====>"); 
	}	
	
	// Test Case Id : Entity_140
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 6)
	public void testEntitySearchWithLessThan4Characters()
	{
		String firstEntityName = "Saurab";
		String secondEntityName = "Rahul";
        LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        
        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        EntityAPI.createEntityWithPhoto(firstEntityName);
        EntityAPI.createEntityWithPhoto(secondEntityName);
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(secondEntityName).get(0).getText(), secondEntityName));
        entitiesManagerPage.enterEntitySearchData("Sau");
        Assert.assertEquals(false, Verification.verify(entitiesManagerPage.getEntityTableList().get(0).getText(), firstEntityName));

        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
		Reporter.log("<===== Passed =====>"); 
	}		
							
	// Test Case Id : Entity_150
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 7)
	public void testExistingEntityWithSingleImage()
	{	
		String entityName = "Entity_Person_TC_150";
		LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        
        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField("entitypersontc150@quintype.com");
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButtonWithoutPhoto(entityName);
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Image"));
        entitiesManagerPage.clickOnSaveButton();
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        int countPhotoAfterUpload = entitiesManagerPage.removeImageIconCount().size();
        Assert.assertEquals(countPhotoAfterUpload, 1);
        entitiesManagerPage.clickCloseEditEntityPopUp();
        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");		
	}
	
	// Test Case Id : Entity_160
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 8 )
	public void testExistingEntityWithMultipleImages()
	{
		String entityName = "Entity_Person_TC_160";
		String[] imageFilesList = dataPropertyFile.getProperty("Entity_Images").split(";");
		int imageCount = imageFilesList.length;
		LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        
        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField("entitypersontc160@quintype.com");
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButtonWithoutPhoto(entityName);
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Images"));
        entitiesManagerPage.clickOnSaveButton();
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        int countPhotoAfterUpload = entitiesManagerPage.removeImageIconCount().size();
        Assert.assertEquals(countPhotoAfterUpload, imageCount);
        entitiesManagerPage.clickCloseEditEntityPopUp();
        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");	
	}
	// Test Case Id : Entity_170	// Actually Not required as we have a same kind of scenario in Entity_060 TC 
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 9 )
	public void testCreateEntityWithSingleImage()
	{
		String entityName = "Entity_Person_TC_170";
		String[] imageFilesList = dataPropertyFile.getProperty("Entity_Image").split(";");
		int imageCount = imageFilesList.length;
		
		LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        
        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.clickEntityTypeField();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField("entitypersontc170@quintype.com");
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Image"));
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        int countPhotoAfterUpload = entitiesManagerPage.removeImageIconCount().size();
        Assert.assertEquals(countPhotoAfterUpload, imageCount);
        entitiesManagerPage.clickCloseEditEntityPopUp();
        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");
	}
	// Test Case Id : Entity_180
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 10 )
	public void testCreateEntityWithMultipleImage()
	{
		String entityName = "Entity_Person_TC_180";
		String[] imageFilesList = dataPropertyFile.getProperty("Entity_Images").split(";");
		int imageCount = imageFilesList.length;
		
		LoginPage loginPage = new LoginPage(driver);
	    WorkspacePage workspacePage = new WorkspacePage(driver);
	    EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
	        
	    Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
	    loginPage.loginToApplication();
	    workspacePage.clickWorkspaceMenuButton();
	    workspacePage.clickEntitiesLink();
	    entitiesManagerPage.clickAddEntityButton();
	    entitiesManagerPage.clickEntityTypeField();
	    entitiesManagerPage.enterEntityTypeData("Person1");
	    entitiesManagerPage.enterNameInField(entityName);
	    entitiesManagerPage.enterEmailInField("entitypersontc180@quintype.com");
	    entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Images"));
	    entitiesManagerPage.clickEntitySaveButton();
	    Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
	    driver.navigate().refresh();
	    entitiesManagerPage.enterEntitySearchData(entityName);
	    entitiesManagerPage.clickEntityEditButton(entityName);
	    int countPhotoAfterUpload = entitiesManagerPage.removeImageIconCount().size();
	    Assert.assertEquals(countPhotoAfterUpload, imageCount);
        entitiesManagerPage.clickCloseEditEntityPopUp();
	        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");
	}			
	// Test Case Id : Entity_190
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 11 )
	public void testDeleteSingleImageFromEntity()
	{
		String entityName = "Entity_Person_TC_190";
		LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
		        
        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickEntitiesLink();
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.clickEntityTypeField();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField("entitypersontc190@quintype.com");
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Images"));
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        int countPhotoAfterUpload = entitiesManagerPage.removeImageIconCount().size();
        entitiesManagerPage.clickRemoveImageIcon();
        entitiesManagerPage.clickOnSaveButton();
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        int countPhotoAfterDelete = entitiesManagerPage.removeImageIconCount().size();
        Assert.assertEquals((countPhotoAfterUpload-1), countPhotoAfterDelete);
        entitiesManagerPage.clickCloseEditEntityPopUp();
		        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");
	}
	// Test Case Id : Entity_200
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 12 )
	public void testDeleteMultipleImageFromEntity()
	{
		String entityName = "Entity_Person_TC_200";
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
			        
        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.clickEntityTypeField();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField("entitypersontc200@quintype.com");
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Images"));        
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        int countPhotoAfterUpload = entitiesManagerPage.removeImageIconCount().size();
        entitiesManagerPage.clickRemoveImageIcon();
        entitiesManagerPage.clickRemoveImageIcon();
        entitiesManagerPage.clickOnSaveButton();
        driver.navigate().refresh();			
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        int countPhotoAfterDelete = entitiesManagerPage.removeImageIconCount().size();
        Assert.assertEquals((countPhotoAfterUpload-2), countPhotoAfterDelete);
        entitiesManagerPage.clickCloseEditEntityPopUp();
			        
        workspacePage.clickProfileButton();
        Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");
    }
	// Test Case Id : Entity_210
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 13 )
	public void testDeleteAndAddMultipleImagesFromEntity()
	{
		String entityName = "Entity_Person_TC_210";
		String[] imageFilesList = dataPropertyFile.getProperty("Entity_Images").split(";");
		int imageCount = imageFilesList.length;
		LoginPage loginPage = new LoginPage(driver);
        WorkspacePage workspacePage = new WorkspacePage(driver);
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        
        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.clickEntityTypeField();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField("entitypersontc210@quintype.com");
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Images"));
        entitiesManagerPage.clickEntitySaveButton();
        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        entitiesManagerPage.clickRemoveImageIcon();
        entitiesManagerPage.clickRemoveImageIcon();
        entitiesManagerPage.clickRemoveImageIcon();
        entitiesManagerPage.clickOnSaveButton();
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButtonWithoutPhoto(entityName);
        entitiesManagerPage.uploadPhoto(dataPropertyFile.getProperty("Entity_Images"));
        entitiesManagerPage.clickOnSaveButton();
        driver.navigate().refresh();
        entitiesManagerPage.enterEntitySearchData(entityName);
        entitiesManagerPage.clickEntityEditButton(entityName);
        int countPhotoAfterUpload = entitiesManagerPage.removeImageIconCount().size();
        Assert.assertEquals(countPhotoAfterUpload, imageCount);
        entitiesManagerPage.clickCloseEditEntityPopUp();
        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");
  	}	
	
	// Test Case Id : Entity_220
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 14)
	public void testEntityPositionAtTop()
	{
		ArrayList<String> entityNameList = new ArrayList<String>();
		ArrayList<String> entityNameListFromTable = new ArrayList<String>();
			
		LoginPage loginPage = new LoginPage(driver);
	    WorkspacePage workspacePage = new WorkspacePage(driver);
	    EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
	    
	    Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
	    loginPage.loginToApplication();
	    workspacePage.clickWorkspaceMenuButton();
	    workspacePage.clickEntitiesLink();
//			Using this loop to create entities with different name
	    for(int i=1 ; i<=3 ; i++)
		{	
			String entityName = "Entity_Person_TC_220_"+i;
			entityNameList.add(entityName);
			String entityMailId = "entitypersontc"+i+"@quintype.com";
			entitiesManagerPage.clickAddEntityButton();
	        entitiesManagerPage.clickEntityTypeField();
		    entitiesManagerPage.enterEntityTypeData("Person1");
	        entitiesManagerPage.enterNameInField(entityName);
	        entitiesManagerPage.enterEmailInField(entityMailId);
	        entitiesManagerPage.clickEntitySaveButton();
		}
	    
		for(int i=entityNameList.size()-1 ; i>=0 ; i--)
		{
//			System.out.println("ABC "+i+"  :"+entitiesManagerPage.getEntityNameFromTable(i));
			entityNameListFromTable.add(entitiesManagerPage.getEntityNameFromTable(i));
		}
		Assert.assertTrue(entityNameList.equals(entityNameListFromTable));
	        
	    workspacePage.clickProfileButton();
	    Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
	    Reporter.log("<===== Passed =====>");
	}
	
	//Test Case ID: Entity_230
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 15)
	public void testSaveAndDeleteButtonInExistingEntity()
	{
		String entityName = "Person_Entity_TC_230";
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
		
        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
//        EntityAPI.createEntityWithPhoto(PublisherAPI.responseData, entityName);
        loginPage.loginToApplication();
        workspacePage.clickWorkspaceMenuButton();
        workspacePage.clickEntitiesLink();
        
        entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField("entitypersontc230@quintype.com");
        entitiesManagerPage.clickEntitySaveButton();
        entitiesManagerPage.clickEntityEditButtonWithoutPhoto(entityName);
        Assert.assertTrue(entitiesManagerPage.verifySaveButtonIsVisible(), "Save button is not visible while editing the entity");
        Assert.assertTrue(entitiesManagerPage.verifyDeleteButtonIsVisible(),"Delete button is not visible while editing the entity");
        entitiesManagerPage.clickCloseEditEntityPopUp();
        
        workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");
	}
	//Test Case ID: Entity_240
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 16, dependsOnMethods = {"testSaveAndDeleteButtonInExistingEntity"})
	public void testDeleteExistingEntity( )
	{
		String entityName = "Person_Entity_TC_230"; ;
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
		
		Assert.assertEquals("Login", driver.getTitle(), "Itsman is down or not reachable at this moment");
		loginPage.loginToApplication();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickEntitiesLink();
//		driver.navigate().refresh();
		entitiesManagerPage.enterEntitySearchData(entityName);
		entitiesManagerPage.clickEntityEditButtonWithoutPhoto(entityName);
		entitiesManagerPage.clickOnDeleteButton();
		driver.navigate().refresh();
		Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(entitiesManagerPage.getEntityTableList(), entityName));
		
		workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");
	}
	
	//Test Case ID: Entity_250
	@Test(groups = {"functest", "platform", "smoketest"}, priority = 17)
	public void testDeleteNewEntity(){
		String entityName = "Person_Entity_TC_250";
		LoginPage loginPage = new LoginPage(driver);
		WorkspacePage workspacePage = new WorkspacePage(driver);
		EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
		
		Assert.assertEquals("Login", driver.getTitle(), "Itsman is down or not reachable at this moment");
//		EntityAPI.createEntityWithPhoto(PublisherAPI.responseData, entityName);
		loginPage.loginToApplication();
		workspacePage.clickWorkspaceMenuButton();
		workspacePage.clickEntitiesLink();
		
		entitiesManagerPage.clickAddEntityButton();
        entitiesManagerPage.enterEntityTypeData("Person1");
        entitiesManagerPage.enterNameInField(entityName);
        entitiesManagerPage.enterEmailInField("entitypersontc250@quintype.com");
        entitiesManagerPage.clickEntitySaveButton();
        driver.navigate().refresh();
		entitiesManagerPage.enterEntitySearchData(entityName);
		entitiesManagerPage.clickEntityEditButtonWithoutPhoto(entityName);
		entitiesManagerPage.clickOnDeleteButton();
		driver.navigate().refresh();
		Assert.assertTrue(Verification.verifyExpectedElementNotPresentInList(entitiesManagerPage.getEntityTableList(), entityName));
		
		workspacePage.clickProfileButton();
		Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
        Reporter.log("<===== Passed =====>");
		
	}
	//Test Case ID: Entity_280
		@Test(groups = {"functest", "platform", "smoketest"}, priority = 18)
		public void testCreateEntityWithoutMandatoryField(){
			String entityMailId = "entitypersontc280@quintype.com";
			LoginPage loginPage = new LoginPage(driver);
	        WorkspacePage workspacePage = new WorkspacePage(driver);
	        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
	        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
	        loginPage.loginToApplication();
	        workspacePage.clickWorkspaceMenuButton();
	        workspacePage.clickEntitiesLink();
	        entitiesManagerPage.clickAddEntityButton();
	        entitiesManagerPage.clickEntityTypeField();
	        entitiesManagerPage.enterEntityTypeData("Person1");
	        
	        entitiesManagerPage.enterEmailInField(entityMailId);
	        entitiesManagerPage.clickEntitySaveButton();
	        
	        Assert.assertTrue(entitiesManagerPage.verifyErrorMessageDisplay());
	        entitiesManagerPage.clickCloseEditEntityPopUp();
	        workspacePage.clickProfileButton();
			Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
	        Reporter.log("<===== Passed =====>");
		}
		
//		Test Case ID : Entity_270
		@Test(groups = {"functest", "platform", "smoketest"}, priority = 18)
		public void testCreateNestedEntity()
		{
			String industryEntityName = "IT TC 270";
			String cityEntityName = "Bangalore 270";
			String entityName = "Company Entity TC 270";
		
			LoginPage loginPage = new LoginPage(driver);
	        WorkspacePage workspacePage = new WorkspacePage(driver);
	        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
	        Assert.assertEquals("Login" , driver.getTitle(), "Itsman is down or not reachable at this moment");
	        loginPage.loginToApplication();
	        workspacePage.clickWorkspaceMenuButton();
	        workspacePage.clickEntitiesLink();
	        entitiesManagerPage.clickAddEntityButton();
	        entitiesManagerPage.enterEntityTypeData("Industry");
	        entitiesManagerPage.enterNameInField(industryEntityName);
	        entitiesManagerPage.clickEntitySaveButton();
	        
	        entitiesManagerPage.clickAddEntityButton();
	        entitiesManagerPage.enterEntityTypeData("City");
	        entitiesManagerPage.enterNameInField(cityEntityName);
	        entitiesManagerPage.clickEntitySaveButton();
	        
	        entitiesManagerPage.clickAddEntityButton();
	        entitiesManagerPage.enterEntityTypeData("Company");
	        entitiesManagerPage.enterNameInField(entityName);
	        entitiesManagerPage.selectEntityIndustry(industryEntityName);
	        entitiesManagerPage.clickEntitySaveButton();
	        
	        Assert.assertTrue(Verification.verify(entitiesManagerPage.getEntityTableList(entityName).get(0).getText(), entityName));
	        
	        workspacePage.clickProfileButton();
	        Assert.assertEquals("Login" , workspacePage.clickLogoutLink(), "User is not able to logout from Itsman");
	        Reporter.log("<===== Passed =====>"); 
		}
				
	@AfterClass(groups = {"functest", "platform", "smoketest"})
	public void closeDBConnection()
	{
		ConnectDB.closeDBConnection();	// Closing the DB connection
	}
}
