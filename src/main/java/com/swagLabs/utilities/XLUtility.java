package com.swagLabs.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class XLUtility {
	
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
	}
	
	/**
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public int getRowCount(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	/**
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public int getRowCounts(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		int rowcount = WorkbookFactory.create(fi).getSheet(sheetName).getLastRowNum();
		fi.close();
		return rowcount;
	}
	
	
	/**
	 * @param sheetName
	 * @param rownum
	 * @return
	 * @throws IOException
	 */
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount=row.getPhysicalNumberOfCells();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	/**
	 * @param sheetName
	 * @param rownum
	 * @return
	 * @throws IOException
	 */
	public int getCellCounts(String sheetName,int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		int cellcount = WorkbookFactory.create(fi).getSheet(sheetName).getRow(rownum).getLastCellNum();
		fi.close();
		return cellcount;
	}
	
	
	
	/**
	 * @param sheetName
	 * @param rownum
	 * @param colnum
	 * @return
	 * @throws IOException
	 */
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try
		{
			data = formatter.formatCellValue(cell); //Returns the formatted value of a cell 
			                                         //as a String regardless
		}
		catch(Exception e) 
		{
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	/**
	 * @param sheetname
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public  String getExelData(String sheetname, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {
		 FileInputStream file = new FileInputStream(path);
		 String value = WorkbookFactory.create(file).getSheet(sheetname).getRow(rowNum).getCell(cellNum).getStringCellValue();
			/*DataFormatter formatter = new DataFormatter();
			String data;
			try
			{
				data = formatter.formatCellValue(cell); //Returns the formatted value of a cell 
				                                         //as a String regardless
			}
			catch(Exception e) 
			{
				data = "";
			}*/
			fi.close();
			return value;
}
}
