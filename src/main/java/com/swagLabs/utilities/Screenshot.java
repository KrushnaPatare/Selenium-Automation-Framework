package com.swagLabs.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;


public class Screenshot {
	/**
	 * @param driver
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public String generateScreenshot(WebDriver driver) throws IOException {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-mm-yyyy hh-mm-ss a");
		String realTime = time.format(customFormat);
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotDestinationPath = AutoConstant.screenShotPath  + realTime + ".png";
		File destination = new File(screenshotDestinationPath);
		FileHandler.copy(source, destination);
		return screenshotDestinationPath;
	}
	
	
	public void takeScreenshotAndLog(WebDriver driver, String logMessage ) throws IOException, InterruptedException 
	{
		Thread.sleep(2000);
	    String screenshotPath;
	    try {
	        screenshotPath = generateScreenshot(driver);
	        ExtentReportManager.test.log(Status.INFO, "<b>" + logMessage + "</b>",
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    } catch (IOException e) 
	    {
	        e.printStackTrace();
	        ExtentReportManager.test.log(Status.WARNING, "Failed to capture screenshot for: " + logMessage);
	    }
	}

}
