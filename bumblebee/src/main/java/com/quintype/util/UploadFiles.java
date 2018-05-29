package com.quintype.util;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebElement;

public class UploadFiles extends OpenBrowser{
	
	public static void uploadAttachment(WebElement uploadAttachmentLink, String attachmentName){
		String attachmentPath = "./src/main/resources";
		
		try {
			File filePath = new File(attachmentPath);
			String absolutePath = filePath.getCanonicalPath()+"\\"+attachmentName;
			String requiredPath = absolutePath.replace('\\','/');
			uploadAttachmentLink.sendKeys(requiredPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
