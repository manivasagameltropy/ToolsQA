package com.qapitol.Util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static String path =  "C:\\Users\\Qapitol\\Desktop\\Book.xlsx";
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;

    public static Object [][] getData() throws IOException, IOException {
        System.out.println("Entered in to getData");
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheetAt(0);
        Object[][] data = null;

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount-1; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell =  row.getCell(j);
                if(cell != null){
                    switch (cell.getCellType()){
                        case STRING :
                            data[i][j] = cell.getStringCellValue();
                            System.out.println(data[i][j]);
                    }

                }
            }
        }
        return data;

    }

    public static List<String> readColumnData(String filePath, int headerRow, int colIndex) throws IOException {
        List<String> columnData = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        try (Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Reads the first sheet. Modify as needed.

            for (int i = headerRow + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(colIndex);
                    if (cell != null) {
                        columnData.add(cell.toString()); // Reads cell data as a string.
                    }
                }
            }
        }
        return columnData;
    }

    public static String readCellDataByHeader(String filePath, String headerName, int rowIndex) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        try (Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Reads the first sheet. Modify as needed.
            Row headerRow = sheet.getRow(0); // Assumes the first row is the header row.

            if (headerRow == null) {
                throw new IllegalArgumentException("Header row is missing in the sheet.");
            }

            // Find the column index for the given header name
            int colIndex = -1;
            for (Cell cell : headerRow) {
                if (headerName.equalsIgnoreCase(cell.getStringCellValue())) {
                    colIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (colIndex == -1) {
                throw new IllegalArgumentException("Header not found: " + headerName);
            }

            // Get the specific row (adding 1 to rowIndex to skip the header row)
            Row row = sheet.getRow(rowIndex + 1);

            if (row != null) {
                Cell cell = row.getCell(colIndex);
                if (cell != null) {
                    return cell.toString(); // Returns cell data as a string.
                }
            }
        }
        return null; // Returns null if the cell is empty or not found.
    }

    public static String readSingleCell(String filePath, int headerRow, int colIndex) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        try (Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Reads the first sheet. Modify as needed.

            // First non-header row will be the one right after the headerRow.
            Row dataRow = sheet.getRow(headerRow + 1);
            if (dataRow != null) {
                Cell cell = dataRow.getCell(colIndex);
                if (cell != null) {
                    return cell.toString(); // Convert cell content to String.
                }
            }
        }
        return null; // Return null if no data is found in the specified cell.
    }

    public static Object[][] getHardData(){
        Object[][] data = {
                {"asnvbsd","dvwd"},
                {"dvwfe","dvefe"},
                {"asnvbsd","dvwd"},
                {"dvwfe","dvefe"}
        };
        return data;
    }
}
