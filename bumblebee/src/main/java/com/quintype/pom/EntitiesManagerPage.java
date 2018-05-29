package com.quintype.pom;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.quintype.util.ScrollPage;
import com.quintype.util.UploadImage;
import com.quintype.util.Verification;
import com.quintype.util.WaitForElement;

public class EntitiesManagerPage
{
	private WebDriver driver;
	
	@FindBy(css = "a.button.green")
	private WebElement addEntityButton;
	
	@FindBy(id = "entity-type")
	private WebElement entityTypeField;
	
	@FindBy(id = "name")
	private WebElement nameField;
	
	@FindBy(id = "email")
	private WebElement emailField;
	
	@FindBy(css = ".image-upload.add-image")
	private WebElement photoLink;
	
	@FindBy(css = ".image-upload input[type='file']")
	private WebElement uploadImageLink;
	
	@FindBy(css = ".entities__button ")
	private WebElement entitySaveButton;
	
	@FindBy(css = ".entities__table td")
	private List<WebElement> entitiesTableFields;
	
	@FindBy(css = ".pagination .previous")
	private WebElement paginationBar;
	
	@FindBy(css = ".button.edit")
	private List<WebElement> listOfEditButton;
	
	@FindBy(css = ".entities__button--save")
	private WebElement entitySaveButtonAfterUpdate;
	
	@FindBy(css = ".entities__button--delete")
	private WebElement entityDeleteButton;
	
	@FindBy(css = ".entities__search")
	private WebElement entitySearchField;
	
	@FindBy(css = ".remove-image")
	private List<WebElement> removeImageLinks;
	
	@FindBy(css = ".remove-image")
	private WebElement removeImageIcon;
	
	@FindBy(css = ".close.icon")
	private WebElement closeEditEntityPopUp;
	
	@FindBy(css = ".upload-image-msg")
	private List<WebElement> uploadImageMessage;
	
	@FindBy(css = ".error")
	private WebElement errorMessageElement;
	
	@FindBy(css = ".Select-control")
	private WebElement entityIndustry;

	@FindBy(css = ".spinner")
	private List<WebElement> spinner;
		
