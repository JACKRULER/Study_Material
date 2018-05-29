package com.quintype.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MeTypeConfigFile
{
    public String metypeURL;

    public String talktypeAuthUser1;
    public String publisherAccessToken;
    public String metypeFEURL;
    public String metypeAccountId;
    public String metypeUser1;
    public String usernameOfAccessToken;

    public MeTypeConfigFile(){
    	Properties prop = new Properties();
        try {
            File propFile = new File("./metype.properties");
            prop.load(new FileInputStream(propFile));
            this.metypeURL = prop.getProperty("baseMeTypeURL");
            this.talktypeAuthUser1 = prop.getProperty("talktype_auth_user1");
            this.metypeFEURL = prop.getProperty("metypeFEURL");
            this.metypeAccountId = prop.getProperty("metypeAccountId");
            this.publisherAccessToken = prop.getProperty("publisherAccessToken");
            this.metypeUser1 = prop.getProperty("talktype_name");
            this.usernameOfAccessToken = prop.getProperty("usernameOfAccessToken");


        } catch (IOException e) {
            System.err.println("Error in reading metype.properties file");
            e.printStackTrace();

        }
    }

}