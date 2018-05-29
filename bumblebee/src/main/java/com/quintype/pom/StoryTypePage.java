package com.quintype.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.quintype.util.WaitForElement;

public class StoryTypePage
{
	private WebDriver driver;

	@FindBy(css = "span[title*='Photo']")
	private WebElement photoStoryButton;

	@FindBy(css = "span[title*='Video']")
	private WebElement videoStoryButton;

	@FindBy(css = "span[title*='Listicle']")
	private WebElement listicleStoryButton;

	@FindBy(css= "span[title='Create Live Blog']")
	private WebElement liveBlogStoryButton;

	@FindBy(css = "span[title='Create a blank story']")
	private WebElement blankStoryButton;

	@FindBy(css = "span[title='Breaking News']")
	private WebElement breakingNewsButton;

	@FindBy(css = "span[title='Create a story collection']")
	private WebElement storyCollectionButton;

	public StoryTypePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickPhotoStoryButton()		// method to click on photo story button
	{
		WaitForElement.waitForElementToBeVisible(driver, photoStoryButton);
		photoStoryButton.click();
	}
	public void clickVideoStoryButton() 	// method to click on video story button
	{
		WaitForElement.waitForElementToBeVisible(driver, videoStoryButton);
		videoStoryButton.click();
	}
	public void clickListicleStoryButton() 	// method to click on listicle story button
	{
		WaitForElement.waitForElementToBeVisible(driver, listicleStoryButton);
		listicleStoryButton.click();
	}
	public void clickLiveBlogStoryButton() 	// method to click on live story button
	{
		WaitForElement.waitForElementToBeVisible(driver, liveBlogStoryButton);
		liveBlogStoryButton.click();
	}
	public void clickBlankStoryButton() 	// method to blank on photo story button
	{
		WaitForElement.waitForElementToBeVisible(driver, blankStoryButton);
		blankStoryButton.click();
	}
	public void clickStoryCollectionButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, storyCollectionButton);
		storyCollectionButton.click();
	}
	public void clickBreakingNewsButton()
	{
		WaitForElement.waitForElementToBeVisible(driver, breakingNewsButton);
		breakingNewsButton.click();
	}	
	
}
