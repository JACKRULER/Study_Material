package com.quintype.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.quintype.util.CheckAlert;
import com.quintype.util.CurrentDate;
import com.quintype.util.ScrollPage;
import com.quintype.util.UploadFiles;
import com.quintype.util.UploadImage;
import com.quintype.util.Verification;
import com.quintype.util.WaitForElement;

public class NewStoryPage {
	private WebDriver driver;
	private Actions actions;
	private int cardCount = 0;
	private int chooseImageCount = 0;
	private int uploadImageCount = 0;
	private int uploadedImageCount = 1;
	private int imageCaptionCount = 1;
	private int upAddButtonOnCard = 0;
	private int downAddButtonOnCard = 1;
	private int uploadImageGalleryCount = 1;
	private String storyTitle;

	@FindBy(id = "new-story-link")
	private WebElement newStoryButton;

	@FindBy(css = ".uploaded-image .edit-button") // Not using as of now
	private List<WebElement> uploadedImage;

	@FindBy(css = "div.card-container")
	private List<WebElement> storyCards;

	@FindBy(css = "div#story-title div[role='textbox']")
	private WebElement titleField;

	@FindBy(css = "#story-subtitle div[role='textbox']")
	private WebElement subTitleField;

	@FindBy(xpath = "//label[text()='Image Caption']/..//div[@contenteditable='true']") // Matching with multiple
	private WebElement heroImageCaptionField;

	@FindBy(css = "#hero-image .add-image")
	private WebElement chooseHeroImage;

	@FindBy(css = "#hero-image input[type='file']")
	private WebElement uploadHeroImage;

	@FindBy(css = ".image-upload")
	private List<WebElement> chooseImageLink;

	@FindBy(css = ".image-upload input[type='file']")
	private List<WebElement> uploadImageLink;

	@FindBy(css = ".uploaded-image div[contenteditable='true']")
	private List<WebElement> imageCaptionField;

	@FindBy(css = "a.card-add")
	private WebElement newCardAddButton;

	@FindBy(css = "a.story-element-summary")
	private WebElement summaryElementButton;

	@FindBy(css = "div.story-element-summary")
	private WebElement summaryField;

	@FindBy(css = "a.story-element-text")
	private WebElement textElementButton;

	@FindBy(css = "div.story-element-text")
	private WebElement textField;

	@FindBy(css = "a.story-element-image")
	private WebElement imageElementButton;

	@FindBy(css = "a.story-element-image-gallery")
	private WebElement imageGalleryButton;

	@FindBy(css = "div.story-element-image-gallery .image-upload label")
	private List<WebElement> chooseImageGalleryImage;

	@FindBy(css = "div.story-element-image-gallery .image-upload input[type='file']")
	private List<WebElement> uploadImageGalleryImage;

	@FindBy(css = ".image-gallery-stack img")
	private List<WebElement> imageGalleryUplodedImages;

	@FindBy(css = "a.story-element-youtube-video")
	private WebElement youtubeButton;

	@FindBy(css = "div.story-element-video > input")
	private WebElement youtubeField;

	@FindBy(css = "a.story-element-soundcloud-audio")
	private WebElement soundCloudButton;

	@FindBy(css = "div.story-element-audio > input")
	private WebElement soundCloudField;

	@FindBy(css = "a.story-element-tweet")
	private WebElement twitterButton;

	@FindBy(css = "div.story-element-twitter > input")
	private WebElement twitterField;

	@FindBy(css = "a.story-element-youtube-video")
	private WebElement instagramButton;

	@FindBy(xpath = "div.story-element-twitter > input")
	private WebElement instagramField;

	@FindBy(css = "a.story-element-social-media")
	private WebElement socialMediaButton;

	@FindBy(xpath = "//label[text()='Social media URL']/..//input[@type='text']")
	private WebElement socialMediaField;

	// @FindBy(css = "a.story-element-jworkspacePagelayer")
	// private WebElement jwPlayerButton;

	@FindBy(css = "a.story-element-jwplayer")
	private WebElement jwPlayerButton;

	// @FindBy(css = "div.story-element-jworkspacePagelayer > input")
	@FindBy(css = "div.story-element-jwplayer input")
	private WebElement jwPlayerField;

	@FindBy(css = "a.story-element-jsembed")
	private WebElement jsEmbedButton;

	@FindBy(css = "div.story-element-jsembed > textarea")
	private WebElement jsEmbedField;

	@FindBy(css = "")
	private WebElement waitForJSEmbedToLoad;

	@FindBy(css = "a.story-element-location")
	private WebElement locationButton;

	@FindBy(css = ".story-element-location>input[value='Add Google Map']")
	private WebElement addGoogleMapLink;