	public EntitiesManagerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddEntityButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, addEntityButton);
		JavascriptExecutor Executor =((JavascriptExecutor)driver);
		Executor.executeScript("arguments[0].click();", addEntityButton);		
	}
	public void clickEntityTypeField()
	{
		WaitForElement.waitForElementToBeClickable(driver, entityTypeField);
		entityTypeField.click();
	}
	public void enterEntityTypeData(String entityType)
	{	
		WaitForElement.waitForElementToBeClickable(driver, entityTypeField);
		Select selectEntityType = new Select(entityTypeField);
		selectEntityType.selectByVisibleText(entityType);
	}
	public void enterNameInField(String name)
	{
		WaitForElement.waitForElementToBeClickable(driver, nameField);
		nameField.click();
		nameField.sendKeys(name);
	}
	public void enterEmailInField(String email)
	{
		WaitForElement.waitForElementToBeClickable(driver, emailField);
		emailField.click();
		emailField.sendKeys(email);
	}
	public void uploadPhoto(String entityImageFile)
	{	
		UploadImage.uploadEntityImages(photoLink, uploadImageLink, entityImageFile, uploadImageMessage);
	}
	public void clickEntitySaveButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, entitySaveButton);	//	@author = NIKESH
		entitySaveButton.click();
		WaitForElement.waitForElementToInvisible(driver, spinner);
		WaitForElement.waitForElementToBeClickable(driver, entitySearchField);
	}
	public List<WebElement> getEntityTableList()
	{
		try{	// Not getting condition to wait & without sleep its taking the old value from the table
			Thread.sleep(5000);
		}catch(Exception e){}
		WaitForElement.waitForElementToBeVisible(driver, entitiesTableFields);
		return entitiesTableFields;
	}
	public List<WebElement> getEntityTableList(String expectedEntity)
	{
		WaitForElement.waitForElementToBeMatch(driver, entitiesTableFields, expectedEntity);
		return entitiesTableFields;
	}
	public boolean getPaginationBarStatus()
	{
//		WaitForElement.waitForAjax(driver, "https://rio-demo.staging.quintype.com/api/entity?q=Mumbai");
		try{
			new WebDriverWait(driver, 15).until(ExpectedConditions.invisibilityOfElementLocated(By.className(".pagination .previous")));
			return true;
		}catch(Exception e)
		{
			return false;			
		}
	}
	public void clickEntityEditButton(String entityName)
	{	
		Verification.verifyEntityOnEntitiesTable(entityName);
		WebElement entityEditButton = driver.findElement(By.xpath("//td[text()='"+entityName+"']/..//a"));
		WaitForElement.waitForElementToBeClickable(driver, entityEditButton);
		entityEditButton.click();
		WaitForElement.waitForElementToBeVisible(driver, removeImageLinks);
	}
	public void clickEntityEditButtonWithoutPhoto(String entityName)
	{
		Verification.verifyEntityOnEntitiesTable(entityName);
		WebElement entityEditButton = driver.findElement(By.xpath("//td[text()='"+entityName+"']/..//a"));
		WaitForElement.waitForElementToBeClickable(driver, entityEditButton);
		entityEditButton.click();			
		WaitForElement.waitForElementToBeClickable(driver, entitySaveButtonAfterUpdate);
	}
	
	public void clickOnSaveButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, entitySaveButtonAfterUpdate);
		entitySaveButtonAfterUpdate.click();
		WaitForElement.waitForElementToInvisible(driver, spinner);
		WaitForElement.waitForElementToBeVisible(driver, entitySearchField);
	}
	public boolean verifySaveButtonIsVisible()
	{
		try{
			WaitForElement.waitForElementToBeClickable(driver, entitySaveButtonAfterUpdate);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public void clickOnDeleteButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, entityDeleteButton);
		entityDeleteButton.click();
		WaitForElement.waitForElementToBeClickable(driver, entitySearchField);
	}
	public boolean verifyDeleteButtonIsVisible()
	{
		try{
			WaitForElement.waitForElementToBeClickable(driver, entityDeleteButton);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public void enterEntitySearchData(String searchData)
	{	
		ScrollPage.scrollUP(driver);
		Actions action = new Actions(driver);
		WaitForElement.waitForElementToBeClickable(driver, entitySearchField);
		entitySearchField.clear();
		action.moveToElement(entitySearchField).click().sendKeys(searchData).sendKeys(Keys.ENTER).build().perform();
		// After searching for entity, its take some time to update the table. For that not getting specific condition to wait.
		// Do not remove this sleep wait.
//		try{		
//			Thread.sleep(5000);
//		}catch(Exception e){}
	}
	public boolean isEntitySeachFieldDisplayed()
	{
		try {
			return entitySearchField.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<WebElement> removeImageIconCount()
	{	
		return removeImageLinks;
	}
	
	public void clickRemoveImageIcon()
	{
		WaitForElement.waitForElementToBeVisible(driver, removeImageIcon);
		removeImageIcon.click();	
	}
	
	public void clickCloseEditEntityPopUp()
	{
		WaitForElement.waitForElementToBeClickable(driver, closeEditEntityPopUp );
		closeEditEntityPopUp.click();
	}
	
	public String getEntityNameFromTable(int rowNumber)
	{
		WaitForElement.waitForElementToBeClickable(driver, entitiesTableFields.get(0));
		return driver.findElements(By.cssSelector(".entities__table td:nth-child(1)")).get(rowNumber).getText();
	}
	
	public boolean verifyErrorMessageDisplay()
	{
		WaitForElement.waitForElementToBeVisible(driver, errorMessageElement);
		return errorMessageElement.isDisplayed();
	}
	
	public void selectEntityIndustry(String industryEntityName){
		Actions action = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, entityIndustry);
		action.moveToElement(entityIndustry).click().sendKeys(industryEntityName).build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		action.moveToElement(entityIndustry).sendKeys(Keys.ENTER).build().perform();
	}
	
}