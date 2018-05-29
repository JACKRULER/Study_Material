package com.quintype.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ValidatorConfigFile {
    public String ValidatorURL;
    public String pubID1;
    public String pubID2;

    public ValidatorConfigFile() {
        Properties prop = new Properties();
        try {
            File propFile = new File("./ace.properties");
            prop.load(new FileInputStream(propFile));
            this.ValidatorURL = prop.getProperty("baseValidatorURL");
        } catch (IOException e) {
            System.err.println("Error in reading Validator.properties file");
            e.printStackTrace();

        }
    }

}