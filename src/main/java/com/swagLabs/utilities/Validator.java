package com.swagLabs.utilities;

import org.testng.Assert;

public class Validator 
{
	public static void verifyUrl(String actualUrl, String expectedUrl, String result) 
	{
	    LogUtils.info(String.format("Validating login result. Expected Outcome: %s, Actual URL: %s, Expected URL: %s", result, actualUrl, expectedUrl));
	    switch (result.toLowerCase()) 
	    {
	        case "pass":
	            LogUtils.info(String.format("Expected URL '%s' does not match Actual URL '%s' for 'pass' condition.", expectedUrl, actualUrl));
	            Assert.assertEquals(actualUrl, expectedUrl, String.format("Expected URL '%s' does not match Actual URL '%s' for 'pass' condition.", expectedUrl, actualUrl));
	            break;

	        case "fail":
	            LogUtils.info(String.format("Expected URL '%s' matches Actual URL '%s' for 'fail' condition.", expectedUrl, actualUrl));
	            Assert.assertNotEquals(actualUrl, expectedUrl, String.format("Expected URL '%s' matches Actual URL '%s' for 'fail' condition.", expectedUrl, actualUrl));
	            break;

	        default:
	            throw new IllegalArgumentException(String.format("Invalid result argument '%s'. Use 'pass' or 'fail'.", result));
	    }
	    LogUtils.info(String.format("Validation completed successfully for result: %s", result));
	}
	
	public static void verifyUrl(String actualUrl, String expectedUrl) 
	{
	    LogUtils.info(String.format("Validating URLs. Actual URL: %s, Expected URL: %s", actualUrl, expectedUrl));

	    if (actualUrl.equals(expectedUrl)) 
	    {
	        LogUtils.info(String.format("Expected URL '%s' matches with Actual URL '%s'.", expectedUrl, actualUrl));
	    } 
	    else 
	    {
	        LogUtils.info(String.format("Expected URL '%s' does not match with Actual URL '%s'.", expectedUrl, actualUrl));
	    }

	    // Perform the assertion based on the comparison
	    Assert.assertEquals(actualUrl, expectedUrl, String.format("Expected URL '%s' does not match Actual URL '%s'.", expectedUrl, actualUrl));
	    LogUtils.info("URL validation completed successfully.");
	}
	

	public static void verifyTrue(boolean condition, String message) 
	{
	    LogUtils.info(String.format("Validating condition. Message: %s, Condition: %s", message, condition));
	    if (condition) 
	    {
	        LogUtils.info(String.format("Condition passed: %s", message));
	    } 
	    else 
	    {
	        LogUtils.info(String.format("Condition failed: %s", message));
	    }
	    // Perform the assertion based on the condition
	    Assert.assertTrue(condition, message);
	    LogUtils.info("Condition validation completed successfully.");
	    ReportUtils.logMessage(message);
	}

	
	public static void verifyFalse(boolean condition, String message) 
	{
	    LogUtils.info(String.format("Validating condition as false. Message: %s, Condition: %s", message, condition));
	    if (!condition) 
	    {
	        LogUtils.info(String.format("Condition passed: %s. The condition is false as expected.", message));
	    } 
	    else 
	    {
	        LogUtils.info(String.format("Condition failed: %s. The condition is true, which is not expected.", message));
	    }
	    // Perform the assertion to ensure the condition is false
	    Assert.assertFalse(condition, message);
	    LogUtils.info("Condition validation (false) completed successfully.");
	    ReportUtils.logMessage(message);
	}
	
	
	public static void verifyTitle(String actualTitle, String expectedTitle, String result) 
	{
	    LogUtils.info(String.format("Validating page title. Expected Outcome: %s, Actual Title: %s, Expected Title: %s", result, actualTitle, expectedTitle));

	    switch (result.toLowerCase()) 
	    {
	        case "pass":
	            LogUtils.info(String.format("Expected Title '%s' does not match Actual Title '%s' for 'pass' condition.", expectedTitle, actualTitle));
	            Assert.assertEquals(actualTitle, expectedTitle, String.format("Expected Title '%s' does not match Actual Title '%s' for 'pass' condition.", expectedTitle, actualTitle));
	            break;

	        case "fail":
	            LogUtils.info(String.format("Expected Title '%s' matches Actual Title '%s' for 'fail' condition.", expectedTitle, actualTitle));
	            Assert.assertNotEquals(actualTitle, expectedTitle, String.format("Expected Title '%s' matches Actual Title '%s' for 'fail' condition.", expectedTitle, actualTitle));
	            break;

	        default:
	            throw new IllegalArgumentException(String.format("Invalid result argument '%s'. Use 'pass' or 'fail'.", result));
	    }
	    LogUtils.info(String.format("Page title validation completed successfully for result: %s", result));
	}

	
	public static void verifyTitle(String actualTitle, String expectedTitle) 
	{
	    LogUtils.info(String.format("Validating Page Titles. Actual Title: %s, Expected Title: %s", actualTitle, expectedTitle));

	    if (actualTitle.equals(expectedTitle)) 
	    {
	        LogUtils.info(String.format("Expected Title '%s' matches with Actual Title '%s'.", expectedTitle, actualTitle));
	    } 
	    else 
	    {
	        LogUtils.info(String.format("Expected Title '%s' does not match with Actual Title '%s'.", expectedTitle, actualTitle));
	    }
	    // Perform the assertion based on the comparison
	    Assert.assertEquals(actualTitle, expectedTitle, String.format("Expected Title '%s' does not match Actual Title '%s'.", expectedTitle, actualTitle));
	    LogUtils.info("Page title validation completed successfully.");
	}


}