	@FindBy(css = ".map-container input[class='controls map-search-box']")
	private WebElement typeLocationField;

	@FindBy(xpath = "//input[@value='Insert Map']")
	private WebElement clickInsertMap;

	@FindBy(css = "a.story-element-quote")
	private WebElement quoteButton;

	@FindBy(css = "div.story-element-quote div[contenteditable=true]")
	private List<WebElement> quoteField;

	@FindBy(css = "a.story-element-blockquote")
	private WebElement blockQuoteButton;

	@FindBy(css = "div.story-element-blockquote div[contenteditable=true]")
	private List<WebElement> blockQuoteField;

	@FindBy(css = "a.story-element-blurb")
	private WebElement blurbButton;

	@FindBy(css = ".story-element-blurb>div>div")
	private WebElement blurbField;

	@FindBy(css = "a.story-element-bigfact")
	private WebElement bigFactButton;

	@FindBy(css = "div.story-element-bigfact div[contenteditable=true]")
	private List<WebElement> bigFactField;

	@FindBy(css = "a.story-element-q-and-a")
	private WebElement qAButton;

	@FindBy(css = "div.story-element-q-and-a div[contenteditable=true]")
	private List<WebElement> qAndAField;

	@FindBy(css = "div.DraftEditor-editorContainer")
	private List<WebElement> introFieldListicle;

	@FindBy(css = "div.story-element input[name='item-title']")
	private List<WebElement> titleFieldListicle;

	@FindBy(css = "div.live-blog-card .checkbox")
	private List<WebElement> listOfLiveBlogCardCheckbox;

	@FindBy(css = "div.live-blog-card input[type='text']")
	private List<WebElement> listOfLiveBlogCardTitle;

	@FindBy(linkText = "Content")
	private WebElement contentTab;

	@FindBy(linkText = "Metadata")
	private WebElement metadataTab;

	@FindBy(linkText = "Story Attributes")
	private WebElement storyAttributesTab;

	@FindBy(css = "div#summary div[role='textbox']")
	private WebElement socialShareField;

	@FindBy(css = "div#tags div[class='Select-control'] > .Select-placeholder")
	private WebElement tagField;

	@FindBy(css = "div#sections div[class='Select-control']")
	private WebElement sectionField;

	@FindBy(css = "div#custom-url div[role='textbox']")
	private WebElement customURLField;

	@FindBy(css = ".save-button-changed.save")
	private WebElement saveButtonWithoutAutoSave;

	@FindBy(css = ".icon.button.large.undefined.preview")
	private WebElement previewButton;

	@FindBy(id = "story-submit-link")
	private WebElement submitButton;

	@FindBy(id = "story-approve-link")
	private WebElement approveButton;

	@FindBy(id = "publish-or-schedule")
	private WebElement publishButton;

	@FindBy(id = "story-close-link")
	private WebElement closeButton;

	@FindBy(id = "story-publish-link")
	private WebElement publishLink;

	@FindBy(css = ".publish-container > select")
	private WebElement publishDropDown;

	@FindBy(css = ".publish-container option[value='story-schedule']")
	private WebElement storyScheduleLink;

	@FindBy(css = "div.time-picker input")
	private WebElement scheduleTimePicker;

	@FindBy(css = ".save-button.blue")
	private WebElement saveButtonBlue;

	@FindBy(id = "story-reject-link")
	private WebElement rejectButton;

	@FindBy(id = "story-retract-link")
	private WebElement retractButton;

	@FindBy(css = "#sections .Select-option")
	private List<WebElement> listOfSections;

	@FindBy(css = "#stories li[class='loading ']")
	private WebElement loadStories;

	@FindBy(css = ".card-body .trash")
	private List<WebElement> cardElementDeleteButtons;

	@FindBy(css = ".card-container .story-state-icon")
	private List<WebElement> cardErrorIcons;

	@FindBy(css = "div.story-element .down")
	private List<WebElement> storyElementDownButtonsList;

	@FindBy(css = "div.card-container .down")
	private List<WebElement> storyCardDownButtonsList;

	@FindBy(css = "a.story-element-attachment")
	private WebElement attachment;

	@FindBy(css = "div.file-upload input")
	private WebElement uploadAttachment;

	@FindBy(css = "a.story-element-table")
	private WebElement tableElement;

	@FindBy(css = ".story-element-table input")
	private WebElement uploadCSV;

	@FindBy(css = "a.story-element-brightcove-video")
	private WebElement brightCoveElementButton;

	@FindBy(css = "div.story-element-brightcove textarea")
	private WebElement brightCoveElementField;

	@FindBy(css = ".spinner")
	private WebElement storySaveSpinner;

	@FindBy(css = ".tab-has-error[data-tab='metadata']")
	private WebElement metadataTabError;

