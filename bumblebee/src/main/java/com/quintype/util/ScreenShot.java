package com.quintype.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot extends OpenBrowser
{
	public static void takeScreenShot(WebDriver driver, String className, String str)
	{
		// try{
		// 	File folder = new File("./screenShots/"+className);
		// 	if(!folder.exists())
		// 	{
		// 		folder.mkdir();
		// 	}
		// 	File pathOfScreenShotFolder = new File("./screenShots/"+className);
		// 	String absolutePath = pathOfScreenShotFolder.getCanonicalPath();
		// 	String requiredPath = absolutePath.replace('\\','/');
			
  //       	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  //       	FileUtils.copyFile(srcFile, new File(requiredPath+"/"+str+" "+CurrentDate.getCurrentDateForScreenShot()+".png"));
        	
  //       	ReloadPage.resetToLoginPage(driver);
  //       }catch(IOException e)
		// {
		// 	log.debug(e);
		// }
	}

	public static void deleteScreenshots()
	{
		try{	
			File theDir = new File("./screenShots/");
		
			for (File file: theDir.listFiles()) 
			{
		    	if(file.getName().equals("Test Folder")) 
		    	{
		    			
		    	}else 
		    	{
		        	file.delete();
		    	}
			}	
		}catch(NullPointerException npe)
		{
			npe.printStackTrace();
		}
	}

}
