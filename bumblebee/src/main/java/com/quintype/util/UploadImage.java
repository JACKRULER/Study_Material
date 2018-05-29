package com.quintype.util;

import java.util.List;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UploadImage extends OpenBrowser
{
	public static void uploadImage(WebElement uploadImageLink, String[] imageName)
	{
		String pathOfImageFolder = "./images/";
		try{
			File filePath = new File(pathOfImageFolder);
			String fileName = imageName[0];
 			String absolutePath = filePath.getCanonicalPath()+"\\"+fileName;
 			String requiredPath = absolutePath.replace('\\','/');
			uploadImageLink.sendKeys(requiredPath);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void uploadImage(WebElement uploadImageLink, String imageName)
	{
		String pathOfImageFolder = "./images/";
		try{
			File filePath = new File(pathOfImageFolder);
			String fileName = imageName;
 			String absolutePath = filePath.getCanonicalPath()+"\\"+fileName;
 			String requiredPath = absolutePath.replace('\\','/');
// 			System.out.println("\nAbsolute Path: "+requiredPath+"\n");
			uploadImageLink.sendKeys(requiredPath);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void uploadEntityImages(WebElement photoLink, WebElement uploadImageLink, String imageFileName, List<WebElement> uploadImageMessage)
	{	
		Actions a =  new Actions(driver);
		String[] imageName = imageFileName.split(";");
		String pathOfImageFolder = "./images/";
		for(int i=0;i<imageName.length;i++){
			WaitForElement.waitForElementToBeVisible(driver, photoLink);
			a.moveToElement(photoLink).click().build().perform();
			try{
				File filePath = new File(pathOfImageFolder);
				String fileName = imageName[i];
 				String absolutePath = filePath.getCanonicalPath()+"\\"+fileName;
 				String requiredPath = absolutePath.replace('\\','/');
				uploadImageLink.sendKeys(requiredPath);
			}catch(IOException e)
			{
				e.printStackTrace();
			}
//			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".remove-image")));
			WaitForElement.waitForElementToInvisible(driver, uploadImageMessage);
			
		}
	}
	
	
	
}
