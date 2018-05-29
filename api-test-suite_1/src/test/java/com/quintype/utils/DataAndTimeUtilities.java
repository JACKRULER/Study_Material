package com.quintype.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAndTimeUtilities
{
    private static DateFormat dateFormat;
    private static Date dateobj;
    private static String currentDate;

	public static String getCurrentDateAndTime() 
	{
        dateFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");
        dateobj = new Date();
        currentDate = dateFormat.format(dateobj);
        return 	currentDate;	
	}
    public static String getCurrentDateForScreenShot() 
    {
        dateFormat = new SimpleDateFormat("dd MMM yyyy, HH-mm-ss");
        dateobj = new Date();
        currentDate = dateFormat.format(dateobj);
        return  currentDate;    
    }
    public static String getCurrentDate() 
	{
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        dateobj = new Date();
        currentDate = dateFormat.format(dateobj);
        return 	currentDate;	
	}
    public static String getCurrentDateForEmail()
    {
        dateFormat = new SimpleDateFormat("ddMMMyyyyHHmmss");
        dateobj = new Date();
        currentDate = dateFormat.format(dateobj);
        return  currentDate;
    }
}
