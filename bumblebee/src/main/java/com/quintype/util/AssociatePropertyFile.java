package com.quintype.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AssociatePropertyFile extends OpenBrowser{
	private static Properties dataPropertyFile;
	private static Properties envFile;
	
	public static Properties associatePropertyFile(String path , boolean debugMode)
	{
		File dataFile = null;
		
		if(debugMode)
		{
			dataFile = new File("./dataFile.properties");

		}
		else {
			if (path.equalsIgnoreCase("dbPath"))

				dataFile = new File(System.getProperty("dbPath"));
			if (path.equalsIgnoreCase("dataPath"))

				dataFile = new File(System.getProperty("dataPath"));
		}



		FileInputStream dataInputFile = null;
		try {
			dataInputFile = new FileInputStream(dataFile);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		dataPropertyFile = new Properties();
		try {
			dataPropertyFile.load(dataInputFile);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return dataPropertyFile;
	}
	
//	public static Properties associateEnvFile(String env)
//	{
//		File dataFile = null;
//		if(env.equalsIgnoreCase("platform"))
//		{
//			dataFile = new File("./platformDataFile.properties");
//		} else{
//			
//		}
//		FileInputStream dataInputFile = null;
//		try {
//			dataInputFile = new FileInputStream(dataFile);
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		envFile = new Properties();
//		try {
//			envFile.load(dataInputFile);
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		return envFile;
//	}
}
