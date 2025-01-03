package com.swagLabs.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

/**
 * This class is used to provide data for test cases that are data driven;
 */
public class DataProviders 
{
	/**
	 * This method fetches data from an Excel sheet and returns it as a 2D String array.
	 * 
	 * @param sheetName the name of the sheet from which data needs to be fetched
	 * @return a 2D String array containing the data from the specified sheet
	 * @throws IOException if there is an error reading the Excel file
	 */
	public static String[][] getDataFromExcel(String sheetName) throws IOException 
	{
	    String dataSheetPath = PropertiesReader.getProperty("testDataPath");
	    XLUtility xlutil = new XLUtility(dataSheetPath);

	    int rowCount = xlutil.getRowCount(sheetName);
	    int colCount = xlutil.getCellCount(sheetName, 0);

	    String[][] data = new String[rowCount][colCount];

	    for (int i = 1; i <= rowCount; i++) // Start from the second row (index 1)
	    { 
	        for (int j = 0; j < colCount; j++) // Start from the first column (index 0)
	        { 
	            data[i - 1][j] = xlutil.getCellData(sheetName, i, j); // Store data in the array
	        }
	    }

	    return data;
	}

	@DataProvider(name = "loginData")
	public static String[][] loginDataProvider() throws IOException 
	{
	    return getDataFromExcel("Credentials"); // Pass the sheet name dynamically
	}
	
	

	@DataProvider(name = "checkoutData")
	public static String[][] checkoutDataProvider() throws IOException 
	{
	    return getDataFromExcel("PersonalData"); // Pass the sheet name dynamically
	}



	
//	public static ArrayList<HashMap<String,String>> getCheckoutData2() throws IOException //data provider method should always be static.
//	{
//		String loginDataSheetPath = TestContext.testDataPath;
//		XLUtility xlutil = new XLUtility(loginDataSheetPath);
//		
//		int rownum = xlutil.getRowCount("credentials"); //This method gives the count form 1 to last the row number but in actual code row indexing starts from 0.
//		int colcount = xlutil.getCellCount("credentials", 0); //This method gives the count form 1 to last the column number but in actual code column indexing starts from 0.
//		ArrayList<HashMap<String,String>> data = new ArrayList<>();
//		
//		for(int i=1; i<=rownum; i++) //This will fetch data from second row.
//		{
//			HashMap<String,String> map = new HashMap<>();
//			for(int j=1; j<colcount; j++) //This will fetch data from second column.
//			{
//				String columnName = xlutil.getCellData("credentials", 0, j);
//				String cellData = xlutil.getCellData("credentials", i, j);
//				map.put(columnName, cellData);
//			}
//			data.add(map);
//		}
//		return data;
//	}
	
	
}


