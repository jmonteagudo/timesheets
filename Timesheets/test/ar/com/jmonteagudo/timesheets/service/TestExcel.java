package ar.com.jmonteagudo.timesheets.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import ar.com.jmonteagudo.timesheets.util.Configuration;

class TestExcel {
	
	@Test
	void toBeAbleToInstanceAnWorkbook() {
		XSSFWorkbook workbookTimesheets = new XSSFWorkbook();
		assertNotNull(workbookTimesheets);
	}
	
	@Test
	void toCreateANewFile() {
		try {
			XSSFWorkbook workbookTimesheets = new XSSFWorkbook();
			FileOutputStream out;
	
			out = new FileOutputStream(new File(Configuration.properties.getProperty("fileName")));
	
			workbookTimesheets.write(out);
			out.close();
			workbookTimesheets.close();
			
			File file = new File(Configuration.properties.getProperty("fileName"));
			FileInputStream in = new FileInputStream(file);
			XSSFWorkbook workbookLectura = new XSSFWorkbook(in);
			assertNotNull(workbookLectura);
			workbookLectura.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void crearHojaTest() throws Exception {
		FileOutputStream out;
		
		out = new FileOutputStream(new File("C:\\tmp\\pruebaEscritura.xlsx"));


		XSSFWorkbook workbookEscritura = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbookEscritura.createSheet("Hoja1");
		XSSFRow row = spreadsheet.createRow((short)1);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("Prueba");
		cell = row.createCell(1);
		cell.setCellValue(1);
		
		workbookEscritura.write(out);
		workbookEscritura.close();
		out.close();
	}
}
