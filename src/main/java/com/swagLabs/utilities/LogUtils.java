package com.swagLabs.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils
{
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    // General logging
    public static void info(String message) 
    {
        logger.info(  message);
    }

    public static void debug(String message) 
    {
        logger.debug(  message);
    }

    public static void warn(String message) 
    {
        logger.warn(  message);
    }

    public static void error(String message) 
    {
        logger.error(message);
    }

    // Test lifecycle logging
    public static void startTest(String testName) 
    {
        logger.info(testName);
    }

    public static void endTest(String testName) 
    {
        logger.info(testName);
    }

    public static void testPassed(String testName) 
    {
        logger.info(testName);
    }

    public static void testFailed(String testName, Throwable throwable) 
    {
        logger.error(testName, throwable);
    }

    // WebDriver events logging
    public static void logElementClick(String elementDescription) 
    {
        logger.info(elementDescription);
    }

    public static void logTextEntered(String text, String elementDescription) 
    {
        logger.info(text, elementDescription);
    }

    public static void logElementDisplayed(String elementDescription) 
    {
        logger.info(elementDescription);
    }

    public static void logPageTitle(String title) 
    {
        logger.info(title);
    }

    // Screenshot logging
    public static void logScreenshot(String screenshotPath) 
    {
        logger.info(screenshotPath);
    }

    // Configuration logging
    public static void logBrowserDetails(String browserName, String browserVersion) 
    {
        logger.info(browserName, browserVersion);
    }

    public static void logEnvironmentDetails(String url, String platform) 
    {
        logger.info(url, platform);
    }

    // Debugging helpers
    public static void logJavaScriptExecution(String script) 
    {
        logger.debug(script);
    }

    public static void logElementLocation(String elementDescription, String locator) 
    {
        logger.debug(elementDescription, locator);
    }

    // Assertion logging
    public static void logAssertionPass(String assertionMessage) 
    {
        logger.info(assertionMessage);
    }

    public static void logAssertionFail(String assertionMessage) 
    {
        logger.error(assertionMessage);
    }

    // Exception handling
    public static void logException(Exception e) 
    {
        logger.error(e.getMessage(), e);
    }

    // Exception handling (Error)
    public static void logException(Error e) 
    {
        logger.error(e.getMessage(), e);
    }
}