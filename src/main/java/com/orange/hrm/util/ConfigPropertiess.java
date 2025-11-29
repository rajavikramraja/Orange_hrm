package com.orange.hrm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ConfigPropertiess {
	static Properties prop;
	WebDriver driver;

	public static Properties initProperty(){
		final String path=System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
		try {
			prop=new Properties();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File configFile= new File(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(configFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}


	public static Object[][] getData(String SheetName){
		String excelPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Testdata.xlsx";
		File excelFile=new File(excelPath);
		Object[][] data = null;
		try {
			FileInputStream excelInput=new FileInputStream(excelFile);
			Workbook wrk=new XSSFWorkbook(excelInput);
			Sheet sheets = wrk.getSheet(SheetName);
			int physicalNumberOfRows = sheets.getPhysicalNumberOfRows()+1;
			int physicalNumberOfCells = sheets.getRow(0).getPhysicalNumberOfCells();
			data=new Object[physicalNumberOfRows-1][physicalNumberOfCells];
			DataFormatter format=new DataFormatter();
			//			return data;			
			for (int i = 1; i < physicalNumberOfRows; i++) {
				Row row = sheets.getRow(i);
				if(row == null) {

					continue;}
				for (int j = 0; j < physicalNumberOfCells; j++) {
					Cell cell = row.getCell(j);

					if(cell==null) {
						data[i-1][j]="";
					}
					else
						data[i-1][j]=format.formatCellValue(cell);
					//System.out.println("Data"+Arrays.deepToString(data));
				}}

			excelInput.close();
			wrk.close();
		}catch (Exception e) {
			// TODO: handle exception
		}

		return data;
	}
	public static Object[][] getDataExcel(String SheetName) {
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\Testdata.xlsx";
		File file=new File(path);
		FileInputStream fileInputStream;
		Object[][] finalData=null;
		try {
			fileInputStream = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheet(SheetName);
			int rowcount = sheet.getPhysicalNumberOfRows();
			int cellcount = sheet.getRow(0).getPhysicalNumberOfCells();
			int datatemp=0;
			Object[][] tempData=new Object[rowcount-1][cellcount];
			DataFormatter format=new DataFormatter();
			for (int i = 1; i < rowcount; i++) {
				Row row = sheet.getRow(i);
				if(row==null)continue;
				boolean isempty=true;
				for (int j = 0; j < cellcount; j++) {
					Cell cell = row.getCell(j);
					String value=(cell==null)?"":format.formatCellValue(cell);
					if (!value.trim().isEmpty()) isempty=false;
					tempData[datatemp][j]=value;
				}
				if(!isempty)datatemp++;
			}
			finalData=new Object[datatemp][cellcount];
			for (int i = 0; i < datatemp; i++) {
				finalData[i]=tempData[i];

			}
			fileInputStream.close();
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalData;	

	}
}

//
//if(cell!=null) {
//	switch (cell.getCellType()) {
//	case STRING:
//		data[i-1][j]=cell.getStringCellValue();
//		break;
//	case NUMERIC:
//		data[i-1][j]=cell.getNumericCellValue();
//		break;
//	case FORMULA:
//		data[i-1][j]=cell.getCellFormula();
//		break;
//	case BLANK:
//		data[i-1][j]="";
//		break;
//	case BOOLEAN:
//		data[i-1][j]=cell.getBooleanCellValue();
//		break;
//	default:
//		throw new Exception("Data not found");
//	}}
//	else 
//		data[i-1][j]="";
//	System.out.println("Cell is Empty");
//	}
