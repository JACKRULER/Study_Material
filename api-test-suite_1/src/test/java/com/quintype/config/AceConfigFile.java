package com.quintype.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AceConfigFile {
    public String itsmanURL;
    public String frontendURL;
    public String sketchesURL;
    public String basicAuth;
    public String username;
    public String password;
    public int sectionID;
    public int publisherID;

    public AceConfigFile(){
        Properties prop = new Properties();
        try {
            File propFile = new File("./ace.properties");
            prop.load(new FileInputStream(propFile));
            this.itsmanURL = prop.getProperty("baseitsmanURL");
            this.frontendURL = prop.getProperty("baseFrontendURL");
            this.sketchesURL=prop.getProperty("basesketchesURL");
            this.basicAuth = prop.getProperty("x_QT_Auth");
            this.username = prop.getProperty("username");
            this.password = prop.getProperty("password");
            this.sectionID = Integer.parseInt(prop.getProperty("sectionID"));
            this.publisherID = Integer.parseInt(prop.getProperty("publisherID"));


        } catch (IOException e) {
            System.err.println("Error in reading ace.properties file");
            e.printStackTrace();

        }
    }
}
