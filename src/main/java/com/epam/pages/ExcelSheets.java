package com.epam.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.epam.utils.Config;
import com.epam.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.epam.utils.FrameworkExceptions;
import com.epam.utils.TestDataFieldNames;

public class ExcelSheets {

    private static final  Logger LOGGER = LoggerFactory.getLogger(ExcelSheets.class);

    public static void excelDataValidation() throws EncryptedDocumentException, InvalidFormatException, IOException, FrameworkExceptions {
        FileInputStream excelFile1 = null;
        FileInputStream excelFile2 = null;
        try{

            // get input excel files
            excelFile1 = new FileInputStream(new File(Config.getConfig().getConfigProperty(Constants.INBOUNDPATH)));
            excelFile2 = new FileInputStream(new File(Config.getConfig().getConfigProperty(Constants.OUTBOUNDPATH)));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook1 = new XSSFWorkbook(excelFile1);
            XSSFWorkbook workbook2 = new XSSFWorkbook(excelFile2);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet1 = workbook1.getSheetAt(0);
            XSSFSheet sheet2 = workbook2.getSheetAt(0);

            // Compare sheets
            if(compareTwoSheets(sheet1, sheet2)) {
                LOGGER.info("\n\nThe two excel sheets are Equal");
            } else {
                LOGGER.error("\n\nThe two excel sheets are Not Equal");
            }
        } catch (Exception e) {
            LOGGER.error("Failed in excelDataValidation() method of ExcelSheets class " + e );
            throw new FrameworkExceptions("Failed in excelDataValidation() method of ExcelSheets class");
        }
        finally{
            excelFile1.close();
            excelFile2.close();
        }
    }
    // Compare Two Sheets
    public static boolean compareTwoSheets(XSSFSheet sheet1, XSSFSheet sheet2) throws InterruptedException {
        int firstRow1 = sheet1.getFirstRowNum();
        int lastRow1 = sheet1.getLastRowNum();
        boolean equalSheets = true;
        for(int i=firstRow1; i <= lastRow1; i++) {
            LOGGER.info("\n\nComparing Row "+i);
            XSSFRow rowSheet1 = sheet1.getRow(i);
            XSSFRow rowSheet2 = sheet2.getRow(i);
            if(!compareTwoRows(rowSheet1, rowSheet2)) {
                equalSheets = false;
                Thread.sleep(200);
                LOGGER.error("Row "+i+" - Not Equal");
                // break;
            } else {
                LOGGER.info("Row "+i+" - Equal");
            }
        }
        return equalSheets;
    }
    // Compare Two Rows
    public static boolean compareTwoRows(XSSFRow rowSheet1, XSSFRow rowSheet2) throws InterruptedException {
        if((rowSheet1 == null) && (rowSheet2 == null)) {
            return true;
        } else if((rowSheet1 == null) || (rowSheet2 == null)) {
            return false;
        }
        int firstCell1 = rowSheet1.getFirstCellNum();
        int lastCell1 = rowSheet1.getLastCellNum();
        boolean equalRows = true;
        // Compare all cells in a row
        for(int i=firstCell1; i < lastCell1; i++) {
            XSSFCell cell1 = rowSheet1.getCell(i);
            XSSFCell cell2 = rowSheet2.getCell(i);
            if(!compareTwoCells(cell1, cell2)) {
                equalRows = false;
                Thread.sleep(200);
                LOGGER.error("Cell "+i+" - NOt Equal");
            } else {
                Thread.sleep(200);
                LOGGER.info("Cell "+i+" - Equal");
            }
        }
        return equalRows;
    }

    // Compare Two Cells
    @SuppressWarnings("deprecation")
    public static boolean compareTwoCells(XSSFCell cellSheet1, XSSFCell cellSheet2) {
        if((cellSheet1 == null) && (cellSheet2 == null)) {
            return true;
        } else if((cellSheet1 == null) || (cellSheet2 == null)) {
            return false;
        }

        boolean equalCells = false;
        int type1 = cellSheet1.getCellType();
        int type2 = cellSheet2.getCellType();
        if (type1 == type2) {
            if (cellSheet1.getCellStyle().equals(cellSheet2.getCellStyle())) {
                // Compare cells based on its type
                switch (cellSheet1.getCellType()) {
                    case HSSFCell.CELL_TYPE_FORMULA:
                        if (cellSheet1.getCellFormula().equals(cellSheet2.getCellFormula())) {
                            equalCells = true;
                        }
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if (cellSheet1.getNumericCellValue() == cellSheet2.getNumericCellValue()) {
                            equalCells = true;
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        if (cellSheet1.getStringCellValue().equals(cellSheet2.getStringCellValue())) {
                            equalCells = true;
                        }
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        if (cellSheet1.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                            equalCells = true;
                        }
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        if (cellSheet1.getBooleanCellValue() == cellSheet2.getBooleanCellValue()) {
                            equalCells = true;
                        }
                        break;
                    case HSSFCell.CELL_TYPE_ERROR:
                        if (cellSheet1.getErrorCellValue() == cellSheet2.getErrorCellValue()) {
                            equalCells = true;
                        }
                        break;
                    default:
                        if (cellSheet1.getStringCellValue().equals(cellSheet2.getStringCellValue())) {
                            equalCells = true;
                        }
                        break;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return equalCells;
    }
}