	@FindBy(css = "div.header-indicate.is-error")
	private WebElement headerError;

	@FindBy(css = ".card-add-search")
	private WebElement existingCardAddButton;

	@FindBy(css = ".search-form input[type='search']")
	private WebElement searchExistingStory;

	@FindBy(css = ".list .list-item:nth-of-type(2) .list-story-title")
	private WebElement selectStory;

	@FindBy(css = "div:nth-of-type(1) .preview-card-result")
	private WebElement selectCard;

	@FindBy(css = "span[title='Add to the Story']")
	private WebElement addCard;

	@FindBy(css = ".card-pin")
	private List<WebElement> pins;

	public NewStoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterTitleFieldData(String title) // method to enter Title for the story
	{
		WaitForElement.waitForElementToBeVisible(driver, titleField);
		storyTitle = (title + " : " + CurrentDate.getCurrentDateAndTime());
		titleField.sendKeys(storyTitle);
	}

	public String getTitleFieldData() {
		WaitForElement.waitForElementToBeVisible(driver, titleField);
		return titleField.getText();
	}

	public String getEnteredStoryTitle() {
		return storyTitle;
	}

	public void enterSubTitleFieldData(String subTitle) {
		WaitForElement.waitForElementToBeVisible(driver, subTitleField);
		subTitleField.sendKeys(subTitle);
	}

