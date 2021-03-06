package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils {

	private Workbook wbook = null;
	public Workbook getWbook() {
		return wbook;
	}

	
	private FileInputStream in = null;
	private FileOutputStream out = null;
	private Sheet sheet=null;

	public FileInputStream getIn() {
		return in;
	}

	
	public FileOutputStream getOut() {
		return out;
	}
public Workbook returnWbook() {
	return wbook;
}
	
	public excelUtils(String excelLocation) throws IOException {

		in = new FileInputStream(excelLocation);
		String excelExtension = excelLocation.split("\\.")[1];
		
		switch (excelExtension) {
		case "xlsx":
			wbook = new XSSFWorkbook(in);
			break;
		case "xls":
			wbook = new HSSFWorkbook(in);
			break;
		}
		if (wbook == null) {
			System.out.println("Excel file not found");
		}

	
	}

	public Sheet getSheet() {
		return sheet;
	}
	public Object[][] getSheetData() throws IOException {
			int totalRows = sheet.getLastRowNum() + 1;
			int totalColumns = sheet.getRow(totalRows - 1).getLastCellNum() ;
			Object[][] data = new Object[totalRows - 1][totalColumns];

			for (int i = 1; i < totalRows; i++) {
				for (int j = 0; j < totalColumns; j++) {
					if (sheet.getRow(i).getCell(j) == null) {
						sheet.getRow(i).createCell(j);
					}

					data[i - 1][j] = this.returnCellValue(i, j);
					in.close();
					out.close();

				}
			}
			return data;
		
	}
public Map<String,Integer> getHeaderMap(Sheet sheet){
	Map<String,Integer>columnMap=new HashMap<>();
	for(int i=0;i<sheet.getRow(0).getLastCellNum();i++) {
		columnMap.put(sheet.getRow(0).getCell(i).getStringCellValue(), sheet.getRow(0).getCell(i).getColumnIndex());
	
	}
	return columnMap;
}
	// Get cellValue as Object
	public Object returnCellValue(int rowNum, int cellNum) {
			Object cellValue = null;
			if(sheet.getRow(rowNum)==null) {
				sheet.createRow(rowNum);
			}
		if (sheet.getRow(rowNum).getCell(cellNum) == null) {
			sheet.getRow(rowNum).getCell(cellNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				sheet.getRow(rowNum).getCell(cellNum).setCellValue("");
				cellValue = sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
				return cellValue;

			}
			if (sheet.getRow(rowNum).getCell(cellNum).getCellType() == CellType.BOOLEAN) {
				cellValue = sheet.getRow(rowNum).getCell(cellNum).getBooleanCellValue();
				return cellValue;
			}
			if (sheet.getRow(rowNum).getCell(cellNum).getCellType() == CellType.NUMERIC) {
				cellValue = sheet.getRow(rowNum).getCell(cellNum).getNumericCellValue();
				return cellValue;
			}
			if (sheet.getRow(rowNum).getCell(cellNum).getCellType() == CellType.STRING) {
				cellValue = sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
				return cellValue;
			}
			if (sheet.getRow(rowNum).getCell(cellNum).getCellType() == CellType.FORMULA) {
				cellValue = sheet.getRow(rowNum).getCell(cellNum).getCellFormula();
				return cellValue;
			}
			return cellValue;
		
}

	// Check if sheet exists in excel
	public boolean setSheet(String sheetName) {

		boolean sheetexists = false;
		Iterator<Sheet> sheets = wbook.sheetIterator();

		while (sheets.hasNext()) {
			if (sheets.next().getSheetName().equalsIgnoreCase(sheetName)) {
				sheet=wbook.getSheet(sheetName);
				sheetexists=true;
				break;
			}
		}

		return sheetexists;
	}

	private List<String> getSheetColumnHeaders() {
		
			List<String> columnHeaders = new ArrayList<>();

			for (int i = 0; i <sheet.getRow(0).getLastCellNum(); i++) {
				columnHeaders.add(sheet.getRow(0).getCell(i).getStringCellValue());
			}
			return columnHeaders;
		}

	public List<Object> getColumnData(String columnName) {
			List<Object> columnData = new ArrayList<>();
			if (getSheetColumnHeaders().contains(columnName)) {
				int columnNum = getSheetColumnHeaders().indexOf(columnName);
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {
					columnData.add(returnCellValue(i, columnNum));
				}
			}
			return columnData;

		
	}
	
	public void setData() throws IOException {
	
			wbook.write(out);
	
	}
	
	
	
	
	
	
}
