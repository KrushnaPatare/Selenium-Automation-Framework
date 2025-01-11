package com.swagLabs.utilities;

public class ExceptionHandler 
{
	public static void handleException(Exception e, String message) 
	{
        LogUtils.error(message + " Exception: " + e.getMessage());
        ExtentReportManager.getTest().fail(message + " Exception: " + e.getMessage());
        throw new RuntimeException(e); // Ensure the exception propagates to TestNG
    }
	
	public static void handleException(AssertionError e, String message) 
	{
        LogUtils.error(message + " AssertionError: " + e.getMessage());
        ExtentReportManager.getTest().fail(message + " AssertionError: " + e.getMessage());
        throw new RuntimeException(e); // Ensure the assertionError propagates to TestNG
    }
}




















