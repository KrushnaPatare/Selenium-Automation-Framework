package com.swagLabs.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * This class contains the different variables that are needed in the project.
 */
public class AutoConstant 
{
	static LocalDateTime time = LocalDateTime.now();
	static DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy hh-mm-ss a");
	static String realTime = time.format(customFormat);
	
	
	public static String url = "https://www.saucedemo.com";
	public static String username = "standard_user";
	public static String password = "secret_sauce";

	public static String extentReportPath = "F:\\WorkSpace\\SwagLabs-Ecommerce\\ExtReport\\ExtentReports"+realTime+".html";
	public static String screenShotPath = "F:\\WorkSpace\\SwagLabs-Ecommerce\\Screenshots\\";
	public static String reportPath = "F:\\WorkSpace\\SwagLabs-Ecommerce\\ExtReport";
	public static String loginTestDataPath = "F:\\WorkSpace\\SwagLabsEcommerce\\DDT\\TestData.xlsx";
	public static String newloginTestDataPath = "F:\\WorkSpace\\SwagLabs-Ecommerce\\DDT\\TestDataNew.xlsx";
}
