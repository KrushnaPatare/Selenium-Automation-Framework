package com.swagLabs.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

/**
 * This class is used to provide data for test cases that are data-driven.
 */
public class DataProviders {

    /**
     * Fetches data from an Excel sheet.
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

        for (int i = 1; i <= rowCount; i++) 
        { // Start from the second row (index 1)
            for (int j = 0; j < colCount; j++) 
            { // Start from the first column (index 0)
                data[i - 1][j] = xlutil.getCellData(sheetName, i, j); // Store data in the array
            }
        }

        return data;
    }

    /**
     * DataProvider for login data.
     *
     * @return a 2D String array containing login credentials
     * @throws IOException if there is an error reading the Excel file
     */
    @DataProvider(name = "loginData") 
    public static Object[][] loginDataProvider() throws IOException 
    {
        return getDataFromExcel("Credentials");
    }

    /**
     * DataProvider for checkout data.
     *
     * @return a 2D String array containing personal data
     * @throws IOException if there is an error reading the Excel file
     */
    @DataProvider(name = "checkoutData") 
    public static Object[][] checkoutDataProvider() throws IOException 
    {
        return getDataFromExcel("PersonalData");
    }

    // Uncomment and update this if you need ArrayList-based data provider
    /*
    public static synchronized ArrayList<HashMap<String, String>> getCheckoutData2() throws IOException {
        String loginDataSheetPath = PropertiesReader.getProperty("testDataPath");
        XLUtility xlutil = new XLUtility(loginDataSheetPath);

        int rownum = xlutil.getRowCount("credentials");
        int colcount = xlutil.getCellCount("credentials", 0);
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        for (int i = 1; i <= rownum; i++) {
            HashMap<String, String> map = new HashMap<>();
            for (int j = 1; j < colcount; j++) {
                String columnName = xlutil.getCellData("credentials", 0, j);
                String cellData = xlutil.getCellData("credentials", i, j);
                map.put(columnName, cellData);
            }
            data.add(map);
        }
        return data;
    }
    */
}






















