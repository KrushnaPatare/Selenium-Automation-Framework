package com.swagLabs.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XLUtility 
{
    private static final Logger logger = LoggerFactory.getLogger(XLUtility.class);

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public Cell cells;
    public CellStyle style;
    String path;

    
    public XLUtility(String path) 
    {
        this.path = path;
        logger.info("XLUtility initialized with file path: {}", path);
    }

    
    /**
     * Reads data from a specific cell in the Excel file.
     *
     * @param sheetName the name of the sheet
     * @param rowNum    the row number
     * @param cellNum   the cell number
     * @return the value of the cell as a String
     * @throws EncryptedDocumentException
     * @throws IOException
     */
    public String getExcelData(String sheetName, int rowNum, int cellNum)
    		throws EncryptedDocumentException, IOException 
    {
        logger.info("Reading data from sheet: {}, row: {}, column: {}", sheetName, rowNum, cellNum);
        String value;
        try (FileInputStream file = new FileInputStream(path)) 
        {
            value = WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
            logger.debug("Data retrieved: {}", value);
        } 
        catch (Exception e) 
        {
            logger.error("Error while reading data from sheet: {}, row: {}, column: {}", sheetName, rowNum, cellNum, e);
            throw e;
        }
        return value;
    }
    

    public int getRowCount(String sheetName) throws IOException 
    {
        logger.info("Getting row count for sheet: {}", sheetName);
        try (FileInputStream fi = new FileInputStream(path)) 
        {
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();
            logger.debug("Row count for sheet {}: {}", sheetName, rowCount);
            return rowCount;
        } 
        catch (Exception e) 
        {
            logger.error("Error while getting row count for sheet: {}", sheetName, e);
            throw e;
        }
    }

    
    public int getCellCount(String sheetName, int rowNum) throws IOException 
    {
        logger.info("Getting cell count for sheet: {}, row: {}", sheetName, rowNum);
        try (FileInputStream fi = new FileInputStream(path)) 
        {
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            int cellCount = row.getPhysicalNumberOfCells();
            logger.debug("Cell count for sheet: {}, row: {}: {}", sheetName, rowNum, cellCount);
            return cellCount;
        } 
        catch (Exception e) 
        {
            logger.error("Error while getting cell count for sheet: {}, row: {}", sheetName, rowNum, e);
            throw e;
        }
    }

    
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException 
    {
        logger.info("Fetching cell data from sheet: {}, row: {}, column: {}", sheetName, rowNum, colNum);
        try (FileInputStream fi = new FileInputStream(path)) 
        {
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);
            DataFormatter formatter = new DataFormatter();
            String data = formatter.formatCellValue(cell);
            logger.debug("Cell data retrieved: {}", data);
            return data;
        } 
        catch (Exception e) 
        {
            logger.error("Error while retrieving cell data from sheet: {}, row: {}, column: {}",
            		sheetName, rowNum,colNum, e);
            throw e;
        }
    }
    
    
    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
    { 
	    File xlfile=new File(path);
	    if( !xlfile.exists())    // If the file does not exist, then create a new file
	    {
		    workbook = new XSSFWorkbook();
		    fo = new FileOutputStream(path);
		    workbook.write(fo);
	    }
	    
	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    
	    if(workbook.getSheetIndex(sheetName)==-1) // If the sheet does not exist, then create a new sheet
	    	workbook.createSheet(sheetName);
	    
	    sheet = workbook.getSheet(sheetName);
	    
	    if(sheet.getRow(rownum)==null)        // If the row does not exist, then create a new row
	    	sheet.createRow(rownum);
	    
	    row = sheet.getRow(rownum);
	    
	    cell = row.createCell(colnum);
	    
//	    System.out.println(rownum);
//	    System.out.println(colnum);
	    System.out.println(data);

	    
	    cell.setCellValue(data);
	    fo = new FileOutputStream(path);
	    workbook.write(fo);
	    workbook.close();
	    fi.close();
	    fo.close();
    }
    
    
    public static void writeWebTableDataIntoExcel(String sheetname, List<List<String>> data)
    		throws IOException 
    {
      String dataSheetPath = PropertiesReader.getPropertyFromFile("testDataPath");
      XLUtility xlutil = new XLUtility(dataSheetPath);
        
      Iterator<List<String>> itr = data.iterator();
        
      int rownum = 0;
        
      while(itr.hasNext()) 
      {
        List<String> rowData = itr.next();
        Iterator<String> itd = rowData.iterator();
        
        int colnum = 0;
        
        while(itd.hasNext()) 
        {
          String value = itd.next();	
//        System.out.println(value);	
          xlutil.setCellData(sheetname, rownum, colnum, value);
          colnum++;
//  	  System.out.println(rownum);
//	      System.out.println(colnum);
        }
          
          rownum++;
      }    
        
    }
    
    public static String getSpecificCellData(List<HashMap<String,String>>sheetData, String inColName, String inColValue, String outColName) 
    {
    	String value = null;
    	for(HashMap<String, String> rowData: sheetData) 
    	{
			if(rowData.get(inColName) == inColValue) 
			{
				value = rowData.get(outColName);
			}
    	}
    	return value;
    }
    
    
    
}
