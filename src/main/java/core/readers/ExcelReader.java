package core.readers;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class ExcelReader {

    private static String EXCEL_FILE_PATH = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "data.xlsx";
//workbook is the .xlsx
    private static XSSFWorkbook workbook = null;

    public static void initWorkBook() {
        try {
            workbook = new XSSFWorkbook(new File(EXCEL_FILE_PATH));
        }
        catch(IOException io) {

        }
        catch(InvalidFormatException inv) {

        }

    }

//    public static void getDataFromSheetName(String sheetName) {
public static Object[][] getDataFromSheetName(String sheetName) {
    XSSFSheet sheet = workbook.getSheet(sheetName);

    // Find out the last row first
    int lastRowNumber = sheet.getLastRowNum();
    System.out.println("Last row number: " + lastRowNumber);

    Object[][] obj = new Object[lastRowNumber+1][sheet.getRow(lastRowNumber).getLastCellNum()];

    // create for loop to iterate row by row
    for (int r = 0; r <=lastRowNumber; r++) {

        // Capture a row and break it into cells
        Row row = sheet.getRow(r);
        // Find out last cell from the row
        int lastCellNumber = row.getLastCellNum();

        // Iterate cell to get the data
        for (int c = 0; c < lastCellNumber; c++) {
            // get the cell by index
            Cell cell = row.getCell(c);

            if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
                // print the cell data
                System.out.print(r + "," + c + " : ");
                System.out.print(cell.getStringCellValue());
                System.out.print(", ");
                obj[r][c] = cell.getStringCellValue();
            }
            else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                // print the cell data
                System.out.print(r + "," + c + " : ");
                System.out.print(String.valueOf(cell.getNumericCellValue()));
                System.out.print(", ");
                obj[r][c] = String.valueOf(cell.getNumericCellValue());
            }
            else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                // print the cell data
                System.out.print(r + "," + c + " : ");
                System.out.print(String.valueOf(cell.getBooleanCellValue()));
                System.out.print(", ");
                obj[r][c] = String.valueOf(cell.getBooleanCellValue());
            }
        }
        System.out.println();
    }
    return obj;
}

    public static void main(String[] args) {
        ExcelReader.initWorkBook();
//        ExcelReader.getDataFromSheetName("classes");
        ExcelReader.getDataFromSheetName("test2");
    }

}