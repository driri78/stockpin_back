package com.stockpin.project.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class GetExcelDataService {
	
	// 엑셀 파일 읽기
	/* 경로
	 * 1. "data/excel/kosdaq.xlsx"
	 * 2. "data/excel/kospi.xlsx"
	 *  */
	public List<List<String>> readExcelFile(String excelFilePath) {
	    return this.getSheetData(this.getSheetByIndex(readExcelFileWorkBook(excelFilePath), 0));
	}
	
	public XSSFWorkbook readExcelFileWorkBook(String excelFilePath) {
		XSSFWorkbook workbook = null;
		Resource resource = new ClassPathResource(excelFilePath);
		try(FileInputStream fileInputStream = new FileInputStream(resource.getFile())) {
			workbook = new XSSFWorkbook(fileInputStream);
			
		}catch(IOException e) {
			System.err.println("workbook 읽기 실패..");
			e.printStackTrace();
		}
		if (workbook == null) {
	        throw new RuntimeException("엑셀 파일을 읽을 수 없습니다.");
	    }
		System.out.println("workbook 읽기 성공!");
		return workbook;
	}
	
	// 시트 이름으로 시트 검색
	public XSSFSheet getSheetByName(XSSFWorkbook workbook, String sheetName) {
		return workbook.getSheet(sheetName);
	}
	
	// 시트 인덱스로 시트 검색
	public XSSFSheet getSheetByIndex(XSSFWorkbook workbook, int sheetIndex) {
		return workbook.getSheetAt(sheetIndex);
	}
	
	// 시트 데이터 파싱
	public List<List<String>> getSheetData(XSSFSheet sheet) {
		List<List<String>> result = new ArrayList<>();
		
		for(Row row : sheet) {
			List<String> rowData = new ArrayList<>();
			
			for(Cell cell : row) {
				String value = formatCelltoString(cell);
				rowData.add(value);
			}
			result.add(rowData);
		}
		System.out.println("excelData 읽기 성공! => 총 : " + result.size() +"개");
		return result;
	}
	
	public String formatCelltoString(Cell cell) {
		String result = "없음....";
		
		switch(cell.getCellType()) {
		case STRING:
			result = cell.getStringCellValue();
			break;
		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)) {
				result = cell.getDateCellValue().toString();
			}else {
				result = String.valueOf(cell.getNumericCellValue());
			}
			break;
		case BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA:
			result = cell.getCellFormula();
			break;
		default:
			break;
		}
		return result;
	}
	
}
