package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {

    public FileInputStream fileInputStream;
    public FileOutputStream fileOutputStream;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public XLUtility(String path){
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException{
       fileInputStream = new FileInputStream(path);
       workbook = new XSSFWorkbook(fileInputStream);
       sheet = workbook.getSheet(sheetName);
       int rowCount = sheet.getLastRowNum();
       workbook.close();
       fileInputStream.close();
       return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException
    {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
         row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException{

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter dataFormatter = new DataFormatter();
        String data;
        try{
            data = dataFormatter.formatCellValue(cell);//retursns the formated value of the string
        }catch (Exception e){
            data="";
        }
        workbook.close();
        fileInputStream.close();
        return  data;
    }

    public  void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {

        File xlFile = new File(path);
        if (!xlFile.exists()) { // if file not exist hten cretae new file
            workbook = new XSSFWorkbook();
            fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
        }

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);

        if (workbook.getSheetIndex(sheetName) == -1)
            workbook.createSheet();
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rowNum) == null) // if row not ecist then  create row
            sheet.createRow(rowNum);
        row = sheet.getRow(rowNum);

        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();

    }

}