	public void selectHeroImage(String imageName) // method to select a hero image & to enter image caption
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, chooseHeroImage);
		actions.moveToElement(chooseHeroImage).click().build().perform();

		UploadImage.uploadImage(uploadHeroImage, imageName); // Method to upload the images

		WaitForElement.waitForElementToBeVisible(driver, By.cssSelector("#hero-image .img-upload-image"));
		String imageCaption = imageName.replace(".jpg", "");
		actions.moveToElement(heroImageCaptionField).click().sendKeys(imageCaption).build().perform();
		actions.moveToElement(uploadedImage.get(0), 200, 200).click().perform();
	}

	public void clickNewCardAddButton() // Code add a new card & click on plus button
	{
		WaitForElement.waitForElementToBeVisible(driver, newCardAddButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", newCardAddButton);
		actions = new Actions(driver);
		String title = getStoryTitle();
		if (title.contains("Text Story Type")) {
			cardCount = cardCount + 1;
			WaitForElement.waitForElementToBeVisible(driver, storyCards.get(cardCount));
			actions.moveToElement(storyCards.get(cardCount), 50, 50).click().perform();
		}
	}

	public void clickAddStoryElementButton() {
		cardCount = cardCount + 1;
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, storyCards.get(cardCount));
		actions.moveToElement(storyCards.get(cardCount), 50, 50).click().perform();
	}

	public void clickUpAddButtonOfCard() {
		actions = new Actions(driver);
		List<WebElement> addButtons = driver.findElements(By.cssSelector(".card-container .add"));
		actions.moveToElement(addButtons.get(upAddButtonOnCard), 0, 0).click().build().perform();
	}

	public void clickDownAddButtonOfCard() {
		actions = new Actions(driver);
		List<WebElement> addButtons = driver.findElements(By.cssSelector(".card-container .add"));
		actions.moveToElement(addButtons.get(downAddButtonOnCard), 0, 0).click().build().perform();
		downAddButtonOnCard = downAddButtonOnCard + 2;
	}

	public void enterSummaryFieldData(String summaryData) // Code to enter Summary Element data
	{
		WaitForElement.waitForElementToBeVisible(driver, summaryElementButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", summaryElementButton);
		WaitForElement.waitForElementToBeVisible(driver, summaryField);
		actions = new Actions(driver);
		actions.moveToElement(summaryField).sendKeys(summaryData).build().perform();
	}

	public String getSummaryFieldData() {
		WaitForElement.waitForElementToBeVisible(driver, summaryField);
		return summaryField.getText();
	}

	public void enterTextFieldData(String textData) // Code to enter Text Element data
	{
		WaitForElement.waitForElementToBeVisible(driver, textElementButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", textElementButton);
		WaitForElement.waitForElementToBeVisible(driver, textField);
		actions = new Actions(driver);
		actions.moveToElement(textField).sendKeys(textData).build().perform();
	}

	public String getStoryTitle() {
		try {
			WebElement titleField = driver
					.findElement(By.cssSelector("#header-card header[role='banner'] div[class='Select-control']"));
			return titleField.getText();
		} catch (Exception e) {
			WebElement titleField = driver.findElement(By.cssSelector("#header-card header[role='banner'] h1"));
			return titleField.getText();
		}
	}

	public void selectImage(String imageName) // code to select an image & to enter the image caption
	{
		String title = getStoryTitle();
		String preFix = "(//figure[@class='uploaded-image']//a[@class='edit-button'])[";
		String postFix = "]";
		chooseImageCount = chooseImageCount + 2;
		uploadImageCount = uploadImageCount + 1;
		uploadedImageCount++;
		imageCaptionCount = imageCaptionCount + 2;
		actions = new Actions(driver);
		if (title.contains("Photo Story Type")) {
			WaitForElement.waitForElementToBeVisible(driver, chooseImageLink.get(chooseImageCount));
			actions.moveToElement(chooseImageLink.get(chooseImageCount)).click().build().perform();
			UploadImage.uploadImage(uploadImageLink.get(uploadImageCount), imageName); // Method to upload the images
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(preFix + uploadedImageCount + postFix)));
			String imageCaption = imageName.replace(".jpg", "");
			actions.moveToElement(imageCaptionField.get(imageCaptionCount)).click().sendKeys(imageCaption).build()
					.perform();

		} else if (title.contains("Listicle Story Type")) {
			WaitForElement.waitForElementToBeVisible(driver, chooseImageLink.get(chooseImageCount));
			actions.moveToElement(chooseImageLink.get(chooseImageCount)).click().build().perform();
			UploadImage.uploadImage(uploadImageLink.get(uploadImageCount), imageName); // Method to upload the images
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(preFix + uploadedImageCount + postFix)));
			String imageCaption = imageName.replace(".jpg", "");
			actions.moveToElement(imageCaptionField.get(imageCaptionCount)).click().sendKeys(imageCaption).build()
					.perform();
		} else {
			WaitForElement.waitForElementToBeVisible(driver, imageElementButton);
			JavascriptExecutor executor = ((JavascriptExecutor) driver);
			executor.executeScript("arguments[0].click();", imageElementButton);
			WaitForElement.waitForElementToBeVisible(driver, chooseImageLink.get(chooseImageCount));
			actions.moveToElement(chooseImageLink.get(chooseImageCount)).click().build().perform();
			UploadImage.uploadImage(uploadImageLink.get(uploadImageCount), imageName); // Method to upload the images
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(preFix + uploadedImageCount + postFix)));
			String imageCaption = imageName.replace(".jpg", "");
			actions.moveToElement(imageCaptionField.get(imageCaptionCount)).click().sendKeys(imageCaption).build()
					.perform();
		}
	}

	public void addImageGallery(int imageCount, String images) {
		WaitForElement.waitForElementToBeVisible(driver, imageGalleryButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", imageGalleryButton);
		String imageName[] = images.split(";");

		for (int i = 0; i < imageCount; i++) {
			chooseImageGalleryImage.get(0).click();
			WaitForElement.waitForElementToBeClickable(driver, chooseImageGalleryImage.get(1));
			UploadImage.uploadImage(uploadImageGalleryImage.get(0), imageName[i]);
			WaitForElement.waitForElementToBeVisible(driver, driver.findElements(
					By.xpath("(//ul[@class='image-gallery-stack']//img)[" + uploadImageGalleryCount + "]")));
			uploadImageGalleryCount = uploadImageGalleryCount + 1;
		}

	}

	public void enterYoutubeFieldData(String youtubeURL) // Code to enter Youtube Element data
	{
		String title = getStoryTitle();
		actions = new Actions(driver);
		if (title.contains("Video Story Type")) {
			WaitForElement.waitForElementToBeVisible(driver, youtubeField);
			actions.moveToElement(youtubeField).click().sendKeys(youtubeURL).build().perform();
			WaitForElement.waitForElementToBeVisible(driver, By.cssSelector(".story-element-video iframe"));
		} else {
			WaitForElement.waitForElementToBeVisible(driver, socialMediaButton);
			JavascriptExecutor executor = ((JavascriptExecutor) driver);
			executor.executeScript("arguments[0].click();", socialMediaButton);
			WaitForElement.waitForElementToBeVisible(driver, socialMediaField);
			actions.moveToElement(socialMediaField).click().sendKeys(youtubeURL).build().perform();
			WaitForElement.waitForElementToBeVisible(driver, By.cssSelector(".story-element-video iframe"));
		}
	}

	public void enterSoundCloudFieldData(String soundCloudURL) // Code to enter Sound Cloud Element data
	{
		WaitForElement.waitForElementToBeClickable(driver, socialMediaButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", socialMediaButton);
		WaitForElement.waitForElementToBeVisible(driver, socialMediaField);
		actions = new Actions(driver);
		actions.moveToElement(socialMediaField).click().sendKeys(soundCloudURL).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, By.cssSelector(".story-element-audio iframe"));
	}

	public void enterTwitterFieldData(String twitterURL) // Code to enter Twitter Element data
	{
		Assert.assertTrue(socialMediaButton.isDisplayed());
		WaitForElement.waitForElementToBeClickable(driver, socialMediaButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", socialMediaButton);
		WaitForElement.waitForElementToBeVisible(driver, socialMediaField);
		actions = new Actions(driver);
		actions.moveToElement(socialMediaField).click().sendKeys(twitterURL).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, By.cssSelector("[id^=twitter-widget]"));
	}

	public void enterInstaFieldData(String instaURL) // Code to enter Instagram Element data
	{
		WaitForElement.waitForElementToBeClickable(driver, socialMediaButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", socialMediaButton);
		WaitForElement.waitForElementToBeVisible(driver, socialMediaField);
		actions = new Actions(driver);
		actions.moveToElement(socialMediaField).click().sendKeys(instaURL).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, By.cssSelector("[id^=instagram-embed]"));
	}

	public void enterJWPlayerFieldData(String jwPlayerURL) // Code to enter JWPlayer Element data
	{
		WaitForElement.waitForElementToBeVisible(driver, jwPlayerButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", jwPlayerButton);
		WaitForElement.waitForElementToBeVisible(driver, jwPlayerField);
		actions = new Actions(driver);
		actions.moveToElement(jwPlayerField).sendKeys(jwPlayerURL).build().perform();
	}

	public void enterJSEmbedFieldData(String jsEmbedURL) // Code to enter JS Embed Element data
	{
		WaitForElement.waitForElementToBeVisible(driver, jsEmbedButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", jsEmbedButton);
		WaitForElement.waitForElementToBeVisible(driver, jsEmbedField);
		actions = new Actions(driver);
		actions.moveToElement(jsEmbedField).sendKeys(jsEmbedURL).build().perform();
		// WaitForElement.waitForElementToBeVisible(driver,
		// By.cssSelector(".story-element .story-element-jsembed"));
		// WaitForElement.waitForElementToBeVisible(driver,
		// By.cssSelector(".story-element .story-element-jsembed iframe"));
	}

	public void addMap(String locationToSearch) // Add a map
	{
		WaitForElement.waitForElementToBeVisible(driver, locationButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", locationButton);
		WaitForElement.waitForElementToBeVisible(driver, addGoogleMapLink);
		actions = new Actions(driver);
		actions.moveToElement(addGoogleMapLink).click().build().perform();
		WaitForElement.waitForElementToBeVisible(driver, typeLocationField);
		actions.moveToElement(typeLocationField).click().sendKeys(locationToSearch).build().perform();
		try {
			Thread.sleep(1000); // Not getting proper condition to wait.
		} catch (InterruptedException e) {
		}

		actions.sendKeys(Keys.ENTER).build().perform();
		try {
			Thread.sleep(2000); // Not getting proper condition to wait.
		} catch (InterruptedException e) {
		}

		WaitForElement.waitForElementToBeVisible(driver, clickInsertMap);
		actions.moveToElement(clickInsertMap).click().build().perform();
	}

	public void enterQuoteFieldData(String quoteFieldData, String quoteAttributionData) // Code to enter Qoute Field
																						// data
	{
		WaitForElement.waitForElementToBeVisible(driver, quoteButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", quoteButton);
		WaitForElement.waitForElementToBeVisible(driver, quoteField.get(0));

		actions = new Actions(driver);
		actions.moveToElement(quoteField.get(0)).sendKeys(quoteFieldData).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, quoteField.get(1));
		actions.moveToElement(quoteField.get(1)).click().sendKeys(quoteAttributionData).build().perform();
	}

	public void enterBlockquoteFieldData(String blockQuoteFieldData, String blockQuoteAttributionData) // Code to enter
																										// BlockQoute
																										// Field data
	{
		WaitForElement.waitForElementToBeVisible(driver, blockQuoteButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", blockQuoteButton);
		WaitForElement.waitForElementToBeVisible(driver, blockQuoteField.get(0));
		actions = new Actions(driver);
		actions.moveToElement(blockQuoteField.get(0)).sendKeys(blockQuoteFieldData).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, blockQuoteField.get(1));
		actions.moveToElement(blockQuoteField.get(1)).click().sendKeys(blockQuoteAttributionData).build().perform();
	}

	public void enterBlurbFieldData(String blurbData) // Code to enter Blurb Field data
	{
		WaitForElement.waitForElementToBeVisible(driver, blurbButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", blurbButton);
		WaitForElement.waitForElementToBeVisible(driver, blurbField);
		actions = new Actions(driver);
		actions.moveToElement(blurbField).sendKeys(blurbData).build().perform();
	}

	public void enterBigFactFieldData(String bigFactFieldData, String bigFactDescriptinData) // Code to enter BigFact
																								// Field data
	{
		WaitForElement.waitForElementToBeVisible(driver, bigFactButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", bigFactButton);
		WaitForElement.waitForElementToBeVisible(driver, bigFactField.get(0));
		actions = new Actions(driver);
		actions.moveToElement(bigFactField.get(0)).sendKeys(bigFactFieldData).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, bigFactField.get(1));
		actions.moveToElement(bigFactField.get(1)).click().sendKeys(bigFactDescriptinData).build().perform();
	}

	public void enterQAFieldData(String questionFieldData, String answerFieldData) // Code to enter Q & A Field data
	{
		WaitForElement.waitForElementToBeVisible(driver, qAButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", qAButton);
		WaitForElement.waitForElementToBeVisible(driver, qAndAField.get(0));

		actions = new Actions(driver);
		actions.moveToElement(qAndAField.get(0)).sendKeys(questionFieldData).build().perform();
		WaitForElement.waitForElementToBeVisible(driver, qAndAField.get(1));
		actions.moveToElement(qAndAField.get(1)).click().sendKeys(answerFieldData).build().perform();
	}

	public void uploadAttachment(String fileName) {

		WaitForElement.waitForElementToBeVisible(driver, attachment);
		attachment.click();
		UploadFiles.uploadAttachment(uploadAttachment, fileName);
	}

	public void uploadCSV(String fileName) {

		WaitForElement.waitForElementToBeVisible(driver, tableElement);
		tableElement.click();
		UploadFiles.uploadAttachment(uploadCSV, fileName);

	}

	public void enterIntroFieldDataListicle(String introData, int i) // Code to enter Text Element data
	{
		WaitForElement.waitForElementToBeVisible(driver, introFieldListicle.get(i));
		actions = new Actions(driver);
		actions.moveToElement(introFieldListicle.get(i)).click().sendKeys(introData + ": " + i).build().perform();
	}

	public void enterTextFieldDataListicle(String textData, int i) // Code to enter Text Element data
	{
		WaitForElement.waitForElementToBeVisible(driver, titleFieldListicle.get(i));
		actions = new Actions(driver);
		actions.moveToElement(titleFieldListicle.get(i)).click().sendKeys(textData + ": " + i).build().perform();
	}

	public void selectKeyEventCheckboxLiveBlog() {
		WaitForElement.waitForElementToBeVisible(driver, listOfLiveBlogCardCheckbox.get(0));
		listOfLiveBlogCardCheckbox.get(0).click();
	}

	public void enterTitleFieldDataLiveBlog(String titleOfLiveBlogCard) {
		WaitForElement.waitForElementToBeVisible(driver, listOfLiveBlogCardTitle.get(0));
		actions = new Actions(driver);
		actions.moveToElement(listOfLiveBlogCardTitle.get(0)).click().sendKeys(titleOfLiveBlogCard).build().perform();
	}

	public List<WebElement> getTitleFieldOfLiveBlog() {
		WaitForElement.waitForElementToBeClickable(driver, listOfLiveBlogCardTitle.get(0));
		return listOfLiveBlogCardTitle;
	}

	public void clickContentLink() // Code to click on Metadata link
	{
		ScrollPage.scrollUP(driver);
		WaitForElement.waitForElementToBeClickable(driver, contentTab);
		contentTab.click();
	}

	public void clickMetadataLink() // Code to click on Metadata link
	{
		WaitForElement.waitForElementToBeVisible(driver, existingCardAddButton);
		ScrollPage.scrollUP(driver);
		WaitForElement.waitForElementToBeClickable(driver, metadataTab);
		metadataTab.click();
	}

	public void enterSocialShareFieldData(String socialShareMessage) // Code to enter Social Share Field data
	{
		ScrollPage.scrollUP(driver);
		WaitForElement.waitForElementToBeVisible(driver, socialShareField);
		socialShareField.sendKeys(socialShareMessage);
	}

	public void enterTagFieldData(String tag) // code to enter tag field data
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, tagField);
		actions.moveToElement(tagField).click().build().perform();
		actions.moveToElement(tagField).sendKeys(tag).sendKeys(Keys.ENTER).build().perform();
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tags .Select-item-icon")));
	}

	public void enterSectionFieldData(String section) // code to enter section field data
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, sectionField);
		actions.moveToElement(sectionField).click().sendKeys(section).sendKeys(Keys.ENTER).build().perform();
	}

	public void enterCustomURLFieldData(String customURL) // Code to enter Custom URL Field data
	{
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeVisible(driver, customURLField);
		actions.moveToElement(customURLField).click().sendKeys(customURL).build().perform();
	}

	public void clickStoryAttributesLink() // Code to click on Metadata link
	{
		WaitForElement.waitForElementToBeClickable(driver, storyAttributesTab);
		storyAttributesTab.click();
	}

	public void selectAttribute(String attributeName, String attributeValue) {
		actions = new Actions(driver);
		WebElement attributeField = driver
				.findElement(By.xpath("//label[text()='" + attributeName + "']/..//div[@class='Select-control']"));
		WaitForElement.waitForElementToBeVisible(driver, attributeField);
		actions.moveToElement(attributeField).click().sendKeys(attributeValue).sendKeys(Keys.ENTER).build().perform();
	}

	public boolean getSelectedAttributeStatus(String attributeName, String attributeValue) {
		WebElement attributeDropDown;
		try {
			attributeDropDown = driver.findElement(
					By.xpath("//label[text()='" + attributeName + "']/..//span[text()='" + attributeValue + "']"));
			WaitForElement.waitForElementToBeVisible(driver, attributeDropDown);
			return attributeDropDown.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAttributeListed(String attributeName) {
		WebElement attributeField;
		try {
			attributeField = driver.findElement(By.xpath("//label[text()='" + attributeName + "']"));
			WaitForElement.waitForElementToBeVisible(driver, attributeField);
			return attributeField.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public List<WebElement> getAttributeValuesList(String attributeName) {
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		actions = new Actions(driver);
		WebElement attributeField = driver
				.findElement(By.xpath("//label[text()='" + attributeName + "']/..//div[@class='Select-control']"));
		WaitForElement.waitForElementToBeVisible(driver, attributeField);
		actions.moveToElement(attributeField).click().build().perform();
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		List<WebElement> attributeValuesList = driver
				.findElements(By.xpath("//label[text()='" + attributeName + "']/..//div[@class='Select-menu']/div"));
		return attributeValuesList;
	}

	// public List<WebElement> getAttributeValuesList(String attributeName)
	// {
	// actions = new Actions(driver);
	// WebElement attributeField =
	// driver.findElement(By.cssSelector("#"+attributeName+" .Select-control"));
	// WaitForElement.waitForElementToBeVisible(driver, attributeField);
	// actions.moveToElement(attributeField).click().build().perform();
	// List<WebElement> attributeValuesList =
	// driver.findElements(By.cssSelector("#"+attributeName+" .Select-menu>div"));
	// return attributeValuesList;
	// }
	public void clickSaveButton() // Code to click on save button before auto save
	{
		ScrollPage.scrollUP(driver);
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeClickable(driver, saveButtonWithoutAutoSave);
		actions.moveToElement(saveButtonWithoutAutoSave).click().build().perform();
		WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
		// WaitForElement.waitForElementToBeClickable(driver, submitButton);
	}

	public void clickSaveButtonforNBAS() // Code to click on save button before auto save
	{
		ScrollPage.scrollUP(driver);
		actions = new Actions(driver);
		WaitForElement.waitForElementToBeClickable(driver, saveButtonWithoutAutoSave);
		actions.moveToElement(saveButtonWithoutAutoSave).click().build().perform();
		// WaitForElement.waitForElementToBeClickable(driver, submitButton);
	}

	public boolean clickSubmitButton() {
		WaitForElement.waitForElementToBeClickable(driver, submitButton);
		submitButton.click();
		return CheckAlert.verifyAcceptAlert(driver);
	}

	public void clickApproveButton() {
		WaitForElement.waitForElementToBeClickable(driver, approveButton);
		approveButton.click();
	}

	public boolean isApproveButtonDisplay() {
		try {
			return approveButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickPublishButton() {
		WaitForElement.waitForElementToBeClickable(driver, publishButton);
		publishButton.click();
	}

	public void clickCloseButton() {
		WaitForElement.waitForElementToBeClickable(driver, closeButton);
		closeButton.click();
	}

	public boolean isCloseButtonDisplayed() {
		try {
			return closeButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickPublishLink() {
		WaitForElement.waitForElementToBeClickable(driver, publishLink);
		publishLink.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(newStoryButton));
		return CheckAlert.verifyAcceptAlert(driver);
	}

	public void clickPublishDropDown() {
		WaitForElement.waitForElementToBeClickable(driver, publishDropDown);
		publishDropDown.click();
		WaitForElement.waitForElementToBeClickable(driver, storyScheduleLink);
		storyScheduleLink.click();
	}

	public boolean clickRejectButton() {
		WaitForElement.waitForElementToBeClickable(driver, rejectButton);
		rejectButton.click();
		return CheckAlert.verifyAcceptAlert(driver);
	}

	public void clickRetractButton() {
		WaitForElement.waitForElementToBeVisible(driver, retractButton);
		try {
			Thread.sleep(1000); // Not getting proper condition to wait.
		} catch (InterruptedException e) {
		}
		actions = new Actions(driver);
		actions.moveToElement(retractButton).click().build().perform();
	}

	public void scheduleTime() {
		WaitForElement.waitForElementToBeVisible(driver, scheduleTimePicker);
		actions = new Actions(driver);
		actions.moveToElement(scheduleTimePicker).click().sendKeys(Keys.RIGHT).build().perform();
	}

	public void clickSaveButtonBlue() {
		WaitForElement.waitForElementToBeClickable(driver, saveButtonBlue);
		saveButtonBlue.click();
	}

	public List<WebElement> getListOfSections() {
		WaitForElement.waitForElementToBeClickable(driver, sectionField);
		sectionField.click();
		WaitForElement.waitForElementToBeVisible(driver, listOfSections);
		return listOfSections;
	}

	public void deleteCardElementFromCard(int element) {
		WaitForElement.waitForElementToBeClickable(driver, storyCards.get(element + 1));
		actions = new Actions(driver);
		actions.moveToElement(storyCards.get(element + 1)).moveToElement(cardElementDeleteButtons.get(element)).click()
				.build().perform();
	}

	public boolean isCardHasError() {
		try {
			return cardErrorIcons.get(0).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getCardFirstElementData() {
		return storyCards.get(1).findElement(By.tagName("p")).getText();
	}

	public void clickDownButtonOfFirstCardElement() {
		actions = new Actions(driver);
		actions.moveToElement(storyCards.get(1)).moveToElement(storyElementDownButtonsList.get(0)).click().build()
				.perform();
	}

	public void clickDownButtonOfFirstCard() {
		WaitForElement.waitForElementToBeClickable(driver, storyCardDownButtonsList.get(0));
		actions = new Actions(driver);
		actions.moveToElement(storyCards.get(1)).moveToElement(storyCardDownButtonsList.get(0)).click().build()
				.perform();
	}

	public boolean isStorySaveSpinnerDisplayed() {
		try {
			return storySaveSpinner.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public void waitForSpinnerToGo() {
		WaitForElement.waitForElementToInvisible(driver, By.cssSelector(".spinner"));
	}

	public void enterBrightCoveElementData(String text) {
		WaitForElement.waitForElementToBeVisible(driver, brightCoveElementButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", brightCoveElementButton);
		WaitForElement.waitForElementToBeVisible(driver, brightCoveElementField);
		actions = new Actions(driver);
		actions.moveToElement(brightCoveElementField).sendKeys(text).build().perform();
	}

	public boolean isMetadataTabErrorDisplayed() {
		return metadataTabError.isDisplayed();
	}

	public boolean isHeaderErrorDisplayed() {
		return headerError.isDisplayed();
	}

	public void clickAddExistingCardButton() {
		WaitForElement.waitForElementToBeClickable(driver, existingCardAddButton);
		existingCardAddButton.click();
	}

	public void searchForExistingStory(String searchStoryTitle) {
		WaitForElement.waitForElementToBeVisible(driver, searchExistingStory);
		searchExistingStory.click();
		searchExistingStory.sendKeys(searchStoryTitle);
		searchExistingStory.sendKeys(Keys.RETURN);
	}

	public void selectExistingStory() {
		WaitForElement.waitForElementToBeClickable(driver, selectStory);
		selectStory.click();
	}

	public void selectExistingCard() {
		WaitForElement.waitForElementToBeVisible(driver, selectCard);
		actions = new Actions(driver);
		actions.moveToElement(selectCard).click(addCard).build().perform();
	}

	public void pastePlainTextToTextField() {
		WaitForElement.waitForElementToBeVisible(driver, textElementButton);
		JavascriptExecutor executor = ((JavascriptExecutor) driver);
		executor.executeScript("arguments[0].click();", textElementButton);
		WaitForElement.waitForElementToBeVisible(driver, textField);
		actions = new Actions(driver);
		actions.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "v")).build().perform();
	}

	public String getTextElementData() {
		WaitForElement.waitForElementToBeVisible(driver, textField);
		return textField.getText();
	}

	public void pinCard(int cardNo) {
		if (cardNo < pins.size()) {
			WaitForElement.waitForElementToBeVisible(driver, pins.get(cardNo));
			pins.get(cardNo).click();
		} else {
			Assert.fail("Card to be pinned is not present on the page");
		}
	}

	public void unPinCard(int cardNo) {
		if (cardNo < pins.size()) {
			WaitForElement.waitForElementToBeVisible(driver, pins.get(cardNo));
			String pinned = pins.get(cardNo).getAttribute("data-for");
			if (Verification.verify(pinned, "pinned-card")) {
				pins.get(cardNo).click();
			} else {
				Assert.fail("Card is not pinned to unpin");
			}
		} else {
			Assert.fail("Card to be pinned is not present on the page");
		}
	}

}
