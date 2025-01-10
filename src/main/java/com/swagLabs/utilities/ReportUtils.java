package com.swagLabs.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.swagLabs.base.BaseClass;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ReportUtils 
{
    /**
     * Adds a screenshot to the report with a message.
     *
     * @param message the message to log
     */
    public static void addScreenshot(String message) 
    {
        try 
        {
            String base64Image = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.BASE64);
            ExtentReportManager.test.log(Status.INFO, "<b>" + message + "</b>",
                ExtentReportManager.test.addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
        } 
        catch (Exception e) 
        {
            ExtentReportManager.test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
        }
    }

    /**
     * Adds a screenshot to the report with a custom status and message.
     *
     * @param status  the status (e.g., PASS, FAIL, INFO)
     * @param message the message to log
     */
    public static void addScreenshot(Status status, String message) 
    {
        try 
        {
            String base64Image = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.BASE64);
            ExtentReportManager.test.log(status, message,
                ExtentReportManager.test.addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
        } 
        catch (Exception e) 
        {
            ExtentReportManager.test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
        }
    }

    public static void addScreenshotWithHighlight(WebElement element, String message) 
    {
        try 
        {
            JavascriptExecutor js = (JavascriptExecutor) BaseClass.driver;
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            addScreenshot(message);
            js.executeScript("arguments[0].style.border=''", element); // Remove highlight
        } 
        catch (Exception e) 
        {
            ExtentReportManager.test.log(Status.WARNING, "Failed to highlight element: " + e.getMessage());
        }
    }

    public static void addFullPageScreenshot(String message) {
        try 
        {
            AShot ashot = new AShot();
            Screenshot screenshot = ashot.shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(BaseClass.driver);
            // Use the existing toBase64 method
            String base64Image = ImageUtils.toBase64(screenshot.getImage());
            ExtentReportManager.test.log(Status.INFO, message,
                ExtentReportManager.test.addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
        } 
        catch (Exception e) 
        {
            ExtentReportManager.test.log(Status.WARNING, "Failed to capture full-page screenshot: " + e.getMessage());
        }
    }


    /**
     * Logs a simple message with details.
     *
     * @param message the message to log
     */
    public static void logMessage(String message) 
    {
        ExtentReportManager.test.log(Status.INFO, message);
    }

    /**
     * Logs a message with a custom status and details.
     *
     * @param status  the status (e.g., PASS, FAIL, INFO)
     * @param message the message to log
     */
    public static void logMessage(Status status, String message) 
    {
        ExtentReportManager.test.log(status, message);
    }

    public static void logExecutionTime(long startTime, long endTime) 
    {
        long duration = endTime - startTime;
        ExtentReportManager.test.log(Status.INFO, "Execution Time: " + (duration / 1000) + " seconds");
    }


}

























