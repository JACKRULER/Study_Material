package com.quintype.scripts.ace.validator;

import com.quintype.config.ValidatorConfigFile;
import com.quintype.endpoints.ace.validator.QuintypeValidator;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestValidator extends SetUp {

    ValidatorConfigFile validatorConfigFile;
    Logger logger = LogManager.getLogger();
    String pubURL = "https://www.samachara.com";

    //----------------------------------------------------------------------------------------------------------------------
    //HOMEPAGE
    @Test
    public void validateHomePageSEO() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strSEOResponse = QuintypeValidator.validateSEO(System.getProperty("publisherURL"), validatorConfigFile);
        Assert.assertEquals(strSEOResponse, "PASS");

    }

    @Test
    public void validateHomePageRobots() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strRobotsResponse = QuintypeValidator.validateRobots(System.getProperty("publisherURL"), validatorConfigFile);
        Assert.assertEquals(strRobotsResponse, "PASS");

    }

    @Test
    public void validateHomePageOg() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strogResponse = QuintypeValidator.validateOG(System.getProperty("publisherURL"), validatorConfigFile);
        Assert.assertEquals(strogResponse, "PASS");

    }

    @Test
    public void validateHomePageHeaders() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strHeaderResponse = QuintypeValidator.validateHEADERS(System.getProperty("publisherURL"), validatorConfigFile);
        Assert.assertEquals(strHeaderResponse, "PASS");

    }
    //----------------------------------------------------------------------------------------------------------------------
    //STORYPAGES

    @Test
    public void validateStoryPageAmp() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strAmpResponse = QuintypeValidator.validateAMP(System.getProperty("publisherURL") + System.getProperty("storySlug"), validatorConfigFile);
        Assert.assertEquals(strAmpResponse, "NA");

    }

    @Test
    public void validateStoryPageSeo() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strSEOResponse = QuintypeValidator.validateSEO(System.getProperty("publisherURL") + System.getProperty("storySlug"), validatorConfigFile);
        Assert.assertEquals(strSEOResponse, "PASS");


    }

    @Test
    public void validateStoryPageHeaders() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strHeaderResponse = QuintypeValidator.validateHEADERS(System.getProperty("publisherURL") + System.getProperty("storySlug"), validatorConfigFile);
        Assert.assertEquals(strHeaderResponse, "PASS");

    }

    @Test
    public void validateStoryPageOg() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strOgResponse = QuintypeValidator.validateOG(System.getProperty("publisherURL") + System.getProperty("storySlug"), validatorConfigFile);
        Assert.assertEquals(strOgResponse, "PASS");

    }

    //----------------------------------------------------------------------------------------------------------------------
    //SectionPages
    @Test
    public void validateSectionPageHeaders() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strHeaderResponse = QuintypeValidator.validateHEADERS(System.getProperty("publisherURL") + System.getProperty("sectionSlug"), validatorConfigFile);
        Assert.assertEquals(strHeaderResponse, "PASS");

    }

    @Test
    public void validatesectionPageOg() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strOgResponse = QuintypeValidator.validateOG(System.getProperty("publisherURL") + System.getProperty("sectionSlug"), validatorConfigFile);
        Assert.assertEquals(strOgResponse, "PASS");

    }

    @Test
    
    public void validateSectionPageSeo() {
        validatorConfigFile = SetUp.getValidatorConfigObject();
        String strSEOResponse = QuintypeValidator.validateOG(System.getProperty("publisherURL") + System.getProperty("sectionSlug"), validatorConfigFile);
        Assert.assertEquals(strSEOResponse, "PASS");

    }
}
//----------------------------------------------------------------------------------------------------------------------
