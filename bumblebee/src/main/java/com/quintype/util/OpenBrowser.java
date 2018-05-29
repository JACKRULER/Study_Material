package com.quintype.util;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import com.quintype.api.PublisherAPI;
import com.quintype.api.TestData;
import org.testng.Reporter;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import org.apache.log4j.Logger;

public class OpenBrowser 
{
	public static WebDriver driver;				// Instance variable declaration
	public static Properties dataPropertyFile;
	public static Properties dataFileMeType;
	public static Properties envFile;
	public static Logger log;
	
  	@BeforeSuite(groups = { "functest", "metypesmoketest","smoketest", "platform", "platformReg4", "PlatformReg2", "PlatformReg1", "PlatformReg3" })
  	public static void launchApplication()
  	{
		log = Logger.getLogger("devpinoyLogger");
		log.info("Properties File Associated");
		log.info("Opening Chrome");
		ChromeDriverManager.getInstance().setup();	// Getting an instance of the Chrome Browser				
		driver = new ChromeDriver();
		log.info("Chrome Opened");
		System.out.println("Driver Address => "+driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		// Applied implicitwait to wait for 5 sec
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point position = new Point(0, 0);
		driver.manage().window().setPosition(position);
		Dimension maximizedScreenSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		driver.manage().window().setSize(maximizedScreenSize);
		if(System.getProperty("env")!=(null)) {
			dataPropertyFile = AssociatePropertyFile.associatePropertyFile("dataPath", false);
			if(System.getProperty("domain").equalsIgnoreCase("metype"))
				driver.get(dataPropertyFile.getProperty("meTypeURL"));
			else
				driver.get(dataPropertyFile.getProperty("itsmanURL"));
		}

		else {
			dataPropertyFile = AssociatePropertyFile.associatePropertyFile("dataPath", true);
			driver.get(dataPropertyFile.getProperty("itsmanURL"));

		}
		log.info("Execution of Suite Started");

  	}

  	@AfterMethod(groups = { "functest", "smoketest", "platform"})
    public void takeScreenShotOnFailure(ITestResult testResult) 
    {	
  		CheckAlert.verifyAcceptAlert(driver);
        Reporter.setCurrentTestResult(testResult);
      
        try{
            if(testResult.getStatus() == ITestResult.FAILURE) {
                String[] className = testResult.getTestClass().toString().split("scripts.");
                String path = "./screenShots/"+className[className.length-1].replaceAll("]", "");
                File folder = new File(path);
                if(!folder.exists()) {
                    folder.mkdir();
                }
       
// 		Below piece of code is to get console data, if test fails.                
//                LogEntries logEntries = driver.manage().logs().get("browser");
//                File driverLog = new File(testResult.getName() + ".log");
//                FileWriter out = new FileWriter(driverLog);
//                for (LogEntry logEntry : logEntries.getAll()) {
//                  out.write(logEntry.toString() + "\n");
//                }
//                out.close();
                
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(path +"/"+ testResult.getName() + "-" 
                    + CurrentDate.getCurrentDateForScreenShot() +  ".jpg"));
                if(System.getProperty("env").equals("dev")) {
                	String relativePath = "./screenShots/"+className[className.length-1].replaceAll("]", "") +"/"+ testResult.getName() + "-" + CurrentDate.getCurrentDateForScreenShot() +  ".jpg";
                	File file = new File(relativePath);
                	String absolutePath = file.getCanonicalPath();
                	Reporter.log("Test Failed ===>");
                    Reporter.log("<a href=\"" + absolutePath + "\">" + "ScreenShot Link" + "</a>");
                }else if(System.getProperty("env").equals("fun")) 
                {
                	String relativePath = "/screenShots/screenShots/"+className[className.length-1].replaceAll("]", "") +"/"+ testResult.getName() + "-" + CurrentDate.getCurrentDateForScreenShot() +  ".jpg";
                	String absolutePath = "http://ci.quinpress.com/go/files/"+System.getenv("GO_PIPELINE_NAME")+"/"+System.getenv("GO_PIPELINE_COUNTER")+"/"+System.getenv("GO_STAGE_NAME")+"/"+System.getenv("GO_STAGE_COUNTER")+"/"+System.getenv("GO_JOB_NAME")+relativePath;
    				Reporter.log("Test Failed ===>");
                    Reporter.log("<a href=\"" + absolutePath + "\">" + "ScreenShot Link" + "</a>");            
                }
            	log.info("Failed Test Method => "+testResult.getName());
            	System.out.println("Failed Test Method => "+testResult.getName()+" : "+CurrentDate.getCurrentDateAndTime());
            }   
            if(System.getProperty("domain").equalsIgnoreCase("metype"))
            {
    			driver.get(dataFileMeType.getProperty("meTypeURL"));
            }else
            {
        			driver.get(dataPropertyFile.getProperty("itsmanURL")+"/workspace");
                  	CheckAlert.verifyAcceptAlert(driver);
            }
    		
       	}catch(IOException e) {
            e.printStackTrace();
        } 
    }       

	@AfterSuite(groups = {"functest", "smoketest", "platform", "platformReg4", "PlatformReg2", "PlatformReg1", "PlatformReg3" })	// this will quit the browser after executing the suite
	public static void closeChromeBrowser()
	{	
		log.info("Execution of Suite Completed");
		driver.quit();					// Closing the browser
	}
	
}
