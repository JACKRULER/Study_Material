package com.quintype.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AccessTypeConfigFile
{
    public String accesstypeURL;

    public String token1;

    public String accountID;

    public String accesstypeProvider;

    public AccessTypeConfigFile(){
    	Properties prop = new Properties();
        try {
            File propFile = new File("./accesstype.properties");
            prop.load(new FileInputStream(propFile));
            this.accesstypeURL = prop.getProperty("baseAccesstypeURL");
            this.token1 = prop.getProperty("stagingToken");
            this.accountID = prop.getProperty("account_id");
            this.accesstypeProvider = prop.getProperty("accesstypeProvider");

        } catch (IOException e) {
            System.err.println("Error in reading accesstype.properties file");
            e.printStackTrace();

        }
    }

}