package com.swagLabs.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils 
{
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    // General logging
    public static void info(String message) 
    {
        logger.info(message);
    }

    public static void debug(String message) 
    {
        logger.debug(message);
    }

    public static void warn(String message) 
    {
        logger.warn(message);
    }

    public static void error(String message) 
    {
        logger.error(message);
    }

    // Test lifecycle logging
    public static void startTest(String testName) 
    {
        logger.info("Starting Test: " + testName);
    }

    public static void endTest(String testName) {
        logger.info("Ending Test: " + testName);
    }

    public static void testPassed(String testName) 
    {
        logger.info("Test Passed: " + testName);
    }

    public static void testFailed(String testName, Throwable throwable) 
    {
        logger.error("Test Failed: " + testName, throwable);
    }

    // WebDriver events logging
    public static void logElementClick(String elementDescription) 
    {
        logger.info("Clicked on element: " + elementDescription);
    }

    public static void logTextEntered(String text, String elementDescription) 
    {
        logger.info("Entered text: '" + text + "' into element: " + elementDescription);
    }

    public static void logElementDisplayed(String elementDescription) 
    {
        logger.info("Element is displayed: " + elementDescription);
    }

    public static void logPageTitle(String title) 
    {
        logger.info("Current Page Title: " + title);
    }

    // Screenshot logging
    public static void logScreenshot(String screenshotPath) 
    {
        logger.info("Screenshot saved at: " + screenshotPath);
    }

    // Configuration logging
    public static void logBrowserDetails(String browserName, String browserVersion) 
    {
        logger.info("Browser: " + browserName + ", Version: " + browserVersion);
    }

    public static void logEnvironmentDetails(String url, String platform) 
    {
        logger.info("Test Environment - URL: " + url + ", Platform: " + platform);
    }

    // Debugging helpers
    public static void logJavaScriptExecution(String script) 
    {
        logger.debug("Executing JavaScript: " + script);
    }

    public static void logElementLocation(String elementDescription, String locator) 
    {
        logger.debug("Located element: " + elementDescription + " using locator: " + locator);
    }

    // Assertion logging
    public static void logAssertionPass(String assertionMessage) 
    {
        logger.info("Assertion Passed: " + assertionMessage);
    }

    public static void logAssertionFail(String assertionMessage) 
    {
        logger.error("Assertion Failed: " + assertionMessage);
    }

    // Exception handling
    public static void logException(Exception e) 
    {
        logger.error("An exception occurred: " + e.getMessage(), e);
    }

    // Exception handling
    public static void logException(Error e) 
    {
        logger.error("An exception occurred: " + e.getMessage(), e);
    }

}
