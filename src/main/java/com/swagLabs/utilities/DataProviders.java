package com.swagLabs.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

/**
 * This class is used to provide data for test cases that are data driven;
 */
public class DataProviders 
{
	
	/** This method returns login data like Username, Password, result
	 * @return String[][]
	 * @throws IOException
	 */
	@DataProvider(name="loginData")
	public static String[][] getloginData() throws IOException //data provider method should always be static.
	{
		String loginDataSheetPath = AutoConstant.newloginTestDataPath;
		XLUtility xlutil = new XLUtility(loginDataSheetPath);
		
		int rownum = xlutil.getRowCount("credentials"); //This method gives the count form 1 to last the row number but in actual code row indexing starts from 0.
		int colcount = xlutil.getCellCount("credentials", 0); //This method gives the count form 1 to last the column number but in actual code column indexing starts from 0.
		
		String loginData [][] = new String[rownum][colcount-1];
		for(int i=1; i<=rownum; i++) //This will fetch data from second row.
		{
			for(int j=1; j<colcount; j++) //This will fetch data from second column.
			{
				loginData[i-1][j-1]= xlutil.getCellData("credentials", i, j);//indexing in array to store data starts from 0.
			}
		}
		return loginData;
	}
	
	@DataProvider(name="checkoutData")
	public static String[][] getCheckoutData() throws IOException //data provider method should always be static.
	{
		String loginDataSheetPath = AutoConstant.newloginTestDataPath;
		XLUtility xlutil = new XLUtility(loginDataSheetPath);
		
		int rownum = xlutil.getRowCount("credentials"); //This method gives the count form 1 to last the row number but in actual code row indexing starts from 0.
		int colcount = xlutil.getCellCount("credentials", 0); //This method gives the count form 1 to last the column number but in actual code column indexing starts from 0.
		
		String loginData [][] = new String[rownum][colcount-1];
		for(int i=1; i<=rownum; i++) //This will fetch data from second row.
		{
			for(int j=1; j<colcount; j++) //This will fetch data from second column.
			{
				loginData[i-1][j-1]= xlutil.getCellData("credentials", i, j);//indexing in array to store data starts from 0.
			}
		}
		return loginData;
	}
	
	
	
	/*
	@Test
	public void teu() throws IOException 
	{
		String loginDataSheetPath = AutoConstant.newloginTestDataPath;
		XLUtility xlutil = new XLUtility(loginDataSheetPath);
		
		int rownum = xlutil.getRowCount("credentials"); //This method gives the count form 1 to last the row number but in actual code row indexing starts from 0.
		int colcount = xlutil.getCellCount("credentials", 0); //This method gives the count form 1 to last the column number but in actual code column indexing starts from 0.
		
		System.out.println(rownum+"rownum");//6
		System.out.println(colcount+"colcount");//5

		//String loginData [][] = new String[rownum][colcount];
		for(int i=1; i<=rownum; i++) //This will fetch data from second row.
		{
			for(int j=2; j<colcount; j++) //This will fetch data from third column.
			{
				String u = xlutil.getCellData("credentials", i, j);//indexing in array to store data starts from 0.
				System.out.println(u);
			}
							System.out.println("====================");
		}	
	}
*/
}
