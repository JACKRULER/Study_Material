package com.quintype.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollPage extends OpenBrowser
{
	public static void scrollUP(WebDriver driver)        // for scrolling page till top
    {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0,0);");
    }
    public static void scrollDown(WebDriver driver)        // for scrolling page till bottom
    {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
}
