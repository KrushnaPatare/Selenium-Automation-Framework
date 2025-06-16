package com.swagLabs.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.swagLabs.base.BaseClass;
import com.swagLabs.utilities.PropertiesReader;
import com.swagLabs.utilities.SeleniumUtils;
import com.swagLabs.utilities.XLUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class wbtTest extends BaseClass 
{
	
	@Test
	public void webTableTest() throws InterruptedException, IOException 
	{
		openBrowser("chrome");

        // Open the base URL
        driver.get("https://rahulshettyacademy.com/AutomationPractice");
		Thread.sleep(3000);
		
		WebElement table = driver.findElement(By.xpath("(//table[@id='product'])[2]"));
        BaseClass.selUtils.scrollToElement(table);
		
		List<List<String>> tableData = BaseClass.selUtils.collectDataWebTable(table, "thead/tr", "thead/tr/th", "tbody/tr", "tbody/tr[1]/td");
//		System.out.println(tableData);
		
        XLUtility.writeWebTableDataIntoExcel("WebTable", tableData);
		
	}
	
	
	

	
	
	
	
}
