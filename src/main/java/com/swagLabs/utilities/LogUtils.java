package com.swagLabs.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils
{
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    // General logging
    public static void info(String message) 
    {
        logger.info("[Thread: {}] {}", Thread.currentThread().getId(), message);
    }

    public static void debug(String message) 
    {
        logger.debug("[Thread: {}] {}", Thread.currentThread().getId(), message);
    }

    public static void warn(String message) 
    {
        logger.warn("[Thread: {}] {}", Thread.currentThread().getId(), message);
    }

    public static void error(String message) 
    {
        logger.error("[Thread: {}] {}", Thread.currentThread().getId(), message);
    }

    // Test lifecycle logging
    public static void startTest(String testName) 
    {
        logger.info("[Thread: {}] Starting Test: {}", Thread.currentThread().getId(), testName);
    }

    public static void endTest(String testName) 
    {
        logger.info("[Thread: {}] Ending Test: {}", Thread.currentThread().getId(), testName);
    }

    public static void testPassed(String testName) 
    {
        logger.info("[Thread: {}] Test Passed: {}", Thread.currentThread().getId(), testName);
    }

    public static void testFailed(String testName, Throwable throwable) 
    {
        logger.error("[Thread: {}] Test Failed: {}", Thread.currentThread().getId(), testName, throwable);
    }

    // WebDriver events logging
    public static void logElementClick(String elementDescription) 
    {
        logger.info("[Thread: {}] Clicked on element: {}", Thread.currentThread().getId(), elementDescription);
    }

    public static void logTextEntered(String text, String elementDescription) 
    {
        logger.info("[Thread: {}] Entered text: '{}' into element: {}", Thread.currentThread().getId(), text, elementDescription);
    }

    public static void logElementDisplayed(String elementDescription) 
    {
        logger.info("[Thread: {}] Element is displayed: {}", Thread.currentThread().getId(), elementDescription);
    }

    public static void logPageTitle(String title) 
    {
        logger.info("[Thread: {}] Current Page Title: {}", Thread.currentThread().getId(), title);
    }

    // Screenshot logging
    public static void logScreenshot(String screenshotPath) 
    {
        logger.info("[Thread: {}] Screenshot saved at: {}", Thread.currentThread().getId(), screenshotPath);
    }

    // Configuration logging
    public static void logBrowserDetails(String browserName, String browserVersion) 
    {
        logger.info("[Thread: {}] Browser: {}, Version: {}", Thread.currentThread().getId(), browserName, browserVersion);
    }

    public static void logEnvironmentDetails(String url, String platform) 
    {
        logger.info("[Thread: {}] Test Environment - URL: {}, Platform: {}", Thread.currentThread().getId(), url, platform);
    }

    // Debugging helpers
    public static void logJavaScriptExecution(String script) 
    {
        logger.debug("[Thread: {}] Executing JavaScript: {}", Thread.currentThread().getId(), script);
    }

    public static void logElementLocation(String elementDescription, String locator) 
    {
        logger.debug("[Thread: {}] Located element: {} using locator: {}", Thread.currentThread().getId(), elementDescription, locator);
    }

    // Assertion logging
    public static void logAssertionPass(String assertionMessage) 
    {
        logger.info("[Thread: {}] Assertion Passed: {}", Thread.currentThread().getId(), assertionMessage);
    }

    public static void logAssertionFail(String assertionMessage) 
    {
        logger.error("[Thread: {}] Assertion Failed: {}", Thread.currentThread().getId(), assertionMessage);
    }

    // Exception handling
    public static void logException(Exception e) 
    {
        logger.error("[Thread: {}] An exception occurred: {}", Thread.currentThread().getId(), e.getMessage(), e);
    }

    // Exception handling (Error)
    public static void logException(Error e) 
    {
        logger.error("[Thread: {}] An error occurred: {}", Thread.currentThread().getId(), e.getMessage(), e);
    }
}