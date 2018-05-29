package com.quintype.utils;


import com.jayway.restassured.RestAssured;
import com.quintype.config.*;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class SetUp
{
	@SuppressWarnings("unused")
    public static String basicAuth;
    public static ConfigFile configObject = null;
	public static ConfigFile getConfigObject()
    {
    	return configObject;
    }
	private static MeTypeConfigFile metypeConfigObject = null;
	public static MeTypeConfigFile getmetypeConfigObject()
	{
		return metypeConfigObject;
	}

    private static AceConfigFile aceConfigObject=null;
	public static AceConfigFile getAceConfigObject() {return aceConfigObject;}
	private static ValidatorConfigFile validatorConfigObject=null;
	public static ValidatorConfigFile getValidatorConfigObject() {return validatorConfigObject;}

	private static AccessTypeConfigFile accessTypeConfigFile = null;
	public static AccessTypeConfigFile getAccessTypeConfigFile()
	{
		return accessTypeConfigFile;
	}
  @BeforeSuite
	public static void setup()
	{
        System.out.println("Property files loaded: ");
        configObject = new ConfigFile();
        metypeConfigObject = new MeTypeConfigFile();

		aceConfigObject = new AceConfigFile();
		validatorConfigObject = new ValidatorConfigFile();

        accessTypeConfigFile = new AccessTypeConfigFile();

        basicAuth = configObject.basicAuth;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        //RestAssured.filters(ErrorLoggingFilter.errorLogger());
	}
	
	@AfterSuite
	public static void executionCompleted()
	{
		Reporter.log("Execution of the API Suite Completed", true);
	}
}
