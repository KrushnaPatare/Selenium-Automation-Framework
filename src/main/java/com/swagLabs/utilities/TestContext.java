package com.swagLabs.utilities;

import java.util.HashMap;


public class TestContext 
{
	HashMap<Object, Object> data = new HashMap<>();

	public static String extentReportPath = "F:\\WorkSpace\\SwagLabs-Ecommerce\\ExtReport\\ExtentReports"+SeleniumUtils.getFormattedDateTime("dd-MM-yyyy hh-mm-ss a")+".html";
	
}
