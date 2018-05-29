package com.quintype.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFile
{
    public String itsmanURL;
    public String sketchesURL;
    public String basicAuth;
    public String username;
    public String password;
    public int sectionID;
    public int publisherID;
    public int authorID;

    public String basicAuth1;
    public String username1;
    public String password1;
    public int authorID1;
    public String displayName;

    public String basicAuth2;
    public String username2;
    public String password2;

    public String basicAuth3;
    public String username3;
    public String password3;
    public String x_QT_Auth;


    public String itsmanURL4;
    public String sketchesURL4;
    public String basicAuth4;
    public String username4;
    public String password4;
    public String x_QT_Auth4;

    public ConfigFile(){
    	Properties prop = new Properties();
        try {
            File propFile = new File("./itsman.properties");
            prop.load(new FileInputStream(propFile));
            this.itsmanURL = prop.getProperty("baseItsmanURL");
            this.sketchesURL = prop.getProperty("baseSketchesURL");            
            this.basicAuth = prop.getProperty("x_QT_Auth");
            this.basicAuth1 = prop.getProperty("x_QT_Auth1");
            this.username = prop.getProperty("username");
            this.password = prop.getProperty("password");
            this.username1 = prop.getProperty("username1");
            this.password1 = prop.getProperty("password1");
            this.sectionID = Integer.parseInt(prop.getProperty("sectionID"));
            this.publisherID = Integer.parseInt(prop.getProperty("publisherID"));
            this.authorID = Integer.parseInt(prop.getProperty("authorID"));
            this.authorID1 = Integer.parseInt(prop.getProperty("authorID1"));
            this.displayName = prop.getProperty("displayname");
            this.basicAuth2 = prop.getProperty("x_QT_Auth2");
            this.username2 = prop.getProperty("username2");
            this.password2 = prop.getProperty("password2");
            this.username3 = prop.getProperty("username3");
            this.password3 = prop.getProperty("password3");
            this.basicAuth3 = prop.getProperty("x_QT_Auth3");



            this.itsmanURL4 = prop.getProperty("baseItsmanURL2");
            this.sketchesURL4 = prop.getProperty("baseSketchesURL2");
            this.username4 = prop.getProperty("username4");
            this.password4 = prop.getProperty("password4");
            this.basicAuth4 = prop.getProperty("x_QT_Auth4");



        } catch (IOException e) {
            System.err.println("Error in reading itsman.properties file");
            e.printStackTrace();

        }
    }

}