package com.swagLabs.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Parameterization {
	public static String getExelData(String sheetname, int row, int cell)
			throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("F:\\WorkSpace\\SwagLabs\\src\\test\\resources\\TestData.xlsx");
		String value = WorkbookFactory.create(file).getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
		return value;
	}
}