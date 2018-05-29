package com.quintype.pom;

import java.util.List;

import com.quintype.util.UploadImage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class ConfigurePage {

	private WebDriver driver;
	private Actions actions;

	@FindBy(xpath = "//span[text()='Entities']/../..//label")
	private WebElement entitieToggleButton;

	@FindBy(css = "div.collapsible-card__header")
	private List<WebElement> cardHeaders;

	@FindBy(xpath = "//span[text()='Collection Associated Metadata']/../..//label")
	private WebElement collectionAssociatedMetadataToggle;

	@FindBy(xpath = "//span[text()='Non blocking story save']/../..//label")
	private WebElement nonBlockingStorySaveToggle;

	@FindBy(linkText = "Promotional Message")
	private WebElement promotionalMessageLink;

	@FindBy(css = "div.public-DraftEditor-content")
	private List<WebElement> promotionalMessageTextFields;

	@FindBy(css="div.title")
	private WebElement title;

	@FindBy(css = "button.btn--primary")
	private WebElement updateButton;

	@FindBy(css = "div.header-indicate.is-success p")
	private WebElement updatedSuccessfullyMessage;

	@FindBy(linkText = "AMP")
	private WebElement ampTab;

	@FindBy (css = "amp-configuration")
	private WebElement ampConfiguration;

	@FindBy(css = "input[data-for='primaryColor']")
	private WebElement primaryColor;

	@FindBy (css = "input[data-for='secondaryColor']")
	private WebElement secondaryColor;

	@FindBy (css = "input[data-for='headerBackground']")
	private WebElement headerBgColor;

	@FindBy (css = "input[data-for='footerBackground']")
	private WebElement footerBgColor;

	@FindBy (css = "input[data-for='footerTextColor']")
	private WebElement footerTextColor;

	@FindBy (css = "input[data-for='sectionTitleColor']")
	private WebElement sectionTitleColor;

	@FindBy (css = "input[data-for='ampBodyUnitPath']")
	private WebElement bodyAdUnitPath;

	@FindBy (css = "input[data-for='ampBodyWidth']")
	private WebElement bodyAdWidth;

	@FindBy (css = "input[data-for='ampBodyHeight']")
	private WebElement bodyAdHeight;

	@FindBy (css = "input[data-for='ampTopUnitPath']")
	private WebElement topAdUnitPath;

	@FindBy (css = "input[data-for='ampTopWidth']")
	private WebElement topAdWidth;

	@FindBy (css = "input[data-for='ampTopHeight']")
	private WebElement topAdHeight;

	@FindBy (css = "input[data-for='ampBottomUnitPath']")
	private WebElement bottomAdUnitPath;

	@FindBy (css = "input[data-for='ampBottonWidth']")
	private WebElement bottomAdWidth;

	@FindBy (css = "input[data-for='ampBodyHeight']")
	private WebElement bottomAdHeight;

	@FindBy (css = "input[data-for='primaryFontFamily']")
	private WebElement primaryFontFamily;

	@FindBy (css = "input[data-for='primaryFontUrl']")
	private WebElement primaryFontCSS;

	@FindBy (css = "input[data-for='secondaryFontFamily']")
	private WebElement secondaryFontFamily;

	@FindBy (css = "input[data-for='secondaryFontUrl']")
	private WebElement secondaryFontCSS;

	@FindBy (css = "input[data-for='googleTrackingId']")
	private WebElement gaTrackingID;

	@FindBy (css = "input[data-for='gtmID']")
	private WebElement gtmID;

	@FindBy (css = "input[data-for='comscore1']")
	private WebElement comscoreC1;

	@FindBy (css = "input[data-for='comscore2']")
	private WebElement comscoreC2;

	@FindBy (css = "input[data-for='logoUrl']")
	private WebElement logoURL;

	@FindBy (css = ".amp-configuration__save")
	private WebElement ampSaveButton;

	@FindBy (css = ".header-indicate.is-success")
	private WebElement successBannerMessage;

	@FindBy (linkText = "Breaking News Defaults")
	private WebElement breakingNewsTemplates;

	@FindBy (css = ".public-DraftEditor-content")
	private WebElement contentField;

	@FindBy(css = ".image-upload input[type='file']")
	private WebElement uploadImage;

	@FindBy(css = ".image-upload")
	private List<WebElement> chooseImageLink;

	@FindBy(css = "#image")
	private WebElement uploadImageLink;

	@FindBy(css = ".uploaded-image .edit-button")
	private List<WebElement> uploadedImage;

	@FindBy(xpath = "//label[text()='Image Attribution']/..//div[@contenteditable='true']")		// Matching with multiple
	private WebElement imageAttributionField;

	@FindBy(xpath = "//label[text()='Image Caption']/..//div[@contenteditable='true']")		// Matching with multiple
	private WebElement imageCaptionField;

	@FindBy(css = "button[name='button']")
	private WebElement saveButton;

	public ConfigurePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickEntitiesToggleButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, entitieToggleButton);
		entitieToggleButton.click();
	}
	public boolean verifyCardHeaderDisplayed()
	{
		try{
			return cardHeaders.get(0).isDisplayed();
		}catch(Exception e)
		{
			return false;
		}
	}

	public void clickCollectionAssociatedMetadataToggle(){
		WaitForElement.waitForElementToBeClickable(driver, collectionAssociatedMetadataToggle);
		collectionAssociatedMetadataToggle.click();
	}

	public void clickNonBlockingStorySaveToggle(){
		WaitForElement.waitForElementToBeClickable(driver, nonBlockingStorySaveToggle);
		nonBlockingStorySaveToggle.click();
		driver.navigate().refresh();
	}
	public void clickPromotionalMessageTab()
	{
		WaitForElement.waitForElementToBeVisible(driver,promotionalMessageLink);
		WaitForElement.waitForElementToBeClickable(driver, promotionalMessageLink);
		promotionalMessageLink.click();
		WaitForElement.waitForElementToBeVisible(driver,promotionalMessageTextFields);
	}
	public void enterDefaultPromotionalMessage(String defaultSignature)
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, promotionalMessageTextFields.get(0));
        actions.moveToElement(promotionalMessageTextFields.get(0)).click().sendKeys(Keys.CONTROL, "a").sendKeys(Keys.DELETE).keyUp(Keys.LEFT_CONTROL).build().perform();
		promotionalMessageTextFields.get(0).sendKeys(defaultSignature);
	}
	public void enterSyndicatedPromotionalMessage(String syndicatedSignature)
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, promotionalMessageTextFields.get(1));
        actions.moveToElement(promotionalMessageTextFields.get(1)).click().sendKeys(Keys.CONTROL, "a").sendKeys(Keys.DELETE).keyUp(Keys.LEFT_CONTROL).build().perform();
        promotionalMessageTextFields.get(1).sendKeys(syndicatedSignature);
	}
	public void enterUGCPromotionalMessage(String uGCSignature)
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, promotionalMessageTextFields.get(2));
        actions.moveToElement(promotionalMessageTextFields.get(2)).click().sendKeys(Keys.CONTROL, "a").sendKeys(Keys.DELETE).keyUp(Keys.LEFT_CONTROL).build().perform();
        promotionalMessageTextFields.get(2).sendKeys(uGCSignature);
	}
	public WebElement getPromotionalMessageTextFields(int i)
	{
		WaitForElement.waitForElementToBeVisible(driver, promotionalMessageTextFields.get(i));
		WaitForElement.fluentWaitForElement(driver,promotionalMessageTextFields.get(i));
		return promotionalMessageTextFields.get(i);
	}
	public void clickUpdateButton()
	{
		WaitForElement.waitForElementToBeClickable(driver, updateButton);
		updateButton.click();
	}
	public WebElement getSuccessMessageElement()
	{
		WaitForElement.waitForElementToBeVisible(driver, updatedSuccessfullyMessage);
		return updatedSuccessfullyMessage;
	}
	public void clickAMPTab()
	{
		WaitForElement.waitForElementToBeClickable(driver,ampTab);
		ampTab.click();
	}
	public void enterPrimaryColor(String colorPrimary)
	{
		WaitForElement.waitForElementToBeVisible(driver, primaryColor);
		primaryColor.click();
		primaryColor.clear();
		primaryColor.sendKeys(colorPrimary);
	}
	public void enterSecondaryColor(String colorSecondary)
	{
		WaitForElement.waitForElementToBeVisible(driver, secondaryColor);
		secondaryColor.click();
		secondaryColor.clear();
		secondaryColor.sendKeys(colorSecondary);
	}
	public void enterHeaderBGColor(String colorHeaderBG)
	{
		WaitForElement.waitForElementToBeVisible(driver, headerBgColor);
		headerBgColor.click();
		headerBgColor.clear();
		headerBgColor.sendKeys(colorHeaderBG);
	}
	public void enterFooterBGColor(String colorFooterBG)
	{
		WaitForElement.waitForElementToBeVisible(driver, footerBgColor);
		footerBgColor.click();
		footerBgColor.clear();
		footerBgColor.sendKeys(colorFooterBG);
	}
	public void enterFooterTextColor(String colorFooterText)
	{
		WaitForElement.waitForElementToBeVisible(driver, footerTextColor);
		footerTextColor.click();
		footerTextColor.clear();
		footerTextColor.sendKeys(colorFooterText);
	}
	public void enterSectionTitleColor(String colorSectionTitle)
	{
		WaitForElement.waitForElementToBeVisible(driver, sectionTitleColor);
		sectionTitleColor.click();
		sectionTitleColor.clear();
		sectionTitleColor.sendKeys(colorSectionTitle);
	}
	public void enterBodyAdUnitPath(String bodyAdPath)
	{
		WaitForElement.waitForElementToBeVisible(driver, bodyAdUnitPath);
		bodyAdUnitPath.click();
		bodyAdUnitPath.clear();
		bodyAdUnitPath.sendKeys(bodyAdPath);
	}
	public void enterBodyAdWidth(String widthBodyAd)
	{
		WaitForElement.waitForElementToBeVisible(driver, bodyAdWidth);
		bodyAdWidth.click();
		bodyAdWidth.clear();
		bodyAdWidth.sendKeys(widthBodyAd);
	}
	public void enterBodyAdHeight(String heightBodyAd)
	{
		WaitForElement.waitForElementToBeVisible(driver, bodyAdHeight);
		bodyAdHeight.click();
		bodyAdHeight.clear();
		bodyAdHeight.sendKeys(heightBodyAd);
	}
	public void enterTopAdUnitPath(String topAdPath)
	{
		WaitForElement.waitForElementToBeVisible(driver, topAdUnitPath);
		topAdUnitPath.click();
		topAdUnitPath.clear();
		topAdUnitPath.sendKeys(topAdPath);
	}
	public void enterTopAdWidth(String widthTopAd)
	{
		WaitForElement.waitForElementToBeVisible(driver, topAdWidth);
		topAdWidth.click();
		topAdWidth.clear();
		topAdWidth.sendKeys(widthTopAd);
	}
	public void enterTopAdHeight(String heightTopAd)
	{
		WaitForElement.waitForElementToBeVisible(driver, topAdHeight);
		topAdHeight.click();
		topAdHeight.clear();
		topAdHeight.sendKeys(heightTopAd);
	}
	public void enterBottomAdUnitPath(String bottomAdPath)
	{
		WaitForElement.waitForElementToBeVisible(driver, bottomAdUnitPath);
		bottomAdUnitPath.click();
		bottomAdUnitPath.clear();
		bottomAdUnitPath.sendKeys(bottomAdPath);
	}
	public void enterBottomAdWidth(String widthBottomAd)
	{
		WaitForElement.waitForElementToBeVisible(driver, bottomAdWidth);
		bottomAdWidth.click();
		bottomAdWidth.clear();
		bottomAdWidth.sendKeys(widthBottomAd);
	}
	public void enterBottomAdHeight(String heightBottomAd)
	{
		WaitForElement.waitForElementToBeVisible(driver, bottomAdHeight);
		bottomAdHeight.click();
		bottomAdHeight.clear();
		bottomAdHeight.sendKeys(heightBottomAd);
	}
	public void enterFontFamilyPrimary(String familyPrimaryFont)
	{
		WaitForElement.waitForElementToBeVisible(driver, primaryFontFamily);
		primaryFontFamily.click();
		primaryFontFamily.clear();
		primaryFontFamily.sendKeys(familyPrimaryFont);
	}
	public void enterFontCSSPrimary(String cssPrimaryFont)
	{
		WaitForElement.waitForElementToBeVisible(driver, primaryFontCSS);
		primaryFontCSS.click();
		primaryFontCSS.clear();
		primaryFontCSS.sendKeys(cssPrimaryFont);
	}
	public void enterFontFamilySecondary(String familySecondaryFont)
	{
		WaitForElement.waitForElementToBeVisible(driver, secondaryFontFamily);
		secondaryFontFamily.click();
		secondaryFontFamily.clear();
		secondaryFontFamily.sendKeys(familySecondaryFont);
	}
	public void enterFontCSSSecondary(String cssSecondaryFont)
	{
		WaitForElement.waitForElementToBeVisible(driver, secondaryFontCSS);
		secondaryFontCSS.click();
		secondaryFontCSS.clear();
		secondaryFontCSS.sendKeys(cssSecondaryFont);
	}
	public void enterTrackingIdGa(String trackingId)
	{
		WaitForElement.waitForElementToBeVisible(driver, gaTrackingID);
		gaTrackingID.click();
		gaTrackingID.clear();
		gaTrackingID.sendKeys(trackingId);
	}
	public void enterGtmId(String gtmId)
	{
		WaitForElement.waitForElementToBeVisible(driver, gtmID);
		gtmID.click();
		gtmID.clear();
		gtmID.sendKeys(gtmId);
	}
	public void enterComscore1(String cscore1)
	{
		WaitForElement.waitForElementToBeVisible(driver, comscoreC1);
		comscoreC1.click();
		comscoreC1.clear();
		comscoreC1.sendKeys(cscore1);
	}
	public void enterComscore2(String cscore2)
	{
		WaitForElement.waitForElementToBeVisible(driver, comscoreC2);
		comscoreC2.click();
		comscoreC2.clear();
		comscoreC2.sendKeys(cscore2);
	}
	public void enterURLLogo(String logo)
	{
		WaitForElement.waitForElementToBeVisible(driver, logoURL);
		logoURL.click();
		logoURL.clear();
		logoURL.sendKeys(logo);
	}
	public void clickAMPSaveButton()
	{
		WaitForElement.waitForElementToBeClickable(driver,ampSaveButton);
		ampSaveButton.click();
	}
	public String getSuccessMessage()
	{
		WaitForElement.waitForElementToBeVisible(driver, successBannerMessage);
		String successMessage = successBannerMessage.getText();
		return successMessage;
	}

	public void clickBreakingNewsDefaultsTab()
	{
		WaitForElement.waitForElementToBeClickable(driver,breakingNewsTemplates);
		breakingNewsTemplates.click();
	}

	public void enterContent(String content)
	{
		WaitForElement.waitForElementToBeClickable(driver,contentField);
		contentField.clear();
		contentField.sendKeys(content);
	}




	public void selectImage(String imageName) // method to select image
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, uploadImageLink);
		actions.moveToElement(uploadImageLink).click().build().perform();
		UploadImage.uploadImage(uploadImage, imageName);
		String imageCaption = imageName.replace(".jpg", "");
		imageAttributionField.clear();
		imageCaptionField.clear();
		actions.moveToElement(imageAttributionField).click().sendKeys(imageCaption).build().perform();
		actions.moveToElement(imageCaptionField).click().sendKeys(imageCaption).build().perform();

		actions.moveToElement(uploadedImage.get(0), 200, 200).click().perform();
	}

	public void clickSaveButton()
	{
		WaitForElement.waitForElementToBeClickable(driver,saveButton);
		saveButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void waitForTitle()
	{
		WaitForElement.waitForElementToBeVisible(driver,title);
	}


}
